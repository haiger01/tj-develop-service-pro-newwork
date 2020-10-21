package com.scistor.develop.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.scistor.develop.tools.BlockAttribute.*;
import static com.scistor.develop.util.ObjectUtil.obj2Map;

public class ServletUtil {
    public static ServletRequestAttributes getRequestAttr() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }


    public static HttpServletResponse getResponse() {
        return getRequestAttr().getResponse();
    }

    public static HttpServletRequest getRequest() {

        return getRequestAttr().getRequest();
    }

    public static HttpSession getSession() {
        return getRequestAttr().getRequest().getSession();
    }

    public static <T> T sessionModifyObj(Object obj, Class<T> clazz) {
//        public String usercode;             //登陆用户编码
//        public String companyName;          //  '企业、机构、银行名称',
//        public String companyId;            //  '企业、机构、银行id',
//        public String companyType;          // '类型：1企业，2金融机构，3银行，4金融局',
//        public String createrUser;          // '数据创建人，申请人',
        HttpSession session = getSession();
        Map map = obj2Map(obj);
        map.put("usercode", session.getAttribute(SESSION_KEYNAME_USER_CODE) + "");
        map.put("companyName", session.getAttribute(SESSION_KEYNAME_COMPANY_NAME) + "");
        map.put("companyId", session.getAttribute(SESSION_KEYNAME_COMPANY_ID) + "");
        map.put("companyType", session.getAttribute(SESSION_KEYNAME_COMPANY_TYPE) + "");
        map.put("createrUser", session.getAttribute(SESSION_KEYNAME_USER_CODE) + "");
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    public static String downloadFile(String fileName) {

        HttpServletResponse response = getResponse();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        String fileNameStr = fileName.split("/")[fileName.split("/").length - 1];
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + "天津金融服务发展平台企业授权书" + ";filename*=utf-8''"
                    + URLEncoder.encode("天津金融服务发展平台企业授权书.docx", "utf-8"));

        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {

            Files.copy(Paths.get(fileName.substring(0, fileName.lastIndexOf("/")), fileNameStr), response.getOutputStream());
        } catch (Exception e) {

        }
        return "success";
    }
}
