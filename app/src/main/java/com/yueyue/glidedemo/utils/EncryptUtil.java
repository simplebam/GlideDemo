package com.yueyue.glidedemo.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * author : yueyue on 2018/5/10 14:48
 * desc   :
 */
public class EncryptUtil {

    //http://www.cnblogs.com/shanyou/p/5474647.html
    private static final String HMAC_SHA1_NAME = "HmacSHA1";
    private static final String UTF8_ENCODING = "UTF-8";

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *  @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     *                    引用1:https://blog.csdn.net/janronehoo/article/details/7590976
     */
    public static byte[] HmacSHA1(String encryptText, String encryptKey) {
        try {
            byte[] data = encryptKey.getBytes(UTF8_ENCODING);
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, HMAC_SHA1_NAME);
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(HMAC_SHA1_NAME);
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);

            byte[] text = encryptText.getBytes(UTF8_ENCODING);
            //完成 Mac 操作
            return mac.doFinal(text);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            ToastUtil.showShort("HmacSHA1错误:" + e.toString());
        }
        return null;
    }


    /**
     * URL安全的Base64编码，解码 - CSDN博客 https://blog.csdn.net/mr_raptor/article/details/50806657
     * @param data
     * @return
     */
    public static String safeUrlBase64Encode(byte[] data) {
        String encodeBase64 = Base64.encodeToString(data, Base64.URL_SAFE | Base64.NO_WRAP);
        return encodeBase64.replace('+', '-')
                .replace('/', '_')
                .replaceAll("=", "");
    }

    //二行制转字符串
    public static String byte2hex(byte[] bytes) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; bytes != null && n < bytes.length; n++) {
            stmp = Integer.toHexString(bytes[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
