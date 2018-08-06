package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.hplper.CustomDrawerLayout;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollLinearLayoutManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.MessagePlatformAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.VipPlatform;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.DataPickerActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessageFabu;
import com.zhangju.xingquban.interestclassapp.util.TimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by hqf 2017/11/25
 * 我的vip商户--信息平台
 */
@ContentView(R.layout.activity_message_platform)
public class MessagePlatformActivity extends FastActivity {


    @BindView(R.id.find_message_return)
    LinearLayout findMessageReturn;
    @BindView(R.id.find_message_shaixuan)
    TextView findMessageShaixuan;

    @BindView(R.id.findmessage)
    RelativeLayout findmessage;
    @BindView(R.id.recyclerview_vip_info)
    RecyclerView recyclerviewVipInfo;
    @BindView(R.id.me_find_message_bj)
    ImageView meFindMessageBj;
    @BindView(R.id.find_message_radio_all)
    RadioButton findMessageRadioAll;
    @BindView(R.id.find_message_radio_zhaop)
    RadioButton findMessageRadioZhaop;
    @BindView(R.id.find_message_radio_zul)
    RadioButton findMessageRadioZul;
    @BindView(R.id.find_message_radio_zhaos)
    RadioButton findMessageRadioZhaos;
    @BindView(R.id.find_message_radiogroup1)
    RadioGroup findMessageRadiogroup1;
    @BindView(R.id.find_message_radio_zhuanr)
    RadioButton findMessageRadioZhuanr;
    @BindView(R.id.find_message_radio_qit)
    RadioButton findMessageRadioQit;
    @BindView(R.id.find_message_radiogroup2)
    RadioGroup findMessageRadiogroup2;
    @BindView(R.id.find_message_llxinxiliebie)
    LinearLayout findMessageLlxinxiliebie;
    @BindView(R.id.tv_now_time)
    TextView tvNowTime;
    @BindView(R.id.find_message_radio_buxian)
    RadioButton findMessageRadioBuxian;
    @BindView(R.id.find_message_radio_yizhou)
    RadioButton findMessageRadioYizhou;
    @BindView(R.id.find_message_radio_yiyue)
    RadioButton findMessageRadioYiyue;
    @BindView(R.id.find_message_radio_sanyue)
    RadioButton findMessageRadioSanyue;
    @BindView(R.id.find_message_radiogroup3)
    RadioGroup findMessageRadiogroup3;
    @BindView(R.id.find_message_radio_zidingy)
    RadioButton findMessageRadioZidingy;
    @BindView(R.id.find_message_radiogroup4)
    RadioGroup findMessageRadiogroup4;
    @BindView(R.id.linear_right)
    LinearLayout linear_right;
    @BindView(R.id.drawer_layout)
    CustomDrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;


    private String startTime = null;
    private String endTime = null;
    private String type="" ; /// null不传  1  (招生)  2  (招聘) 3  （租赁） 4  （转租）

    private MessagePlatformAdapter adapter;
    private List<VipPlatform> platformList = new ArrayList<>();
    boolean isRefresh;

    @Override
    protected void alreadyPrepared() {
        setVipPlatformAdapter();
        setonListenner();
        setDrawerLayout();
        getVipPlatformData();

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRefresh=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh){
            getVipPlatformData();
            isRefresh=false;
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * 获取vip信息平台数据
     */
    private void getVipPlatformData() {
        final Request request = Request.obtain(MeInterface.POST_PLATFORM_SEARCH);
        String token = UserManager.getInstance().getToken();
        request.addHeader("X-CustomToken", token);
        request.put("infoType", type);
        request.setListener(new SimpleListener<Response<List<VipPlatform>>>() {
            @Override
            public void onResponseListener(Request r, Response<List<VipPlatform>> result) {
                boolean success = result.success;
                if (success) {
                    List<VipPlatform> vipPlatformList = result.data;
                    platformList.clear();
                    if (platformList!=null){
                        platformList.addAll(vipPlatformList);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);

    }

    private void setDrawerLayout() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //Toolbar上面最左边显示三杠图标监听DrawerLayout
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerLayout.openDrawer(Gravity.RIGHT);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.closeDrawer(Gravity.RIGHT);


            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void setVipPlatformAdapter() {
        adapter = new MessagePlatformAdapter(MessagePlatformActivity.this, platformList);
        ScrollLinearLayoutManager manager = new ScrollLinearLayoutManager(MessagePlatformActivity.this);
        manager.setScrollEnabled(false);
        recyclerviewVipInfo.setLayoutManager(manager);
        recyclerviewVipInfo.setAdapter(adapter);
        adapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                adapter.setPosition(position);

            }
        });

    }

    private void setonListenner() {
        findMessageRadiogroup1.setOnCheckedChangeListener(listener1);
        findMessageRadiogroup2.setOnCheckedChangeListener(listener2);
        findMessageRadiogroup3.setOnCheckedChangeListener(listener3);
        findMessageRadiogroup4.setOnCheckedChangeListener(listener4);

        //处理drawableayout滑动事件冲突
        linear_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                }
                return true;
            }
        });
        setCurrentTime();
    }

    RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            findMessageRadiogroup2.setOnCheckedChangeListener(null);
            findMessageRadiogroup2.clearCheck();
            findMessageRadiogroup2.setOnCheckedChangeListener(listener2);
        }
    };
    RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            findMessageRadiogroup1.setOnCheckedChangeListener(null);
            findMessageRadiogroup1.clearCheck();
            findMessageRadiogroup1.setOnCheckedChangeListener(listener1);
        }
    };
    RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            findMessageRadiogroup4.setOnCheckedChangeListener(null);
            findMessageRadiogroup4.clearCheck();
            findMessageRadiogroup4.setOnCheckedChangeListener(listener4);
        }
    };
    RadioGroup.OnCheckedChangeListener listener4 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            findMessageRadiogroup3.setOnCheckedChangeListener(null);
            findMessageRadiogroup3.clearCheck();
            findMessageRadiogroup3.setOnCheckedChangeListener(listener3);
        }
    };

    private void setCurrentTime() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH) + 1);
        int day = now.get(Calendar.DAY_OF_MONTH);

        tvNowTime.setText(year + "/" + month + "/" + day);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.find_message_return, R.id.find_message_radio_zhuanr, R.id.find_message_radio_zhaos, R.id.find_message_radio_zul, R.id.find_message_radio_zhaop, R.id.find_message_radio_all, R.id.find_message_radio_buxian, R.id.find_message_radio_yizhou, R.id.find_message_radio_yiyue, R.id.find_message_shaixuan,  R.id.me_find_message_bj, R.id.find_message_radio_sanyue, R.id.find_message_radio_zidingy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_message_return:
                finish();
                break;
            case R.id.find_message_shaixuan:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.me_find_message_bj:
                startActivity(new Intent(this, FindMessageFabu.class));
                break;
            //全部
            case R.id.find_message_radio_all:
                type = "";
                search();

                break;
            //招聘
            case R.id.find_message_radio_zhaop:
                type = "2";
                search();
                break;
            //租赁
            case R.id.find_message_radio_zul:
                type = "3";
                search();
                break;
            //招生
            case R.id.find_message_radio_zhaos:
                type = "1";
                search();
                break;

            //转让
            case R.id.find_message_radio_zhuanr:
                type = "4";
                search();
                break;

            //不限
            case R.id.find_message_radio_buxian:
                startTime = null;
                search();
                break;

            //一周内
            case R.id.find_message_radio_yizhou:
                startTime = TimeUtil.getTimeDetail(-7);
                search();
                break;

            //一月内
            case R.id.find_message_radio_yiyue:
                startTime = TimeUtil.getTimeDetail(-30);
                search();
                break;

            //三月内

            case R.id.find_message_radio_sanyue:
                startTime = TimeUtil.getTimeDetail(-90);
                search();
                break;


            ///自定义时间
            case R.id.find_message_radio_zidingy:
                Intent intent = new Intent(MessagePlatformActivity.this, DataPickerActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String time = data.getStringExtra("time").replace("年", "-").replace("月", "-").replace("日", "");
            String nowDate = TimeUtil.getNowDate();
            try {
                int day = TimeUtil.getdaysBetween(nowDate, time);
                if (day == 0) {
                    startTime = TimeUtil.getNowDate() + "-00-00-00";
                } else {
                    startTime = TimeUtil.getTimeDetail(day);
                }
                search();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    private void search() {
        drawerLayout.closeDrawer(Gravity.RIGHT);
        getVipPlatformData();
    }

}
