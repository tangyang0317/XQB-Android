package com.zhangju.xingquban.interestclassapp.ui.fragment.near;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.NearDataAdapter;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.NearMSRight;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NearDistrictBean;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearShare.NearShareActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//课程列表
public class DistrictActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.district_return)
    LinearLayout districtReturn;
    @BindView(R.id.near_edit_share)
    LinearLayout nearEditShare;

    @BindView(R.id.near_recylerview)
    RecyclerView nearRecylerview;
    @BindView(R.id.near_pulltorefrsh)
    PullToRefreshScrollView nearPulltorefrsh;
    private Context mContext = this;

    public static final String TAG = "DistrictActivity";
    public static final String PAGE_INDEX = "1";


    private SharedPreferences spf;

    private int page = 1;
    private List<NearSubjectBean.AaDataBean> mParentLists = new ArrayList<>();

    private Subscription subscription;
    private NearDataAdapter nearDataAdapter;
    private List<NearDataBean.AaDataBean> list = new ArrayList<>();
    //科目数据集合
    private List<NearMSRight.AaDataBean> listSubjectLeft = new ArrayList<>();
    private List<ArrayList<NearMSRight.AaDataBean.ChildsBean>> listSubjectRignt = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Map<String, String> conditions = new HashMap<>();
    private List<NearDistrictBean.AaDataBean> districtBean = new ArrayList<>();

    //获取默认数据
    private Observer<NearDataBean> observer = new Observer<NearDataBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(NearDataBean nearDataBean) {
            if (page == 1) {
                list = nearDataBean.getAaData();
            } else {
                list.addAll(nearDataBean.getAaData());
            }
            Log.i(TAG, "on-----------: " + list.size());
            Log.i(TAG, "on-----------: " + page);
            nearDataAdapter = new NearDataAdapter(mContext, list);
            nearRecylerview.setAdapter(nearDataAdapter);
            nearPulltorefrsh.onRefreshComplete();
            Log.i(TAG, "onNext: " + nearDataBean.getAaData().toString());

        }
    };

    //获取教师数据
    private Observer<NearDataBean> observerTeacher = new Observer<NearDataBean>() {

        @Override
        public void onCompleted() {
            Log.i(TAG, "onCompleted: -------------");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(NearDataBean nearDataBean) {
            list = nearDataBean.getAaData();
            nearDataAdapter.setTeacherData(list);
            Log.i(TAG, "onNext: " + nearDataBean.getAaData().toString());
        }
    };
    //获取机构的数据
    private Observer<NearDataBean> observerInstitution = new Observer<NearDataBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(NearDataBean nearDataBean) {
            list = nearDataBean.getAaData();
            nearDataAdapter.setInstitutionData(list);
            Log.i(TAG, "onNext: " + nearDataBean.getAaData().toString());
        }
    };
    //获取科目选项中左边的数据
    private Observer<NearSubjectBean> observerSubject = new Observer<NearSubjectBean>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onNext=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(mContext, e.getMessage(), ToastUtil.LENGTH_LONG).show();
        }

        @Override
        public void onNext(NearSubjectBean nearSubjectBean) {
            Log.i(TAG, "======onNext: observerSubject======" + nearSubjectBean.getAaData().toString());
        }
    };
    //获取科目右边的数据
    private Observer<NearMSRight> observerNearMSRight = new Observer<NearMSRight>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onNext=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(mContext, e.getMessage(), ToastUtil.LENGTH_LONG).show();
        }

        @Override
        public void onNext(NearMSRight nearMSRight) {
            Log.i(TAG, "======nearMSRight: " + nearMSRight.getAaData().toString());
            listSubjectLeft = nearMSRight.getAaData();
            for (int i = 0; i < listSubjectLeft.size(); i++) {
                ArrayList<NearMSRight.AaDataBean.ChildsBean> list = new ArrayList<>();
                list.addAll(listSubjectLeft.get(i).getChilds());
                listSubjectRignt.add(list);
            }

        }
    };
    //区域的地区名数据
    private Observer<NearDistrictBean> observerNearDistrict = new Observer<NearDistrictBean>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onCompleted=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(mContext, e.getMessage(), ToastUtil.LENGTH_LONG).show();
        }

        @Override
        public void onNext(NearDistrictBean nearDistrictBean) {
            Log.i(TAG, "======nearDistrictBean: " + nearDistrictBean.getAaData().toString());
            districtBean = nearDistrictBean.getAaData();
            Log.i(TAG, "onNex========t: " + districtBean);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        ButterKnife.bind(this);

//        getNearSubject();
        //获取项目数据
        getNearMSRight();
        setNearPulltorefrsh();
        //初始化选项的本地数据


        getNearDistrict();

        nearRecylerview.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
//        linearLayoutManager.setAutoMeasureEnabled(true);
        nearRecylerview.setLayoutManager(linearLayoutManager);
        getData();
    }

   /* *//*
   * 向选项栏中添加数据
   * *//*
    private void addItemDatas() {
        if (isListNull(listSubjectLeft) && isListNull(listSubjectRignt)) {
            addItem(expandtabView, mPriceLists, "", "类型");
            addItem(expandtabView, listSubjectLeft, listSubjectRignt, "", "", "科目");
            addItem1(expandtabView, districtBean, mChildrenListLists, "", "", "区域");
            addItem(expandtabView, mSortLists, "", "筛选");
        }
    }*/

    /*
    * 判断集合是否为空，不为空就返回true
    * */
    private boolean isListNull(List<?> list) {
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @OnClick({R.id.near_edit_share, R.id.district_return})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.near_edit_share:
                startActivity(new Intent(mContext, NearShareActivity.class));
                break;

            case R.id.district_return:
                finish();
                break;
        }
    }








    //区域数据
    public void getNearDistrict() {
        subscription = NetWork.getNearDistrict().getNearDistrict(LocationManager.getInstance().getLocation().cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerNearDistrict);
    }

    //美术右数据
    public void getNearMSRight() {
        subscription = NetWork.getNearMSRight().getNearMSRight()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerNearMSRight);
    }

    //附近美术左
    public void getNearSubject() {
        subscription = NetWork.getNearSubject().getNearSubject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerSubject);
    }

    //附近获取默认数据
    public void getData() {
        Location location= LocationManager.getInstance().getLocation();
        subscription = NetWork.getNear().getNearDaat(location.longitude,location.latitude,location.cityId, page, "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //附近数据页码加载
    public void getDatapsf() {
        subscription = NetWork.getNearDatapsf().getNearDaatPtf(page, "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //附近老师数据
    public void getTeacherData() {
        Location location=LocationManager.getInstance().getLocation();
        subscription = NetWork.getNearTeacher().getNearTeacher(location.longitude,location.latitude,location.cityId, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerTeacher);
    }

    //附近机构数据
    public void getInstitutionData() {
        Location location=LocationManager.getInstance().getLocation();
        subscription = NetWork.getNearInstitution().getNearInstitution(location.longitude,location.latitude,location.cityId, "2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerInstitution);
    }

    //有条件查询页面数据
    public void getNearDatasCondition() {
        subscription = NetWork.getNearInstitution().getNearDatasCondition(conditions)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //数据刷新
    public void setNearPulltorefrsh() {
        nearPulltorefrsh.setMode(PullToRefreshBase.Mode.BOTH);
        nearPulltorefrsh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getData();
                page = 1;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                getDatapsf();
            }
        });
    }

    public String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
