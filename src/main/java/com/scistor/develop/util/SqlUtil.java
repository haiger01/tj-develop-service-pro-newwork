package com.scistor.develop.util;

import com.scistor.develop.data.entity.CompanyInfo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.*;
import static com.scistor.develop.tools.SQLBlockAttribute.*;
import static com.scistor.develop.util.ObjectUtil.*;
import static com.scistor.develop.util.ServletUtil.getSession;

public final class SqlUtil {
    private SqlUtil() {
    }

    //对指定数据改为like语句的前准结构
    public static String sql2like(String str) {
        if (str == null || str.length() == 0) return " 1=1 ";
        return str.replaceAll(",", SQL_CK_LIKE + ",");
    }

    /**
     * @Description //查询sql组成
     * @Author 岳浩
     * @Date 2020/2/25 1:53
     **/
    public static String map2where(Object obj, String... keys) {

        return map2where(SQL_CK_TABLE_STR, obj, keys).replaceAll(SQL_CK_TABLE_STR + ".", "");
    }

    /**
     * @Description //查询sql组成
     * @Author 岳浩
     * @Date 2020/2/25 1:53
     **/
    public static String map2where(String tableName, Object obj, String... keys) {
        Map map = obj2Map(obj);
        StringBuilder whereSql = new StringBuilder();
        for (String key : keys) {
            if (key == null || key.length() < 1) continue;
            //得到值

            //如果需要like
            if (key.indexOf(SQL_CK_LIKE) > 0) {
                String keyStr = key.replace(SQL_CK_LIKE, "");
                //字段全选表示的是该字段不参与查询--直接跳过----20200506--张军
                if (map.get(keyStr.replaceAll("`","")) == null || "".equals(map.get(keyStr.replaceAll("`",""))) || "全选".equals(map.get(keyStr.replaceAll("`",""))))
                    continue;

                if (keyStr.indexOf(".") < 0)
                    whereSql.append(tableName + "." + hump2Line(keyStr) + " like '%" + map.get(keyStr.replaceAll("`","")) + "%' and ");
                else
                    whereSql.append(hump2Line(keyStr) + " like '%" + map.get(keyStr.replaceAll("`","")) + "%' and ");

            }
            // 比较大于号
            else if (key.indexOf(">") > 0) {

                String key1 = key.split(">")[0];
                String key2 = ">" + key.split(">")[1];

                //当前字段只用于大于小于
                if (map.get(key) != null && !map.get(key).equals("")) {
                    if (key1.indexOf(".") < 0)
                        whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key) + "' and ");
                    else
                        whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key) + "' and ");
                    continue;
                }

                if (map.get(key1) == null || "".equals(map.get(key1)))
                    continue;

                if (key1.indexOf(".") < 0)
                    whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");
                else
                    whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");

            }
            //比较小于号
            else if (key.indexOf("<") > 0) {
                String key1 = key.split("<")[0];
                String key2 = "<" + key.split("<")[1];

                //当前字段只用于大于小于
                if (map.get(key) != null && !map.get(key).equals("")) {
                    if (key1.indexOf(".") < 0)
                        whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key) + "' and ");
                    else
                        whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key) + "' and ");
                    continue;
                }

                if (map.get(key1) == null || "".equals(map.get(key1)))
                    continue;

                if (key1.indexOf(".") < 0)
                    whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");
                else
                    whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");

            }
            //比较不等于号110行
            else if (key.indexOf("!") > 0) {
                String key1 = key.split("!")[0];
                String key2 = "!" + key.split("!")[1];

                //当前字段只用于大于小于
                if (map.get(key) != null && !map.get(key).equals("")) {
                    if (key1.indexOf(".") < 0)
                        whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key) + "' and ");
                    else
                        whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key) + "' and ");
                    continue;
                }

                if (map.get(key1) == null || "".equals(map.get(key1)))
                    continue;

                if (key1.indexOf(".") < 0)
                    whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");
                else
                    whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");

            } else if (key.indexOf("!") > 0) {
                String key1 = key.split("!")[0];
                String key2 = "!" + key.split("!")[1];

                if (map.get(key1) == null || "".equals(map.get(key1)))
                    continue;

                if (key1.indexOf(".") < 0)
                    whereSql.append(tableName + "." + hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");
                else
                    whereSql.append(hump2Line(key1) + key2 + "'" + map.get(key1) + "' and ");

            }
            //时间的查询
            else if (key.indexOf("-") > 0) {
                // date_format([date_name],'%Y-%m-%d') = '2016-08-06'
                String key1 = key.split("--")[0];
                String key2 = key.split("--")[1];

                if (map.get(key1) == null || "".equals(map.get(key1)))
                    continue;
                // new SimpleDateFormat("yyyy-MM-dd").format(map.get(key1)).substring(0,10);
                if (key1.indexOf(".") < 0)
                    whereSql.append("date_format(" + tableName + "." + hump2Line(key1) + ",'%Y-%m-%d')" + key2 + "'" + new SimpleDateFormat("yyyy-MM-dd").format(map.get(key1)).substring(0, 10) + "' and ");
                else
                    whereSql.append("date_format(" + hump2Line(key1) + ",'%Y-%m-%d')" + key2 + "'" + new SimpleDateFormat("yyyy-MM-dd").format(map.get(key1)).substring(0, 10) + "' and ");
            }
            //直接拼接
            else {
                if (map.get(key) == null || "".equals(map.get(key)))
                    continue;

                if (key.indexOf(".") < 0)
                    whereSql.append(tableName + "." + hump2Line(key) + " = '" + map.get(key) + "' and ");
                else
                    whereSql.append(hump2Line(key) + " = '" + map.get(key) + "' and ");
            }
        }
        //如果拼完什么都不需要
        if (whereSql.length() == 0) {
            return " 1=1 ";
        }
        whereSql.setLength(whereSql.length() - 4);
        return whereSql.toString();
    }

//6   0
    public static String getBetWeenAndSql(String tableName, String key, Object befor, Object after) {
        StringBuilder whereSql = new StringBuilder();


        if (ckStrIsNotnull(befor + "", after + "")) {
            if (key.indexOf(".") < 0)
                whereSql.append(tableName + "." + hump2Line(key) + " between " + befor + " and  " + after + "  ");
            else
                whereSql.append(hump2Line(key) + " between " + befor + " and  " + after + "  ");
        } else {
            return " 1=1 ";
        }

        return whereSql.toString();
    }
                                                //6     0
    public static String getBetWeenAndSql(String key, Object befor, Object after) {
        if (befor == null) befor = 0;
        if (after == null) after = Long.MAX_VALUE;
        return getBetWeenAndSql(SQL_CK_TABLE_STR, key, befor, after).replaceAll(SQL_CK_TABLE_STR + ".", "");
    }

    public static String getInBySql(String key, List<String> list) {
        if (list == null || list.size() == 0) return " 1=1 ";

        StringBuilder inStr = new StringBuilder();
        list.forEach(n -> inStr.append("'" + n + "',"));
        inStr.setLength(inStr.length() - 1);

        return " " + hump2Line(key) + " in(" + inStr + ") ";
    }


    public static String getInBySql(String key, String[] list) {

        return getInBySql(key, Arrays.asList(list));
    }

    public static String getInBySql(String key, Object o) {
        String str = obj2Str(obj2Map(o).get(key));
        if (str == null) return " 1=1 ";
        return getInBySql(key, str.split(","));
    }


    /**
     * 仅限于auth路径使用
     * @param tablesName
     * @param companeType
     * @return
     */
    public static String getAuthCaseSql(String tablesName,String companeType) {
        String aa = "-1";
        if("1".equals(companeType))//aa = " 0 AND company_id = '天津借贷有限公司'";
            aa = " 0 AND company_id = '"+getSession().getAttribute(SESSION_KEYNAME_COMPANY_ID)+"'";
        return CASESQL.replace("TABLENAME",tablesName).replaceAll("DELETEFLAG_COMPANTID",aa);
    }

    public static void main(String[] args) {
        System.out.println(getAuthCaseSql("financial_goods","1"));
    }
}
