/*
package com.scistor.develop.service.business;

import com.scistor.develop.dao.mapper.business.MicrofinanceMapper;
import com.scistor.develop.data.entity.FinancialGoods;
import com.scistor.develop.data.entity.Microfinance;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.error.IidaException;
import com.scistor.develop.service.ParentService;
import com.scistor.develop.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_COMPANY_ID;
import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_COMPANY_TYPE;
import static com.scistor.develop.tools.BlockAttribute.SESSION_KEYNAME_USER_TEL;
import static com.scistor.develop.util.DateTool.dateTimeSec2Str;
import static com.scistor.develop.util.ObjectUtil.ckStrIsNotnull;
import static com.scistor.develop.util.ObjectUtil.delMapNotKey;
import static com.scistor.develop.util.ObjectUtil.mapKeyLine2Hump;
import static com.scistor.develop.util.ServletUtil.getSession;
import static com.scistor.develop.util.SqlUtil.*;
import static com.scistor.develop.util.SqlUtil.getBetWeenAndSql;

@Service
public class MicrofinanceService extends ParentService {

    @Autowired
    NewMessageService newMessageService;
    @Autowired
    MicrofinanceMapper microfinanceMapper;

    static final String theTableName = "microfinance";


    public Microfinance addMicrofinance(Microfinance microfinance) throws IidaException {
        microfinance.setType("1");
        microfinance.setCheckType("0");
        microfinance.setSuccess(0L);
        microfinance.setFail(0L);
        microfinance.setApplnum(0L);
        microfinance.setTel(getSession().getAttribute(SESSION_KEYNAME_USER_TEL) + "");
        if (parentInsert(theTableName, microfinance) == 0) {
            throw createError(ExceptionCode.code301);
        }
        newMessageService.addNewMessgae("4", "-1", "13", "-1", "-1", "-1", "-1",microfinance.getId()+"","-1");
        //newMessageService.addNewMessgae2("4","4","-1","6","-1",microfinance.getId()+"");
        return microfinance;
    }


    */
/**
     * @Description //修改小额贷款
     * @Author 张军
     * @Date 2020/5/13
     * @Parameter --
     **//*

    public int updateMicrofinance(Microfinance microfinance) throws IidaException {
        Map map = new HashMap();
        map = delMapNotKey(microfinance,
                "tel", "loanChanne", "guaranteeMode", "proName", "goodsType", "characteristic",
                "appCustomer", "appCondition", "checkType", "type", "createrUser", "fixedTel", "loanTimeLimitMax",
                "loanTimeLimitMin", "loanAmountMax", "loanAmountMin", "loanRateMax", "loanRateMin", "loanRate",
                "videoUrl","videoCoverUrl","proContactPerson","proContactPersonPhone",
                "loanRateStr", "loanAmountStr", "loanTimeLimitStr", "proInt", "addressCustomerNeeds","modeOfRepayment");
        if (parentUpdate(theTableName, map, "where id=" + microfinance.getId()) == 0) {
            throw createError(ExceptionCode.code301);
        }
        return 1;
    }

    */
/**
     * @Description //下线金融产品
     * @Author 岳浩
     * @Date 2020/2/27 21:32
     * @Parameter --
     * type：当前状态
     **//*

    public int upMicrofinance(Microfinance microfinance) throws IidaException {
        Map map = new HashMap();

        //如果checkType不为空，则是要修改审核状态

        if (ckStrIsNotnull(microfinance.getCheckType())) {
            microfinance.setCheckTime(dateTimeSec2Str(new Date()));
            //审核拒绝下线
            if (microfinance.getCheckType().equals("2"))
                microfinance.setType("0");
                //通过审核上线
            else if (microfinance.getCheckType().equals("1"))
                microfinance.setType("1");

            map = delMapNotKey(microfinance,
                    "checkType", "checkMsg", "checkTime", "updateTime", "type");
        } else {
            map = delMapNotKey(microfinance,
                    "type", "updateTime", "deleteFlag");
        }
        if (parentUpdate(theTableName, map, "where id=" + microfinance.getId()) == 0) {
            throw createError(ExceptionCode.code301);
        }
        return 1;
    }


    */
/**
     * 查询产品详情
     * @param microfinance
     * @return
     *//*

    public Microfinance getMicrofinanceById(Microfinance microfinance) {
        String companeType = getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE) + "";
        //如果是普通用户，屏蔽联系方式
        if ("1".equals(companeType) || companeType == null || companeType.equals("null")) {
            microfinance = microfinanceMapper.getMicrofinanceById(microfinance.getId());
            microfinance.setTel("");
            microfinance.setFixedTel("");
            //未登陆用户，屏蔽银行名
            if (companeType == null || companeType.equals("null"))
                microfinance.setCompanyName("*******");
            return microfinance;
        }

        return microfinanceMapper.getMicrofinanceById(microfinance.getId());
    }


    */
/**
     * @Description //删除金融产品
     * @Author 张军
     * @Date 2020/2/27 21:32
     * @Parameter
     **//*

    public int delMicrofinance(Microfinance microfinance) throws IidaException {
        microfinance.setDeleteFlag(1);
        Map map = delMapNotKey(microfinance,
                "deleteFlag", "applnum");
        if (parentUpdate(theTableName, map, "where id=" + microfinance.getId()) == 0) {
            throw createError(ExceptionCode.code301);
        }
        return 1;
    }

    */
/**
     * 机构发布的产品被申请+1
     * @param id
     * @param cloum
     * @return
     *//*

    public int microfinanceAddCloum(Long id, String cloum) {
        return parentAddCloum(theTableName, cloum, id);
    }

    */
/**
     * @Description //小贷产品列表
     * @Author 张军
     * @Date 2020/5/19
     * @Parameter --
     * loanChanne;      贷款渠道
     * loanTimeLimit;   贷款期限
     * guaranteeMode;   担保方式
     * loanAmount;      贷款额度
     * companyI;        企业ID
     * id;              主键
     **//*

    public Map<String, Object> listByConditions(Microfinance microfinance, int start, int end) {

        Map<String, Object> dataMap = new HashMap<>();

        String companeType = getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE) + "";

        //需要做等于条件的key
        String sqlLeys = "loanChanne,type,companyId,id,checkType,usercode,modeOfRepayment,";

        //like查询
        String sqlLike = "proName,proInt,characteristic,appCustomer,companyName,guaranteeMode,";

        //封装为sql
        sqlLeys += sql2like(sqlLike);
        String[] keys = sqlLeys.split(",");
        String whereSql = map2where(microfinance, keys);

        //增加betweenAnd
        if (ckStrIsNotnull(microfinance.getLoanTimeLimitMin()) || ckStrIsNotnull(microfinance.getLoanTimeLimitMax())) {
            whereSql += " and (" + getBetWeenAndSql("loanTimeLimitMin", microfinance.getLoanTimeLimitMin(), microfinance.getLoanTimeLimitMax());
            whereSql += " or " + getBetWeenAndSql("loanTimeLimitMax", microfinance.getLoanTimeLimitMin(), microfinance.getLoanTimeLimitMax()) + ")";
        }
        //增加betweenAnd
        if (ckStrIsNotnull(microfinance.getLoanAmountMin()) || ckStrIsNotnull(microfinance.getLoanAmountMax())) {
            whereSql += " and (" + getBetWeenAndSql("loanAmountMin", microfinance.getLoanAmountMin(), microfinance.getLoanAmountMax());
            whereSql += " or " + getBetWeenAndSql("loanAmountMax", microfinance.getLoanAmountMin(), microfinance.getLoanAmountMax()) + ")";
        }
        //增加betweenAnd
        if (ckStrIsNotnull(microfinance.getLoanRateMin()) || ckStrIsNotnull(microfinance.getLoanRateMax())) {
            whereSql += " and (" + getBetWeenAndSql("loanRateMin", microfinance.getLoanRateMin(), microfinance.getLoanRateMax());
            whereSql += " or " + getBetWeenAndSql("loanRateMax", microfinance.getLoanRateMin(), microfinance.getLoanRateMax()) + ")";
        }
        //List<Microfinance> list = microfinanceMapper.listByConditions(whereSql, start, end, microfinance.getOrderCloumn(), microfinance.getOrderDesc());

        List<Map> list = microfinanceMapper.getListByConditions(SqlUtil.getAuthCaseSql("microfinance",companeType),whereSql, start, end, microfinance.getOrderCloumn(), microfinance.getOrderDesc());
        list.forEach(y -> mapKeyLine2Hump(y));


        //如果是普通用户，屏蔽联系方式
        */
/*if ("1".equals(companeType) || companeType == null || companeType.equals("null")) {
            for (Map g : list) {
                g.put("tel","");
                g.put("fixedTel","");
                //未登陆用户，屏蔽银行名
                if (companeType == null || companeType.equals("null"))
                    g.put("companyName","*******");
            }
        }*//*


        //调用map生成where的语句
        dataMap.put("count", microfinanceMapper.countByConditions(whereSql));
        dataMap.put("list", list);


        return dataMap;
    }
}
*/
