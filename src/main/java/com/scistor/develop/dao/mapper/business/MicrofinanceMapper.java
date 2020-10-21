package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Microfinance;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MicrofinanceMapper {
    @Results(id = "MicrofinanceResultMap", value = {
            @Result(property = "tel", column = "tel"),
            @Result(property = "loanChanne", column = "loan_channe"),
            @Result(property = "guaranteeMode", column = "guarantee_mode"),
            @Result(property = "proName", column = "pro_name"),
            @Result(property = "goodsType", column = "goods_type"),
            @Result(property = "characteristic", column = "characteristic"),
            @Result(property = "appCustomer", column = "app_customer"),
            @Result(property = "appCondition", column = "app_condition"),
            @Result(property = "checkType", column = "check_type"),
            @Result(property = "checkUser", column = "check_user"),
            @Result(property = "checkMsg", column = "check_msg"),
            @Result(property = "checkTime", column = "check_time"),
            @Result(property = "type", column = "type"),
            @Result(property = "id", column = "id"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "fixedTel", column = "fixed_tel"),
            @Result(property = "success", column = "success"),
            @Result(property = "fail", column = "fail"),
            @Result(property = "applnum", column = "applnum"),
            @Result(property = "loanTimeLimitMax", column = "loan_time_limit_max"),
            @Result(property = "loanTimeLimitMin", column = "loan_time_limit_min"),
            @Result(property = "loanAmountMax", column = "loan_amount_max"),
            @Result(property = "loanAmountMin", column = "loan_amount_min"),
            @Result(property = "loanRateMax", column = "loan_rate_max"),
            @Result(property = "loanRateMin", column = "loan_rate_min"),
            @Result(property = "loanRate", column = "loan_rate"),
            @Result(property = "loanRateStr", column = "loan_rate_str"),
            @Result(property = "loanAmountStr", column = "loan_amount_str"),
            @Result(property = "loanTimeLimitStr", column = "loan_time_limit_str"),
            @Result(property = "proInt", column = "pro_int"),
            @Result(property = "addressCustomerNeeds", column = "address_customer_needs"),
            @Result(property = "modeOfRepayment", column = "mode_of_repayment"),
            @Result(property = "videoUrl", column = "video_url"),
            @Result(property = "videoCoverUrl", column = "video_cover_url"),
            @Result(property = "proContactPerson", column = "pro_contact_person"),
            @Result(property = "proContactPersonPhone", column = "pro_contact_person_phone")

    })
    @Select("select * from microfinance where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Microfinance> listByConditions(@Param("wheres") String wheres,
                                        @Param("start") int start,
                                        @Param("end") int end,
                                        @Param("orderCloumn")String orderCloumn,
                                        @Param("orderDesc")String orderDesc);


    @Select("select ${caseSql} microfinance.* from microfinance where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Map> getListByConditions(@Param("caseSql") String caseSql,
                                  @Param("wheres") String wheres,
                                  @Param("start") int start,
                                  @Param("end") int end,
                                  @Param("orderCloumn") String orderCloumn,
                                  @Param("orderDesc") String orderDesc);


    @Select("select count(1) from microfinance where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);


    @Select("select * from microfinance where id=${id}")
    @ResultMap(value = "MicrofinanceResultMap")
    Microfinance getMicrofinanceById(@Param("id") Long id);

}
