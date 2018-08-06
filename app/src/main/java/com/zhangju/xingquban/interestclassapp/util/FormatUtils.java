package com.zhangju.xingquban.interestclassapp.util;

import java.text.DecimalFormat;

/**
 * Created by hqf on 2017/10/27.
 */

public class FormatUtils {
    /**
     * @param var0
     * @param var1
     * @return
     */
    public static String getIntDivision(int var0, int var1) {
        float num = (float) var0 / var1;
        DecimalFormat decimal = new DecimalFormat("0.0");
        return decimal.format(num);

    }

}
