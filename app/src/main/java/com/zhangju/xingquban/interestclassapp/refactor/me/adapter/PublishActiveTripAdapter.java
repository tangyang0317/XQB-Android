package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.app.FastDialog;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.utils.TimeUtil;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddTripActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveTrip;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.PublishActiveTripFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动行程适配器
 */
public class PublishActiveTripAdapter extends FastAdapterForRecycler<PublishActiveTrip>{
    private int mAddInfoPosition=0; //记录Activity返回时传递的新信息
    private Fragment mHost;

    public PublishActiveTripAdapter(Fragment fragment){
        super(fragment.getContext(),R.layout.item_publish_active_trip);
        mHost=fragment;
    }

    @Override
    public void binding(final int position, final PublishActiveTrip data, CommonViewHolder holder) {
        holder.setText(R.id.title, data.title);
        holder.setText(R.id.time,data.time);
        holder.setText(R.id.content,data.content);

        holder.setVisibility(R.id.up,View.VISIBLE);
        holder.setVisibility(R.id.down,View.VISIBLE);
        holder.setVisibility(R.id.addBelow,position==getItemCount()-1?View.VISIBLE:View.GONE);
        if(position==0) //隐藏上移图标
            holder.setVisibility(R.id.up,View.INVISIBLE);
        if(position==getItemCount()-1) //隐藏下移
            holder.setVisibility(R.id.down,View.INVISIBLE);
        holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FastDialog.showMessageDialog("确定删除该模块？",true).show(((AppCompatActivity) mContext).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData().remove(position);
                        notifyItemRemoved(position);
                    }
                });
            }
        });
        holder.setOnClickListener(R.id.up, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData().remove(position);
                getData().add(position-1,data);
                notifyDataSetChanged();
            }
        });
        holder.setOnClickListener(R.id.down, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData().remove(position);
                getData().add(position+1,data);
                notifyDataSetChanged();
            }
        });
        holder.setOnClickListener(R.id.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddInfoPosition=position;
                mHost.startActivityForResult(new Intent(mContext,AddTripActivity.class),PublishActiveTripFragment.REQ_ADD_TRIP);
            }
        });
        holder.setOnClickListener(R.id.addBelow, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mAddInfoPosition=position+1;
                mHost.startActivityForResult(new Intent(mContext,AddTripActivity.class),PublishActiveTripFragment.REQ_ADD_TRIP);
            }
        });
        holder.setOnClickListener(R.id.time, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final String dateFormat="yyyy/MM/dd EE kk:mm";
                String dateStr=((TextView)v).getText().toString();
                Date date= TextUtils.isEmpty(dateStr)?new Date():TimeUtil.StringToDate(dateStr,dateFormat);
                final Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);
                DatePickerDialog dateDialog=new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                        TimePickerDialog timeDialog=new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                                calendar.set(year,month,dayOfMonth,hourOfDay,minute);
                                data.time=TimeUtil.dateToString(calendar.getTime(),dateFormat);
                                notifyDataSetChanged();
                            }
                        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                        timeDialog.show();
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dateDialog.show();
            }
        });
    }

    @Override
    public void addData(PublishActiveTrip data) {
        getData().add(mAddInfoPosition,data);
        notifyDataSetChanged();
    }
}