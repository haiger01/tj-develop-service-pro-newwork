package com.scistor.develop.util;


import com.loopj.android.http.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.scistor.develop.tools.BlockAttribute.RSA_PRIVATEKEY;
import static com.scistor.develop.tools.BlockAttribute.RSA_PUBLICKEY;


/**
 * RSA 加解密的util
 */
public class RSAUtil {


    /**
     * RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024
     */
    public static final int KEY_SIZE = 1024;


    /**
     * 公钥加密  加密
     *
     * @param pubKey
     * @param data
     * @return
     */
    public static String encode(String pubKey, String data) {
        KeyFactory factory = null;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(pubKey, Base64.DEFAULT));
            factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(data.getBytes());
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 私钥解密
     *
     * @param priKey
     * @param encodeData
     * @return
     */
    public static String decode(String priKey, String encodeData) throws Exception {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(priKey, Base64.DEFAULT));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(Base64.decode(encodeData, Base64.DEFAULT));
        return new String(bytes);


    }

    /**
     * 创建公私钥
     */
    public static HashMap<String, String> createKeyMap() {
        KeyPairGenerator keyPairGenerator = null;
        HashMap<String, String> map = new HashMap<>();
        String publicKey = "";
        String privateKey = "";
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey aPublic = (RSAPublicKey) generateKeyPair.getPublic();
            RSAPrivateKey aPrivate = (RSAPrivateKey) generateKeyPair.getPrivate();
            publicKey = Base64.encodeToString(aPublic.getEncoded(), Base64.DEFAULT);
            privateKey = Base64.encodeToString(aPrivate.getEncoded(), Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        map.put("publicKey", publicKey);
        map.put("privateKey", privateKey);
        return map;
    }


}
