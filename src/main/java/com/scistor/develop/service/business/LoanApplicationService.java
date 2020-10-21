package com.scistor.develop.service.business;

import com.scistor.develop.dao.mapper.business.*;
import com.scistor.develop.data.entity.CompanyInfo;
import com.scistor.develop.data.entity.EquityAudit;
import com.scistor.develop.data.entity.LoanApplication;
import com.scistor.develop.data.entity.ProjectTeamDirectory;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.service.ParentService;
import com.scistor.develop.tools.BlockAttribute;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.scistor.develop.controller.ParentController.mapMergaRecords;
import static com.scistor.develop.tools.BlockAttribute.*;
import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_SPLIT_STR;
import static com.scistor.develop.util.DateTool.dateTimeSec2Str;
import static com.scistor.develop.util.ObjectUtil.*;
import static com.scistor.develop.util.ServletUtil.getSession;
import static com.scistor.develop.util.SqlUtil.*;

@Service
public class LoanApplicationService extends ParentService {

    @Autowired
    LoanApplicationMapper loanApplicationMapper;
    @Autowired
    public MatchingMapper matchingMapper;

    static final String theTableName = "loan_application";
    @Autowired
    ProjectTeamDirectoryMapper projectTeamDirectoryMapper;

    @Autowired
    EquityAuditMapper equityAuditMapper;

    @Autowired
    CompanyInfoMapper companyInfoMapper;



    /**
     * @Description //贷款列表
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --s
     **/
    public Map<String, Object> listByConditions(LoanApplication loanApplication, int start, int end, String... param) {

        Map<String, Object> dataMap = new HashMap<>();
        //需要做等于条件的key

        //如果为1，说明是企业 companyDeleteFlag = 1 的不看
        //如果不为4，说明是银行或者担保机构，guarantorsDeleteFlag = 1 的不看
        //如果为4 条件不添加
        String sql1 = (String) (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("1") ?
                "companyDeleteFlag!=," : (!getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4") ? "guarantorsDeleteFlag!=," : ""));


        String sqlLeys = "applynum,applydate,companyId,appid,finproId,acceptCompanyId,purpose,loanType,lineofcredit,loanNum,loanTime,";
        sqlLeys += sql1;

        //like查询
        String sqlLike = "id,accountName,companyName,adminName,acceptCompanyName,";

        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");

        //调用map生成where的语句
        String whereSql = map2where(loanApplication, keys);

        //增加betweenAnd
        if (param != null && param.length > 0) {
            // applydateMax , applydateMin , guaDateMax , guaDateMin ;
            if (ckStrIsNotnull(param[0]) || ckStrIsNotnull(param[1]))
                //修改查询条件---最大值和最小值放反了，之前是param[0], param[1]
                //{"6个月及以下":{"max":"6","min":"0"},"12个月及以下":{"max":"12","min":"0"},"36个月及以下":{"max":"36","min":"0"},"36个月及以上":{"min":"36"}}
                whereSql += " and  " + getBetWeenAndSql("applydate", param[1], param[0]);

            if (ckStrIsNotnull(param[2]) || ckStrIsNotnull(param[3]))
                whereSql += " and  " + getBetWeenAndSql("guaDate", param[3], param[2]);
        }
        //增加IN查询
        whereSql += " and  " + getInBySql("checkType", loanApplication);

        dataMap.put("list", loanApplicationMapper.listByConditions(whereSql, start, end, loanApplication.getOrderCloumn(), loanApplication.getOrderDesc()));
        dataMap.put("count", loanApplicationMapper.countByConditions(whereSql));

        return dataMap;
    }


    /**
     * @Description //企业融资需求--企业的二级菜单
     * @Author 张军
     * @Date 2020/5/6 16:28
     * @Parameter --s
     **/
    public Map<String, Object> loanApplicationListByConditions(LoanApplication loanApplication, int start, int end) {

        Map<String, Object> dataMap = new HashMap<>();
        //需要做等于条件的key

        //如果为1，说明是企业 companyDeleteFlag = 1 的不看
        //如果不为4，说明是银行或者担保机构，guarantorsDeleteFlag = 1 的不看
        //如果为4 条件不添加
        String sql1 = (String) (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("1") ?
                "companyDeleteFlag!=," : (!getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4") ? "guarantorsDeleteFlag!=," : ""));

        //查询制定企业的某个需求的受理列表
        String sqlLeys = "loanType,appid,";
        sqlLeys += sql1;

        //like查询
        String sqlLike = "id,";

        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");

        //调用map生成where的语句
        String whereSql = map2where(loanApplication, keys);
        //增加IN查询----受理结果集合
        whereSql += " and  " + getInBySql("checkType", loanApplication);
        dataMap.put("list", loanApplicationMapper.listByConditions(whereSql, start, end, loanApplication.getOrderCloumn(), loanApplication.getOrderDesc()));
        dataMap.put("count", loanApplicationMapper.countByConditions(whereSql));

        return dataMap;
    }




    /**
     * @Description
     * @Author 张军
     * @Date 2020/10/17
     * @Parameter --s
     **/
    public Map<String, Object> getEnterpriseGovernmentDataList(LoanApplication loanApplication, int start, int end) {

        Map<String, Object> dataMap = new HashMap<>();
        //需要做等于条件的key

        //如果为1，说明是企业 companyDeleteFlag = 1 的不看
        //如果不为4，说明是银行或者担保机构，guarantorsDeleteFlag = 1 的不看
        //如果为4 条件不添加
        String sql1 = "guarantorsDeleteFlag!=,";

        //查询制定企业的某个需求的受理列表
        String sqlLeys = "loanType,appid,";
        sqlLeys += sql1;

        //like查询
        String sqlLike = "id,companyName,proName";

        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");

        //调用map生成where的语句
        String whereSql = map2where(loanApplication, keys);
        List<Map> list = loanApplicationMapper.netWorkListByConditions(whereSql, start, end, theTableName + ".creater_time", "DESC");
        String companyIds = "";

        for (Map m : list) {
            mapKeyLine2Hump(m);
            companyIds += ("'" + m.get("companyId") + "',");
        }

        if (StringUtils.isEmpty(companyIds) && companyIds.length() > 1) {
            companyIds = companyIds.substring(0,companyIds.length() -1);
            whereSql += " and company_id in('"+ companyIds +"') ";
        }

        //查询企业信息
        List<CompanyInfo> companyList = companyInfoMapper.listByConditions(whereSql, start, end, theTableName + ".creater_time", "DESC");
        Map companyMap = new HashMap();
        for (CompanyInfo companyInfo : companyList) {
            companyMap.put(companyInfo.getCompanyId(),companyInfo);
        }
        dataMap.put("companyMap", companyMap);
        dataMap.put("list", list);
        dataMap.put("count", loanApplicationMapper.countByConditions(whereSql));
        return dataMap;
    }



    /**
     * @Description //贷款列表
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public List<LoanApplication> listByConditions(LoanApplication loanApplication) {

        //如果为1，说明是企业 companyDeleteFlag = 1 的不看
        //如果不为4，说明是银行或者担保机构，guarantorsDeleteFlag = 1 的不看
        //如果为4 条件不添加
        String sql1 = (String) (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("1") ?
                "companyDeleteFlag!=," : (!getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4") ? "guarantorsDeleteFlag!=," : ""));

        //需要做等于条件的key
        String sqlLeys = "companyId,id,finproId,appid,acceptCompanyId,checkType,purpose,loanType,applynum,applydate,lineofcredit,loanNum,";

        sqlLeys += sql1;

        //like查询
        String sqlLike = "accountName,companyName,adminName,acceptCompanyName,guaranteeMode,";

        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");

        //调用map生成where的语句
        String whereSql = map2where(loanApplication, keys);
        whereSql += " and  " + getInBySql("checkType", loanApplication);

        return loanApplicationMapper.listByConditions(whereSql, 0, 999, loanApplication.getOrderCloumn(), loanApplication.getOrderDesc());

    }

    /**
     * @Description //申请贷款
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public int addLoanApplication(LoanApplication loanApplication) {

        //判断布允许为空的key
        String[] notNullKeys = ("appid,applynum,applydate,loanType").split(",");
        String notKey = ckObjKeyIsNotNull(loanApplication, notNullKeys);
        if (notKey != null) {
            throw createError(ExceptionCode.code301, "参数错误>" + notKey);
        }
        if (loanApplication.getId() == null) {
            loanApplication.setId(111L);
        }
        return parentInsert(theTableName, loanApplication);

    }

    /**
     * @Description //贷款详情
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public LoanApplication getLoanApplicationById(LoanApplication loanApplication) {
        return loanApplicationMapper.getLoanApplicationById(loanApplication.getId());
    }


    /**
     * @Description //获取受理记录，判断是否已经受理过或者拒绝过
     * @Author 张军
     * @Date 2020/5/9
     * @Parameter --
     **/
    public long getAcceptNumberByDemandIdAndApplyId(Long appid,String acceptCompanyId) {
        return loanApplicationMapper.getAcceptNumberByDemandIdAndApplyId(appid,acceptCompanyId);
    }


    /**
     * @Description //贷款详情增加字段
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public Map getLoanApplicationByIdMap(LoanApplication loanApplication) {
        Map map = loanApplicationMapper.getLoanApplicationByIdLeftJoinFinancialGoods(loanApplication.getId());
        mapKeyLine2Hump(map);

        mapMergaRecords(map);

        //添加企业信息
        CompanyInfo companyInfo = companyInfoMapper.getCompanyInfoByCompanyId(map.get("companyId")+"");
        map.put("businessPlan",companyInfo.getBusinessPlan());
        map.put("roadshowVideo",companyInfo.getRoadshowVideo());
        map.put("investmentIndustry",companyInfo.getInvestmentIndustry());
        map.put("investmentStage",companyInfo.getInvestmentStage());
        map.put("financingAmount",companyInfo.getFinancingAmount());
        map.put("dataCollectionUrl",companyInfo.getDataCollectionUrl());

        return map;
    }

    /**
     * @Description //小额贷款--受理详情
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public Map getLoanApplicationByIdMicrofinance(LoanApplication loanApplication) {
        Map map = loanApplicationMapper.getLoanApplicationByIdLeftJoinMicrofinance(loanApplication.getId());
        mapKeyLine2Hump(map);

        mapMergaRecords(map);
        //添加企业信息
        CompanyInfo companyInfo = companyInfoMapper.getCompanyInfoByCompanyId(map.get("companyId")+"");
        map.put("businessPlan",companyInfo.getBusinessPlan());
        map.put("roadshowVideo",companyInfo.getRoadshowVideo());
        map.put("investmentIndustry",companyInfo.getInvestmentIndustry());
        map.put("investmentStage",companyInfo.getInvestmentStage());
        map.put("financingAmount",companyInfo.getFinancingAmount());

        return map;
    }

    /**
     * @Description //融资租赁--受理详情
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public Map getLoanApplicationByIdFinancingLeaseDetail(LoanApplication loanApplication) {
        Map map = loanApplicationMapper.getLoanApplicationByIdLeftJoinFinancingLease(loanApplication.getId());
        mapKeyLine2Hump(map);

        mapMergaRecords(map);

        //添加企业信息
        CompanyInfo companyInfo = companyInfoMapper.getCompanyInfoByCompanyId(map.get("companyId")+"");
        map.put("businessPlan",companyInfo.getBusinessPlan());
        map.put("roadshowVideo",companyInfo.getRoadshowVideo());
        map.put("investmentIndustry",companyInfo.getInvestmentIndustry());
        map.put("investmentStage",companyInfo.getInvestmentStage());
        map.put("financingAmount",companyInfo.getFinancingAmount());
        return map;
    }



    /**
     * @Description //担保详情增加字段
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public Map getLoanApplicationByIdGuarantee(LoanApplication loanApplication) {
        Map map = loanApplicationMapper.getLoanApplicationByIdLeftJoinGuarantee(loanApplication.getId());
        mapKeyLine2Hump(map);
        mapMergaRecords(map);
        //添加企业信息
        CompanyInfo companyInfo = companyInfoMapper.getCompanyInfoByCompanyId(map.get("companyId")+"");
        map.put("businessPlan",companyInfo.getBusinessPlan());
        map.put("roadshowVideo",companyInfo.getRoadshowVideo());
        map.put("investmentIndustry",companyInfo.getInvestmentIndustry());
        map.put("investmentStage",companyInfo.getInvestmentStage());
        map.put("financingAmount",companyInfo.getFinancingAmount());
        return map;
    }

    /**
     * @Description //贷款审核
     * @Author 岳浩
     * @Date 2020/2/27 21:28
     * @Parameter --
     * acceptCompanyId:受理银行id
     * acceptCompanyName:受理银行名称
     * acceptCreate:受理时间
     * acceptUser:受理人
     * acceptTel:受理人联系方式
     * acceptMesage:受理意见
     * checkStart:数据查询开始时间
     * checkEnd:数据查询结束时间
     * checkType:审核状态：1企业申请，2同意授权 3.银行受理中 4.受理不通过，5.受理通过 6.审核通过放款，7.审核不通过
     * loanNum:贷款金额
     * loanTime:下款时间
     * loanAccount:下款账号
     * colAccount;       //收款认证账户
     * colName;          //收款认证户名
     * colBank;          //收款认证银行
     **/
    public int updateType(LoanApplication loanApplication) {

        //删除map内多余的key只留需要的操作企业信息

        HttpSession session = getSession();
        //如果受理通过或者受理不通过
        if (loanApplication.getCheckType().equals("4") || loanApplication.getCheckType().equals("5")) {
            loanApplication.setAcceptCompanyId(session.getAttribute(SESSION_KEYNAME_COMPANY_ID) + "");
            loanApplication.setAcceptCompanyName(session.getAttribute(SESSION_KEYNAME_COMPANY_NAME) + "");
            loanApplication.setAcceptCreate(dateTimeSec2Str(new Date()));
            loanApplication.setCheckStart(dateTimeSec2Str(new Date()));
            loanApplication.setCheckEnd(dateTimeSec2Str(new Date()));
        }
        //如果是审核通过放款--前台传值了----20200603
        /*if (loanApplication.getCheckType().equals("8")) {
            loanApplication.setLoanTime(new Date());
        }*/

        Map map = new HashMap();
        map.put("checkType", loanApplication.getCheckType());
        map.put("checkTime", new Date());
        map.put("loanAppId", loanApplication.getId());
        parentInsert("loan_appli_records", map);

        return parentUpdate(theTableName, loanApplication, "where id=" + loanApplication.getId());
    }


    /**
     * @Description
     * @Author 张军
     * @Date 2020/4/8 1:23
     * @Parameter --
     **/
    public int delLoanApplicationById(String id) {
        LoanApplication loanApplication = new LoanApplication();
        Map map = null;
        if (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("1")) {
            loanApplication.setCompanyDeleteFlag("1");
            map = delMapNotKey(loanApplication,
                    "companyDeleteFlag");
        } else if (!getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4")) {
            loanApplication.setGuarantorsDeleteFlag("1");
            map = delMapNotKey(loanApplication,
                    "guarantorsDeleteFlag");
        }
        return parentUpdate(theTableName, map, "where id=" + id);

    }

    /**
     * @Description //首页平台成功
     * @Author 张军
     * @Date 2020/2/27 21:28
     * @Parameter --
     **/
    public List<LoanApplication> resultsShowList() {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setCheckType("8");
        String whereSql = map2where(loanApplication, "checkType");
        List<LoanApplication> list = loanApplicationMapper.listByConditions(whereSql, 0, 100, loanApplication.getOrderCloumn(), loanApplication.getOrderDesc());

        LoanApplication l1 = new LoanApplication();
        l1.setCompanyName("天津厚普网络技术有限公司");
        l1.setAcceptCompanyName("齐鲁银行");
        LoanApplication l2 = new LoanApplication();
        l2.setCompanyName("中创海通(天津)国际贸易有限公司");
        l2.setAcceptCompanyName("齐鲁银行");
        int i = 0;
        int y = 0;
        for (LoanApplication l : list) {
            if (l.getCompanyName().equals("天津厚普网络有限公司") && l.getAcceptCompanyName().equals("齐鲁银行"))
                i++;
            if (l.getCompanyName().equals("中创海通(天津)国际贸易有限公司") && l.getAcceptCompanyName().equals("齐鲁银行"))
                y++;
        }
        if (i == 0)
            list.add(l1);
        if (y == 0)
            list.add(l2);
        return list;
    }

    /**
     * @Description //撮合详情
     * @Author 张军
     * @Date 2020/4/12
     * @Parameter --
     **/
    public Map applyDetails(Long applyId, String loanType) {
        String sql = "";
        if ("1".equals(loanType)) {
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time," +
                    "loan_demand.loan_channe,loan_demand.name_intended_bank,company_info.org_number,financial_goods.pro_name,t.preliminary_results,t.field_investigation_results," +
                    "t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results,loan_application.* FROM " +
                    "loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id LEFT JOIN " +
                    "loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN financial_goods ON loan_application.finpro_id = financial_goods.id " +
                    "LEFT JOIN (SELECT loan_app_id," +
                    "MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END) preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END) field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END) data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END) bank_account_opening_result, " +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END) bank_audit_results FROM " +
                    "loan_appli_records WHERE loan_appli_records.loan_app_id = " + applyId + " GROUP BY loan_appli_records.loan_app_id) t ON " +
                    "t.loan_app_id = loan_application.id WHERE loan_application.delete_flag = 0 and loan_application.id = " + applyId;
            /*sql = "SELECT company_info.admin_tel, loan_demand.creater_time demand_creater_time, " +
                    "financial_goods.pro_name, loan_application.* FROM loan_application LEFT JOIN company_info " +
                    "ON loan_application.company_id = company_info.company_id LEFT JOIN loan_demand ON " +
                    "loan_application.appid = loan_demand.id LEFT JOIN financial_goods ON loan_application.finpro_id = financial_goods.id" +
                    " WHERE loan_application.id = " +applyId;*/
        } else if("2".equals(loanType)) {
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time,loan_demand.loan_channe,loan_demand.name_intended_bank,company_info.org_number,guarantee.pro_name," +
                    "t.preliminary_results,t.field_investigation_results,t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results," +
                    "loan_application.* FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
                    "LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN guarantee ON loan_application.finpro_id = guarantee.id" +
                    " LEFT JOIN (SELECT loan_app_id,MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END)  preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END)  field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END)  data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END)  bank_account_opening_result," +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END)  bank_audit_results FROM loan_appli_records" +
                    " WHERE loan_appli_records.loan_app_id = " + applyId + " GROUP BY loan_appli_records.loan_app_id) t ON t.loan_app_id = loan_application.id" +
                    " WHERE loan_application.delete_flag = 0 AND loan_application.id = " + applyId;
            /*sql = "SELECT company_info.admin_tel,loan_demand.creater_time demand_creater_time," +
                    "guarantee.pro_name,loan_application.* FROM loan_application LEFT JOIN company_info ON " +
                    "loan_application.company_id = company_info.company_id LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id" +
                    " LEFT JOIN guarantee ON loan_application.finpro_id = guarantee.id WHERE loan_application.delete_flag = 0 and" +
                    "  loan_application.id = " +applyId;*/
        } else if ("6".equals(loanType)){
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time,loan_demand.loan_channe,loan_demand.name_intended_bank,company_info.org_number,microfinance.pro_name," +
                    "t.preliminary_results,t.field_investigation_results,t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results," +
                    "loan_application.* FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
                    "LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN microfinance ON loan_application.finpro_id = microfinance.id" +
                    " LEFT JOIN (SELECT loan_app_id,MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END)  preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END)  field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END)  data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END)  bank_account_opening_result," +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END)  bank_audit_results FROM loan_appli_records" +
                    " WHERE loan_appli_records.loan_app_id = " + applyId + " GROUP BY loan_appli_records.loan_app_id) t ON t.loan_app_id = loan_application.id" +
                    " WHERE loan_application.delete_flag = 0 AND loan_application.id = " + applyId;
        } else if ("7".equals(loanType)){
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time,loan_demand.loan_channe,loan_demand.name_intended_bank,company_info.org_number,financing_lease.pro_name," +
                    "t.preliminary_results,t.field_investigation_results,t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results," +
                    "loan_application.* FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
                    "LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN financing_lease ON loan_application.finpro_id = financing_lease.id" +
                    " LEFT JOIN (SELECT loan_app_id,MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END)  preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END)  field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END)  data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END)  bank_account_opening_result," +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END)  bank_audit_results FROM loan_appli_records" +
                    " WHERE loan_appli_records.loan_app_id = " + applyId + " GROUP BY loan_appli_records.loan_app_id) t ON t.loan_app_id = loan_application.id" +
                    " WHERE loan_application.delete_flag = 0 AND loan_application.id = " + applyId;
        }
        return matchingMapper.applyDetails(sql);
    }

    public List<Map> applyList(String loanType) {
        String sql = "";
        if ("1".equals(loanType)) {
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time," +
                    "loan_demand.loan_channe,loan_demand.name_intended_bank,company_info.org_number,financial_goods.pro_name," +
                    "t.preliminary_results,t.field_investigation_results,CASE WHEN loan_application.check_type = '0' OR loan_application.check_type = '1' " +
                    "OR loan_application.check_type = '3' THEN '等待受理' WHEN loan_application.check_type = '2' OR loan_application.check_type = '4.01' " +
                    "OR loan_application.check_type = '5' OR loan_application.check_type = '5.1' OR loan_application.check_type = '5.2' " +
                    "OR loan_application.check_type = '5.3' OR loan_application.check_type = '6' OR loan_application.check_type = '7.5' THEN '审核中'" +
                    " WHEN loan_application.check_type = '4' OR loan_application.check_type = '4.1' OR loan_application.check_type = '4.2'" +
                    " OR loan_application.check_type = '4.3' OR loan_application.check_type = '7' OR loan_application.check_type = '9' THEN '审核失败'" +
                    " WHEN loan_application.check_type = '8' THEN '审核成功' END audit_status," +
                    "t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results,loan_application.* FROM " +
                    "loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id LEFT JOIN " +
                    "loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN financial_goods ON loan_application.finpro_id = financial_goods.id " +
                    "LEFT JOIN (SELECT loan_app_id," +
                    "MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END) preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END) field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END) data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END) bank_account_opening_result, " +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END) bank_audit_results FROM " +
                    "loan_appli_records where loan_appli_records.company_type != '9' GROUP BY loan_appli_records.loan_app_id) t ON " +
                    "t.loan_app_id = loan_application.id WHERE loan_application.delete_flag = 0 and loan_application.loan_type = "+loanType;
        } else if("2".equals(loanType)) {
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time,loan_demand.loan_channe," +
                    "loan_demand.name_intended_bank,company_info.org_number,guarantee.pro_name,CASE WHEN loan_application.check_type = '0' OR loan_application.check_type = '1' " +
                    "OR loan_application.check_type = '3' THEN '等待受理' WHEN loan_application.check_type = '2' OR loan_application.check_type = '4.01' " +
                    "OR loan_application.check_type = '5' OR loan_application.check_type = '5.1' OR loan_application.check_type = '5.2' " +
                    "OR loan_application.check_type = '5.3' OR loan_application.check_type = '6' OR loan_application.check_type = '7.5' THEN '审核中'" +
                    " WHEN loan_application.check_type = '4' OR loan_application.check_type = '4.1' OR loan_application.check_type = '4.2'" +
                    " OR loan_application.check_type = '4.3' OR loan_application.check_type = '7' OR loan_application.check_type = '9' THEN '审核失败'" +
                    " WHEN loan_application.check_type = '8' THEN '审核成功' END audit_status," +
                    "t.preliminary_results,t.field_investigation_results,t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results," +
                    "loan_application.* FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
                    "LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN guarantee ON loan_application.finpro_id = guarantee.id" +
                    " LEFT JOIN (SELECT loan_app_id,MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END)  preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END)  field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END)  data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END)  bank_account_opening_result," +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END)  bank_audit_results FROM loan_appli_records" +
                    " where loan_appli_records.company_type != '9' GROUP BY loan_appli_records.loan_app_id) t ON t.loan_app_id = loan_application.id" +
                    " WHERE loan_application.delete_flag = 0  and loan_application.loan_type = "+loanType;
        } else if ("6".equals(loanType)) {
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time,loan_demand.loan_channe," +
                    "loan_demand.name_intended_bank,company_info.org_number,microfinance.pro_name,CASE WHEN loan_application.check_type = '0' OR loan_application.check_type = '1' " +
                    "OR loan_application.check_type = '3' THEN '等待受理' WHEN loan_application.check_type = '2' OR loan_application.check_type = '4.01' " +
                    "OR loan_application.check_type = '5' OR loan_application.check_type = '5.1' OR loan_application.check_type = '5.2' " +
                    "OR loan_application.check_type = '5.3' OR loan_application.check_type = '6' OR loan_application.check_type = '7.5' THEN '审核中'" +
                    " WHEN loan_application.check_type = '4' OR loan_application.check_type = '4.1' OR loan_application.check_type = '4.2'" +
                    " OR loan_application.check_type = '4.3' OR loan_application.check_type = '7' OR loan_application.check_type = '9' THEN '审核失败'" +
                    " WHEN loan_application.check_type = '8' THEN '审核成功' END audit_status," +
                    "t.preliminary_results,t.field_investigation_results,t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results," +
                    "loan_application.* FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
                    "LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN microfinance ON loan_application.finpro_id = microfinance.id" +
                    " LEFT JOIN (SELECT loan_app_id,MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END)  preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END)  field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END)  data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END)  bank_account_opening_result," +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END)  bank_audit_results FROM loan_appli_records" +
                    " where loan_appli_records.company_type != '9' GROUP BY loan_appli_records.loan_app_id) t ON t.loan_app_id = loan_application.id" +
                    " WHERE loan_application.delete_flag = 0  and loan_application.loan_type = "+loanType;
        } else if ("7".equals(loanType)) {
            sql = "SELECT company_info.admin_tel,company_info.credit_code,loan_demand.creater_time demand_creater_time,loan_demand.loan_channe," +
                    "loan_demand.name_intended_bank,company_info.org_number,financing_lease.pro_name,CASE WHEN loan_application.check_type = '0' OR loan_application.check_type = '1' " +
                    "OR loan_application.check_type = '3' THEN '等待受理' WHEN loan_application.check_type = '2' OR loan_application.check_type = '4.01' " +
                    "OR loan_application.check_type = '5' OR loan_application.check_type = '5.1' OR loan_application.check_type = '5.2' " +
                    "OR loan_application.check_type = '5.3' OR loan_application.check_type = '6' OR loan_application.check_type = '7.5' THEN '审核中'" +
                    " WHEN loan_application.check_type = '4' OR loan_application.check_type = '4.1' OR loan_application.check_type = '4.2'" +
                    " OR loan_application.check_type = '4.3' OR loan_application.check_type = '7' OR loan_application.check_type = '9' THEN '审核失败'" +
                    " WHEN loan_application.check_type = '8' THEN '审核成功' END audit_status," +
                    "t.preliminary_results,t.field_investigation_results,t.data_collection_results,t.bank_account_opening_result,t.bank_audit_results," +
                    "loan_application.* FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
                    "LEFT JOIN loan_demand ON loan_application.appid = loan_demand.id LEFT JOIN financing_lease ON loan_application.finpro_id = financing_lease.id" +
                    " LEFT JOIN (SELECT loan_app_id,MAX(CASE WHEN check_type = '5' or check_type='4' THEN check_type ELSE NULL END)  preliminary_results," +
                    "MAX(CASE WHEN check_type = '5.1' or check_type = '4.1' THEN check_type ELSE NULL END)  field_investigation_results," +
                    "MAX(CASE WHEN check_type = '5.2' or check_type = '4.2' THEN check_type ELSE NULL END)  data_collection_results," +
                    "MAX(CASE WHEN check_type = '5.3' or check_type = '4.3' THEN check_type ELSE NULL END)  bank_account_opening_result," +
                    "MAX(CASE WHEN check_type = '7' or check_type = '7.5' THEN check_type ELSE NULL END)  bank_audit_results FROM loan_appli_records" +
                    " where loan_appli_records.company_type != '9' GROUP BY loan_appli_records.loan_app_id) t ON t.loan_app_id = loan_application.id" +
                    " WHERE loan_application.delete_flag = 0  and loan_application.loan_type = "+loanType;
        }
        return matchingMapper.applyDetailsList(sql);
    }

    public Map bankPerformanceDetailsList(LoanApplication loanApplication, int start, int end) {
        Map<String, Object> dataMap = new HashMap<>();
        //需要做等于条件的key

        //如果为1，说明是企业 companyDeleteFlag = 1 的不看
        //如果不为4，说明是银行或者担保机构，guarantorsDeleteFlag = 1 的不看
        //如果为4 条件不添加
        /*String sql1 = (String) (getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("1") ?
                "companyDeleteFlag!=," : (!getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE).equals("4") ? "guarantorsDeleteFlag!=," : ""));*/

        //查询制定企业的某个需求的受理列表
        String sqlLeys = "loanType,";
        //sqlLeys += sql1;

        //like查询
        String sqlLike = "id,companyName,";

        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");

        //调用map生成where的语句
        String whereSql = map2where(loanApplication, keys);
        loanApplication.setCheckType("7.5,8");
        //增加IN查询----受理结果集合
        whereSql += " and  " + getInBySql("checkType", loanApplication);


        dataMap.put("list", loanApplicationMapper.listByConditions(whereSql, start, end, loanApplication.getOrderCloumn(), loanApplication.getOrderDesc()));
        dataMap.put("count", loanApplicationMapper.countByConditions(whereSql));
        return dataMap;
    }

    public Map bankPerformanceList(LoanApplication loanApplication, int start, int end) {
        Map<String, Object> dataMap = new HashMap<>();
        //like查询
        String sqlLeys = "";
        String sqlLike = "acceptCompanyName,";
        //封装为sql
        sqlLeys += sql2like(sqlLike);

        //调用map生成where的语句
        String[] keys = (sqlLeys).split(",");

        //调用map生成where的语句
        String whereSql = map2where(loanApplication, keys);
        loanApplication.setCheckType("7.5,8");
        //增加IN查询----受理结果集合
        whereSql += " and  " + getInBySql("checkType", loanApplication);

        dataMap.put("list", loanApplicationMapper.bankPerformanceList(whereSql, start, end));
        dataMap.put("count", loanApplicationMapper.countBankPerformanceList(whereSql));
        return dataMap;
    }


    /**
     * 专员中心-融资动态列表
     * @param start
     * @param end
     * @return
     */
    public Map getSpecialistLoanApplicationList(EquityAudit equityAudit, int start, int end, String reviewProgress, String reviewStatus) {
        Map<String, Object> dataMap = new HashMap<>();
        //调用map生成where的语句
        String sql = sql = "where 1=1 ";

        if(ckStrIsNotnull(equityAudit.getId()+"")){
            sql += " and equity_audit.id like '%"+equityAudit.getId()+"%' ";
        }

        if(ckStrIsNotnull(equityAudit.getCompanyName())){
            sql += " and equity_audit.company_name like '%"+equityAudit.getCompanyName()+"%' ";
        }

        /*if(ckStrIsNotnull(reviewProgress) || ckStrIsNotnull(reviewStatus))
            sql += " and checkType in("+ BlockAttribute.CheckType.getCheckType(reviewProgress,reviewStatus) +") ";*/

        if("1".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+""))
            sql += " and equity_audit.company_id = '"+ getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) +"' ";
        if("9".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+""))
            sql += " and equity_audit.accept_company_id = '"+ getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID) +"' ";
        if("10".equals(getSession().getAttribute(SESSION_KEYNAME_COMPANY_TYPE)+"")){
            //查询
            ProjectTeamDirectory projectTeamDirectory = new ProjectTeamDirectory();
            projectTeamDirectory.setSpecialistName(getSession().getAttribute(SESSION_KEYNAME_COMPANY_NAME)+"");
            String companyIds = projectTeamDirectoryMapper.getProjectTeamDirectoryBySpecialistName(projectTeamDirectory.getSpecialistName());
            if(StringUtils.isNotEmpty(companyIds)){
                sql += " and equity_audit.company_id in('"+ companyIds.replaceAll(",","','") +"') ";
            } else {
                sql += " and equity_audit.company_id = '-1111' ";//表示没有列表
            }
        }

        String whereSql = "SELECT equity_audit.id,equity_audit.company_name companyName,equity_audit.company_id companyId,company_info.investment_industry investmentIndustry," +
                "company_info.investment_stage companyInvestmentStage,company_info.financing_amount financingAmount,equity_audit.creater_time createrTime,company_info.usercode," +
                "equity_audit.update_time updateTime,equity_audit.delete_flag deleteFlag,equity_audit.accept_company_id acceptCompanyId,equity_audit.equity_name equityName," +
                "equity_audit.accept_company_name acceptCompanyName,equity_audit.equity_id equityId,equity_audit.loan_time loanTime,equity_audit.records records,CASE WHEN equity_audit.check_type = '2'" +
                " OR equity_audit.check_type = '4' THEN '信息初审' WHEN equity_audit.check_type = '5' THEN '数据审核' WHEN equity_audit.check_type = '4.01'" +
                " OR equity_audit.check_type = '4.1' THEN '贷前调查' WHEN equity_audit.check_type = '5.1' OR equity_audit.check_type = '7' THEN '最终审核'" +
                " ELSE '实际放款' END reviewProgress,CASE WHEN equity_audit.check_type = '8' THEN '审核成功' WHEN equity_audit.check_type = '4'" +
                " OR equity_audit.check_type = '4.1' OR equity_audit.check_type = '4.2' OR equity_audit.check_type = '4.3' OR" +
                " equity_audit.check_type = '7' OR equity_audit.check_type = '9' THEN '审核失败' ELSE '审核中' END checkTypeName,equity_audit.check_type checkType FROM equity_audit" +
                " LEFT JOIN company_info ON equity_audit.company_id = company_info.company_id ";
        whereSql += sql;

        String whereSql2 = "SELECT count(1) FROM equity_audit" +
                " LEFT JOIN company_info ON equity_audit.company_id = company_info.company_id ";
        whereSql2 += sql;

        dataMap.put("list", equityAuditMapper.equityAuditlistByConditions(whereSql, start, end,"equity_audit.creater_time","DESC"));
        dataMap.put("count", equityAuditMapper.countequityAuditlistByConditions(whereSql2));
        return dataMap;
    }


    /**
     * 获取股权详情
     * @param equityAudit
     * @return
     */
    public Map getSpecialistLoanApplicationDetail(EquityAudit equityAudit) {
        Map map = equityAuditMapper.getSpecialistLoanApplicationDetail(equityAudit.getId());
        if(!CollectionUtils.isEmpty(map)){
            mapKeyLine2Hump(map);
            mapMergaRecords(map);
        }
        return map;
    }


    /**
     * 根据companyType为1的企业ID查询企业金融产品再行数
     * @param companyInfo
     * @return
     */
    public long getAnyNumberByCompanyId(CompanyInfo companyInfo) {

        //查询企业个性化需求的个数
        return loanApplicationMapper.getAnyNumberByCompanyId(companyInfo.getCompanyId());
    }



}
