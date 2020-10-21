package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.NewsApply;
import com.scistor.develop.data.entity.NewsDoc;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsApplyMapper {
    @Results(id = "NewsApplyResultMap" , value = {
            @Result(property = "id", column = "id"),
            @Result(property = "newsId", column = "news_id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "applyName", column = "apply_name"),
            @Result(property = "applyPhone", column = "apply_phone"),
            @Result(property = "applyCompany", column = "apply_company"),
            @Result(property = "conferenceNumber", column = "conference_number"),
            @Result(property = "conferenceName", column = "conference_name"),
            @Result(property = "cultivateState", column = "cultivate_state"),
            @Result(property = "feedbackType", column = "feedback_type"),
            @Result(property = "feedbackIdea", column = "feedback_idea"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "watchFlag", column = "watch_flag"),
            @Result(property = "quitFlag", column = "quit_flag"),

            @Result(property = "tittle", column = "tittle"),
            @Result(property = "cultivateTime", column = "cultivate_time")
    })

    @Select("SELECT " +
            "   ndc.tittle,ndc.cultivate_time,  " +
            "   nay.* " +
            "FROM news_apply nay " +
            "LEFT JOIN news_doc ndc ON ndc.id = nay.news_id " +
            "WHERE ndc.delete_flag = 0 " +
            "AND nay.usercode = #{userCode} " +
            "ORDER BY ndc.update_time desc limit #{start},#{end}")
    List<NewsApply> listByConditions(@Param("userCode") String userCode,
                                   @Param("start") int start,
                                   @Param("end") int end);

    @Select("SELECT " +
            "   COUNT(nay.id) " +
            "FROM news_apply nay " +
            "LEFT JOIN news_doc ndc ON ndc.id = nay.news_id " +
            "WHERE ndc.delete_flag = 0 " +
            "AND nay.usercode = #{userCode}")
    long countByConditions(@Param("userCode") String userCode);

    @Select("select * from news_apply where id=${id}")
    @ResultMap(value = "NewsApplyResultMap")
    NewsApply getNewsApplyById(@Param("id") Long id);

    @Select("select * from news_apply where news_id=${id}")
    @ResultMap(value = "NewsApplyResultMap")
    List<NewsApply>  getNewsApplyListByNewsId(@Param("id") Long id);

    @Select("SELECT * FROM news_apply WHERE news_id = ${newsId} AND usercode = #{userCode} LIMIT 0,1")
    @ResultMap(value = "NewsApplyResultMap")
    NewsApply  getNewsApplyDaByNewsIdAndUserCode(@Param("newsId") Long newsId,@Param("userCode") String userCode);

}
