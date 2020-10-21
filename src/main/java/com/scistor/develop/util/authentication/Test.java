package com.scistor.develop.util.authentication;

import com.alibaba.fastjson.JSON;
import com.scistor.develop.util.base64.Base64Coder;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String serverurl = "http://112.35.1.155:1992/sms/norsubmit";
        String rep = httpPost(serverurl, "13021957907", "你好");
        //返回信息结构
        //{"msgGroup":"1012171730000000744297","rspcod":"success","success":true}
    }

    public static String getSmsParam(String mobiles, String content) throws Exception {
        String ecName = "天津市金融工作局";
        String apId = "tjjrjd";
        String secretKey = "Jesrg35e21jub565";
        String sign = "WUmwCDzQ7";
        String addSerial = "";
        return getSmsParam(ecName, apId, secretKey, mobiles, content, sign, addSerial);
    }

    public static String getSmsParam(String ecName, String apId, String secretKey, String mobiles, String content, String sign, String addSerial) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((ecName));
        stringBuffer.append(apId);
        stringBuffer.append(secretKey);
        stringBuffer.append(mobiles);

        stringBuffer.append(  content );
        stringBuffer.append(sign);
        stringBuffer.append(addSerial);
        String selfMac = DigestUtils.md5DigestAsHex(stringBuffer.toString().getBytes("utf-8"));
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("mac", selfMac);
        dataMap.put("ecName", ecName);
        dataMap.put("apId", apId);
        dataMap.put("secretKey", secretKey);
        dataMap.put("mobiles", mobiles);
        dataMap.put("content", content);
        dataMap.put("sign", sign);
        dataMap.put("addSerial", addSerial);
        String param = new String(JSON.toJSONBytes(dataMap), StandardCharsets.UTF_8);// UTF-8

        String encode = Base64Coder.encodeLines(gbEncoding(param).getBytes());
        return encode;
    }

    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray(); //utfBytes = [测, 试]
        StringBuilder unicodeBytes = new StringBuilder();
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            // Unicode:汉字的Unicode编码范围为\u4E00-\u9FA5 \uF900-\uFA2D,如果不在这个范围内就不是汉字了
            int c = utfBytes[byteIndex];
//            if((c>=0x4E00 && c<=0x9FA5) || (c>=0xF900 && c<=0xFA2D)){
            if ((c > 255)) {
                String hexB = Integer.toHexString(c);
                unicodeBytes.append("\\u").append(hexB);
            } else {
                unicodeBytes.append((char) c);
            }
        }
        return unicodeBytes.toString();
    }

    /*public static String httpPost(String urlStr, String phoneNumber, String smsContent) {

        String line = "";
        long basetime = System.nanoTime();// 12位
        String phones[] = phoneNumber.split(",");
        StringBuilder sbPhone = new StringBuilder();
        for (int i = 0; i < phones.length; i++) {
            String phone = phones[i];
            sbPhone.append(String.valueOf(basetime + i).substring(4)).append(":").append(phone).append(",");
        }
        if (sbPhone.length() > 0) sbPhone.setLength(sbPhone.length() - 1);
        try {
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "9999");
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Connection", "close");
            con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            String xmlInfo = getSmsParam(phoneNumber, smsContent);
            if (xmlInfo.equals("")) {
                return "";
            } else {
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(xmlInfo);
                out.flush();
                out.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder sb = new StringBuilder();
                for (line = br.readLine(); line != null; line = br.readLine()) {
                    sb.append(new String(line.getBytes(),"UTF-8") + "\n");
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            line = "";
        }
        return line;
    }*/


    public static String httpPost(String urlStr, String phoneNumber,String smsContent) {
        if (null == phoneNumber || "".equals(phoneNumber)) return "";
        if (null == smsContent || "".equals(smsContent)) return "";

        URL url = null;
        OutputStream os = null;
        InputStream inStrm = null;
        try {
            url = new URL(urlStr);

            //超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
            System.setProperty("sun.NET.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");

            URLConnection rulConnection = url.openConnection();// 此处的urlConnection对象实际上是根据URL的

            // 请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,故此处最好将其转化 为HttpURLConnection类型的对象,以便用到HttpURLConnection更多的API.如下:
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;

            // 设定请求的方法为"POST"，默认是GET
            httpUrlConnection.setRequestMethod("POST");

            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true, 默认情况下是false;
            httpUrlConnection.setDoOutput(true);

            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpUrlConnection.setDoInput(true);

            // Post 请求不能使用缓存
            httpUrlConnection.setUseCaches(false);

            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            httpUrlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            // 连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
            httpUrlConnection.connect();

            os = httpUrlConnection.getOutputStream();
            //使用utf-8编码解决中文乱码
            String param = getSmsParam(phoneNumber, smsContent);
            os.write(param.getBytes("UTF-8"));

            // 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
            os.flush();

            // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
            // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
            os.close();

            inStrm = httpUrlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStrm));
            StringBuilder strBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuilder.append(new String(line.getBytes(),"UTF-8") + "\n");
            }

            return strBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inStrm != null)
                    inStrm.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
