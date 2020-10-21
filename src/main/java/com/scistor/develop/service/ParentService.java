package com.scistor.develop.service;

import com.scistor.develop.dao.mapper.BaseDao;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.error.IidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.*;
import static com.scistor.develop.util.DateTool.dateTimeSec2Str;
import static com.scistor.develop.util.ObjectUtil.hump2Line;
import static com.scistor.develop.util.ObjectUtil.obj2Map;
import static com.scistor.develop.util.ServletUtil.getSession;

@Service
public class ParentService {

    @Autowired
    public BaseDao baseDao;

    public Long longBySql(String sql) {
        return baseDao.longBySql(sql);
    }

    public List<Map<String, Object>> parentMapBySql(String sql) {
        return baseDao.mapBySql(sql);
    }


    /**
     * @Author 岳浩
     * @Description //可兼容map类型以及对象进行储存
     * @Date 2020/2/21 0:35
     **/
    public int parentInsert(String table, Object object) {

        Map<String, Object> map = obj2Map(object);
        //去掉公共字段
        try {
            map.remove("orderCloumn");
            map.remove("orderDesc");
        } catch (Exception e) {
        }
        //推送系统消息不能使用session--张军--20200422
        if(!"3".equals(map.get("messageType"))){
            HttpSession session = getSession();
            if (map.get("usercode") == null)
                map.put("usercode", session.getAttribute(SESSION_KEYNAME_USER_CODE) + "");
            if (map.get("companyType") == null)
                map.put("companyType", session.getAttribute(SESSION_KEYNAME_COMPANY_TYPE) + "");
            if (map.get("createrUser") == null)
                map.put("createrUser", session.getAttribute(SESSION_KEYNAME_USER_NAME) + "");
            if (map.get("companyName") == null && session.getAttribute(SESSION_KEYNAME_COMPANY_NAME) != null)
                map.put("companyName", session.getAttribute(SESSION_KEYNAME_COMPANY_NAME) + "");
            if (map.get("companyId") == null && session.getAttribute(SESSION_KEYNAME_COMPANY_ID) != null)
                map.put("companyId", session.getAttribute(SESSION_KEYNAME_COMPANY_ID) + "");
        }

        if (map.get("createrTime") == null)
            map.put("createrTime", new Date());

        if(map.get("updateTime") == null)
            map.put("updateTime", map.get("createrTime"));
        map.put("deleteFlag", 0);
        StringBuilder keys = new StringBuilder();
        List valList = new ArrayList();
        for (String key : map.keySet()) {
            if (key.indexOf("YH_LSNOT_STR") > 0) continue;

            if (map.get(key) == null) continue;
            keys.append(hump2Line(key) + ",");
            valList.add(map.get(key));
        }
        keys.setLength(keys.length() - 1);
        return baseDao.insert(table, keys.toString(), valList);
    }


    /**
     * @Author 张军
     * @Description //直接保存查询到的对象
     * @Date 2020/2/21 0:35
     **/
    public int parentRecordInsert(String table, Object object) {

        Map<String, Object> map = obj2Map(object);
        //去掉公共字段
        try {
            map.remove("orderCloumn");
            map.remove("orderDesc");
        } catch (Exception e) {
        }
        StringBuilder keys = new StringBuilder();
        List valList = new ArrayList();
        for (String key : map.keySet()) {
            if (key.indexOf("YH_LSNOT_STR") > 0) continue;

            if (map.get(key) == null) continue;
            keys.append(hump2Line(key) + ",");
            valList.add(map.get(key));
        }
        keys.setLength(keys.length() - 1);
        return baseDao.insert(table, keys.toString(), valList);
    }


    /**
     * @Author 张军
     * @Description //直接保存查询到的对象
     * @Date 2020/2/21 0:35
     **/
    public int parentRecordInsert2(String table, Object object) {

        Map<String, Object> map = obj2Map(object);
        //去掉公共字段
        try {
            map.remove("orderCloumn");
            map.remove("orderDesc");
        } catch (Exception e) {
        }
        if (map.get("createrTime") == null)
            map.put("createrTime", new Date());

        map.put("updateTime", map.get("createrTime"));
        map.put("deleteFlag", 0);
        StringBuilder keys = new StringBuilder();
        List valList = new ArrayList();
        for (String key : map.keySet()) {
            if (key.indexOf("YH_LSNOT_STR") > 0) continue;

            if (map.get(key) == null) continue;
            keys.append(hump2Line(key) + ",");
            valList.add(map.get(key));
        }
        keys.setLength(keys.length() - 1);
        return baseDao.insert(table, keys.toString(), valList);
    }



    /**
     * @Author 岳浩
     * @Description // 可兼容map类型以及对象进行修改
     * @Date 2020/2/21 0:36
     **/
    public int parentUpdate(String table, Object object, String where) {
        Map<String, Object> map = obj2Map(object);
        //去掉公共字段
        try {
            map.remove("orderCloumn");
            map.remove("orderDesc");
        } catch (Exception e) {
        }

        if(map.get("updateTime") == null)
            map.put("updateTime", dateTimeSec2Str(new Date()));
        StringBuilder upList = new StringBuilder();
        for (String key : map.keySet()) {
            if (key.indexOf("YH_LSNOT_STR") > 0) continue;
            if (map.get(key) == null) continue;
            Object data = map.get(key);
            if (data.getClass().getSimpleName().equals("Date")) {
                data = dateTimeSec2Str((Date) data);
            }
            upList.append(" " + hump2Line(key) + "='" + data + "',");
        }
        upList.setLength(upList.length() - 1);
        return baseDao.update(table, upList.toString(), where);
    }


    /**
     * @Author 岳浩
     * @Description // 可兼容map类型以及对象进行修改
     * @Date 2020/2/21 0:36
     **/
    public int parentUpdateSql(String table, String upSql, String where) {

        return baseDao.update(table, upSql, where);
    }


    public static void main(String[] args) {
        Object obj = new Date();
        System.out.println(obj.getClass().getSimpleName());
    }

    /**
     * @Author 岳浩
     * @Description // 可兼容map类型以及对象进行删除
     * @Date 2020/2/21 0:36
     **/
    public int parentDelete(String table, Long id) {

        return baseDao.deleteById(table, id);
    }

    public int parentAddCloum(String table, String cloum, Long id) {

        return baseDao.addCloumById(table, cloum, id);
    }

    public int parentAddCloum(String table, String cloum, Long num, Long id) {

        return baseDao.addCloumANumById(table, cloum, id);
    }

    public int parentAddCloumByUsercode(String table, String cloum, String usercode) {

        return baseDao.addCloumByUsercode(table, cloum, usercode);
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

    public void rtMap(ExceptionCode exceptionCode, Map<String, Object> data) {
        data.put(CODE_NAME, exceptionCode.getCode());
        data.put(MSG_NAME, exceptionCode.getMsg());
    }


}
