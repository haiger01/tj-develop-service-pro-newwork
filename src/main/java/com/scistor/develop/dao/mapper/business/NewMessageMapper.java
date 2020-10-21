package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.NewMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewMessageMapper {
    @Results(id = "NewMessageResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"), @Result(property = "messageType", column = "message_type"),
            @Result(property = "tittle", column = "tittle"),
            @Result(property = "message", column = "message"),
            @Result(property = "type", column = "type"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "toCompanyId", column = "to_company_id")
    })
    @Select("select * from new_message where delete_flag = 0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<NewMessage> listByConditions(@Param("wheres") String wheres,
                                      @Param("start") int start,
                                      @Param("end") int end,
                                      @Param("orderCloumn") String orderCloumn,
                                      @Param("orderDesc") String orderDesc);

    @Select("select count(1) from new_message where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from new_message where id=${id}")
    @ResultMap(value = "NewMessageResultMap")
    NewMessage getNewMessageById(@Param("id") Long id);
}
