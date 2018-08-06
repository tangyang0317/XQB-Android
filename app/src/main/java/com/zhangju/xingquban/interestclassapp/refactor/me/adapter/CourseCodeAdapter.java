package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.base.OldViewHolder;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCheckCourse;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.QrCodeDialog;

import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 课程二维码适配器
 */
public class CourseCodeAdapter extends FastAdapter<ResponseCheckCourse.QrCode>{

    public CourseCodeAdapter(Context context) {
        super(context, R.layout.item_course_code);
    }

    public CourseCodeAdapter(Context context, List<ResponseCheckCourse.QrCode> data) {
        super(context,  R.layout.item_course_code, data);
    }

    @Override
    public void binding(int position, final ResponseCheckCourse.QrCode data, OldViewHolder holder) {
        String courseTitle="课程码："+data.qcode;

        ((ImageView)holder.getView(R.id.qrCode)).setImageBitmap(data.codeIcon);
        if(data.status==1){
            holder.setVisibility(R.id.mask,View.GONE);
            holder.setVisibility(R.id.maskText,View.GONE);
            holder.setText(R.id.courseCode, Utils.getTextSomeOtherColor(4,courseTitle.length(),courseTitle,mContext.getResources().getColor (R.color.EF4E4C)));
            holder.setOnClickListener(R.id.qrCode,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    QrCodeDialog dialog=new QrCodeDialog();

                    bundle.putString(QrCodeDialog.ARG_STR_CODE,data.qcode);
                    dialog.setArguments(bundle);
                    dialog.show(((AppCompatActivity)mContext).getSupportFragmentManager(),"qrCode");
                }
            });
        }
        else{
            holder.setVisibility(R.id.mask,View.VISIBLE);
            holder.setVisibility(R.id.maskText,View.VISIBLE);
            holder.setText(R.id.courseCode, Utils.getTextSomeOtherColor(4,courseTitle.length(),courseTitle+"（已使用）",mContext.getResources().getColor (R.color.EF4E4C)));
            holder.setOnClickListener(R.id.qrCode,null);
            ((TextView)holder.getView(R.id.courseCode)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}