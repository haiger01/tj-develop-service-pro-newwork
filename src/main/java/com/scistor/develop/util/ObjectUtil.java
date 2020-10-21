package com.scistor.develop.util;

/**
 * @author 岳浩
 * @实体类转换为map
 */

import com.alibaba.fastjson.JSON;
import com.scistor.develop.data.entity.CompanyInfo;
import org.slf4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.scistor.develop.tools.SQLBlockAttribute.SQL_CK_LIKE;
import static com.scistor.develop.util.DateTool.dateTimeSec2Str;
import static com.scistor.develop.util.StringTool.isNull;
import static com.scistor.develop.util.StringTool.sqlInjs;

public final class ObjectUtil {

    private ObjectUtil() {
    }

    public static String amount2Str(String str) {

        if (str.length() > 4 && str.length() < 9)
            str = String.format("%.2f", (Double.valueOf(str) / 10000)) + "万";
        else if (str.length() > 8)
            str = String.format("%.2f", (Double.valueOf(str) / 100000000)) + "亿";

        return str;
    }

    /**
     * @Description //都不为空则true
     * @Author 岳浩
     * @Date 2020/3/3 0003 23:42
     * @Parameter --
     **/
    public static boolean ckStrIsNotnull(String... strs) {
        if (strs==null) return false;
        for (String str : strs) {
            if (str == null || str.length() == 0 || str.equals("null")) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description //校验都不等于比较字符,有等于的返回false
     * @Author 岳浩
     * @Date 2020/3/3 0003 23:42
     * @Parameter --
     **/
    public static boolean cknotEquals(String equse, String... strs) {
        for (String str : strs) {
            if (equse.equals(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description //校验当前map 需要的KEY都不为空,通过校验返回"",未通过校验则返回该key，敏感
     * @Author 岳浩
     * @Date 2020/3/3 0003 22:39
     * @Parameter --
     **/
    public static String ckObjKeyIsNotNull(Object obj, String... keys) {

        Map map = obj2Map(obj);
        for (String key : keys) {
            if (map.get(key) == null || map.get(key).equals("null") || map.get(key).equals("")) {
                return key;
            }
        }
        return null;
    }

    /**
     * @Description //map里的key全部下划线变驼峰
     * @Author 岳浩
     * @Date 2020/3/2 0002 7:52
     * @Parameter --
     **/
    public static void mapKeyLine2Hump(Map map) {
        if (map == null || map.size() == 0) return;
        Set<Object> keySet = new HashSet<>();
        keySet.addAll(map.keySet());
        for (Object key : keySet) {

            //如果没有下划线则跳过
            if (key.toString().indexOf("_") == -1) continue;

            map.put(line2Hump(key.toString()), map.get(key));
            map.remove(key);
        }
    }

    /**
     * @Description //map里的key全部下划线变驼峰
     * @Author 岳浩
     * @Date 2020/3/2 0002 7:52
     * @Parameter --
     **/
    public static void listKeyLine2Hump(List<Map> list) {
        if (list == null) return;
        for (Map m : list) {
            mapKeyLine2Hump(m);
        }
    }

    /**
     * @Description // 删除list内某个KEY
     * @Author 岳浩
     * @Date 2020/3/2 0002 7:52
     * @Parameter --
     **/
    public static List listMapRemoveKey(List list, String... keys) {
        if (list == null) return list;
        List dataList = new ArrayList();
        for (Object obj : list) {
            Map map = obj2Map(obj);
            for (String key : keys) {
                if (map.get(key) != null) {
                    map.remove(key);
                }
            }
            dataList.add(map);
        }
        return dataList;
    }

    /**
     * @Description //map删除不包含的KEY
     * @Author 岳浩
     * @Date 2020/2/25 1:46
     **/
    public static Map delMapNotKey(Object obj, String... notdelKey) {
        Map map = obj2Map(obj);
        Map newMap = new HashMap();
        for (String key : notdelKey) {
            newMap.put(key, map.get(key));
        }
        map = new HashMap();
        map.putAll(newMap);
        return map;
    }

    /**
     * 下划线转驼峰
     */
    public static String line2Hump(String str) {
        str = str.toLowerCase();
        Matcher matcher = Pattern.compile("_(\\w)").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰转下划线
     */
    public static String hump2Line(String str) {
        String yStr = str.substring(0, 1).toLowerCase();
        str = str.substring(1, str.length());
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return yStr + sb.toString();
    }

    /**
     * @Author 岳浩
     * @Description //保证不出错转为str
     **/
    public static String str(String str) {
        return (String) str;
    }

    /**
     * @Description //有继承类属性转为map
     * @Author 岳浩
     * @Date 2018/8/18 16:17
     */
    public static Map<String, Object> obj2Map(Object obj) {
        if (obj == null) return new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            dataMap = (Map) obj;
            return dataMap;
        } catch (Exception e) {
        }
        try {
            dataMap = JSON.parseObject(JSON.toJSONString(obj));
        } catch (Exception e) {

        }
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                dataMap.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    public static <T> T map2Obj(Map<String, Object> map, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        System.out.println(JSON.toJSONString(map2Object(m, CompanyInfo.class)));
    }

    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = String.valueOf(map.get(field.getName()));
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        if (obj != null) field.set(obj, null);
                    } else {
                        if (obj != null) field.set(obj, new Date(Long.parseLong(datetimestamp)));
                    }
                } else {
                    if (obj != null) field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * @Author 岳浩
     * @Description // 多个类转为map
     * @Date 2019/3/18 15:28
     * @Param [objs]
     **/
    public static Map<String, Object> objs2Map(Object... objs) {
        if (objs == null) return new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        for (Object obj : objs) {
            dataMap.putAll(obj2Map(obj));
        }
        return dataMap;
    }


    /**
     * @return java.lang.String
     * @Description /示例   map.put("name|like","值1") ;
     * map.put("status|=","值2")  ;
     * mapToHql("tb1",map,null,null): "where 1=1 and name like '值1' and status = '值2'";
     * @Author 岳浩
     * @Date 2018/9/3 20:43
     * @Param [name, obj, id, code]
     */
    public static String mapToHql(String name, Map<String, Object> dataMap, Object... noValues) {
        Map<String, String> noValMap = new HashMap<>();
        if (noValues != null) {
            for (Object noValue : noValues) {
                noValMap.put(String.valueOf(noValue), "1");
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            if (noValMap.get(String.valueOf(entry.getValue())) == null && entry.getValue() != null && isNull(String.valueOf(entry.getValue()))) {
                String keyVal = entry.getKey();
                String key = keyVal.substring(0, keyVal.indexOf("|"));
                String bds = keyVal.substring(keyVal.indexOf("|") + 1, keyVal.length());
                Object val = entry.getValue();
                //属性不为boolean且不为空，不为-1,key不再noObjs内
                sb.append(" and " + name + "." + key + " " + bds + " '" + val + "'");
            }
        }
        return sb.toString();
    }

    /**
     * @return java.util.List
     * @Description // 根据某个key,或者某些Key的总和进行降序,由大到小
     * @Author 岳浩
     * @Date 2018/9/17 20:26
     * @接口说明：http://192.168.0.121/web/#
     * @Param [list, key:用于排序的key,desc:desc为降序，其他升序
     */
    public static List sortByKey(List<Map<String, Object>> list, String desc, String... key) {
        Map<String, List<Map<String, Object>>> allMap = new HashMap<>();
        List<BigDecimal> decimalsList = new ArrayList<>();
        for (Map<String, Object> m : list) {
            String keyStr = String.valueOf(m.get(key[0]));
            //如果不是数字、小数类型，就默认未时间，进行墙砖
            try {
                new BigDecimal(keyStr);
            } catch (Exception e) {
                try {
                    Date d = (Date) m.get(key[0]);
                    keyStr = d.getTime() + "";
                } catch (Exception e2) {
                    return list;
                }
            }

            List<Map<String, Object>> dataList = new ArrayList<>();
            if (allMap.get(keyStr) != null) {
                dataList = allMap.get(keyStr);
            }
            dataList.add(m);
            allMap.put(String.valueOf(keyStr), dataList);
            decimalsList.add(new BigDecimal(keyStr));
        }
        //降序
        if (desc.equals("desc")) {
            Collections.sort(decimalsList, new Comparator<BigDecimal>() {
                @Override
                public int compare(BigDecimal o1, BigDecimal o2) {
                    return o2.compareTo(o1);
                }
            });
        }
        //升序
        else {
            Collections.sort(decimalsList);
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (BigDecimal b : decimalsList) {
            String strB = b.toString();
            if (allMap.get(strB) != null) {
                dataList.addAll(allMap.get(strB));
                allMap.remove(strB);
            }
        }
        return dataList;
    }

    /**
     * 相同对象之间赋值 将非空属性赋值给另一个对象
     *
     * @param origin
     * @param destination
     * @param <T>
     * @date 2018-10-5 20:51:56
     */
    public static <T> void mergeObject(T origin, T destination) {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //获取客户端IP
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @return sql异常返回false
     * @Author 岳浩
     * @Description //TODO
     * @Date 2019/3/18 14:28
     * @Param [request, log]
     **/
    public static boolean logInterceptor(HttpServletRequest request, Logger log) {
        if (sqlInjs(request.getParameterMap().values())) {
            log.error("\n>>>>uri    >" + request.getRequestURI()
                    + "\n>>>>ip     >" + getIpAddr(request)
                    + "\n>>>>method >" + request.getMethod()
                    + "\n>>>>params >" + JSON.toJSONString(request.getParameterMap().values())
                    + "\n>>>>error! >捕捉到sql注入");
            return false;
        } else {
            log.debug("\n>>>>uri    >" + request.getRequestURI()
                    + "\n>>>>ip     >" + getIpAddr(request)
                    + "\n>>>>method >" + request.getMethod()
                    + "\n>>>>params >" + JSON.toJSONString(request.getParameterMap().values()));
            return true;
        }
    }

    public static String inputStream2Str(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static String obj2Str(Object o) {
        String str = o + "";
        if (str.equals("null")) return null;
        return str;
    }

    public static boolean checkImg(File file) {
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch (IOException ex) {
            return false;
        }
    }

    public static String getRandom(int length) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for (int i = 0; i < length; i++) {
            str.append(random.nextInt(10));
        }
        //将字符串转换为数字并输出
        return str.toString();
    }


    /**
     * 纯数字正则
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        //Pattern pattern = Pattern.compile("^-?[0-9]+"); //这个也行
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");//这个也行
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
