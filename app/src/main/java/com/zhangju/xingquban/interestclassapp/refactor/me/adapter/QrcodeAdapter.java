package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.base.OldViewHolder;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCheckCourse;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/12/14.
 */
public class QrcodeAdapter extends FastAdapter<ResponseCheckCourse.QrCode>{

    public QrcodeAdapter(Context context) {
        super(context, R.layout.item_qrcode);
    }

    public QrcodeAdapter(Context context, List<ResponseCheckCourse.QrCode> data) {
        super(context,R.layout.item_qrcode, data);
    }

    @Override
    public void binding(int position, ResponseCheckCourse.QrCode data, OldViewHolder holder) {
        TextView textView=holder.getView(R.id.text);
        String text=String.format(Locale.getDefault(),"课程号：%s",data.qcode);

        if(data.status==1){
            textView.setText(Utils.getTextSomeOtherColor(5,text.length(),text,mContext.getResources().getColor(R.color.EF4E4C)));
        }
        else{
            textView.setText(text);
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
