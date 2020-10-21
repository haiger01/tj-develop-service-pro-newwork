package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Authorize;
import com.scistor.develop.data.entity.Guarantee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GuaranteeMapper {
    @Results(id = "GuaranteeResultMap", value = {
            @Result(property = "checkType", column = "check_type"),
            @Result(property = "checkUser", column = "check_user"),
            @Result(property = "checkMsg", column = "check_msg"),
            @Result(property = "checkTime", column = "check_time"),
            @Result(property = "type", column = "type"),
            @Result(property = "policyFlag", column = "policy_flag"),
            @Result(property = "rmbFlag", column = "rmb_flag"),
            @Result(property = "region", column = "region"),
            @Result(property = "goodsClass", column = "goods_class"),
            @Result(property = "goodsTrait", column = "goods_trait"),
            @Result(property = "netDirectFlag", column = "net_direct_flag"),
            @Result(property = "generalFlag", column = "general_flag"),
            @Result(property = "goodsCategory", column = "goods_category"),
            @Result(property = "conditions", column = "conditions"),
            @Result(property = "material", column = "material"),
            @Result(property = "proName", column = "pro_name"),
            @Result(property = "success", column = "success"),
            @Result(property = "fail", column = "fail"),
            @Result(property = "applnum", column = "applnum"),
            @Result(property = "guaranteeRate", column = "guarantee_rate"),
            @Result(property = "guaranteeRateMax", column = "guarantee_rate_max"),
            @Result(property = "guaranteeRateMin", column = "guarantee_rate_min"),
            @Result(property = "guaranteeLineMax", column = "guarantee_line_max"),
            @Result(property = "guaranteeLineMin", column = "guarantee_line_min"),
            @Result(property = "guaranteeDateMax", column = "guarantee_date_max"),
            @Result(property = "guaranteeDateMin", column = "guarantee_date_min"),
            @Result(property = "id", column = "id"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "fixedTel", column = "fixed_tel"),
            @Result(property = "guaranteeMode", column = "guarantee_mode"),
            @Result(property = "guaranteeRateStr", column = "guarantee_rate_str"),
            @Result(property = "guaranteeLineStr", column = "guarantee_line_str"),
            @Result(property = "guaranteeDateStr", column = "guarantee_date_str"),
            @Result(property = "videoUrl", column = "video_url"),
            @Result(property = "videoCoverUrl", column = "video_cover_url"),
            @Result(property = "proContactPerson", column = "pro_contact_person"),
            @Result(property = "proContactPersonPhone", column = "pro_contact_person_phone")
    })
    @Select("select * from guarantee where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Guarantee> listByConditions(@Param("wheres") String wheres,
                                     @Param("start") int start,
                                     @Param("end") int end,
                                     @Param("orderCloumn") String orderCloumn,
                                     @Param("orderDesc") String orderDesc);

    @Select("select ${caseSql} guarantee.* from guarantee where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Map> getListByConditions(@Param("caseSql") String caseSql,
                                  @Param("wheres") String wheres,
                                  @Param("start") int start,
                                  @Param("end") int end,
                                  @Param("orderCloumn") String orderCloumn,
                                  @Param("orderDesc") String orderDesc);


    @Select("select count(1) from guarantee where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from guarantee where id=${id}")
    @ResultMap(value = "GuaranteeResultMap")
    Guarantee getGuaranteeById(@Param("id") Long id);

}
