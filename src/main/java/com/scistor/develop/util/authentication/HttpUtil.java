package com.scistor.develop.util.authentication;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpUtil {

    private static String charset = "utf-8";

	public static String httpGet(String url){
		/*Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);*/
		HttpClient http = new  HttpClient();
		String result = "";
		//get请求
		GetMethod get = new GetMethod(url);
		// 设置请求报文头的编码
		//		get.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		get.setRequestHeader("Content-Type", "multipart/form-data;charset=utf-8");

		try {
			http.executeMethod(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream(), "utf-8"));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				if (tmp.contains("appDescribe")) {
					//System.out.println("----"+tmp);
					tmp = "\"appDescribe\":"+"\""+tmp.split(":")[1].substring(1, tmp.split(":")[1].length()-1).replaceAll("\"", "").replaceAll("&ldquo;", "“").replaceAll("&rdquo;", "”")+"\",";
					//System.out.println("tmp:"+tmp);
				}
				result += tmp + "\r\n";
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 以post方式请求外部接口
	 * @param urlStr - 接口路径
	 * @param param - 接口参数，参数格式key=value，多个参数以&符号分割，如：name=wzx&age=18
	 * @return
	 */
	public static String httpPost(String urlStr, String param) {
		return httpPost(urlStr, param, "application/x-www-form-urlencoded");
	}

	/**
	 * 以post方式请求外部接口
	 * @param urlStr - 接口路径
	 * @param param - 接口参数，参数格式key=value，多个参数以&符号分割，如：name=wzx&age=18
	 * @param contentType - http传输的内容类型，如：application/x-www-form-urlencoded、application/json
	 * @return
	 */
	public static String httpPost(String urlStr, String param, String contentType) {
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
//			httpUrlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Content-type", contentType);

			// 连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
			httpUrlConnection.connect();

			os = httpUrlConnection.getOutputStream();
			//使用utf-8编码解决中文乱码
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
//			System.out.println("return === " + strBuilder);
//			Map map = JSON.parseObject(JSON.parse(strBuilder.toString()).toString(), Map.class);
//			if(map != null) {
//			if(map.containsKey("isLogin"))
//				System.out.println("isLogin === " + map.get("isLogin"));
//			if(map.containsKey("errorMessage"))
//				System.out.println("errorMessage === " + map.get("errorMessage"));
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
       	   try {
       		   if(inStrm != null)
    			   inStrm.close();
           } catch (IOException e) {
        	   e.printStackTrace();
           }
		}
		return null;
	}


	/**
	 * POST请求，Map形式数据
	 * @param url 请求地址
	 * @param param 请求数据
	 * @param charset 编码方式
	 */
	public static String sendPost(String url, Map<String, String> param,
								  String charset) throws UnsupportedEncodingException {

		StringBuffer buffer = new StringBuffer();
		if (param != null && !param.isEmpty()) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
//				String key = entry.getKey();
//				String value = entry.getValue();
//				String encodeValue = URLEncoder.encode("百度", "UTF-8");
//				System.out.println(encodeValue);
//				buffer = key + "=" + encodeValue + "&";
				buffer.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
						.append("&");

			}
		}
		buffer.substring(0, buffer.length() - 1);

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(buffer);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}


    public static void main(String[] args) {
		/*Map<String,String> paramMap = new HashMap<>();
		paramMap.put("username","qy-000011");
		paramMap.put("password",AESUtils.encrypt58("123456"));
		paramMap.put("confirmPassword",AESUtils.encrypt58("123456"));
		paramMap.put("systemNumber","488196");
		paramMap.put("identifier","11");*/

		String passAES = AESUtils.encrypt58("qq123456");
		String username = "qy-000011";
		String password = passAES;
		String confirmPassword = passAES;
		String systemNumber = "488196";
		String identifier = "11";
		String auth_registerUrl = PropUtils.getProperty("auth_registerUrl");
		String param = "username=" + username + "&password=" + password + "&confirmPassword=" + confirmPassword + "&systemNumber=" + systemNumber + "&identifier=" + identifier;
		String str = HttpUtil.httpPost(auth_registerUrl,param);
		System.out.println(">>>>>>>" + str);

    	//String str = HttpUtil.httpGet("http://10.120.24.12:9994/v1/app/getAppListByPpatformId?platformId=254");
		//System.out.println(">>>>>>>" + str);
    }
}

