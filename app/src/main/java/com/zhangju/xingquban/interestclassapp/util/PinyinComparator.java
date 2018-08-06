package com.zhangju.xingquban.interestclassapp.util;

import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CityNameBean.AaDataBean> {

	public int compare(CityNameBean.AaDataBean o1, CityNameBean.AaDataBean o2) {
		if (o1.getName().equals("@")
				|| o2.getAddrShortPinyin().equals("#")) {
			return -1;
		} else if (o1.getAddrShortPinyin().equals("#")
				|| o2.getName().equals("@")) {
			return 1;
		} else {
			return o1.getAddrShortPinyin().compareTo(o2.getAddrShortPinyin());
		}
	}


}
