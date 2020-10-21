package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.AuditOpinion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuditOpinionMapper {
    @Results(id = "AuditOpinionResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "opinion", column = "opinion"),
            @Result(property = "loanId", column = "loan_id")
    })
    @Select("select * from audit_opinion where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<AuditOpinion> listByConditions(@Param("wheres") String wheres,
                                        @Param("start") int start,
                                        @Param("end") int end,
                                        @Param("orderCloumn") String orderCloumn,
                                        @Param("orderDesc") String orderDesc);
    @Select("select count(1) from audit_opinion where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from audit_opinion where id=${id}")
    @ResultMap(value = "AuditOpinionResultMap")
    AuditOpinion getAuditOpinionById(@Param("id") Long id);
}
