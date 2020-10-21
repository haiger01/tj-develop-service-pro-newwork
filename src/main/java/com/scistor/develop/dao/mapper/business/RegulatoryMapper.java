package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Regulatory;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface RegulatoryMapper {
    @Results(id = "RegulatoryResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dataTime", column = "data_time"),
            @Result(property = "code", column = "code"),
            @Result(property = "describe", column = "describe")
    })
    @Select("select * from regulatory where id > 0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Regulatory> listByConditions(@Param("wheres") String wheres,
                                      @Param("start") int start,
                                      @Param("end") int end,
                                      @Param("orderCloumn")String orderCloumn,
                                      @Param("orderDesc")String orderDesc);
    @Select("select count(1) from regulatory where id > 0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from regulatory where id=${id}")
    @ResultMap(value = "RegulatoryResultMap")
    Regulatory getRegulatoryById(@Param("id") Long id);
}
