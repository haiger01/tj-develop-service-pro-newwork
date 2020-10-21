package com.scistor.develop.dao.mapper.business;


import com.scistor.develop.data.entity.FocusNeeds;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FocusNeedsMapper {
    @Results(id = "FocusNeedsResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "companyType", column = "company_type"),
            @Result(property = "createrUser", column = "creater_user"),
            @Result(property = "createrTime", column = "creater_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "deleteFlag", column = "delete_flag"), @Result(property = "demandId", column = "demand_id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "goodsName", column = "goods_name"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "usercode", column = "usercode"),
            @Result(property = "focusType", column = "focus_type"),
            @Result(property = "focusCompanyId", column = "focus_company_id")
    })
    @Select("select * from focus_needs where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<FocusNeeds> listByConditions(@Param("wheres") String wheres,
                                      @Param("start") int start,
                                      @Param("end") int end,
                                      @Param("orderCloumn") String orderCloumn,
                                      @Param("orderDesc") String orderDesc);

    @Select("select count(1) from focus_needs " +
            " LEFT JOIN loan_demand ON focus_needs.demand_id = loan_demand.id" +
            " where focus_needs.delete_flag=0 and loan_demand.delete_flag=0 and ${wheres} ")
    long countByConditions(@Param("wheres") String wheres);

    @Select("select * from focus_needs where id=${id}")
    @ResultMap(value = "FocusNeedsResultMap")
    FocusNeeds getFocusNeedsById(@Param("id") Long id);

    @Select(" SELECT " +
            " loan_demand.loan_channe, " +
            " loan_demand.loan_time_limit, " +
            " loan_demand.guarantee_mode, " +
            " loan_demand.loan_amount, " +
            " loan_demand.purpose," +
            " loan_demand.company_id demand_company_id, " +
            " loan_demand.creater_user demand_creater_user, " +
            " loan_demand.applicant demand_applicant, " +
            " loan_demand.org_number demand_org_number, " +
            " loan_demand.company_address demand_company_address, " +
            " company_info.admin_tel demand_admin_tel, " +
            " loan_demand.company_name demand_company_name, " +
            " loan_application.check_type checkType, " +
            " loan_application.check_mesage checkMesage, " +
            " loan_application.accept_create checkTime, " +
            " focus_needs.*  " +
            " FROM " +
            " focus_needs " +
            " LEFT JOIN loan_demand ON focus_needs.demand_id = loan_demand.id " +
            " LEFT JOIN company_info ON company_info.company_id=loan_demand.company_id" +
            " LEFT JOIN loan_application ON loan_application.appid = focus_needs.demand_id" +
            " where focus_needs.delete_flag=0 and loan_demand.delete_flag=0 and ${wheres}  order by focus_needs.${orderCloumn} ${orderDesc} limit #{start},#{end}")
    List<Map> listByConditionsLeftJoinloanDemand(@Param("wheres") String wheres,
                                                 @Param("start") int start,
                                                 @Param("end") int end,
                                                 @Param("orderCloumn") String orderCloumn,
                                                 @Param("orderDesc") String orderDesc);
}
