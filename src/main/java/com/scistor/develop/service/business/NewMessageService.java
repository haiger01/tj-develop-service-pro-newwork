/*
package com.scistor.develop.service.business;

import com.alibaba.fastjson.JSON;
import com.scistor.develop.dao.mapper.business.*;
import com.scistor.develop.data.entity.FinancialGoods;
import com.scistor.develop.data.entity.LoanApplication;
import com.scistor.develop.data.entity.NewMessage;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.service.ParentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_COMPANY_ID;
import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_COMPANY_NAME;
import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_COMPANY_TYPE;
import static com.scistor.develop.tools.NewMessageBlockAttribute.*;
import static com.scistor.develop.util.ObjectUtil.ckStrIsNotnull;
import static com.scistor.develop.util.ObjectUtil.delMapNotKey;
import static com.scistor.develop.util.ServletUtil.getSession;
import static com.scistor.develop.util.SqlUtil.map2where;

@Service
public class NewMessageService extends ParentService {
    @Autowired
    NewMessageMapper newMessageMapper;
    @Autowired
    GuaranteeMapper guaranteeMapper;
    @Autowired
    FinancialGoodsMapper financialGoodsMapper;
    @Autowired
    LoanDemandMapper loanDemandMapper;
    @Autowired
    LoanApplicationMapper loanApplicationMapper;
    @Autowired
    MicrofinanceMapper microfinanceMapper;
    @Autowired
    FinancingLeaseMapper financingLeaseMapper;

    @Autowired
    InsuranceMapper insuranceMapper;
    static final String theTableName = "new_message";

    */
/**
     * @Description //消息列表
     * @Author 岳浩
     * @Date 2020/2/27 22:32
     * @Parameter --
     **//*

    public Map<String, Object> listByConditions(NewMessage newMessage, int start, int end) {

        Map<String, Object> dataMap = new HashMap<>();
        newMessage.setToCompanyId(getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "");
        if ("4".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+""))
            newMessage.setToCompanyId("-1");

        //需要做等于条件的key
        String[] keys = ("messageType,type,companyId,toCompanyId,message,deleteFlag").split(",");
        //调用map生成where的语句
        String whereSql = map2where(newMessage, keys);
        dataMap.put("list", newMessageMapper.listByConditions(whereSql, start, end, newMessage.getOrderCloumn(), newMessage.getOrderDesc()));
        dataMap.put("count", newMessageMapper.countByConditions(whereSql));

        return dataMap;
    }

    public Long countByConditions(NewMessage newMessage) {

        //系统消息查询不能使用session
        if (!newMessage.getMessageType().equals("3")) {
            newMessage.setToCompanyId(getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "");
            if (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4"))
                newMessage.setToCompanyId("-1");
        }

        //需要做等于条件的key
        String[] keys = ("messageType,type,companyId,toCompanyId,message").split(",");
        //调用map生成where的语句
        String whereSql = map2where(newMessage, keys);

        return newMessageMapper.countByConditions(whereSql);


    }


    */
/**
     * @Description //消息推送
     * @Author 岳浩
     * @Date 2020/2/27 22:32
     * @Parameter --
     **//*

    public int addNewMessage(NewMessage newMessage) {

        return parentInsert(theTableName, newMessage);

    }


    */
/**
     * @Description //消息推送具体版
     * @Author 岳浩
     * @Date 2020/4/1 0001 17:51
     * @Parameter --
     **//*

    public int addNewMessgae(String messageType, String companyId, String strType, String bankName, String goodsId, String guaraId, String appId,String micId,String finId) {
        try {
            //生成提示语
            NewMessage newMessage = new NewMessage();
            newMessage.setType("0");
            newMessage.setMessage(getNewMessageStr(strType, bankName, goodsId, guaraId, appId,micId,finId));
            newMessage.setMessageType(messageType);//发送消息的等级
            newMessage.setToCompanyId(companyId);//接收企业ID
           // System.out.println("发送消息》" + JSON.toJSONString(newMessage));
            return parentInsert(theTableName, newMessage);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    */
/**
     * @Description //消息推送具体版
     * @Author 张军----改版--股权使用这个
     * @Date 2020/4/1 0001 17:51
     * @Parameter --
     **//*

    public int addNewMessgae(String messageType ,String companyId,String msgId,String proId,String proName) {
        try {
            //生成提示语
            NewMessage newMessage = new NewMessage();
            newMessage.setType("0");
            //CERT_STR_COMAPANY_NAME,CERT_STR_COMAPANY_BANK," +
            //            "CERT_STR_GOODS_NAME,CERT_STR_NUMBER
            String str = NEWMESSAGE_MAP.get(msgId);
            str = str.replaceAll(NEWMESSAGE_LIST[0], getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"");
            str = str.replaceAll(NEWMESSAGE_LIST[1], getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"");
            str = str.replaceAll(NEWMESSAGE_LIST[2], proName);
            str = str.replaceAll(NEWMESSAGE_LIST[3], proId);
            newMessage.setMessage(str);
            newMessage.setMessageType(messageType);//发送消息的等级
            newMessage.setToCompanyId(companyId);//接收企业ID
            //System.out.println("发送消息》" + JSON.toJSONString(newMessage));
            return parentInsert(theTableName, newMessage);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



    */
/**
     * @Description //消息推送具体版
     * @Author 张军----改版--股权使用这个
     * @Date 2020/4/1 0001 17:51
     * @Parameter --
     **//*

    public int addNewMessgae222(String messageType ,String toCompanyId,String msgId,String proId,String proName) {
        try {
            //生成提示语
            NewMessage newMessage = new NewMessage();
            newMessage.setType("0");
            //CERT_STR_COMAPANY_NAME,CERT_STR_COMAPANY_BANK," +
            //            "CERT_STR_GOODS_NAME,CERT_STR_NUMBER
            String str = NEWMESSAGE_MAP.get(msgId);
            str = str.replaceAll(NEWMESSAGE_LIST[0], getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"");
            str = str.replaceAll(NEWMESSAGE_LIST[1], getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"");
            str = str.replaceAll(NEWMESSAGE_LIST[2], proName);
            str = str.replaceAll(NEWMESSAGE_LIST[3], proId);
            newMessage.setMessage(str);
            newMessage.setMessageType(messageType);//发送消息的等级
            newMessage.setToCompanyId(toCompanyId);//接收企业ID
            //System.out.println("发送消息》" + JSON.toJSONString(newMessage));
            return parentInsert(theTableName, newMessage);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }




    */
/**
     * 张军
     * @param msgId                 消息ID（消息模板id）
     * @param messageType           消息级别(给金融局的就是4，给金融机构的就是2，个人感觉没什么实际意义)
     * @param toCompanyId           推送消息对象的ID(即接受信息的是谁，金融局为-1，其他的为companyId)
     * @param companyType           发布产品企业类型(产品属于那个金融机构的，即金融机构的类型)
     * @param bankName              金融企业名称(受理企业融资需求的时候是金融机构名称)
     * @param productId             产品ID(产品ID)
     * @return
     *//*

    public int addNewMessgae2(String msgId,String messageType, String toCompanyId, String companyType, String bankName, String productId) {
        try {

            System.out.println("zj----发送消息》" + getNewMessageStr2(msgId,companyType, bankName, productId));
            //生成提示语
            */
/*NewMessage newMessage = new NewMessage();
            newMessage.setType("0");
            newMessage.setMessage(getNewMessageStr2(msgId,companyType, bankName, productId));
            newMessage.setMessageType(messageType);//发送消息的等级
            newMessage.setToCompanyId(toCompanyId);//接收企业ID
            System.out.println("发送消息》" + JSON.toJSONString(newMessage));
            return parentInsert(theTableName, newMessage);*//*

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    */
/**
     * @Description //消息已读
     * @Author 岳浩
     * @Date 2020/2/27 22:32
     * @Parameter --
     **//*

    public int updateType(NewMessage newMessage) {

        //删除map内多余的key只留需要的操作企业信息
        String[] keys = ("type,deleteFlag,id").split(",");
        Map map = delMapNotKey(newMessage, keys);
        String sql = "";
        if("2".equals(newMessage.getDeleteFlag()+"") || "2".equals(newMessage.getType())){
            sql = " where to_company_id = '"
                    + ("4".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+"")
                    ? -1 : getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID)) + "'";
            if("2".equals(newMessage.getDeleteFlag()+"")) {
                map.put("delete_flag","1");
                map.remove("type");
                sql += " and type = 1 ";
            }
            if("2".equals(newMessage.getType())){
                map.put("type","1");
                map.remove("delete_flag");
                sql += " and delete_flag = 0 ";
            }
        }
        else{
            if("0".equals(newMessage.getDeleteFlag()+""))
                map.put("type","1");
            sql = " where id=" + newMessage.getId();
        }

        return parentUpdate(theTableName, map, sql);
    }

    */
/**
     * @Description //消息已读
     * @Author 岳浩
     * @Date 2020/2/27 22:32
     * @Parameter --
     **//*

    public NewMessage getNewMessageById(NewMessage newMessage) {
        //权限，只能查看自己相关的数据
        newMessage = newMessageMapper.getNewMessageById(newMessage.getId());
        //如果是当前自己的信息--金融局可以查看-1的--张军--20200424
        if (newMessage.getToCompanyId().equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID))
                || ("4".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)) && "-1".equals(newMessage.getToCompanyId())))
            return newMessage;
        else
            throw createError(ExceptionCode.code301);
    }

    */
/**
     * @Description // 获取相应消息体
     * @Author 岳浩
     * @Date 2020/3/31 0031 13:09
     * @Parameter --
     * 1、企业申请金融产品:                  goodsId  guaraId
     * 2、银行添加意向:                      bankName
     * 3、实名认证审核，提醒金融办审核
     * 4、金融办审核之后 ,企业
     * 5、银行向金融办发起银行产品审核
     * 6、担保机构向金融办发起担保产品审核
     * 7、金融办对银行产品审核t7.1f7.2        goodsId
     * 8、金融办对担保产品审核t8.1f8.2        guaraId
     * 9、贷款申请                           appId
     * 10、担保申请                          appId
     * }
     **//*

    public String getNewMessageStr(String strType, String bankName, String goodsId, String guaraId, String appId,String micId,String finId) {

        HttpSession session = getSession();
        //companyName：应该为该appId企业融资需求的对应企业名称--不能从session中获取
        //String companyName = session.getAttribute(SESSION_KEYNAME_COMPANY_NAME) + "";
        String companyName = "";
        if (ckStrIsNotnull(appId) && !"-1".equals(appId)) {
            try {
                LoanApplication loanApplication = loanApplicationMapper.getLoanApplicationById(Long.valueOf(appId));
                if(loanApplication!= null)companyName = loanApplication.getCompanyName();
                //companyName = loanApplicationMapper.getLoanApplicationById(Long.valueOf(appId)).getCompanyName();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    LoanApplication loanApplication = loanApplicationMapper.getLoanApplicationById(Long.valueOf(appId));
                    if(loanApplication!= null)companyName = loanApplication.getCompanyName();
                    //companyName = loanDemandMapper.getLoanDemandById(Long.valueOf(appId)).getCompanyName();
                } catch (Exception e2) {
                    e2.printStackTrace();

                }
            }
        } else
            companyName = session.getAttribute(SESSION_KEYNAME_COMPANY_NAME) + "";

        String goodsName = "";
        //1、企业申请金融产品   "1": "CERT_STR_COMAPANY_NAME 向 CERT_STR_GOODS_NAME 发起申请，请注意查看！",
        if (strType.equals("1") && StringUtils.isNotEmpty(goodsId)) {
            try {
                if (guaraId.equals("-1") && micId.equals("-1") && finId.equals("-1")) //金融产品
                    goodsName = financialGoodsMapper.getFinancialGoodsById(Long.valueOf(goodsId)).getProName();
                else if(goodsId.equals("-1") && micId.equals("-1") && finId.equals("-1")) //担保产品
                    goodsName = guaranteeMapper.getGuaranteeById(Long.valueOf(guaraId)).getProName();
                else if (goodsId.equals("-1") && guaraId.equals("-1")  && finId.equals("-1")) //小贷产品
                    goodsName = microfinanceMapper.getMicrofinanceById(Long.valueOf(micId)).getProName();
                else
                    goodsName = financingLeaseMapper.getFinancingLeaseById(Long.valueOf(micId)).getProName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            companyName = getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"";
        }

        // 2、银行添加意向     "2": "CERT_STR_COMAPANY_BANK 对您发布的需求添加意向，请注意查看！",
        // 3、实名认证审核，提醒金融办审核     "3": "CERT_STR_COMAPANY_NAME 提交实名认证审核，请注意查看！",
        // 4、金融办审核之后 ,企业    "4.1": "您提交的实名认证审核已通过，请注意查看！","4.2": "您提交的实名认证审核未通过，请注意查看！",
        // 5、银行向金融办发起银行产品审核     "5": "CERT_STR_COMAPANY_NAME提交银行产品审核，请注意查看！",
        // 6、担保机构向金融办发起担保产品审核   "6": "CERT_STR_COMAPANY_NAME提交担保产品审核，请注意查看！",
        // 7、金融办对银行产品审核通过   "7.1": "恭喜，您提交银行产品CERT_STR_GOODS_NAME通过审核，请注意查看！","7.2": "您提交银行产品CERT_STR_GOODS_NAME未通过审核，请注意查看！",
        try {
            if (strType.equals("7.1") || strType.equals("7.2"))
                goodsName = financialGoodsMapper.getFinancialGoodsById(Long.valueOf(goodsId)).getProName();
            // 8、金融办对担保产品审核通过   "8.1": "恭喜，您提交担保产品CERT_STR_GOODS_NAME通过审核，请注意查看！","8.2": "您提交担保产品CERT_STR_GOODS_NAME未通过审核，请注意查看！",
            if (strType.equals("8.1") || strType.equals("8.2"))
                goodsName = guaranteeMapper.getGuaranteeById(Long.valueOf(guaraId)).getProName();
            //小贷产品7.3,7.4是小贷的产品
            if (strType.equals("7.3") || strType.equals("7.4"))
                goodsName = microfinanceMapper.getMicrofinanceById(Long.valueOf(micId)).getProName();
            if (strType.equals("7.5") || strType.equals("7.6"))
                goodsName = financingLeaseMapper.getFinancingLeaseById(Long.valueOf(finId)).getProName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 9、贷款申请    "9.2": "恭喜，您的贷款申请编号CERT_STR_NUMBER通过受理","9.1": "抱歉，您的贷款申请编号CERT_STR_NUMBER未通过受理",
        // 10、担保申请   "10.1": "抱歉，您的担保申请编号CERT_STR_NUMBER未通过受理","10.2": "恭喜，您的担保申请编号CERT_STR_NUMBER通过受理"
        // 11、您发布的融资需求（需求编号为：CERT_STR_NUMBER）被（CERT_STR_COMAPANY_BANK）受理，请注意查看！


        return blockGetNewMessageStr(strType, companyName, bankName, ckStrIsNotnull(goodsName) ? "金融产品(" + goodsName + ")" : bankName, appId);
    }


    */
/**
     *
     * @param companyType       产品用户类别
     * @param bankName
     * @param productId         产品ID
     * @param msgId             消息ID
     * @return
     *//*

    public String getNewMessageStr2(String msgId,String companyType, String bankName, String productId) {

        String companyName = "";
        String goodsName = "";
        //1、企业申请金融产品   "1": "CERT_STR_COMAPANY_NAME 向 CERT_STR_GOODS_NAME 发起申请，请注意查看！",
        if (StringUtils.isNotEmpty(productId)) {
            try {
                if ("3".equals(companyType)) //银行金融产品
                    goodsName = financialGoodsMapper.getFinancialGoodsById(Long.valueOf(productId)).getProName();
                else if("5".equals(companyType)) //担保产品
                    goodsName = guaranteeMapper.getGuaranteeById(Long.valueOf(productId)).getProName();
                else if ("6".equals(companyType)) //小贷产品
                    goodsName = microfinanceMapper.getMicrofinanceById(Long.valueOf(productId)).getProName();
                else if ("7".equals(companyType))  //租赁
                    goodsName = financingLeaseMapper.getFinancingLeaseById(Long.valueOf(productId)).getProName();
                else                                //保险
                    goodsName = insuranceMapper.getInsuranceById(Long.valueOf(productId)).getProName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else return null;

        if(ckStrIsNotnull(companyName))companyName = getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"";
        return blockGetNewMessageStr2(msgId, companyName, bankName, ckStrIsNotnull(goodsName) ? "金融产品(" + goodsName + ")" : bankName);
    }
}
*/
