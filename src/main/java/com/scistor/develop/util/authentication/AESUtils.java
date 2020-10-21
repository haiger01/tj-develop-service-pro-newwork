package com.scistor.develop.util.authentication;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by myl on 17/1/5.
 */
public class AESUtils {

    //登录密码,手机号,身份证,加密
	//public static final String AES_KEY = "J44AZQhZxBEpbPR8";
	//public static final String AES_IV = "J44AZQhZxBEpbPR8";
    public static final String AES_KEY = "J74AgQhZxxeKbP18";
    public static final String AES_IV = "J74AgQhZxxeKbP18";

    //	public static final String AES_IV = "financeX98AF32Qe";
	public static String encrypt(String data){
        try {
        	if (null == data || "".equals(data)) return null;
            String key = AES_KEY;
            String iv = AES_KEY;

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     * @param data
     * @param aes_key
     * @param aes_iv
     * @return
     */
    public static String encrypt(String data,String aes_key,String aes_iv){
        try {
            if (null == data || "".equals(data)) return null;
            String key = aes_key;
            String iv = aes_iv;

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	/**
	 * 加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt58(String data) {
        try {
        	if (null == data || "".equals(data)) return "";
            String key = AES_KEY;
            String iv = AES_KEY;

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base58().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
	
    public static String decrypt(String data) {
        try {
        	if (null == data || "".equals(data)) return null;
        	
            String key = AES_KEY;
            String iv = AES_KEY;

            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"UTF-8");
            return originalString.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt58(String data,String... aesKey) {
        try {
        	if (null == data || "".equals(data)) return null;
        	
            String key = AES_KEY;
            String iv = AES_KEY;
            if(aesKey!=null&&aesKey.length>0){
                key=aesKey[0];
                iv=aesKey[0];
            }

            //byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
            byte[] encrypted1 = new Base58().decode(data);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"UTF-8");
            return originalString.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    //请求解析参数

    /**
     * 解析参数
     * @param data  解析内容
     * @param key
     * @param iv
     * @return
     */
    public static String decrypt(String data,String key,String iv){
        try {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "UTF-8");
            return originalString.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) throws Exception {
        System.out.println(encrypt58("张三"));
       
        
        System.out.println(decrypt58("7Mn57fu2FpBuTi52sQ9BSw"));
    }

}
