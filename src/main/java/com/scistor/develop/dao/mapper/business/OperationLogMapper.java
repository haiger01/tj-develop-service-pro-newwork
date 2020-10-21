package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.OperationLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OperationLogMapper {
    @Results(id = "OperationLogResultMap", value = {
            @Result(property = "operId", column = "oper_id"),
            @Result(property = "operModul", column = "oper_modul"),
            @Result(property = "operType", column = "oper_type"),
            @Result(property = "operDesc", column = "oper_desc"),
            @Result(property = "operRequParam", column = "oper_requ_param"),
            @Result(property = "operRespParam", column = "oper_resp_param"),
            @Result(property = "operUserId", column = "oper_user_id"),
            @Result(property = "operUserName", column = "oper_user_name"),
            @Result(property = "operMethod", column = "oper_method"),
            @Result(property = "operUri", column = "oper_uri"),
            @Result(property = "operIp", column = "oper_ip"),
            @Result(property = "operCreateTime", column = "oper_create_time"),
            @Result(property = "decrOperRequParam", column = "decr_oper_requ_param")
    })
    @Select("select * from operation_log where 1=1 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<OperationLog> listByConditions(@Param("wheres") String wheres,
                                        @Param("start") int start,
                                        @Param("end") int end,
                                        @Param("orderCloumn")String orderCloumn,
                                        @Param("orderDesc")String orderDesc);
    @Select("select count(1) from operation_log where 1=1 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from operation_log where id=${id}")
    @ResultMap(value = "OperationLogResultMap")
    OperationLog getOperationLogById(@Param("id") Long id);
}
