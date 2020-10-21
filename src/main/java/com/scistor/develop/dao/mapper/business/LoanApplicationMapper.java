package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.LoanApplication;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoanApplicationMapper {
    @Results(id = "LoanApplicationResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "finproId", column = "finpro_id"),
            @Result(property = "loanType", column = "loan_type"),
            @Result(property = "purpose", column = "purpose"),
            @Result(property = "acceptCompanyId", column = "accept_company_id"),
            @Result(property = "acceptCompanyName", column = "accept_company_name"),
            @Result(property = "acceptCreate", column = "accept_create"),
            @Result(property = "acceptUser", column = "accept_user"),
            @Result(property = "acceptTel", column = "accept_tel"),
            @Result(property = "acceptMesage", column = "accept_mesage"),
            @Result(property = "checkStart", column = "check_start"),
            @Result(property = "checkEnd", column = "check_end"),
            @Result(property = "checkType", column = "check_type"),
            @Result(property = "loanNum", column = "loan_num"),
            @Result(property = "loanTime", column = "loan_time"),
            @Result(property = "loanAccount", column = "loan_account"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "guaranteeMode", column = "guarantee_mode"),
            @Result(property = "applynum", column = "applynum"),
            @Result(property = "applydate", column = "applydate"),
            @Result(property = "colAccount", column = "col_account"),
            @Result(property = "colName", column = "col_name"),
            @Result(property = "colBank", column = "col_bank"),
            @Result(property = "checkMesage", column = "check_mesage"),
            @Result(property = "guaDate", column = "gua_date"),
            @Result(property = "guaNum", column = "gua_num"),
            @Result(property = "guaRate", column = "gua_rate"),
            @Result(property = "guaRegion", column = "gua_region"),
            @Result(property = "guaGuaranteeMode", column = "gua_guarantee_mode"),
            @Result(property = "lineofcredit", column = "lineofcredit"),
            @Result(property = "appid", column = "appid"),
            @Result(property = "companyDeleteFlag", column = "company_delete_flag"),
            @Result(property = "guarantorsDeleteFlag", column = "guarantors_delete_flag"),
            @Result(property = "investigationMsg", column = "investigation_msg"),
            @Result(property = "investigationTime", column = "investigation_time"),
            @Result(property = "collectionMsg", column = "collection_msg"),
            @Result(property = "collectionTime", column = "collection_time"),
            @Result(property = "bankopenMsg", column = "bankopen_msg"),
            @Result(property = "bankopenTime", column = "bankopen_time"),
            @Result(property = "beforCheckType", column = "befor_check_type"),
            @Result(property = "records", column = "records"),
            @Result(property = "reportTime", column = "report_time"),
            @Result(property = "creditTime", column = "credit_time"),
            @Result(property = "rateofcredit", column = "rateofcredit"),
            @Result(property = "dateofcredit", column = "dateofcredit"),
            @Result(property = "cooperationBank", column = "cooperation_bank"),
            @Result(property = "proName", column = "pro_name"),
            @Result(property = "dataAuditTypes", column = "data_audit_types"),
            @Result(property = "authorizationCode", column = "authorization_code"),
            @Result(property = "keyCode", column = "key_code"),
            @Result(property = "dataLabelTypes", column = "data_label_types"),
            @Result(property = "guarantyStyleEnd", column = "guaranty_style_end")


    })
    @Select(" SELECT " +
            " CASE WHEN loan_application.loan_type = 1 AND loan_application.finpro_id IS NOT NULL" +
            " THEN (SELECT financial_goods.pro_name FROM financial_goods  WHERE financial_goods.id = loan_application.finpro_id) " +
            " WHEN loan_application.loan_type = 6 AND loan_application.finpro_id IS NOT NULL" +
            " THEN (SELECT microfinance.pro_name FROM microfinance  WHERE microfinance.id = loan_application.finpro_id) " +
            " WHEN loan_application.loan_type = 7 AND loan_application.finpro_id IS NOT NULL" +
            " THEN (SELECT financing_lease.pro_name FROM financing_lease  WHERE financing_lease.id = loan_application.finpro_id) " +
            " ELSE (SELECT guarantee.pro_name FROM guarantee WHERE guarantee.id = loan_application.finpro_id) END AS pro_name," +
            " loan_application.* " +
            " FROM loan_application WHERE delete_flag =0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<LoanApplication> listByConditions(@Param("wheres") String wheres,
                                           @Param("start") int start,
                                           @Param("end") int end,
                                           @Param("orderCloumn") String orderCloumn,
                                           @Param("orderDesc") String orderDesc);



    @Select(" SELECT " +
            " CASE WHEN loan_application.loan_type = 1 AND loan_application.finpro_id IS NOT NULL" +
            " THEN (SELECT financial_goods.pro_name FROM financial_goods  WHERE financial_goods.id = loan_application.finpro_id) " +
            " WHEN loan_application.loan_type = 6 AND loan_application.finpro_id IS NOT NULL" +
            " THEN (SELECT microfinance.pro_name FROM microfinance  WHERE microfinance.id = loan_application.finpro_id) " +
            " WHEN loan_application.loan_type = 7 AND loan_application.finpro_id IS NOT NULL" +
            " THEN (SELECT financing_lease.pro_name FROM financing_lease  WHERE financing_lease.id = loan_application.finpro_id) " +
            " ELSE (SELECT guarantee.pro_name FROM guarantee WHERE guarantee.id = loan_application.finpro_id) END AS pro_name," +
            " company_info.credit_code,company_info.org_number,authorization_keys,key_code,loan_application.* " +
            " FROM loan_application LEFT JOIN company_info ON loan_application.company_id = company_info.company_id " +
            " LEFT JOIN authorization_keys ON loan_application.id = authorization_keys.loan_appli_id " +
            " WHERE loan_application.delete_flag =0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<Map> netWorkListByConditions(@Param("wheres") String wheres,
                                           @Param("start") int start,
                                           @Param("end") int end,
                                           @Param("orderCloumn") String orderCloumn,
                                           @Param("orderDesc") String orderDesc);



    @Select(" SELECT accept_company_id AS acceptCompanyId,accept_company_name AS acceptCompanyName,COUNT(company_id) AS companyNum," +
            "SUM(lineofcredit) AS lineofcredits,COUNT(lineofcredit) AS lineofcreditNum,IF(SUM(loan_num) > 0,SUM(loan_num),0) AS loanNum," +
            "SUM(CASE WHEN check_type = 8 THEN 1 ELSE 0 END) AS loans FROM loan_application WHERE delete_flag = 0 AND ${wheres} " +
            " GROUP BY accept_company_id limit #{start},#{end}")
    List<Map> bankPerformanceList(@Param("wheres") String wheres,
                                           @Param("start") int start,
                                           @Param("end") int end);

    @Select("SELECT COUNT(1) FROM (SELECT COUNT(1) FROM loan_application WHERE delete_flag=0 AND ${wheres} GROUP BY accept_company_id) t1")
    long countBankPerformanceList(@Param("wheres") String wheres);

    @Select("select count(1) from loan_application where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select count(1) from loan_application where delete_flag IN(0,1) and appid=${appid} and accept_company_id =#{acceptCompanyId}")
    long getAcceptNumberByDemandIdAndApplyId(@Param("appid") Long appid,@Param("acceptCompanyId") String acceptCompanyId);

    @Select("select authorization_keys.data_label_types,authorization_keys.key_code,loan_application.* from loan_application" +
            " LEFT JOIN authorization_keys ON loan_application.id = authorization_keys.loan_appli_id where loan_application.id=${id}")
    @ResultMap(value = "LoanApplicationResultMap")
    LoanApplication getLoanApplicationById(@Param("id") Long id);

    @Select("SELECT " +
            " financial_goods.loan_time_limit_max, " +
            " financial_goods.loan_time_limit_min, " +
            " financial_goods.loan_amount_max, " +
            " financial_goods.loan_amount_min, " +
            " financial_goods.loan_rate_max, " +
            " financial_goods.loan_rate_min, " +
            " financial_goods.loan_rate," +
            " financial_goods.loan_channe," +
            " financial_goods.pro_name," +
            " authorization_keys.data_label_types," +
            " authorization_keys.key_code," +
            " loan_application.* " +
            " FROM loan_application LEFT JOIN financial_goods ON loan_application.finpro_id = financial_goods.id" +
            " LEFT JOIN authorization_keys ON loan_application.id = authorization_keys.loan_appli_id" +
            " where loan_application.id=${id} ")
    Map getLoanApplicationByIdLeftJoinFinancialGoods(@Param("id") Long id);


    @Select("SELECT " +
            " microfinance.loan_time_limit_max, " +
            " microfinance.loan_time_limit_min, " +
            " microfinance.loan_amount_max, " +
            " microfinance.loan_amount_min, " +
            " microfinance.loan_rate_max, " +
            " microfinance.loan_rate_min, " +
            " microfinance.loan_rate," +
            " microfinance.loan_channe," +
            " microfinance.pro_name," +
            " microfinance.address_customer_needs," +
            " authorization_keys.data_label_types," +
            " authorization_keys.key_code," +
            " loan_application.* " +
            " FROM loan_application LEFT JOIN microfinance ON loan_application.finpro_id = microfinance.id" +
            " LEFT JOIN authorization_keys ON loan_application.id = authorization_keys.loan_appli_id" +
            " where loan_application.id=${id} ")
    Map getLoanApplicationByIdLeftJoinMicrofinance(@Param("id") Long id);


    @Select("SELECT " +
            " financing_lease.loan_time_limit_max, " +
            " financing_lease.loan_time_limit_min, " +
            " financing_lease.loan_amount_max, " +
            " financing_lease.loan_amount_min, " +
            " financing_lease.loan_rate_max, " +
            " financing_lease.loan_rate_min, " +
            " financing_lease.loan_rate," +
            " financing_lease.loan_channe," +
            " financing_lease.pro_name," +
            " financing_lease.lease_type," +
            " authorization_keys.data_label_types," +
            " authorization_keys.key_code," +
            " loan_application.* " +
            " FROM loan_application LEFT JOIN financing_lease ON loan_application.finpro_id = financing_lease.id" +
            " LEFT JOIN authorization_keys ON loan_application.id = authorization_keys.loan_appli_id" +
            " where loan_application.id=${id} ")
    Map getLoanApplicationByIdLeftJoinFinancingLease(@Param("id") Long id);

    @Select("SELECT " +
            " guarantee.guarantee_rate, " +
            " guarantee.guarantee_rate_max, " +
            " guarantee.guarantee_rate_min, " +
            " guarantee.guarantee_line_max, " +
            " guarantee.guarantee_line_min, " +
            " guarantee.guarantee_date_max, " +
            " guarantee.guarantee_date_min," +
            " guarantee.policy_flag," +
            " guarantee.rmb_flag," +
            " guarantee.region," +
            " guarantee.goods_class," +
            " guarantee.goods_trait," +
            " guarantee.net_direct_flag," +
            " guarantee.general_flag," +
            " guarantee.goods_category," +
            " guarantee.conditions," +
            " guarantee.material," +
            " guarantee.pro_name," +
            " guarantee.success," +
            " authorization_keys.data_label_types," +
            " authorization_keys.key_code," +
            " loan_application.* " +
            " FROM loan_application LEFT JOIN guarantee ON loan_application.finpro_id = guarantee.id" +
            " LEFT JOIN authorization_keys ON loan_application.id = authorization_keys.loan_appli_id" +
            " where loan_application.id=${id} ")
    Map getLoanApplicationByIdLeftJoinGuarantee(@Param("id") Long id);


    @Select("SELECT COUNT(1) FROM loan_application WHERE loan_application.check_type" +
            " IN(4,4.1,4.2,4.3,7,8,9) AND loan_application.appid " +
            "  = (SELECT id FROM loan_demand WHERE name_intended_bank IS NULL AND company_id = #{companyId} ORDER BY id DESC limit 0,1)")
    long getAnyNumberByCompanyId(@Param("companyId") String companyId);


}
