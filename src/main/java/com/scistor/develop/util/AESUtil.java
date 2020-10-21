package com.scistor.develop.util;


import com.scistor.develop.util.base64.BASE64Decoder;
import com.scistor.develop.util.base64.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.scistor.develop.tools.BlockAttribute.RSA_PRIVATEKEY;


public class AESUtil {


    // 编码
    private static final String ENCODING = "UTF-8";
    //算法
    private static final String ALGORITHM = "AES";
    // 默认的加密算法
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";


    /**
     * 加密
     *
     * @param data
     * @return String
     * @throws Exception
     * @author tyg
     * @date 2018年6月28日下午2:50:35
     */
    public static String encrypt(String password, String data) throws Exception {

        if (password.length() > 16)
            password = password.substring(0, 16);
        for (int i = password.length(); i < 16; i++) {
            password += "a";
        }

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes("ASCII"), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(password.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes(ENCODING));
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。

    }

    /**
     * 解密
     *
     * @param data
     * @return String
     * @throws Exception
     * @author tyg
     * @date 2018年6月28日下午2:50:43
     */
    public static String decrypt(String password, String data) throws Exception {
        if (password.length() > 16)
            password = password.substring(0, 16);
        for (int i = password.length(); i < 16; i++) {
            password += "a";
        }

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes("ASCII"), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(password.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] buffer = new BASE64Decoder().decodeBuffer(data);
        byte[] encrypted = cipher.doFinal(buffer);
        return new String(encrypted, ENCODING);//此处使用BASE64做转码。


    }

    public static void main(String[] args) throws Exception {
        String randomNum = "MgUaNomwUXnHn2m46njlIPQ1jxOWTaDKfo1G/m07FN1Q8YQmHWL17FoPvKLgRgz9ajecu/Eym0IMdRpK+o9fFMKyPpimIvTpdhpB36y3WF9nAo6IEEcrspvirfpy5+vHcw3oPc0qdJw8nd42A7/Rj9tlN0jIOWmHQaj6eh3N4Is=";
        String str = "fZty4uL/OGZPr4yUBKskUj4Asbu1MJFjJKcTbwgDqvY=";
        //加密后的值
        
        //System.out.println(AESUtil.encrypt(code, code));
        String a = RSAUtil.decode(RSA_PRIVATEKEY, randomNum.replaceAll(" ", "+"));
        System.out.println(a);

        System.out.println(AESUtil.decrypt(a, str));
    }


}