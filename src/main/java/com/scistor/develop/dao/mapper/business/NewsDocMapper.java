package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.NewsDoc;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsDocMapper {
    @Results(id = "NewsDocResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "tittle", column = "tittle"),
            @Result(property = "doc", column = "doc"),
            @Result(property = "url", column = "url"),
            @Result(property = "docType", column = "doc_type"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "watchNumber", column = "watch_number"),

            @Result(property = "accessoryUrl", column = "accessory_url"),
            @Result(property = "cultivateTime", column = "cultivate_time"),
            @Result(property = "cultivateAccept", column = "cultivate_accept"),
            @Result(property = "cultivateType", column = "cultivate_type"),

            @Result(property = "num", column = "num")
    })
    @Select("select " +
            " *," +
            " ( SELECT COUNT( id ) FROM news_apply WHERE quit_flag=0 AND news_id = news_doc.id  ) num " +
            " from news_doc where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<NewsDoc> listByConditions(@Param("wheres") String wheres,
                                   @Param("start") int start,
                                   @Param("end") int end,
                                   @Param("orderCloumn")String orderCloumn,
                                   @Param("orderDesc")String orderDesc);

    @Select("select count(1) from news_doc where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from news_doc where id=${id}")
    @ResultMap(value = "NewsDocResultMap")
    NewsDoc getNewsDocById(@Param("id") Long id);
}
