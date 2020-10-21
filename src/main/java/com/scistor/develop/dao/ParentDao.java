package com.scistor.develop.dao;

/**
 * @author 岳浩
 * @所有服务类组件的父类
 * @服务类组件也可通过JPA组件操作数据库
 */

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.scistor.develop.data.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static com.scistor.develop.util.ObjectUtil.hump2Line;

public class ParentDao {

    private final static Logger logger = LoggerFactory.getLogger(ParentDao.class);


    public ParentDao() {
        super();
    }


    //生成字段对应
    public static void main(String[] args) {
        //
        //String[] customersList = new String[]{"1,2","3,4"};
        //System.out.println(StringUtils.join(customersList,"_"));
        System.out.println(getResultsStr(Network.class));
        //String aa = "/home/yh/certFile/7ca21eb53bda4dddbce173799c9561b9/1589112904139.jpg";
        //System.out.println(aa.substring(aa.lastIndexOf("/")+1));

        //System.out.println(DateTool.longDate2Str(1595750661097L));
    }
























    public static String getResultsStr(Class origin) {
        String className = hump2Line(origin.getSimpleName());
        String mapName = origin.getSimpleName() + "ResultMap";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results(id = \"" + mapName + "\", value = {\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toLowerCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("\n})");
        stringBuilder.append("\n @Select(\"select * from " + className + " where delete_flag=0 and ${wheres} order by ${orderCloumn} ${orderDesc}  limit #{start},#{end}\")" +
                " \n   List<" + origin.getSimpleName() + "> listByConditions(@Param(\"wheres\") String wheres,\n" +
                "                                      @Param(\"start\") int start,\n" +
                "                                      @Param(\"end\") int end,\n" +
                "                                      @Param(\"orderCloumn\")String orderCloumn,\n" +
                "                                      @Param(\"orderDesc\")String orderDesc);");
        stringBuilder.append("\n@Select(\"select count(1) from " + className + " where delete_flag=0 and ${wheres} \")\n" +
                "    long countByConditions(@Param(\"wheres\") String wheres);");

        stringBuilder.append("\n@Select(\"select * from " + className + " where id=${id}\")\n" +
                "    @ResultMap(value = \"" + mapName + "\")\n" +
                "    " + origin.getSimpleName() + " get" + origin.getSimpleName() + "ById(@Param(\"id\") Long id);");
        return stringBuilder.toString();
    }

}
