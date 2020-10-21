package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Institutions;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstitutionsMapper {
    @Results(id = "InstitutionsResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "contacts", column = "contacts"),
            @Result(property = "contactsPhone", column = "contacts_phone"),
            @Result(property = "logoUrl", column = "logo_url"),
            @Result(property = "cooperationCompanyLogoUrl", column = "cooperation_company_logo_url")

    })
    @Select("select * from institutions where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<Institutions> listByConditions(@Param("wheres") String wheres,
                                        @Param("start") int start,
                                        @Param("end") int end,
                                        @Param("orderCloumn")String orderCloumn,
                                        @Param("orderDesc")String orderDesc);


    @Select("select company_id companyId from institutions where company_type = 3")
    List<String> getInstitutionsCompantIdList();

    @Select("select count(1) from institutions where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from institutions where id=${id}")
    @ResultMap(value = "InstitutionsResultMap")
    Institutions getInstitutionsById(@Param("id") Long id);

    @Select("select * from institutions where usercode=#{usercode}")
    @ResultMap(value = "InstitutionsResultMap")
    Institutions getFinancialCompanyDetails(@Param("usercode") String usercode);

    @Select("select * from institutions where company_id=#{companyId}")
    @ResultMap(value = "InstitutionsResultMap")
    Institutions getFinancialCompanyDetailsByCompanyId(@Param("companyId") String companyId);

}
