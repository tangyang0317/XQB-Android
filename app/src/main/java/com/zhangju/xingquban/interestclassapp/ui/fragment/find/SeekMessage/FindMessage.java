package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.util.TimeUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//信息平台主页
public class FindMessage extends BaseActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "FindMessage";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.find_message_return)
    LinearLayout findMessageReturn;
    @BindView(R.id.find_message_shaixuan)
    TextView findMessageShaixuan;
    @BindView(R.id.find_message_quxiao)
    TextView findMessageQuxiao;
    @BindView(R.id.find_message_radiogroup1)
    RadioGroup findMessageRadiogroup1;
    @BindView(R.id.find_message_radiogroup2)
    RadioGroup findMessageRadiogroup2;
    @BindView(R.id.find_message_llxinxiliebie)
    LinearLayout findMessageLlxinxiliebie;
    @BindView(R.id.find_message_radiogroup3)
    RadioGroup findMessageRadiogroup3;
    @BindView(R.id.find_message_radiogroup4)
    RadioGroup findMessageRadiogroup4;
    @BindView(R.id.me_find_message_bj)
    ImageView meFindMessageBj;
    @BindView(R.id.linear_right)
    LinearLayout linear_right;
    @BindView(R.id.findmessage)
    RelativeLayout findmessage;
    @BindView(R.id.find_message_radio_all)
    RadioButton findMessageRadioAll;
    @BindView(R.id.find_message_radio_zhaop)
    RadioButton findMessageRadioZhaop;
    @BindView(R.id.find_message_radio_zul)
    RadioButton findMessageRadioZul;
    @BindView(R.id.find_message_radio_zhaos)
    RadioButton findMessageRadioZhaos;
    @BindView(R.id.find_message_radio_zhuanr)
    RadioButton findMessageRadioZhuanr;
    @BindView(R.id.find_message_radio_qit)
    RadioButton findMessageRadioQit;
    @BindView(R.id.find_message_radio_buxian)
    RadioButton findMessageRadioBuxian;
    @BindView(R.id.find_message_radio_yizhou)
    RadioButton findMessageRadioYizhou;
    @BindView(R.id.find_message_radio_yiyue)
    RadioButton findMessageRadioYiyue;
    @BindView(R.id.find_message_radio_sanyue)
    RadioButton findMessageRadioSanyue;
    @BindView(R.id.find_message_radio_zidingy)
    RadioButton findMessageRadioZidingy;
    @BindView(R.id.findMessageRecycler)
    SwipeRefreshRecyclerView findMessageRecycler;
    @BindView(R.id.tv_now_time)
    TextView tvNowTime;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;

    private List<FindMessageBean.AaDataBean> mInfoList = new ArrayList<>();
    private FindMessageAdapter findMessageAdapter;
    private ActionBarDrawerToggle toggle;
    private Subscription subscription;

    private String startTime = null;
    private String endTime = null;
    private Integer type = null; /// null不传  1  (招生)  2  (招聘) 3  （租赁） 4  （转租）

    private int pageIndex = 0;
    private int total = 0;//总页数

    Observer<FindMessageBean> observer1 = new Observer<FindMessageBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            setSwipe();
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final FindMessageBean findMessageBean) {


            if (findMessageBean.isSuccess()) {
                total = findMessageBean.getTotal();
                if (pageIndex == 0) {
                    mInfoList.clear();
                }
                if (findMessageBean.getAaData() != null && findMessageBean.getAaData().size() > 0) {
                    mInfoList.addAll(findMessageBean.getAaData());
                }
                findMessageAdapter.notifyDataSetChanged();
                setSwipe();
            } else {
                ToastUtil.showToast("获取失败");
            }
        }
    };
    private BannerHelper mBannerHelper;

    private void setSwipe() {
        findMessageRecycler.setRefreshing(false);
        findMessageRecycler.setLoading(false);
        findMessageRecycler.setEnabledLoad(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_find_message;
    }

    @Override
    public void initView() {

        setMessagePlatformAdapter();

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

    //信息平台
    private void setMessagePlatformAdapter() {
        findMessageRecycler.setOnListLoadListener(this);
        findMessageRecycler.setOnRefreshListener(this);
        findMessageRecycler.autoRefresh();

        findMessageAdapter = new FindMessageAdapter(FindMessage.this, mInfoList);
        findMessageRecycler.setLayoutManager(new LinearLayoutManager(FindMessage.this));
        findMessageRecycler.setAdapter(findMessageAdapter);

        findMessageAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent = new Intent(mContext, FindMessageDataXq.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MeMessageData", mInfoList.get(position));
                intent.putExtras(bundle);
                intent.putExtra("tag", position);
                startActivity(intent);
            }
        });
    }

    //刷新更多
    @Override
    public void onListLoad() {

        pageIndex++;
        if (pageIndex + 1 > total) {
            findMessageRecycler.setEnabledLoad(false);
            findMessageRecycler.setLoading(false);
            return;
        }
        getTiWenData();


    }

    private void setCurrentTime() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH) + 1);
        int day = now.get(Calendar.DAY_OF_MONTH);

        tvNowTime.setText(year + "/" + month + "/" + day);

    }

    //刷新第一页
    @Override
    public void onRefresh() {
        pageIndex = 0;
        getTiWenData();
    }


    public void getTiWenData() {
        endTime = TimeUtil.getTimeDetail(0);
        subscription = NetWork.getMeTiwen().myMessage(type, pageIndex, "10", startTime, endTime, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    boolean isRefresh;

    @Override
    protected void onPause() {
        super.onPause();
        isRefresh = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isRefresh) {
            pageIndex = 0;
            getTiWenData();
            isRefresh = false;
        }

        //用户类型判断
        User user = UserManager.getInstance().getUser();
        User.Degree degree = user.degree;
        boolean isMember = user.isMember;
        if (degree != null) {
            boolean isCommon = degree.isCommon;
            if (isCommon) {
                meFindMessageBj.setVisibility(View.GONE);
            } else {
                if (isMember) {
                    meFindMessageBj.setVisibility(View.VISIBLE);
                }

            }

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

    @Override
    public void initData() {
        endTime = TimeUtil.getTimeDetail(0);
        setDrawerLayout();
        initBanner();

    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("17");
    }

    public void setDrawerLayout() {
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
                findMessageShaixuan.setVisibility(View.GONE);
                findMessageQuxiao.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(Gravity.RIGHT);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                findMessageShaixuan.setVisibility(View.VISIBLE);
                findMessageQuxiao.setVisibility(View.GONE);
                drawerLayout.closeDrawer(Gravity.RIGHT);


            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    @Override
    public void initListener() {
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

    @OnClick({R.id.find_message_return, R.id.find_message_radio_zhuanr, R.id.find_message_radio_zhaos, R.id.find_message_radio_zul, R.id.find_message_radio_zhaop, R.id.find_message_radio_all, R.id.find_message_radio_buxian, R.id.find_message_radio_yizhou, R.id.find_message_radio_yiyue, R.id.find_message_shaixuan, R.id.find_message_quxiao, R.id.me_find_message_bj, R.id.find_message_radio_sanyue, R.id.find_message_radio_zidingy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_message_return:
                finish();
                break;
            case R.id.find_message_shaixuan:
                findMessageShaixuan.setVisibility(View.GONE);
                findMessageQuxiao.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.find_message_quxiao:
                findMessageShaixuan.setVisibility(View.VISIBLE);
                findMessageQuxiao.setVisibility(View.GONE);
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.me_find_message_bj:
                startActivity(new Intent(this, FindMessageFabu.class));
                break;
            //全部
            case R.id.find_message_radio_all:
                type = null;
                search();
                break;
            //招聘
            case R.id.find_message_radio_zhaop:
                type = 2;
                search();
                break;
            //租赁
            case R.id.find_message_radio_zul:
                type = 3;
                search();
                break;
            //招生
            case R.id.find_message_radio_zhaos:
                type = 1;
                search();
                break;

            //转让
            case R.id.find_message_radio_zhuanr:
                type = 4;
                search();
                break;
            case R.id.find_message_radio_qit:
                type = 5;
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
                Intent intent = new Intent(FindMessage.this, DataPickerActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    private void search() {
        pageIndex = 0;
        findMessageRecycler.autoRefresh();
        drawerLayout.closeDrawer(Gravity.RIGHT);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBannerHelper.stopBanner();
    }
}
