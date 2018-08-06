package com.zhangju.xingquban.interestclassapp.refactor.common.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密/解密工具类
 *
 * @author CHAILEILEI
 */
public class DESUtil {
    // 密钥 长度不得小于24
    private static String secretKey = "bicikeji#zhangju#xignquban";
    // 向量 可有可无 终端后台也要约定
    private static String iv = "17608419";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static byte[] encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        return cipher.doFinal(plainText.getBytes(encoding));
    }

    /**
     * 3DES解密
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(byte[] encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(encryptText);
        return new String(decryptData, encoding);
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secretKey) {
        DESUtil.secretKey = secretKey;
    }

    public static String getIv() {
        return iv;
    }

    public static void setIv(String iv) {
        DESUtil.iv = iv;
    }
}
