package com.zhangju.xingquban.interestclassapp.ui.main;

import org.junit.Test;

import java.util.Random;

/**
 * @Created by  liush on 2018/3/29.
 * @describe ${TODO}
 */
public class MainActivityTest {

    @Test
    public void initView() {
        int size = 4;
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int o = random.nextInt(size);
            System.out.println(o);
        }
    }
}