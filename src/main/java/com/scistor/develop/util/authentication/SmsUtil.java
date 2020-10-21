package com.scistor.develop.util.authentication;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.scistor.develop.tools.ExpiryMap;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 短信发送-阿里大鱼
 */
public class SmsUtil {
	public static void main(String[] args) {
		/*String str = generateVerification();
		sendMsgTest("17695616805", str ,"SMS_159045019");
		System.out.println(str);*/

        notifiApiTestSend();
	}

	public static String generateVerification() {
		String verification = null;
		verification = (int)((Math.random() * 9 + 1) * 100000) + "";
		return verification;
	}

    /***
     * 发送短信验证查看是否超过3次
     * @param phone     手机号
     * @param content   内容
     * @param smsTemplateCode
     * @return      0：成功，1:没有手机号，2：没有内容，3:发送短信太多,4:失败
     */
    public static String sendMsgTest(String phone, String content, String smsTemplateCode) {
        ExpiryMap<String,String> expiryMap = ExpiryMap.getInstance();
        if (StringUtils.isEmpty(phone)) return "1";
        if (StringUtils.isEmpty(content)) return "2";
        try {
            //String smsCode = VerifyCodeUtil.getRandomCode(6);
            //先判断是否超过3次
            String verifyNum = expiryMap.get(Constants.REDIS_PHONE_KEY_NUM + phone);
            int num = 1;
            if (!StringUtils.isEmpty(verifyNum))  num = Integer.parseInt(verifyNum);
            if (num > 5) return "3";

            //发送短信，同时记录发送数量
            String sendNum = expiryMap.get(Constants.REDIS_SEND_ERROR_NUM + phone);
            int textNum = 0;
            if (!StringUtils.isEmpty(sendNum))  textNum = Integer.parseInt(sendNum);
            if (textNum > 3){
                expiryMap.remove(Constants.REDIS_SEND_ERROR_NUM + phone);
                return "4";
            }
            boolean f = sendMsg(phone,content,"SMS_159045019");
            if (f){
                num = num + 1;
                expiryMap.put(Constants.REDIS_PHONE_KEY + phone,content.toLowerCase(),Constants.REDIS_PHONE_TIME);
                expiryMap.put(Constants.REDIS_PHONE_KEY_NUM + phone,num+"",Constants.REDIS_PHONE_NUM_TIME);
                expiryMap.remove(Constants.REDIS_SEND_ERROR_NUM + phone);
                return "0";
            }else{
                //如果一次不成功则多调用几次
                textNum += 1;
                expiryMap.put(Constants.REDIS_SEND_ERROR_NUM + phone,textNum+"");
                sendMsgTest(phone,content,smsTemplateCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "4";
    }

    public static String sendMsgConent(String phone, String content) {
        ExpiryMap<String,String> expiryMap = ExpiryMap.getInstance();
        if (StringUtils.isEmpty(phone)) return "1";
        if (StringUtils.isEmpty(content)) return "2";
        try {
            //String smsCode = VerifyCodeUtil.getRandomCode(6);
            //先判断是否超过3次
            String verifyNum = expiryMap.get(Constants.REDIS_PHONE_KEY_NUM + phone);
            int num = 1;
            if (!StringUtils.isEmpty(verifyNum))  num = Integer.parseInt(verifyNum);
            if (num > 5) return "3";

            //发送短信，同时记录发送数量
            String sendNum = expiryMap.get(Constants.REDIS_SEND_ERROR_NUM + phone);
            int textNum = 0;
            if (!StringUtils.isEmpty(sendNum))  textNum = Integer.parseInt(sendNum);
            if (textNum > 3){
                expiryMap.remove(Constants.REDIS_SEND_ERROR_NUM + phone);
                return "4";
            }
            String serverurl = "http://112.35.1.155:1992/sms/norsubmit";
            String rep = Test.httpPost(serverurl, phone, content);
            Map<String,Object> map = JSON.parseObject(rep,Map.class);
            boolean f = (boolean) map.get("success");
            System.out.println(rep);
            if (f){
                num = num + 1;
                expiryMap.put(Constants.REDIS_PHONE_KEY + phone,content.toLowerCase(),Constants.REDIS_PHONE_TIME);
                expiryMap.put(Constants.REDIS_PHONE_KEY_NUM + phone,num+"",Constants.REDIS_PHONE_NUM_TIME);
                expiryMap.remove(Constants.REDIS_SEND_ERROR_NUM + phone);
                return "0";
            }else{
                //如果一次不成功则多调用几次
                textNum += 1;
                expiryMap.put(Constants.REDIS_SEND_ERROR_NUM + phone,textNum+"");
                sendMsgConent(phone,content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "4";
    }



    public static boolean sendMsg(String phone, String content, String smsTemplateCode) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI46PzZikOoiTV", "0r3p51awVXcbEKUCjFAPrPhrkQrYs2");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "长安通信");
        request.putQueryParameter("TemplateCode", smsTemplateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + content + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            if (null == response) return false;
            Map<String,Object> map = JSON.parseObject(response.getData(),Map.class);
            String code = (String) map.get("Code");
            if ("OK".equals(code) ) return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String notifiApiTestSend(){
        try {
            //获取页面参数
            //用户账号
            String callerId = "天津市金融工作局";
            //密钥
            String password = "tjjrjd";
            //模板ID
            String templateId = "WUmwCDzQ7";
            //自定义参数，以逗号隔开
            String param = "11111";
            //手机号码
            String phone = "13021957907";
            //2.2调用发送方法
            String result = sendNotice(callerId,password,templateId,param,phone);
            System.out.println("发送结果==="+result);
        } catch (Exception e) {
            //LogUtils.error("发送服务通知短信失败", e);
        }
        return "";
    }


    private static String sendNotice(String callerId,String password,String templateId,String param,String phone) throws Exception {
        HttpURLConnection connection;
        BufferedReader reader;
        String line,result="";
        URL postUrl = new URL("http://120.197.89.51:80/EOPS1.0/notice/send_01");
        String postSMS="<SMSEntity callerId=\""+callerId+"\"><password>"+password+"</password><templateId>"+templateId+
        "</templateId><param>"+param+"</param><phone>"
                +phone+"</phone></SMSEntity>";

        connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/xml");
        OutputStream os = connection.getOutputStream();
        os.write(postSMS.getBytes("utf-8"));
        os.flush();
        System.out.println(postSMS.toString());
        reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            result = line;
            line = reader.readLine();
        }
        connection.disconnect();
        return result;
    }



    private static String sendNotice1111(String callerId,String password,String templateId,String param,String phone) throws Exception {
        /*发送系统动态码：

         ******************************注释*********************************************

         *第一步：建立连接*

         *******************************************************************************/

        String getURL = "http://120.197.89.51:80/EOPS1.0/captcha/get/1380013800;callerId="+callerId+";"
                +"password="+password+";templateId="+templateId+";";
        URL getUrl = new URL(getURL);

        /******************************注释*********************************************
        *第二步：根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型 *
        *******************************************************************************/
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

        /******************************注释*********************************************
        *第三步： 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到 *
        *******************************************************************************/

        connection.connect();

        /******************************注释*********************************************
        *第四步：读取输入流,得到响应结果 *
        *******************************************************************************/
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码
        String lines;
        while ((lines = reader.readLine()) != null){
            lines = new String(lines.getBytes(), "utf-8"); return lines;}
        reader.close();

        /******************************注释*********************************************
        *第五步断开连接 *
        *******************************************************************************/
        connection.disconnect();
        return lines;
    }


}