package com.zhangju.xingquban.interestclassapp.util;

import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hqf on 2017/11/22.
 * 城市数据排序
 */

public class SortUtils {
    public static List<CityNameBean.AaDataBean> filledData(CityNameBean.AaDataBean[] date) {
        List<CityNameBean.AaDataBean> mSortList = new ArrayList<>();

        for (CityNameBean.AaDataBean aaDataBean : date) {

            CityNameBean.AaDataBean sortModel = new CityNameBean.AaDataBean();
            sortModel.setName(aaDataBean.getName());
            sortModel.setId(aaDataBean.getId());
            sortModel.setPid(aaDataBean.getPid());
            sortModel.setSpecialAreas(aaDataBean.isSpecialAreas());


            //汉字转换成拼音
            String pinyin = aaDataBean.getAddrShortPinyin();
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setAddrShortPinyin(sortString.toUpperCase());
            } else {
                sortModel.setAddrShortPinyin("#");
            }

            mSortList.add(sortModel);
        }


        return mSortList;

    }

}
