package com.zhangju.xingquban.interestclassapp.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @Created by  liush on 2017/2/24.
 * @describe ${json转换类}
 */

public class JsonUtils {


    @SuppressWarnings("unchecked")
    public static Map<String,Object> json2Map(String json){
        return JSON.parseObject(json, Map.class);
    }

    public static String obj2JsonString(Object obj){
        return JSON.toJSONString(obj);
    }

}
