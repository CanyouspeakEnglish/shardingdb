package com.p4.bank.utilnew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 3des加密解密
 */
public class ThreeDesUtil {

    private static String KEY="abcdefghijklmnopqrstuvwx";
    public static final Logger log = LoggerFactory.getLogger(ThreeDesUtil.class);
    private static String DES_ALGORITHM="DESede/ECB/PKCS5Padding";

    /**
     * 加密
     * @param message 要加密的字符串
     * @param key 加密密钥
     * @return
     */
    public static String desEncrypt(String message) {

        String result = "";
        String newResult = "";
        if(message!=null && !"".equals(message)){
            try {
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, generateKey(KEY));
                byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8"));
                BASE64Encoder enc = new BASE64Encoder();
                result = enc.encode(resultBytes);
                newResult = filter(result);
            } catch (Exception e) {
                log.error("加密异常", e);
                return result;
            }
            log.debug("加密后的数据:" + newResult);
        }

        return newResult;
    }
    /**
     * 加密
     * @param message 要加密的字符串
     * @param key 加密密钥
     * @return
     */
    public static String desEncrypt(String message, String key) {
        //如果KEY为空，走默认KEY
        if(key==null || "".equals(key)){
            key=KEY;
        }
        String result = "";
        String newResult = "";
        if(message!=null && !"".equals(message)){
            try {
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
                byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8"));
                BASE64Encoder enc = new BASE64Encoder();
                result = enc.encode(resultBytes);
                newResult = filter(result);
            } catch (Exception e) {
                log.error("加密异常", e);
                return result;
            }
            log.debug("加密后的数据:" + newResult);
        }

        return newResult;
    }

    /**
     * 解密
     * @param message 要解密的字符串
     * @param key 解密密钥
     * @return
     */
    public static String desDecrypt(String message, String key) {
        //如果KEY为空，走默认KEY
        if(key==null || "".equals(key)){
            key=KEY;
        }
        String result = "";
        if(message!=null && !"".equals(message)){
            try {
                BASE64Decoder dec = new BASE64Decoder();
                byte[] messageBytes = dec.decodeBuffer(message);
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
                byte[] resultBytes = cipher.doFinal(messageBytes);
                result = new String(resultBytes, "UTF-8");
            } catch (Exception e) {
                log.error("解密异常", e);
                return result;
            }
            log.debug("解密后的数据:" + result);
        }
        return result;
    }
    /**
     * 解密
     * @param message 要解密的字符串
     * @param key 解密密钥
     * @return
     */
    public static String desDecrypt(String message) {
        String result = "";
        if(message!=null && !"".equals(message)){
            try {
                BASE64Decoder dec = new BASE64Decoder();
                byte[] messageBytes = dec.decodeBuffer(message);
                Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, generateKey(KEY));
                byte[] resultBytes = cipher.doFinal(messageBytes);
                result = new String(resultBytes, "UTF-8");
            } catch (Exception e) {
                log.error("解密异常", e);
                return result;
            }
            log.debug("解密后的数据:" + result);
        }
        return result;
    }

    /**
     * 去掉加密字符串换行符
     *
     * @param str
     * @return
     */
    public static String filter(String str) {
        String output = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if (asc != 10 && asc != 13) {
                sb.append(str.subSequence(i, i + 1));
            }
        }
        output = new String(sb);
        return output;
    }
    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException,InvalidKeyException,InvalidKeySpecException {

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        DESedeKeySpec keySpec = new DESedeKeySpec(secretKey.getBytes());
        keyFactory.generateSecret(keySpec);
        return keyFactory.generateSecret(keySpec);
    }

    public static void main(String[] args) {

        System.out.println(desDecrypt("Olw2uY5Q0UhiT1JFL7Js7gB74iUKQ7pH"));
    }
}