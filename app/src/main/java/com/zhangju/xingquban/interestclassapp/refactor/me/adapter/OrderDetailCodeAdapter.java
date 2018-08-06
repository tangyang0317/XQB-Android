package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.base.OldViewHolder;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCheckCourse;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/15.
 * 订单详情中课程码列表适配器
 */
public class OrderDetailCodeAdapter extends FastAdapter<ResponseCheckCourse.QrCode>{

    public OrderDetailCodeAdapter(Context context) {
        super(context,android.R.layout.simple_list_item_1);
    }

    public OrderDetailCodeAdapter(Context context, List<ResponseCheckCourse.QrCode> data) {
        super(context,android.R.layout.simple_list_item_1, data);
    }

    @Override
    public void binding(int position, ResponseCheckCourse.QrCode data, OldViewHolder holder){
        String s=String.format(Locale.getDefault(),"课程号：%s",data.qcode);
        holder.setText(android.R.id.text1, Utils.getTextSomeOtherColor(4,s.length(),s,mContext.getResources().getColor(R.color.EF4E4C)));
    }
}