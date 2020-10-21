package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Authorize;
import com.scistor.develop.data.entity.CompanyInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorizeMapper {
    @Results(id = "AuthorizeResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "type", column = "type"),
            @Result(property = "code", column = "code"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "authorizeType", column = "authorize_type"),
            @Result(property = "authorizeCompanyId", column = "authorize_company_id"),
            @Result(property = "appid", column = "appid")

    })
    @Select("select * from authorize where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end} ")
    List<Authorize> listByConditions(@Param("wheres") String wheres,
                                     @Param("start") int start,
                                     @Param("end") int end,
                                     @Param("orderCloumn")String orderCloumn,
                                     @Param("orderDesc")String orderDesc);

    @Select("select count(1) from authorize where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from authorize where id=${id}")
    @ResultMap(value = "AuthorizeResultMap")
    Authorize getAuthorizeById(@Param("id") Long id);

    @Select("select * from authorize where ${wheres}")
    @ResultMap(value = "AuthorizeResultMap")
    Authorize getByConditions(@Param("wheres") String wheres);

}
