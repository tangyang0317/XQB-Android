package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.datepicker.CalendarDay;
import com.zhangju.xingquban.interestclassapp.datepicker.MaterialCalendarView;
import com.zhangju.xingquban.interestclassapp.datepicker.OnDateSelectedListener;
import com.zhangju.xingquban.interestclassapp.datepicker.OnMonthChangedListener;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataPickerActivity extends BaseActivity implements OnDateSelectedListener, OnMonthChangedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @BindView(R.id.find_seekactivity)
    PublicHead head;
    @BindView(R.id.calendarView)
    MaterialCalendarView widget;

    private String time = "";


    @Override
    public int getLayout() {
        return R.layout.activity_data_picker;
    }

    @Override
    public void initView() {
        head.setTitle("选择时间");
        head.setRightTitle("确定");
        head.setRightTextColor(R.color.color_main);

        head.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        head.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("time", time);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {

        //展示当前时间
        Calendar instance = Calendar.getInstance();
        widget.setSelectedDate(instance.getTime());

        time=getSelectedDatesString();

        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);


        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR) - 50, Calendar.JANUARY, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR) + 50, Calendar.DECEMBER, 31);

        widget.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {


//        String selectedDatesString = getSelectedDatesString();
        time = getSelectedDatesString();
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

//        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }


}
