package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.ProjectTeamDirectoryDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ProjectTeamDirectoryDetailsMapper {

    @Results(id = "ProjectTeamDirectoryDetailsResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "numberDocking", column = "number_docking"),
            @Result(property = "dockingDetails", column = "docking_details"),
            @Result(property = "ptdId", column = "ptd_id")
    })
    @Select("select * from project_team_directory_details where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}")
    List<ProjectTeamDirectoryDetails> listByConditions(@Param("wheres") String wheres,
                                                       @Param("start") int start,
                                                       @Param("end") int end,
                                                       @Param("orderCloumn")String orderCloumn,
                                                       @Param("orderDesc")String orderDesc);
    @Select("select count(1) from project_team_directory_details where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);
    @Select("select * from project_team_directory_details where id=${id}")
    @ResultMap(value = "ProjectTeamDirectoryDetailsResultMap")
    ProjectTeamDirectoryDetails getProjectTeamDirectoryDetailsById(@Param("id") Long id);

}
