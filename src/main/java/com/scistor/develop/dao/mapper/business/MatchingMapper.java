package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Matching;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MatchingMapper {
    @Results(id = "MatchingResultMap", value = {
        @Result(property = "companyName", column = "company_name"),
        @Result(property = "createruser", column = "creater_user"),
        @Result(property = "adminTel", column = "admin_tel"),
        @Result(property = "applynum", column = "applynum"),
        @Result(property = "applydate", column = "applydate"),
        @Result(property = "guaranteeMode", column = "guarantee_mode"),
        @Result(property = "loanType", column = "loan_type"),
        @Result(property = "purpose", column = "purpose"),
        @Result(property = "damandCreaterTime", column = "damand_creater_time"),
        @Result(property = "proName", column = "pro_name"),
        @Result(property = "rateofcredit", column = "rateofcredit"),
        @Result(property = "acceptUser", column = "accept_user"),
        @Result(property = "acceptTel", column = "accept_tel"),
        @Result(property = "acceptMesage", column = "accept_mesage"),
        @Result(property = "investigationTime", column = "investigation_time"),
        @Result(property = "investigationMsg", column = "investigation_msg"),
        @Result(property = "collectionTime", column = "collection_time"),
        @Result(property = "collectionMsg", column = "collection_msg"),
        @Result(property = "bankopenTime", column = "bankopen_time"),
        @Result(property = "bankopenMsg", column = "bankopen_msg"),
        @Result(property = "reportTime", column = "report_time"),
        @Result(property = "creditTime", column = "credit_time"),
        @Result(property = "lineofcredit", column = "lineofcredit"),
        @Result(property = "dateofcredit", column = "dateofcredit"),
        @Result(property = "checkMesage", column = "check_mesage"),
        @Result(property = "loanTime", column = "loan_time"),
        @Result(property = "loannum", column = "loan_num"),
            @Result(property = "appid", column = "appid"),
            @Result(property = "loanChanne", column = "loan_channe"),
            @Result(property = "acceptCompanyName", column = "accept_company_name"),
            @Result(property = "guaDate", column = "gua_date"),
            @Result(property = "guaGuaranteeMode", column = "gua_guarantee_mode"),
            @Result(property = "guaRate", column = "gua_rate"),
            @Result(property = "acceptCreate", column = "accept_create"),
            @Result(property = "preliminaryResults", column = "preliminary_results"),
            @Result(property = "fieldInvestigationResults", column = "field_investigation_results"),
            @Result(property = "dataCollectionResults", column = "data_collection_results"),
            @Result(property = "bankAccountOpeningResult", column = "bank_account_opening_result"),
            @Result(property = "bankAuditResults", column = "bank_audit_results")
})
    @Select("${sql}")
    Matching listByConditions(@Param("sql") String sql);

    @Select("${sql}")
    public Map applyDetails(@Param("sql") String sql);


    @Select("${sql}")
    public List<Map> applyDetailsList(@Param("sql") String sql);

    @Select("select count(1) from matching where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from matching where id=${id}")
    @ResultMap(value = "MatchingResultMap")
    Matching getMatchingById(@Param("id") Long id);

}
