package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.ProjectTeamDirectory;
import com.scistor.develop.data.entity.ProjectTeamDirectoryDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectTeamDirectoryMapper {

    @Results(id = "ProjectTeamDirectoryResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "createrCompanyId", column = "creater_company_id"),
            @Result(property = "createrCompanyName", column = "creater_company_name"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrUsercode", column = "creater_usercode"),
            @Result(property = "createrCompanyType", column = "creater_company_type"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyContacts", column = "company_contacts"),
            @Result(property = "companyContactsPhone", column = "company_contacts_phone"),
            @Result(property = "companyRegistrationFlag", column = "company_registration_flag"),
            @Result(property = "specialistName", column = "specialist_name"),
            @Result(property = "specialistId", column = "specialist_id"),
            @Result(property = "specialistContactsPhone", column = "specialist_contacts_phone"),
            @Result(property = "specialistRegistrationFlag", column = "specialist_registration_flag")
    })
    @Select("select * from project_team_directory where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<ProjectTeamDirectory> listByConditions(@Param("wheres") String wheres,
                                                @Param("start") int start,
                                                @Param("end") int end,
                                                @Param("orderCloumn")String orderCloumn,
                                                @Param("orderDesc")String orderDesc);
    @Select("select count(1) from project_team_directory where delete_flag=0 AND ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from project_team_directory where id=${id}")
    @ResultMap(value = "ProjectTeamDirectoryResultMap")
    ProjectTeamDirectory getProjectTeamDirectoryById(@Param("id") Long id);

    @Select("select count(1) from project_team_directory where delete_flag=0 and company_name = #{companyName}")
    long projectCompanyCount(@Param("companyName") String companyName);

    @Select("select count(1) from project_team_directory where delete_flag=0 and specialist_name = #{specialistName}")
    long projectSpecialistCount(@Param("specialistName") String specialistName);

    @Select("select * from project_team_directory where delete_flag=0 and company_name =#{companyName}")
    @ResultMap(value = "ProjectTeamDirectoryResultMap")
    ProjectTeamDirectory getProjectTeamDirectoryDetailsByCompanyName(@Param("companyName") String companyName);

    @Select("select GROUP_CONCAT(company_info.company_id) companyIds from project_team_directory LEFT JOIN company_info" +
            " on project_team_directory.company_name = company_info.company_name where specialist_name = #{specialistName} GROUP BY specialist_Name")
    String getProjectTeamDirectoryBySpecialistName(@Param("specialistName") String specialistName);
}
