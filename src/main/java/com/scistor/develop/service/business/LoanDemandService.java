/*
package com.scistor.develop.service.business;

import com.scistor.develop.dao.mapper.BaseDao;
import com.scistor.develop.dao.mapper.business.*;
import com.scistor.develop.data.entity.*;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.error.IidaException;
import com.scistor.develop.service.ParentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.*;
import static com.scistor.develop.util.DateTool.dateTimeSec2Str;
import static com.scistor.develop.util.ObjectUtil.*;
import static com.scistor.develop.util.ServletUtil.getSession;
import static com.scistor.develop.util.SqlUtil.getBetWeenAndSql;
import static com.scistor.develop.util.SqlUtil.map2where;
import static com.scistor.develop.util.SqlUtil.sql2like;

@Service
public class LoanDemandService extends ParentService {
    @Autowired
    LoanDemandMapper loanDemandMapper;

    @Autowired
    AuditOpinionMapper auditOpinionMapper;

    @Autowired
    InstitutionsMapper institutionsMapper;

    @Autowired
    LoanApplicationMapper loanApplicationMapper;


    static final String theTableName = "loan_demand";

    */
/**
     * @Description //我的需求发布
     * @Author 岳浩
     * @Date 2020/2/25 2:10
     * @Parameter --
     * LoanDemand所有属性
     **//*

    public LoanDemand insert(LoanDemand loanDemand) throws IidaException {
        //判断不允许为空的key---guaranteeMode担保方式去除，020928
        String[] notNullKeys = ("loanTimeLimit,loanAmount,purpose").split(",");
        String notKey = ckObjKeyIsNotNull(loanDemand, notNullKeys);
        if (notKey != null) {
            throw createError(ExceptionCode.code301, "参数错误>" + notKey);
        }
        //获取系统AppId
        loanDemand.setId(44444L);
        loanDemand.setFocusNum(0);
        HttpSession session = getSession();
        loanDemand.setApplicant(session.getAttribute(SESSION_KEYNAME_USER_NAME) + "");
        loanDemand.setOrgNumber(session.getAttribute(SESSION_KEYNAME_ORG_NUMBER) + "");
        loanDemand.setCompanyAddress(session.getAttribute(SESSION_KEYNAME_REG_ADD_INFO) + "");
        loanDemand.setType("1");

        if (parentInsert(theTableName, loanDemand) == 0) {
            createError(ExceptionCode.code5001);
        }
        return loanDemand;
    }

    */
/**
     * @Description //需求列表
     * @Author 岳浩
     * @Date 2020/2/25 2:08
     * @Parameter --
     * loanChanne;      贷款渠道
     * loanTimeLimit;   贷款期限
     * guaranteeMode;   担保方式
     * loanAmount;      贷款额度
     * companyI;        企业ID
     * id;              主键
     **//*

    public Map<String, Object> listByConditions(LoanDemand loanDemand, int start, int end, String... param) {

        Map<String, Object> dataMap = new HashMap<>();

        String companeType = getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE) + "";

        //企业查询自己的
        if("1".equals(companeType)){
            dataMap = demandListByConditions(loanDemand,start,end,param);
            return dataMap;
        }

        //需要做等于条件的key

        //20200506修改查询条件，之前的id和贷款渠道(loanChanne)就应该放在模糊查询里面
        //String sqlLeys = "loanChanne,type,companyId,purpose,id,";
        String sqlLeys = "type,companyId,purpose,";


        //like查询--添加企业需求的id，贷款渠道(loanChanne)搜索条件
        String sqlLike = "id,loanChanne,applicant,guaranteeMode,";
        //封装为sql
        sqlLeys += sql2like(sqlLike);

        String[] keys = (sqlLeys).split(",");
        //调用map生成where的语句
        String whereSql = map2where(theTableName, loanDemand, keys);

        //增加betweenAnd
        if (param != null && param.length > 0) {
            //  loanTimeLimitMax, loanTimeLimitMin, loanAmountMax, loanAmountMin

            if (ckStrIsNotnull(param[0]) || ckStrIsNotnull(param[1]))
                whereSql += " and  " + getBetWeenAndSql("loanTimeLimit", param[0], param[1]);

            if (ckStrIsNotnull(param[2]) || ckStrIsNotnull(param[3]))
                whereSql += " and  " + getBetWeenAndSql("loanAmount", param[2], param[3]);

        }

        //列表不查看某个企业需求
        //未登录状态不进行这个条件限制
        if (ckStrIsNotnull(getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "")) {
            whereSql += " and " + theTableName + ".id NOT IN (SELECT demand_id FROM not_accept" +
                    " WHERE institutions_id = '" + getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "'";

            whereSql += "UNION SELECT appid AS demand_id FROM loan_application where loan_application.check_type = 8  GROUP BY appid ";
            //不是金融办或者企业的时候----金融机构不看自己已经受理过的
            if (!"4".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+"") && !"1".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+""))
                whereSql += " UNION SELECT appid AS demand_id FROM loan_application WHERE loan_application.accept_company_id = '" + getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "' GROUP BY appid ";
            whereSql += ")";

        }
        //除银行以外的其他机构(用户类型为：2)----判断是否已经发布5天或者已经有一个企业拒绝了--精确到秒--张军--20200422
        if ("5".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)) || "2".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE))
                || "6".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)) || "7".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE))) {
            //1.如果有银行就行--银行先看，担保

            whereSql += " and (TIMESTAMPDIFF(SECOND," + theTableName + ".update_time,'" + dateTimeSec2Str(new Date()) + "') > " +
                    ("5".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)) ? 432000 : 864000);
            //受理次数--大于0表示的是已经不是第一次受理了，结合下线状态可以判断为拒绝后可被其他企业查看--贷款次数不为1，并且未上线
            whereSql += " or ((SELECT count(1) FROM loan_application l4 WHERE l4.appid = loan_demand.id AND l4.delete_flag = 0 AND check_type in(4,4.1,4.2,4.3,7,9)) > 0 and " + theTableName + ".type = 1))";
        }
        if("4".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE))){

            String provinceCode = getSession().getAttribute(SESSION_KEYNAME_PROVINCE_CODE)+"";
            if(!ckStrIsNotnull(provinceCode)) provinceCode= "120000";
            whereSql += " and " + theTableName + ".company_id in(select company_id from company_info where reg_add_pro = '" + provinceCode +"'";
            //这个不加表示的是市级别的金融办(单列市是省级)
            if(ckStrIsNotnull(getSession().getAttribute(SESSION_KEYNAME_CITY_CODE)+""))
                whereSql +=" AND reg_add_region = '" + getSession().getAttribute(SESSION_KEYNAME_CITY_CODE) + "'";
            whereSql +=")";
        }

        //被同时3加机构受理就不查看--放款的不展示--20200519--张军
        whereSql += " and name_intended_bank IS NULL and (SELECT count( 1 ) FROM loan_application l3 WHERE l3.appid = loan_demand.id AND l3.delete_flag = 0 AND check_type in(2,5,4.01,5.1,5.2,5.3,6,7.5) ) < 4 ";

        List<Map> list = loanDemandMapper.listByConditionsLeftJoinDemandAndLapp(whereSql, start, end, loanDemand.getOrderCloumn(), loanDemand.getOrderDesc());
        listKeyLine2Hump(list);


        if (companeType == null || companeType.equals("null")) {
            for (Map g : list) {
                g.put("tel", "");
                g.put("fixedTel", "");
                g.put("purpose", "*******");
                g.put("companyName", "*******");
            }
        }

        dataMap.put("list", list);
        dataMap.put("count", loanDemandMapper.countByConditions(whereSql));

        return dataMap;
    }


    public Map<String, Object> demandListByConditions(LoanDemand loanDemand, int start, int end, String... param) {

        Map<String, Object> dataMap = new HashMap<>();
        //需要做等于条件的key

        if(!ckStrIsNotnull(loanDemand.getCompanyId()))
            loanDemand.setCompanyId(getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID)+"");

        //20200506修改查询条件，之前的id和贷款渠道(loanChanne)就应该放在模糊查询里面
        //String sqlLeys = "loanChanne,type,companyId,purpose,id,";
        String sqlLeys = "type,companyId,purpose,";


        //like查询--添加企业需求的id，贷款渠道(loanChanne)搜索条件
        String sqlLike = "id,loanChanne,guaranteeMode,applicant,";
        //封装为sql
        sqlLeys += sql2like(sqlLike);

        String[] keys = (sqlLeys).split(",");
        //调用map生成where的语句
        String whereSql = map2where(theTableName, loanDemand, keys);


        //增加betweenAnd
        if (param != null && param.length > 0) {
            if (ckStrIsNotnull(param[0]) || ckStrIsNotnull(param[1]))
                whereSql += " and  " + getBetWeenAndSql("loanTimeLimit", param[0], param[1]);
            if (ckStrIsNotnull(param[2]) || ckStrIsNotnull(param[3]))
                whereSql += " and  " + getBetWeenAndSql("loanAmount", param[2], param[3]);
        }


        //列表不查看某个企业需求
        whereSql += " and " + theTableName + ".id NOT IN (SELECT demand_id FROM not_accept" +
                " WHERE institutions_id = '" + getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "')";

        List<Map> list = loanDemandMapper.demandListByConditions(whereSql, start, end, loanDemand.getOrderCloumn(), loanDemand.getOrderDesc());
        listKeyLine2Hump(list);
        dataMap.put("list", list);
        dataMap.put("count", loanDemandMapper.countByConditions(whereSql));

        return dataMap;
    }



    public List<LoanDemand> listByConditions(LoanDemand loanDemand) {
        //需要做等于条件的key
        String sqlLeys = "loanChanne,loanTimeLimit,loanAmount,type,companyId,purpose,id,";
        //like查询
        String sqlLike = "guaranteeMode,";
        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String whereSql = map2where(loanDemand, sqlLeys.split(","));
        return loanDemandMapper.listByConditions(whereSql, 0, 999, loanDemand.getOrderCloumn(),  loanDemand.getOrderDesc());

    }

    */
/**
     * @Description //需求count
     * @Author 岳浩
     * @Date 2020/2/25 2:08
     * @Parameter --
     * loanChanne;      贷款渠道
     * loanTimeLimit;   贷款期限
     * guaranteeMode;   担保方式
     * loanAmount;      贷款额度
     * companyI;        企业ID
     * id;              主键
     **//*

    public long countByConditions(LoanDemand loanDemand) {

        //需要做等于条件的key
        String sqlLeys = "loanChanne,loanTimeLimit,loanAmount,type,companyId,purpose,id,";
        //like查询
        String sqlLike = "guaranteeMode,";
        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String whereSql = map2where(loanDemand, sqlLeys.split(","));

        return loanDemandMapper.countByConditions(whereSql);
    }

    */
/**
     * @Description //需求详情
     * @Author 岳浩
     * @Date 2020/2/25 2:08
     * @Parameter --
     * id;              主键
     **//*

    public LoanDemand getByConditions(LoanDemand loanDemand) {
        return loanDemandMapper.getLoanDemandById(loanDemand.getId());
    }


    */
/**
     * 获取非定向需求个数
     * @param companyInfo
     * @return
     *//*

    public Long getAnyNumberByCompanyId(CompanyInfo companyInfo) {
        return loanDemandMapper.getAnyNumberByCompanyId(companyInfo.getCompanyId());
    }


    */
/**
     * @Description // 上、下线当前需求
     * @Author 岳浩
     * @Date 2020/2/25 1:23
     * @Parameter --
     **//*

    public int updateType(LoanDemand loanDemand) {
        Map map = obj2Map(loanDemand);
        //删除map内多余的key只留需要的操作企业信息
        map = delMapNotKey(map,
                "type", "deleteFlag");
        if (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4")) {
            return parentUpdate(theTableName, map, "where id=" + loanDemand.getId());
        }
        return parentUpdate(theTableName, map, "where id=" + loanDemand.getId() + "" +
                " and usercode='" + getSession().getAttribute(SESSION_KEYNAME_USER_CODE) + "'");
    }

    */
/**
     * @Description // 修改需求接口
     * @Author 岳浩
     * @Date 2020/2/25 1:23
     * @Parameter --
     **//*

    public int updateLoanDemand(LoanDemand loanDemand) {
        //修改企业申请的时候需要修改发布时间--张军--20200421
        loanDemand.setCreaterTime(new Date());
        Map map = obj2Map(loanDemand);
        //loanChanne;     //贷款渠道
        //loanTimeLimit;  //贷款期限
        //loanAmount;     //贷款额度
        //guaranteeMode;  //担保方式
        //purpose;        //用途

        //删除map内多余的key只留需要的操作企业信息
        map = delMapNotKey(map,
                "loanTimeLimit", "loanChanne", "loanAmount", "guaranteeMode", "purpose", "createrTime","nameIntendedBank");

        return parentUpdate(theTableName, map, "where id=" + loanDemand.getId() + "" +
                " and usercode='" + getSession().getAttribute(SESSION_KEYNAME_USER_CODE) + "'");
    }

    */
/**
     * @Description // 删除当前需求
     * @Author 岳浩
     * @Date 2020/2/25 1:23
     * @Parameter --
     **//*

    public int delLoanDemand(LoanDemand loanDemand) {

        //删除map内多余的key只留需要的操作企业信息
        Map map = delMapNotKey(loanDemand,
                "deleteFlag", "id");
        return parentUpdate(theTableName, map, "where id=" + loanDemand.getId());
    }

    */
/**
     * @Description //修改改需求的失败次数
     * @Author 岳浩
     * @Date 2020/2/25 1:23
     * @Parameter --
     **//*

    public int parentAddCloum(Long demandId) {
        //删除map内多余的key只留需要的操作企业信息
        return parentAddCloum(theTableName, "num", demandId);
    }


    */
/**
     * @Description //需求意向加一
     * @Author 岳浩
     * @Date 2020/3/6 0006 14:34
     * @Parameter --
     **//*

    public int addFocusNumById(Long id) {
        return parentAddCloum(theTableName, "focus_num", id);
    }

    */
/**
     * @Description //需求意向加一
     * @Author 岳浩
     * @Date 2020/3/6 0006 14:34
     * @Parameter --
     **//*

    public int addFocusNumById(Long id, Long num) {
        return parentAddCloum(theTableName, "focus_num", num, id);
    }


    */
/**
     * @Description //添加银行或者担保机构不受理的需求
     * @Author 张军
     * @Date 2020/4/12 0006 14:34
     * @Parameter --
     **//*

    public Long doNotAccept(Long demandId) {
        getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID);
        String sql = "insert into not_accept(demand_id,institutions_id) values('" + demandId + "','" + getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) + "')";
        return longBySql(sql);
    }


    */
/**
     * @Description //添加审核意见
     * @Author 张军
     * @Date 2020/6/4
     * @Parameter --
     **//*

    public int addLoanDemandAuditOpinion(AuditOpinion auditOpinion) {
        auditOpinion.setCompanyId(getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID)+"");
        auditOpinion.setCompanyName(getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"");
        auditOpinion.setCompanyType(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+"");
        Map map = obj2Map(auditOpinion);
        return parentRecordInsert2("audit_opinion", map);
    }

    */
/**
     * @Description //获取审核意见列表
     * @Author 张军
     * @Date 2020/6/4
     * @Parameter --
     **//*

    public Map loanDemandAuditOpinionList(AuditOpinion auditOpinion) {
        Map<String, Object> dataMap = new HashMap<>();
        //需要做等于条件的key

        String sqlLeys = "loanId,";
        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");
        String whereSql = map2where(auditOpinion, keys);
        if("1".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+"")){
            whereSql+=" and (select count(1) from loan_demand where loan_demand.id = "+ auditOpinion.getLoanId() +
                    " and loan_demand.company_id = '"+getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID)+"') > 0";
        }
        dataMap.put("list", auditOpinionMapper.listByConditions(whereSql, 0, 99999, auditOpinion.getOrderCloumn(), auditOpinion.getOrderDesc()));
        dataMap.put("count", auditOpinionMapper.countByConditions(whereSql));
        return dataMap;
    }


    */
/**
     * 专员中心--企业需求(上线的都展示包括添加意向银行的需求)
     * @param loanDemand
     * @param start
     * @param end
     * @return
     *//*

    public Map getSpecialistLoanDemandList(LoanDemand loanDemand,int start, int end) {
        Map<String, Object> dataMap = new HashMap<>();
        //模糊条件
        String sqlLike = "id,companyName,loanChanne";

        //调用map生成where的语句
        String[] keys = sql2like(sqlLike).split(",");
        String whereSql = map2where(loanDemand, keys);

        dataMap.put("list", loanDemandMapper.listByConditions(whereSql, start, end, loanDemand.getOrderCloumn(), loanDemand.getOrderDesc()));
        dataMap.put("count", loanDemandMapper.countByConditions(whereSql));
        return dataMap;
    }

    */
/**
     * 专员中心--企业需求(上线的都展示包括添加意向银行的需求)
     * @return
     *//*

    public int undoApply(String loanDemandId,String acceptCompanyId) {
             */
/*  信息初审	    2       4	    审核中
                *  数据审核	    5			    审核中
                *  贷前调查	    4.01    4.1		审核中
                *  最终审核	    5.1     7		审核中
                *  实际放款	    7.5			    审核中
                *  实际放款	    8			    审核完成
        *//*

        Map<String,String> map = new HashMap<>();
        map.put("2","4");
        map.put("5","4.1");
        map.put("4.01","4.1");
        map.put("5.1","7");
        map.put("7.5","9");

        //9表示的是股权\
        Institutions institutionsQuery = institutionsMapper.getFinancialCompanyDetailsByCompanyId(acceptCompanyId);
        if(institutionsQuery  == null) return 0;


        if(!"9".equals(institutionsQuery.getCompanyType())){
            LoanApplication loanApplication = loanApplicationMapper.getLoanApplicationById(Long.valueOf(loanDemandId));
            loanApplication.setUpdateTime(new Date());
            loanApplication.setBeforCheckType(loanApplication.getCheckType());
            loanApplication.setCheckType(map.get(loanApplication.getCheckType()));
            parentUpdate("loan_application", loanApplication, "where id=" + loanApplication.getId());
        }
        if("9".equals(institutionsQuery.getCompanyType())){
            EquityAudit equityAudit = new EquityAudit();
            equityAudit.setUpdateTime(new Date());
            equityAudit.setBeforCheckType(equityAudit.getCheckType());
            equityAudit.setCheckType(map.get(equityAudit.getCheckType()));
            parentUpdate("equity_audit", equityAudit, "where id=" + equityAudit.getId());
        }
        return 1;
    }

}
*/
