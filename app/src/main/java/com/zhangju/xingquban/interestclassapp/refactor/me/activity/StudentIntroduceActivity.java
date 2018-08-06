package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.hplper.CustomDrawerLayout;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.StudentIntroduceAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.StudentIntroduce;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindCityAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessageBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessageDataXq;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessageFabuCity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by hqf on 2017/11/25
 * vip商户--生源推荐
 */

@ContentView(R.layout.activity_student_introduce)
public class StudentIntroduceActivity extends FastActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.find_message_return)
    LinearLayout findMessageReturn;
    @BindView(R.id.find_message_shaixuan)
    TextView findMessageShaixuan;
    @BindView(R.id.swiperefreshlayout_student)
    SwipeRefreshRecyclerView swiperefreshlayoutStudent;
    @BindView(R.id.tv_student_subject)
    TextView tvStudentSubject;
    @BindView(R.id.tv_student_city)
    TextView tvStudentCity;
    @BindView(R.id.recyclerview_time)
    RecyclerView recyclerviewTime;
    @BindView(R.id.drawer_layout)
    CustomDrawerLayout drawerLayout;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.tv_span)
    TextView tv_span;
    private ActionBarDrawerToggle toggle;
    int pageIndex = 0;

    private StudentIntroduceAdapter adapter;
    private List<StudentIntroduce> mStudentList = new ArrayList<>();
    int total = 0;

    private List<String> mTime = Arrays.asList("不限", "一周内", "一月内", "三月内", "自定义");//城市数据
    private FindCityAdapter timeAdapter;

    int position = 0;
    boolean isRefresh;

    @Override
    protected void alreadyPrepared() {
        setStudentIntroduceAdapter();
        setTimeAdpter();
        setDrawerLayout();


        tv_span.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                }
                return true;
            }
        });

    }

    private void setTimeAdpter() {
        timeAdapter = new FindCityAdapter(StudentIntroduceActivity.this, mTime);
        recyclerviewTime.setLayoutManager(new GridLayoutManager(StudentIntroduceActivity.this, 4));
        recyclerviewTime.setAdapter(timeAdapter);
        timeAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                timeAdapter.setPosition(position);

            }
        });

    }

    private void setStudentIntroduceAdapter() {


        swiperefreshlayoutStudent.setOnListLoadListener(this);
        swiperefreshlayoutStudent.setOnRefreshListener(this);
        swiperefreshlayoutStudent.autoRefresh();


        adapter = new StudentIntroduceAdapter(StudentIntroduceActivity.this, mStudentList);
        swiperefreshlayoutStudent.setLayoutManager(new LinearLayoutManager(StudentIntroduceActivity.this));
        swiperefreshlayoutStudent.setAdapter(adapter);
        adapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                try {
                    String title = mStudentList.get(position).getTitle() == null ? "" : mStudentList.get(position).getTitle();
                    Integer infoType = mStudentList.get(position).getInfoType() == null ? 0 : mStudentList.get(position).getInfoType();
                    String cityName = mStudentList.get(position).getCityName() == null ? "" : mStudentList.get(position).getCityName();
                    String content = mStudentList.get(position).getContent() == null ? "" : mStudentList.get(position).getContent();
                    String id = mStudentList.get(position).getId() == null ? "" : mStudentList.get(position).getId();
                    String addUserTime = mStudentList.get(position).getAddUserTime() == null ? "" : mStudentList.get(position).getAddUserTime();


                    FindMessageBean.AaDataBean aaDataBean = new FindMessageBean.AaDataBean(title, infoType, cityName, content, id, "", addUserTime);


                    Intent intent = new Intent(StudentIntroduceActivity.this, FindMessageDataXq.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MeMessageData", aaDataBean);
                    intent.putExtras(bundle);
                    intent.putExtra("tag", position);
                    startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });
    }

    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swiperefreshlayoutStudent.setEnabledLoad(false);
            swiperefreshlayoutStudent.setLoading(false);
            return;
        }
        getStudentIntrduceData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh){
            swiperefreshlayoutStudent.autoRefresh();
            isRefresh=false;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRefresh=true;
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        getStudentIntrduceData();

    }

    private void setSwipeEnable() {
        swiperefreshlayoutStudent.setRefreshing(false);
        swiperefreshlayoutStudent.setLoading(false);
        swiperefreshlayoutStudent.setEnabledLoad(true);
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
    private void getStudentIntrduceData() {
        final Request request = Request.obtain(MeInterface.POST_STUDENT_INTRODUCE);
        String token = UserManager.getInstance().getToken();
        request.addHeader("X-CustomToken", token);
        request.put("pageIndex", pageIndex + "");
        request.put("pageSize", "30");
        request.setListener(new SimpleListener<Response<List<StudentIntroduce>>>() {
            @Override
            public void onResponseListener(Request r, Response<List<StudentIntroduce>> result) {
                boolean success = result.success;
                if (success) {
                    total = result.total;
                    List<StudentIntroduce> data = result.data;
                    if (pageIndex == 0) {
                        mStudentList.clear();
                    }
                    mStudentList.addAll(data);
                }
                adapter.notifyDataSetChanged();
                setSwipeEnable();
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                setSwipeEnable();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_student_subject, R.id.tv_sure, R.id.tv_reset, R.id.tv_student_city, R.id.find_message_shaixuan, R.id.find_message_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_message_return:
                finish();
                break;
            case R.id.tv_student_subject:

                Intent intent = new Intent(this, CourseChoiceActivity.class);
                intent.putExtra("type", false);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_student_city:
                Intent intent1 = new Intent(this, FindMessageFabuCity.class);
                intent1.putExtra("position", position);
                startActivityForResult(intent1, 2);
                break;
            case R.id.find_message_shaixuan:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            //重置
            case R.id.tv_reset:
                tvStudentSubject.setText("");
                tvStudentCity.setText("");
                position = 0;
                timeAdapter.setPosition(0);

                break;
            //确定
            case R.id.tv_sure:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                swiperefreshlayoutStudent.autoRefresh();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //科目选择
            if (requestCode == 1) {
                String typeName = data.getStringExtra("typeName");
                String typeId = data.getStringExtra("typeId");
                String subName = data.getStringExtra("subName");
                String subId = data.getStringExtra("subId");

                tvStudentSubject.setText(subName);
//                mTvChoiceSubject.setText(subjectName);

                //城市选择
            } else if (requestCode == 2) {
                String city = data.getStringExtra("city");
                String[] split = city.split(",");
                position = Integer.parseInt(split[1]);//位置
                tvStudentCity.setText(split[0]);//具体城市

            }
        }
    }
}
