package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Insurance;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface InsuranceMapper {
    @Results(id = "InsuranceResultMap", value = {
            @Result(property = "tel", column = "tel"),
            @Result(property = "proName", column = "pro_name"),
            @Result(property = "proInt", column = "pro_int"),
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
            @Result(property = "loanTimeLimitMax", column = "loan_time_limit_max"),
            @Result(property = "loanTimeLimitMin", column = "loan_time_limit_min"),
            @Result(property = "loanRateMax", column = "loan_rate_max"),
            @Result(property = "loanRateMin", column = "loan_rate_min"),
            @Result(property = "loanRate", column = "loan_rate"),
            @Result(property = "loanRateStr", column = "loan_rate_str"),
            @Result(property = "loanAmountStr", column = "loan_amount_str"),
            @Result(property = "loanTimeLimitStr", column = "loan_time_limit_str"),
            @Result(property = "proContactPersonPhone", column = "pro_contact_person_phone"),
            @Result(property = "proContactPerson", column = "pro_contact_person"),
            @Result(property = "productAccessoriesUrl", column = "product_accessories_url")


    })
    @Select("select * from insurance where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Insurance> listByConditions(@Param("wheres") String wheres,
                                     @Param("start") int start,
                                     @Param("end") int end,
                                     @Param("orderCloumn") String orderCloumn,
                                     @Param("orderDesc") String orderDesc);

    @Select("select COUNT(t.id) applnum,insurance.* from insurance LEFT JOIN (select id,insurance_id FROM apply_insurance WHERE delete_flag = 0) t ON t.insurance_id = insurance.id where delete_flag=0 and ${wheres} GROUP BY id order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Map> getListByConditions(@Param("wheres") String wheres,
                                  @Param("start") int start,
                                  @Param("end") int end,
                                  @Param("orderCloumn") String orderCloumn,
                                  @Param("orderDesc") String orderDesc);

    @Select("select count(1) from insurance where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from insurance where id=${id}")
    @ResultMap(value = "InsuranceResultMap")
    Insurance getInsuranceById(@Param("id") Long id);


}
