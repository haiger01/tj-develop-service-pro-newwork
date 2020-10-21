package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Equity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;


import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface EquityMapper {

    @Results(id = "EquityResultMap", value = {
            @Result(property = "tel", column = "tel"),
            @Result(property = "proName", column = "pro_name"),
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
            @Result(property = "success", column = "success"),
            @Result(property = "fail", column = "fail"),
            @Result(property = "applnum", column = "applnum"),
            @Result(property = "remarks", column = "remarks"),
            @Result(property = "investmentIndustry", column = "investment_industry"),
            @Result(property = "investmentStage", column = "investment_stage"),
            @Result(property = "videoUrl", column = "video_url"),
            @Result(property = "videoCoverUrl", column = "video_cover_url"),
            @Result(property = "proContactPerson", column = "pro_contact_person"),
            @Result(property = "proContactPersonPhone", column = "pro_contact_person_phone")
    })
    @Select("select * from equity where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Equity> listByConditions(@Param("wheres") String wheres,
                                  @Param("start") int start,
                                  @Param("end") int end,
                                  @Param("orderCloumn")String orderCloumn,
                                  @Param("orderDesc")String orderDesc);
    @Select("select count(1) from equity where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from equity where delete_flag=0 and id=${id}")
    @ResultMap(value = "EquityResultMap")
    Equity getEquityById(@Param("id") Long id);

}
