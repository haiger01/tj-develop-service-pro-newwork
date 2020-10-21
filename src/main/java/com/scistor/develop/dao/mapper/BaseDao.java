package com.scistor.develop.dao.mapper;


import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseDao {

    @Insert("<script>" +
            " insert into  ${table}  (${keys}) " +
            "   values" +
            "       <foreach collection='list' item='str' open='(' separator=',' close=')'>" +
            "           #{str}" +
            "       </foreach>" +
            "    " +
            "</script>")
    int insert(@Param("table") String table, @Param("keys") String keys, @Param("list") List<Object> list);

    @Update("UPDATE ${table} set ${upList} ${where}")
    int update(@Param("table") String table, @Param("upList") String upList, @Param("where") String where);

    @Delete("delete ${table} where id=#{id}")
    int deleteById(@Param("table") String table, @Param("id") Long id);

    @Delete("delete ${table} where ${where} ")
    int deleteWhere(@Param("table") String table, @Param("where") String where);

    @Update("update ${table} set  ${cloum}= ${cloum}+1 where id=${id}")
    int addCloumById(@Param("table") String table, @Param("cloum") String cloum, @Param("id") Long id);

    @Update("update ${table} set  ${cloum}= ${cloum}+1 where usercode=${usercode}")
    int addCloumByUsercode(@Param("table") String table, @Param("cloum") String cloum, @Param("usercode") String usercode);

    @Update("update ${table} set  ${cloum}= ${cloum}-1 where id=${id} and ${cloum}>0")
    int addCloumANumById(@Param("table") String table, @Param("cloum") String cloum, @Param("id") Long id);

    @Update("update ${table} set  ${cloum}= ${cloum}+${numData} where ${key}=#{data}")
    void addCloumANumByKey(@Param("table") String table, @Param("cloum") String cloum,
                           @Param("key") String key, @Param("data") String data, @Param("numData") Long numData);

    @Select("${sql}")
    Long longBySql(@Param("sql") String Sql);

    @Select("${sql}")
    List<Map<String, Object>> mapBySql(@Param("sql") String Sql);

    @Select("${sql}")
    Map<String, Object> mapBySqlModel(@Param("sql") String Sql);
}