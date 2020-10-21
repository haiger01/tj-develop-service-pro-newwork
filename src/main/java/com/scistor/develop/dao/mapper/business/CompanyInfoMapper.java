package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.CompanyInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyInfoMapper {
    @Results(id = "CompanyInfoResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "orgNumber", column = "org_number"),
            @Result(property = "creditCode", column = "credit_code"),
            @Result(property = "icpCode", column = "icp_code"),
            @Result(property = "regAddPro", column = "reg_add_pro"),
            @Result(property = "regAddCity", column = "reg_add_city"),
            @Result(property = "regAddRegion", column = "reg_add_region"),
            @Result(property = "regAddInfo", column = "reg_add_info"),
            @Result(property = "buslicense", column = "buslicense"),
            @Result(property = "legPersonPic", column = "leg_person_pic"),
            @Result(property = "legPerIdNum", column = "leg_per_id_num"),
            @Result(property = "comAccount", column = "com_account"),
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "accountBank", column = "account_bank"),
            @Result(property = "bankAddPro", column = "bank_add_pro"),
            @Result(property = "bankAddCity", column = "bank_add_city"),
            @Result(property = "bankAddRegion", column = "bank_add_region"),
            @Result(property = "bankAddAddress", column = "bank_add_address"),
            @Result(property = "adminName", column = "admin_name"),
            @Result(property = "adminId", column = "admin_id"),
            @Result(property = "adminTel", column = "admin_tel"),
            @Result(property = "colAccount", column = "col_account"),
            @Result(property = "colName", column = "col_name"),
            @Result(property = "colBank", column = "col_bank"),
            @Result(property = "colNum", column = "col_num"),
            @Result(property = "verificavtionDate", column = "verificavtion_date"),
            @Result(property = "finOperate", column = "fin_operate"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "authorizeUrl", column = "authorize_url"),
            @Result(property = "authorizeer", column = "authorizeer"),
            @Result(property = "otherTel", column = "other_tel"),
            @Result(property = "otherName", column = "other_name"),
            @Result(property = "businessAddress", column = "business_address"),
            @Result(property = "mainBank", column = "main_bank"),
            @Result(property = "mainBusiness", column = "main_business"),
            @Result(property = "financialStatements", column = "financial_statements"),
            @Result(property = "annualTaxReturns", column = "annual_tax_returns"),
            @Result(property = "monthlyTaxReturns", column = "monthly_tax_returns"),
            @Result(property = "capitalFlow", column = "capital_flow"),
            @Result(property = "customers", column = "customers"),
            @Result(property = "loansSituation", column = "loans_situation"),
            @Result(property = "refundStatus", column = "refund_status"),
            @Result(property = "refundTime", column = "refund_time"),
            @Result(property = "playTime", column = "play_time"),
            @Result(property = "playStatus", column = "play_status"),
            @Result(property = "acceptanceBorrower", column = "acceptance_borrower"),
            @Result(property = "acceptanceLender", column = "acceptance_lender"),
            @Result(property = "acceptanceRefundBorrower", column = "acceptance_refund_borrower"),
            @Result(property = "acceptanceRefundLender", column = "acceptance_refund_lender"),
            @Result(property = "refundCode", column = "refund_code"),
            @Result(property = "playCode", column = "play_code"),
            @Result(property = "businessPlan", column = "business_plan"),
            @Result(property = "roadshowVideo", column = "roadshow_video"),
            @Result(property = "investmentIndustry", column = "investment_industry"),
            @Result(property = "investmentStage", column = "investment_stage"),
            @Result(property = "financingAmount", column = "financing_amount"),
            @Result(property = "dataCollectionUrl", column = "data_collection_url")

    })
    @Select("select * from company_info where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<CompanyInfo> listByConditions(@Param("wheres") String wheres,
                                       @Param("start") int start,
                                       @Param("end") int end,
                                       @Param("orderCloumn") String orderCloumn,
                                       @Param("orderDesc") String orderDesc);

    @Select("select count(1) from company_info where delete_flag=0 and ${wheres}")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select id from company_info where delete_flag=0 and ${wheres} group by id limit 1")
    Long countByConditionsWhere(@Param("wheres") String wheres);

    @Select("select * from company_info where company_id =#{company_id}")
    @ResultMap(value = "CompanyInfoResultMap")
    CompanyInfo getCompanyInfoByCompanyId(@Param("company_id") String company_id);


    @Select("select CASE WHEN project_team_directory.company_id IS NULL THEN 0 ELSE 1 END isTeam,company_info.* from company_info left join project_team_directory on company_info.company_id = project_team_directory.company_id where company_info.usercode =#{openId} GROUP BY usercode")
    Map getCompanyInfoByUsercodeIsTeam(@Param("openId") String openId);

    @Select("select * from company_info where id=${id}")
    @ResultMap(value = "CompanyInfoResultMap")
    CompanyInfo getCompanyInfoById(@Param("id") Long id);

    @Select("select company_name companyName from company_info where delete_flag = 0")
    List<Map> getCompanyInfoNameList();

    @Select("select company_name companyName from institutions where delete_flag = 0 and company_type = 10")
    List<Map> getCompanyInfoNameZYList();

}
