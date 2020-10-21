package com.scistor.develop.runstatic;

import com.alibaba.fastjson.JSON;
import com.scistor.develop.service.ParentService;
import com.scistor.develop.tools.BlockAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

import static com.scistor.develop.tools.NewMessageBlockAttribute.NEWMESSAGE_MAP;
import static com.scistor.develop.tools.NewMessageBlockAttribute.NEWMESSAGE_MAP2;

@Component
public class StaticRush {

    @Value("${new_message.json}")
    private String messageJSON;

    /*@Value("${new_message2.json}")
    private String messageJSON2;*/

    @Value("${aes.not_rsa_url}")
    private String not_rsa_url;

    @Autowired
    ParentService parentService;

    @Autowired
    public void init() {
        //装载消息配置
        NEWMESSAGE_MAP.putAll((Map) JSON.parseObject(messageJSON));
        NEWMESSAGE_MAP2 = NEWMESSAGE_MAP;
        //NEWMESSAGE_MAP2.putAll((Map) JSON.parseObject(messageJSON));
        //装载redis配置
        /*REDIS_DATABASE = redisDatabase;
        REDIS_HOST = redisHost;
        REDIS_PORT = redisPort;
        REDIS_PASSWORD = redisPassword;*/
        BlockAttribute.NOT_RSA_URL = Arrays.asList(not_rsa_url.split(","));


        /*LOGIN_REDIS_DATABASE = loginRedisDatabase;
        LOGIN_REDIS_HOST = loginRedisHost;
        LOGIN_REDIS_PASSWORD = loginRedisPassword;
        LOGIN_REDIS_PORT = loginRedisPort;
        LOGIN_REDIS_AESKEY = loginRedisAes_key;*/

    }
}
