package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.AuthorizationKeys;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorizationKeysMapper {

    @Results(id = "AuthorizationKeysResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "keyCode", column = "key_code"),
            @Result(property = "dataLabelTypes", column = "data_label_types"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "loanAppliId", column = "loan_appli_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "createrUser", column = "creater_user")
    })
    @Select("select * from authorization_keys where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<AuthorizationKeys> listByConditions(@Param("wheres") String wheres,
                                             @Param("start") int start,
                                             @Param("end") int end,
                                             @Param("orderCloumn")String orderCloumn,
                                             @Param("orderDesc")String orderDesc);
    @Select("select count(1) from authorization_keys where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from authorization_keys where id=${id}")
    @ResultMap(value = "AuthorizationKeysResultMap")
    AuthorizationKeys getAuthorizationKeysById(@Param("id") Long id);
}
