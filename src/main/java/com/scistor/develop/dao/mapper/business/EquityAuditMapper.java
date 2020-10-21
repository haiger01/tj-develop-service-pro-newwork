package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.EquityAudit;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquityAuditMapper {

    @Results(id = "EquityAuditResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "equityId", column = "equity_id"),
            @Result(property = "acceptCompanyId", column = "accept_company_id"),
            @Result(property = "acceptCompanyName", column = "accept_company_name"),
            @Result(property = "acceptCreate", column = "accept_create"),
            @Result(property = "acceptUser", column = "accept_user"),
            @Result(property = "acceptTel", column = "accept_tel"),
            @Result(property = "acceptMesage", column = "accept_mesage"),
            @Result(property = "checkStart", column = "check_start"),
            @Result(property = "checkEnd", column = "check_end"),
            @Result(property = "checkType", column = "check_type"),
            @Result(property = "loanTime", column = "loan_time"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "applynum", column = "applynum"),
            @Result(property = "checkMesage", column = "check_mesage"),
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
            @Result(property = "equityName", column = "equity_name"),
            @Result(property = "rateofcredit", column = "rateofcredit"),
            @Result(property = "dateofcredit", column = "dateofcredit"),
            @Result(property = "lineofcredit", column = "lineofcredit"),
            @Result(property = "loanNum", column = "loan_num")

    })
    @Select("select * from equity_audit where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<EquityAudit> listByConditions(@Param("wheres") String wheres,
                                       @Param("start") int start,
                                       @Param("end") int end,
                                       @Param("orderCloumn")String orderCloumn,
                                       @Param("orderDesc")String orderDesc);
    @Select("select count(1) from equity_audit where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select count(1) from equity_audit where 1=1 and ${wheres} ")
    long countByConditionsNotDeleteFlag(@Param("wheres") String wheres);

    @Select("${sql} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<Map> equityAuditlistByConditions(@Param("sql") String sql,
                                @Param("start") int start,
                                @Param("end") int end,
                                @Param("orderCloumn")String orderCloumn,
                                @Param("orderDesc")String orderDesc);

    @Select("${sql}")
    long countequityAuditlistByConditions(@Param("sql") String sql);


    @Select("select * from equity_audit where id=${id}")
    @ResultMap(value = "EquityAuditResultMap")
    EquityAudit getEquityAuditById(@Param("id") Long id);




    @Select("SELECT company_info.investment_industry investmentIndustry," +
            "company_info.investment_stage companyInvestmentStage," +
            "company_info.investment_stage companyInvestmentStage," +
            "company_info.bank_add_address bankAddAddress," +
            "company_info.credit_code creditCode," +
            "company_info.admin_name adminName," +
            "company_info.financing_amount financingAmount," +
            "equity_audit.* FROM equity_audit LEFT JOIN company_info " +
            "ON equity_audit.company_id = company_info.company_id where equity_audit.id=${id} ")
    Map getSpecialistLoanApplicationDetail(@Param("id") Long id);

}
