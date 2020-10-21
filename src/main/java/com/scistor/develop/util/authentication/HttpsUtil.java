package com.scistor.develop.util.authentication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;
import java.util.Map.Entry;

public class HttpsUtil {

    private static String charset = "utf-8";


    public static String httpsPost(String url, Map<String,String> map){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("username","qy-011"); //�û���ͨ��AES���ܴ���
    	paramMap.put("password", AESUtils.encrypt58("123456")); //����ͨ��AES���ܴ���
        paramMap.put("password", AESUtils.encrypt58("123456")); //����ͨ��AES���ܴ���
    	paramMap.put("systemNumber","488196"); //Aϵͳ�������

		String str = HttpsUtil.httpsPost("https://auth.ifcert.org.cn/authentication/login",paramMap);
		System.out.println(">>>>>>>" + str);
//		ClientUtil.doPost("https://open.ifcert.org.cn/u/loginAuthentication",createMap);
    }
}

