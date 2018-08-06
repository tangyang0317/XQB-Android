package com.zhangju.xingquban.interestclassapp.refactor.common.utils;


import org.junit.Test;

import java.lang.reflect.Method;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @Created by  liush on 2018/3/16.
 * @describe ${TODO}
 */
public class DESUtilTest {
    @Test
    public void encode() throws Exception {
        // 密钥 长度不得小于24
        String secretKey = "bicikeji#zhangju#xignquban";
        // 向量 可有可无 终端后台也要约定
        String iv = "17608419";
        // 加解密统一使用的编码方式
        String encoding = "utf-8";

        String plainText = String.valueOf(System.currentTimeMillis());

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bytes = cipher.doFinal(plainText.getBytes(encoding));
        System.out.println(bytes.toString());

        System.out.println("当前毫秒值:" + plainText);
        String encode = encodeBase64(bytes);
        encode = encode.replace("+", "%2B").replace("\\", "%5C").replace(" ", "%20").replace("/", "%2F");
        System.out.println(encode);

    }

    public static String encodeBase64(byte[]input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }
}