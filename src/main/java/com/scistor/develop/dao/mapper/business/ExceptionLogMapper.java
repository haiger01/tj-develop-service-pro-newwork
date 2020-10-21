package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.ExceptionLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExceptionLogMapper {

    @Results(id = "ExceptionLogResultMap", value = {
            @Result(property = "excId", column = "exc_id"),
            @Result(property = "excRequParam", column = "exc_requ_param"),
            @Result(property = "excName", column = "exc_name"),
            @Result(property = "excMessage", column = "exc_message"),
            @Result(property = "operUserId", column = "oper_user_id"),
            @Result(property = "operUserName", column = "oper_user_name"),
            @Result(property = "operMethod", column = "oper_method"),
            @Result(property = "operUri", column = "oper_uri"),
            @Result(property = "operIp", column = "oper_ip"),
            @Result(property = "operCreateTime", column = "oper_create_time"),
            @Result(property = "decrExcRequParam", column = "decr_exc_requ_param")

    })
    @Select("select * from exception_log where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<ExceptionLog> listByConditions(@Param("wheres") String wheres,
                                        @Param("start") int start,
                                        @Param("end") int end,
                                        @Param("orderCloumn")String orderCloumn,
                                        @Param("orderDesc")String orderDesc);
    @Select("select count(1) from exception_log where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from exception_log where id=${id}")
    @ResultMap(value = "ExceptionLogResultMap")
    ExceptionLog getExceptionLogById(@Param("id") Long id);
}
