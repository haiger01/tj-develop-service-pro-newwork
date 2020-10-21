package com.scistor.develop.dao.mapper.business;

import com.scistor.develop.data.entity.Dictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictionaryMapper {
    @Results(id = "DictionaryResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"),
            @Result(property = "name", column = "name"),
            @Result(property = "type", column = "type"),
            @Result(property = "strType", column = "str_type"),
            @Result(property = "value", column = "value"),
            @Result(property = "level", column = "level"),
            @Result(property = "usercode", column = "usercode")
    })
    @Select("select * from dictionary where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<Dictionary> listByConditions(@Param("wheres") String wheres,
                                      @Param("start") int start,
                                      @Param("end") int end,
                                      @Param("orderCloumn") String orderCloumn,
                                      @Param("orderDesc") String orderDesc);

    @Select("select count(1) from dictionary where delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from dictionary where id=${id}")
    @ResultMap(value = "DictionaryResultMap")
    Dictionary getDictionaryById(@Param("id") Long id);


    @Select("SELECT count(1) FROM company_info ")
    long companyNum();

    @Select("SELECT count(DISTINCT(company_id)) FROM institutions WHERE company_type !=4")
    long bankNum();

    @Select("SELECT SUM(t.c) FROM (SELECT count(1) c FROM financial_goods WHERE delete_flag = 0 AND type = 1" +
            " UNION SELECT count(1) c FROM financing_lease  WHERE delete_flag = 0 AND type = 1" +
            " UNION SELECT count(1) c FROM guarantee  WHERE delete_flag = 0 AND type = 1" +
            " UNION SELECT count(1) c FROM microfinance  WHERE delete_flag = 0 AND type = 1" +
            " UNION SELECT count(1) c FROM insurance  WHERE delete_flag = 0 AND type = 1) t")
    long proGoodsNum();

    @Select("SELECT count(1) FROM loan_demand")
    long loanDemandNum();

    @Select("SELECT sum(loan_amount) FROM loan_demand")
    long loanDemandAmount();

    @Select("SELECT count(DISTINCT(company_id)) FROM loan_application where check_type='7.5' or check_type='8'")
    long loanDemandCompanyNum();

    @Select("SELECT sum(loan_num) FROM loan_application where check_type='7.5' or check_type='8' ")
    long secussAmount();


}
