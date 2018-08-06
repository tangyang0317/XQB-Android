package com.zhangju.xingquban.interestclassapp.refactor.common.utils;

import android.util.Base64;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2017/12/13.
 * 通用工具包
 */
public class Utils{

    private Utils(){}

    public static byte[] aesEncryption(String encryptionMode, String paddingMode, String password, String initOffset, String message)throws Exception{
        return encryption("AES",encryptionMode,paddingMode,password,initOffset,message);
    }

    public static byte[] desEncryption(String encryptionMode, String paddingMode,String password,String initOffset, String message)throws Exception{
        return encryption("DESede",encryptionMode,paddingMode,password,initOffset,message);
    }

    private static byte[] encryption(String mode,String encryptionMode,String paddingMode,String password,String initOffset,String message)throws Exception{
        byte[] byteContent=message.getBytes();
        IvParameterSpec ivParams=new IvParameterSpec(initOffset.getBytes());
        SecretKeySpec key=new SecretKeySpec(password.getBytes(),mode);

        Cipher cipher=Cipher.getInstance(String.format(Locale.getDefault(),"%s/%s/%s",mode,encryptionMode,paddingMode));
        cipher.init(Cipher.ENCRYPT_MODE,key,ivParams);
        return cipher.doFinal(byteContent);
    }
}