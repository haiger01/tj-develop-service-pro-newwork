package com.scistor.develop.interceptor;

import com.alibaba.fastjson.JSON;
import com.scistor.develop.util.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.NOT_RSA_URL;

@ControllerAdvice
public class AfterInterFace implements ResponseBodyAdvice {
    Logger logger = LoggerFactory.getLogger(AfterInterFace.class);
    @Value("${filepath.file_url}")
    private String fileurl;

    @Value("${filepath.file_path}")
    private String filePath;

    @Override
    public boolean supports(MethodParameter arg0, Class arg1) {
        return true;
    }

    @Value("${aes_rsa_open.flag}")
    private String openFlag;

    @Value("${aes_rsa_open.dataAll}")
    private String dataAll;


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter parameter,
                                  MediaType mediaType, Class clasz,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        String uri = request.getURI().getPath();

        if (uri.indexOf("/error") > -1) {
            return body;
        }

        //获取请求的URL
        String[] requsetList = uri.split("/");
        uri = requsetList[requsetList.length - 1];

        String json = JSON.toJSONString(body);
        //只拦截需要拦截的uri
        if (json.indexOf(filePath) > 0 && !uri.equals("fileUpload") && !uri.equals("companyFileUpload")) {
            try {
                json = json.replace(filePath, fileurl);
            } catch (Exception e) {
            }
        }
        //————加密 2出参根据前端传递的随机数加密
        if ("1".equals(openFlag) && !NOT_RSA_URL.contains(request.getURI().getPath())) {
            ServletServerHttpRequest requestS = (ServletServerHttpRequest) request;
            String radom = requestS.getServletRequest().getHeader("yhStr");
            Map map = new HashMap();
            try {
                map.put("str", AESUtil.encrypt(radom, json));
                if ("1".equals(dataAll)) {
                    map.put("param", requestS.getServletRequest().getParameterMap());
                    map.put("data", JSON.parseObject(json));
                }
                return map;
            } catch (Exception e) {
                logger.error("访问URL" + requestS.getServletRequest().getRequestURI() + "\n加密入参报错，入参:" + JSON.toJSONString(requestS.getServletRequest().getParameterMap()) + "\n" + "计划出参：" + json);
            }
        }

        return JSON.parseObject(json);
    }

}
