package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.SilverTaxInteraction;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface SilverTaxInteractionMapper {

    @Results(id = "SilverTaxInteractionResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "serviceNumber", column = "service_number"),
            @Result(property = "cumulativeCredit", column = "cumulative_credit"),
            @Result(property = "totalCumulativeCreditMoney", column = "total_cumulative_credit_money"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @Select("select * from silver_tax_interaction where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<SilverTaxInteraction> listByConditions(@Param("wheres") String wheres,
                                                @Param("start") int start,
                                                @Param("end") int end,
                                                @Param("orderCloumn")String orderCloumn,
                                                @Param("orderDesc")String orderDesc);
    @Select("select count(1) from silver_tax_interaction where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from silver_tax_interaction where id=${id}")
    @ResultMap(value = "SilverTaxInteractionResultMap")
    SilverTaxInteraction getSilverTaxInteractionById(@Param("id") Long id);

}
