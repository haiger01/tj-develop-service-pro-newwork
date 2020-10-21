package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.ProductRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductRecordMapper {
    @Results(id = "ProductRecordResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productType", column = "product_type"),
            @Result(property = "checkType", column = "check_type"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "checkMsg", column = "check_msg")

    })
    @Select("select * from product_record where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<ProductRecord> listByConditions(@Param("wheres") String wheres,
                                         @Param("start") int start,
                                         @Param("end") int end,
                                         @Param("orderCloumn")String orderCloumn,
                                         @Param("orderDesc")String orderDesc);
    @Select("select count(1) from product_record where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from product_record where id=${id}")
    @ResultMap(value = "ProductRecordResultMap")
    ProductRecord getProductRecordById(@Param("id") Long id);
}
