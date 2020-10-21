package com.scistor.develop.util;

/**
 * @author 岳浩
 * @实体类转换为map
 */


import com.alibaba.fastjson.JSON;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.scistor.develop.tools.BlockAttribute.SQL_STR;
import static com.scistor.develop.util.ObjectUtil.obj2Map;

public final class StringTool {

    private StringTool() {
    }

    /**
     * 把字符串按不同的符号进行拆分
     */
    public static String[] splitString(String value, String split) {
        String symbol = "([-+" + split + "])";
        String[] values = null;
        if (!"".equals(value)) {
            if ((value.lastIndexOf(split) + 1) == value.length())
                value = value.substring(0, value.length() - 1);
            values = value.split(symbol);
        }
        return values;
    }

    /**
     * 把字符串数组替换成字符串
     */
    public static String stringJoin(String[] values) {
        String value = "";
        if (values != null && !"null".equals(values) && values.length > 0) {
            for (String key : values) {
                if (key != null) value = value + ",'" + key.trim() + "'";
            }
            if (value.length() > 0) {
                value = value.substring(1);
            } else {
                value = "''";
            }
        }
        return value;
    }

    /**
     * 把字符串数组替换成字符串
     */
    public static String stringJoin(List<String> values) {
        String value = "";
        if (values != null && values.size() > 0) {
            for (String key : values) {
                if (key != null) value = value + ",'" + key.trim() + "'";
            }
            if (value.length() > 0) {
                value = value.substring(1);
            } else {
                value = "''";
            }
        }
        return value;
    }

    /**
     * 把字符串数组替换成字符串
     */
    public static String stringJoin(Set<String> values) {
        String value = "";
        if (values != null && values.size() > 0) {
            for (String key : values) {
                if (key != null) value = value + ",'" + key.trim() + "'";
            }
            if (value.length() > 0) {
                value = value.substring(1);
            } else {
                value = "''";
            }
        }
        return value;
    }

    /**
     * 过滤特殊html字符
     */
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;// 返回文本字符串
    }

    /**
     * 判断字符串是否包括%、=、<、>、'
     */
    public static boolean stringJuest(String value) {
        if (value != null && (value.indexOf("%") >= 0 || value.indexOf("=") >= 0 || value.indexOf("<") >= 0 || value.indexOf(">") >= 0 || value.indexOf("'") >= 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证输入的邮箱格式是否符合
     *
     * @param email
     * @return 是否合法
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 判断手机号phone是否正确格式
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^13\\d{9}||18\\d{9}||15\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 全角转半角
     */
    public static String QBchange(String QJstr) {
        String outStr = "";
        String Tstr = "";
        byte[] b = null;

        for (int i = 0; i < QJstr.length(); i++) {
            try {
                Tstr = QJstr.substring(i, i + 1);
                b = Tstr.getBytes("unicode");
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (b[3] == -1) {
                b[2] = (byte) (b[2] + 32);
                b[3] = 0;
                try {
                    outStr = outStr + new String(b, "unicode");
                } catch (java.io.UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else
                outStr = outStr + Tstr;
        }
        return outStr;
    }

    /**
     * 防SQL注入
     * 有SQL注入返回true
     */
    public static boolean sqlInj(String str) {
        if (str != null) {
            str = str.toLowerCase();
            for (int i = 0; i < SQL_STR.length; i++) {
                if (str.indexOf(SQL_STR[i]) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return 有SQL注入返回true
     * @Description // 防SQL注入
     * @Author 岳浩
     * @Param [strs]
     */
    public static boolean sqlInjs(String... strs) {
        if (strs == null) return false;
        for (String str : strs) {
            if (sqlInj(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 有SQL注入返回true
     * @Description // 防SQL注入
     * @Author 岳浩
     * @Param [strs]
     */
    public static boolean sqlInjs(Collection strs) {
        if (strs == null) return false;
        for (Object str : strs) {
            if (sqlInj(JSON.toJSONString(str))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 有SQL注入返回true
     * @Description // 防SQL注入
     * @Author 岳浩
     * @Param [strs]
     */
    public static boolean sqlInjs(List<String> strs) {
        if (strs == null) return false;
        for (String str : strs) {
            if (sqlInj(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 有SQL注入返回true
     * @Description // 防SQL注入
     * @Author 岳浩
     * @Param [objs]
     */
    public static boolean sqlInjObJects(Object... objs) {
        if (objs == null) return false;
        for (Object obj : objs) {
            String jsonStr = JSON.toJSONString(obj);
            Map<String, Object> dataMap = obj2Map(obj);
            for (Object objStr : dataMap.values()) {
                if (!objStr.getClass().getSimpleName().equals("Integer")) {
                    if (sqlInj(String.valueOf(objStr))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return 有SQL注入返回true
     * @Description // 防SQL注入
     * @Author 岳浩
     * @Param [objs]
     */
    public static boolean sqlInjMap(Map map) {
        if (map == null) return false;

        if (sqlInj(JSON.toJSONString(map))) {
            return true;
        }

        return false;
    }


    /**
     * URL特殊字符转义
     */
    public static String urlStringTrans(String value) {
        if (value != null && !"".equals(value)) {
            if (value.indexOf("%") >= 0)
                value = value.replaceAll("%", "%25");
            if (value.indexOf("+") >= 0)
                value = value.replaceAll("\\+", "%2B");
            if (value.indexOf("/") >= 0)
                value = value.replaceAll("/", "%2F");
            if (value.indexOf("?") >= 0)
                value = value.replaceAll("\\?", "%3F");
            if (value.indexOf("#") >= 0)
                value = value.replaceAll("#", "%23");
            if (value.indexOf("&") >= 0)
                value = value.replaceAll("&", "%26");
            if (value.indexOf("=") >= 0)
                value = value.replaceAll("=", "%3D");
        }
        return value;
    }

    /**
     * 当页面是multipart/form-data提交时，中文会乱码，需要进行转码
     */
    public static String transCoding(String valueStr) throws Exception {
        if (valueStr != null && !"".equals(valueStr.trim())) {
            byte[] bytes = valueStr.getBytes();
            return new String(bytes, "UTF-8");
        } else {
            return "";
        }
    }

    /**
     * 判断字符串
     *
     * @return boolean
     * @Author 岳浩
     * @Date 2018/7/25 10:05
     * @Description //  该方法只要有值为空，则返回false,空格识别为空字符
     * @Param [strs] 要校验的字符串
     **/
    public static boolean isNull(String... strs) {
        if (strs == null) {
            return false;
        }
        for (String str : strs) {
            if (str == null || "".equals(str.trim())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Object... objs) {
        if (objs == null) {
            return false;
        }
        for (Object obj : objs) {
            return isNull(obj);
        }
        return true;
    }
}
