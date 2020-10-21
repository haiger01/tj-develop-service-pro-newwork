package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.CompanyAuditOpinion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyAuditOpinionMapper {
    @Results(id = "CompanyAuditOpinionResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "checkMsg", column = "check_msg"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "companyInfoId", column = "company_info_id"),
            @Result(property = "createrUser", column = "creater_user")

    })
    @Select("select * from company_audit_opinion where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<CompanyAuditOpinion> listByConditions(@Param("wheres") String wheres,
                                               @Param("start") int start,
                                               @Param("end") int end,
                                               @Param("orderCloumn")String orderCloumn,
                                               @Param("orderDesc")String orderDesc);
    @Select("select count(1) from company_audit_opinion where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from company_audit_opinion where id=${id}")
    @ResultMap(value = "CompanyAuditOpinionResultMap")
    CompanyAuditOpinion getCompanyAuditOpinionById(@Param("id") Long id);
}
