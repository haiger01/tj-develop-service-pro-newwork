package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.LoanDemand;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoanDemandMapper {

    @Results(id = "LoanDemandResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "loanChanne", column = "loan_channe"),
            @Result(property = "loanTimeLimit", column = "loan_time_limit"),
            @Result(property = "guaranteeMode", column = "guarantee_mode"),
            @Result(property = "loanAmount", column = "loan_amount"),
            @Result(property = "num", column = "num"),
            @Result(property = "applicant", column = "applicant"),
            @Result(property = "orgNumber", column = "org_number"),
            @Result(property = "companyAddress", column = "company_address"),
            @Result(property = "purpose", column = "purpose"),
            @Result(property = "type", column = "type"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "focusNum", column = "focus_num"),
            @Result(property = "acceptAmount", column = "acceptAmount"),
            @Result(property = "nameIntendedBank", column = "name_intended_bank")
    })
    @Select("select * from loan_demand where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<LoanDemand> listByConditions(@Param("wheres") String wheres,
                                      @Param("start") int start,
                                      @Param("end") int end,
                                      @Param("orderCloumn") String orderCloumn,
                                      @Param("orderDesc") String orderDesc);

    @Select("select count(1) from loan_demand where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select " +
            " (SELECT count( 1 ) FROM loan_application l3 WHERE l3.appid = loan_demand.id AND l3.delete_flag = 0 AND check_type in(2,5,4.01,5.1,5.2,5.3,6,7.5,8) ) AS acceptAmount, " +
            " loan_demand.* from loan_demand where id=${id}")
    @ResultMap(value = "LoanDemandResultMap")
    LoanDemand getLoanDemandById(@Param("id") Long id);

    @Select("SELECT " +
            " ( SELECT count( 1 ) FROM loan_demand l2 WHERE l2.usercode = loan_demand.usercode AND l2.delete_flag = 0  group by l2.usercode) AS demandnum, " +
            " loan_demand.*  " +
            "FROM " +
            " loan_demand  " +
            "WHERE loan_demand.delete_flag=0 and ${wheres} order by loan_demand.${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<Map> listByConditionsLeftJoinDemandAndLapp(@Param("wheres") String wheres,
                                                    @Param("start") int start,
                                                    @Param("end") int end,
                                                    @Param("orderCloumn") String orderCloumn,
                                                    @Param("orderDesc") String orderDesc);

    @Select("SELECT " +
            " ( SELECT count( 1 ) FROM loan_demand l2 WHERE l2.usercode = loan_demand.usercode AND l2.delete_flag = 0 group by l2.usercode) AS demandnum, " +
            " (SELECT count( 1 ) FROM loan_application l3 WHERE l3.appid = loan_demand.id AND l3.delete_flag = 0 AND check_type in(2,5,4.01,5.1,5.2,5.3,6,7.5,8) ) AS acceptAmount, " +
            " loan_demand.*  " +
            " FROM " +
            " loan_demand  " +
            " WHERE loan_demand.delete_flag=0 " +
            " and ${wheres} order by loan_demand.${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<Map> demandListByConditions(@Param("wheres") String wheres,
                                                    @Param("start") int start,
                                                    @Param("end") int end,
                                                    @Param("orderCloumn") String orderCloumn,
                                                    @Param("orderDesc") String orderDesc);


    @Select("select count(1) from loan_demand where delete_flag=0 and name_intended_bank IS NULL and company_id = #{companyId}")
    long getAnyNumberByCompanyId(@Param("companyId") String companyId);

}

