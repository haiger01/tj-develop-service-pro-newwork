package com.scistor.develop.interceptor;


import com.alibaba.fastjson.JSON;
import com.scistor.develop.error.ExceptionCode;
import com.scistor.develop.error.IidaException;
import com.scistor.develop.util.AESUtil;
import com.scistor.develop.util.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.NOT_RSA_URL;
import static com.scistor.develop.tools.BlockAttribute.RSA_PRIVATEKEY;
import static com.scistor.develop.util.ObjectUtil.ckStrIsNotnull;
import static com.scistor.develop.util.ObjectUtil.obj2Str;

@WebFilter(urlPatterns = "/*")
public class ControllerFileter implements Filter {
    Logger logger = LoggerFactory.getLogger(AfterInterFace.class);
    @Value("${aes_rsa_open.flag}")
    private String openFlag;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //检查开关
        if (!"1".equals(openFlag) || NOT_RSA_URL.contains(request.getRequestURI())
                || request.getRequestURI().indexOf("file/") > -1) {
            ModifyRequestWrapper changeRequestWrapper = new ModifyRequestWrapper((HttpServletRequest) servletRequest);
            filterChain.doFilter(changeRequestWrapper, servletResponse);
            return;
        }

        //————加密1 密文解密

        ModifyRequestWrapper changeRequestWrapper = new ModifyRequestWrapper((HttpServletRequest) servletRequest);

        Map<String, String[]> parameterMap = new HashMap<>(changeRequestWrapper.getParameterMap());

        String aesStr = parameterMap.get("str")[0].replaceAll(" ", "+");
        try {
            aesStr = RSAUtil.decode(RSA_PRIVATEKEY, aesStr);
        } catch (Exception e) {
            logger.error("解密报错：" + request.getRequestURI() + "\n加密入参报错，入参:" + JSON.toJSONString(request.getParameterMap()) + "\n");
        }

        String bodyStr = null;
        try {
            bodyStr = new String(AESUtil.decrypt(aesStr, parameterMap.get("randomNum")[0].replaceAll(" ", "+")));
        } catch (Exception e) {
            logger.error("访问URL" + request.getRequestURI() + "\n加密入参报错，入参:" + JSON.toJSONString(request.getParameterMap()) + "\n");
            throw createError(ExceptionCode.code5001);
        }
        if (bodyStr != null) {
            Map<String, Object> map = JSON.parseObject(bodyStr);
            if (map != null) {
                for (String key : map.keySet()) {
                    String k = obj2Str(map.get(key));
                    //System.out.println("拦截器中参数的key:"+k);
                    if(ckStrIsNotnull(k))
                        k = k.replaceAll(">", "&gt;").replaceAll("<", "&lt;")
                                .replaceAll("&", "&amp;").replaceAll("\"", "&quot;")
                                .replaceAll("\'", "&apos;").replaceAll("%", "\\\\%");
                    //System.out.println("拦截器中转义之后的参数的key:"+k);
                    parameterMap.put(key, new String[]{k});

                    //parameterMap.put(key, new String[]{obj2Str(map.get(key))});
                }
                //System.out.println("拦截器中参数的parameterMap:"+parameterMap);
            }
        }

        changeRequestWrapper.setParameterMap(parameterMap);
        changeRequestWrapper.addHeader("yhStr", aesStr);

        //使用复写后的wrapper
        filterChain.doFilter(changeRequestWrapper, servletResponse);

    }

    /**
     * 创建异常格式
     */
    private IidaException createError(ExceptionCode exceptionCode) {
        return new IidaException(exceptionCode.getCode(), exceptionCode.getMsg());
    }

}
