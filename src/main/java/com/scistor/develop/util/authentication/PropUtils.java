package com.scistor.develop.util.authentication;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeMap;

/**
 * 获取配置文件中参数的工具类
 */
public class PropUtils {
    private static TreeMap<String, String> props = new TreeMap<String, String>();
    private static final String SCHOOL_CONFIG_NAME = "application.properties";
    static int soTimeout = 15000;
    static int conTimeout = 5000;

    static {
        InputStream configStream = null;
        try {
            configStream = PropUtils.class.getClassLoader().getResourceAsStream(SCHOOL_CONFIG_NAME);

            Properties prepayProperties = new Properties();
            prepayProperties.load(configStream);
            Enumeration<?> en = prepayProperties.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = prepayProperties.getProperty(key);
                props.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取配置信息出错", e);
        } finally {
            if (configStream != null) {
                try {
                    configStream.close();
                } catch (IOException e) {
                }
            }
        }
    }


    public static String getProperty(String key, String defaultValue) {
        String result = props.get(key);
        if (result == null) {
            return defaultValue;
        }
        return StringUtils.trim(result);
    }

    public static String getProperty(String key) {
        return getProperty(key, "");
    }


}
