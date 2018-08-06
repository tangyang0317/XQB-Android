package com.zhangju.xingquban.interestclassapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Base64;

import com.zhangju.xingquban.interestclassapp.refactor.common.utils.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CommomAndroidTest {

    /**
     * 加密单元测试
     * @throws Exception
     */
    @Test
    public void testAESEncode()throws Exception{
        byte[] aesByte=Utils.aesEncryption("CBC","PKCS5padding","bicikeji@zhangju","zhangju@bicikeji","13353353534");
        byte[] desByte=Utils.desEncryption("CBC","PKCS5padding","bicikeji","17608419","13353353534");
        String s=Base64.encodeToString(aesByte,Base64.DEFAULT).trim();
        String s2=Base64.encodeToString(desByte,Base64.DEFAULT).trim();
        assertEquals("Pd+yrR6fvCe08g652MJc4w==",s);
        assertEquals("u7bNN170dw4ArNUn05uGxA==",s2);
    }
}