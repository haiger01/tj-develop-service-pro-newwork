package com.scistor.develop.tools;

import com.scistor.develop.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public final class BlockRedis {

    @Autowired
    static ParentService parentService;

    private BlockRedis() {
    }


    public static final String MAP_REDIS_KEY_APPID = "MAP_REDIS_KEY_APPID";

    //用户Redis内的登录状态
    public final static String MAP_REDIS_KEY_SESSSION_ID = "tjdevelop_session_id";

    //用户Redis内的登录状态
    public final static String MAP_REDIS_KEY_OPENSESSION_ID = "tjdevelop_open_id";

    public final static String[] REDIS_DEL_KEY_BEFOR_LIST = "spring:session:sessions:,spring:session:sessions:expires:".split(",");

    public static String REDIS_HOST;
    public static String REDIS_PASSWORD;
    public static String REDIS_PORT;
    public static String REDIS_DATABASE;
    
    public static String LOGIN_REDIS_HOST;
    public static String LOGIN_REDIS_PASSWORD;
    public static String LOGIN_REDIS_PORT;
    public static String LOGIN_REDIS_DATABASE;
    public static String LOGIN_REDIS_AESKEY;
}
