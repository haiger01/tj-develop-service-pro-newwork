package com.scistor.develop.util.authentication;

public class UUIDUtil {

	/**
	 * 生成唯一标识符
	 * @return
	 */
	public static String generateUUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String generateApiKey(){
		try{
			String apikey= Base58.encode(generateUUID().getBytes("UTF-8"));
			if(apikey.indexOf("=")>-1){
				return generateApiKey();
			}else{
				return apikey;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
