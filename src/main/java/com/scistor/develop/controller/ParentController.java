package com.scistor.develop.controller;

/**
 * @author 岳浩
 * @所有控制层的父类：公共方法可放在此类中
 */

import com.scistor.develop.data.entity.FocusNeeds;
import com.scistor.develop.data.entity.LoanDemand;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.error.IidaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.error.ExceptionCode.code302;
import static com.scistor.develop.tools.BlockAttribute.*;
import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_SPLIT_STR;
import static com.scistor.develop.util.ObjectUtil.obj2Map;
import static com.scistor.develop.util.ServletUtil.getSession;

public class ParentController {
    public Logger logger = LoggerFactory.getLogger(ParentController.class);

    public ParentController() {
    }


    /**
     * 创建返回单条数据的数据格式
     */
    protected Map createCommonPack(ExceptionCode exceptionCode, Object o) throws Exception {
        Map map = new HashMap();
        rtMap(exceptionCode, map);
        map.put(DATA_NAME, o);
        return map;
    }

    public void rtMap(ExceptionCode exceptionCode, Map<String, Object> data) {
        data.put(CODE_NAME, exceptionCode.getCode());
        data.put(MSG_NAME, exceptionCode.getMsg());
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 创建异常格式
     */
    protected IidaException createError(ExceptionCode exceptionCode, String msg) {
        return new IidaException(exceptionCode.getCode(), msg);
    }

    /**
     * 创建异常格式
     */
    protected IidaException createError(ExceptionCode exceptionCode) {
        return new IidaException(exceptionCode.getCode(), exceptionCode.getMsg());
    }
//    public List<Map<String, Object>> listMapDecrypt(Map<String, Object> map) throws Exception {
//        try {
//            logger.info("收到请求>>>>>" + JSON.toJSONString(map));
//            //<解密1>根据appkey获取私钥
//            String privateKey = investAppSecretService.getAppPriKey((String) map.get("appkey"));
//            if (privateKey == null) throw createError(ExceptionCode.code5003);
//            //<解密2>根据secret和privateKey获取加密前的随机数
//            String secret = RSAUtil.decode(privateKey, strReplace((String) map.get("signature")));
//            //<解密3>根据解密后的随机数进行decrypt，获得正确的data
//            List<Map<String, Object>> listMap = JSON.parseObject(AESUtil.decode(secret, strReplace((String) map.get("data"))), List.class);
//            //data内的值进行封装
//            for (Map<String, Object> m : listMap) {
//                m.put("appkey", map.get("appkey"));
//                m.put("uuid", map.get("uuid"));
//                m.put("timestamp", map.get("timestamp"));
//            }
//            logger.info("收到请求>>>>listMap>> >>>>" + JSON.toJSONString(listMap));
//            return listMap;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw createError(ExceptionCode.code300);
//        }
//    }

    //此处的私有方法
    private String strReplace(String secret) {
        return secret.replaceAll("#", "+");
    }

    //静态方法剥离，删除意向、增加意向企业
    public static FocusNeeds getFocusNeeds2Del(Object demandId, String focusCompanyId, int deleteFlag) {
        FocusNeeds focusNeeds = new FocusNeeds();
        focusNeeds.setDemandId(demandId + "");
        focusNeeds.setFocusCompanyId(focusCompanyId);
        //有这个参数则是删除

        focusNeeds.setDeleteFlag(deleteFlag);
        return focusNeeds;
    }

    //静态方法剥离，删除需求，恢复需求
    public static LoanDemand getLoanDemand2Del(Object demandId, int deleteFlag) {
        LoanDemand loanDemand = new LoanDemand();
        loanDemand.setId(Long.valueOf(demandId + ""));
        loanDemand.setDeleteFlag(deleteFlag);
        return loanDemand;
    }

    /**
     * @Description //参数为基偶比较，基数为sessionkey,偶数为值，通过sessionKey进行对比，任何一组不通过就返回false
     * @Author 岳浩
     * @Date 2020/4/15 0:45
     **/
    public boolean ckCurrent(String... keys) throws IidaException {
        for (int i = 0; i < keys.length; i += 2) {
            if (!keys[i + 1].equals(getSession().getAttribute(keys[i]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description //参数为基偶比较，基数为key,偶数为值，通过Map进行对比，任何一组不通过就返回false
     * @Author 岳浩
     * @Date 2020/4/15 0:45
     **/
    public boolean ckObj(Object obj, String... keys) throws IidaException {
        Map map = obj2Map(obj);

        for (int i = 0; i < keys.length; i += 2) {
            if (!keys[i + 1].equals(map.get(keys[i]) + "")) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description //参数为基偶比较，基数为key,偶数为值，通过Map进行对比，任何一组不通过就返回false
     * @Author 岳浩
     * @Date 2020/4/15 0:45
     **/
    public void tryObj(Object obj, String... keys) throws IidaException {
        if (!ckObj(obj, keys)) {
            logger.error("\n\r -usernName:" + getSionStr(SESSION_KEYNAME_USER_NAME) +
                    "\n\r -userncode:" + getSionStr(SESSION_KEYNAME_USER_CODE) + "\n\r -试图越权>");
            throw createError(code302);
        }
    }

    /**
     * @Description //参数为基偶比较，基数为sessionkey,偶数为值，通过sessionKey进行对比，任何一组不通过就返回false
     * @Author 岳浩
     * @Date 2020/4/15 0:45
     **/
    public void tryCurrent(String... keys) throws IidaException {
        if (!ckCurrent(keys)) {
            logger.error("\n\r -usernName:" + getSionStr(SESSION_KEYNAME_USER_NAME) +
                    "\n\r -userncode:" + getSionStr(SESSION_KEYNAME_USER_CODE) + "\n\r -试图越权>");
            throw createError(code302);
        }
    }


    /**
     * @Description //参数为基偶比较，基数为sessionkey,偶数为值，通过sessionKey进行对比，任何一组不通过就返回false
     * @Author 岳浩
     * @Date 2020/4/15 0:45
     **/
    public void tryCurrentUnauthorized(String name,String... keys) throws IidaException {
        if (!ckCurrentUnauthorized(name,keys)) {
            logger.error("\n\r -usernName:" + getSionStr(SESSION_KEYNAME_USER_NAME) +
                    "\n\r -userncode:" + getSionStr(SESSION_KEYNAME_USER_CODE) + "\n\r -试图越权>");
            throw createError(code302);
        }
    }

    /**
     * @Description //参数为基偶比较，基数为sessionkey,偶数为值，通过sessionKey进行对比，任何一组不通过就返回false
     * @Author 张军
     * 判断是否在指定权限里面
     * @Date 2020/10/14
     **/
    public boolean ckCurrentUnauthorized(String name,String... keys) throws IidaException {
        boolean flag = false;
        String value = getSession().getAttribute(name)+"";
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(value)) {
                flag = true;
            }
        }
        return flag;
    }

    public String getSionStr(String key) {
        return getSession().getAttribute(key) + "";
    }

    public static void mapMergaRecords(Map map) {
        if (map.get("records") != null) {
            Map m = new HashMap();
            String records = map.get("records") + "";
            for (String s : records.toString().split(",")) {
                m.put(s.split(SQL_CK_SPLIT_STR)[0], s.split(SQL_CK_SPLIT_STR)[1]);
            }
            map.put("records", m);
        }
    }

    public static void main(String[] args) throws Exception{
        ParentController parentController = new ParentController();
        parentController.tryCurrent(SESSION_KEYNAME_COMPANY_TYPE, "4", SESSION_KEYNAME_COMPANY_TYPE, "9");
    }
}
