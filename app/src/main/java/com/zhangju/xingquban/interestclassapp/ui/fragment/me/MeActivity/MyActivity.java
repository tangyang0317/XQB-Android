package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.huodongshouru.MeActivityHdsr;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.jianpiao.MeActivityJianp;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wocanyude.MeActivityWcyd;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodeguanzhu.MeActivityWdgz;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodepiaoquan.MeActivityWdpj;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.MeActivityWfbd;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.user.MeUserBean;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyActivity extends BaseActivity {
    public static final String TAG = "MyActivity";

    @BindView(R.id.me_activity_head)
    PublicHead meActivityHead;
    @BindView(R.id.me_activity_icon)
    CustomRoundView meActivityIcon;
    @BindView(R.id.me_name)
    TextView meName;
    @BindView(R.id.me_activity_user)
    LinearLayout meActivityUser;
    @BindView(R.id.me_activity_wfbd)
    LinearLayout meActivityWfbd;
    @BindView(R.id.me_activity_hdsr)
    LinearLayout meActivityHdsr;
    @BindView(R.id.me_activity_ksjp)
    LinearLayout meActivityKsjp;
    @BindView(R.id.me_activity_zbf)
    TextView meActivityZbf;
    @BindView(R.id.me_activity_cyz)
    TextView meActivityCyz;
    @BindView(R.id.me_activity_jigou)
    LinearLayout meActivityJigou;
    @BindView(R.id.me_activity_wdpj)
    LinearLayout meActivityWdpj;
    @BindView(R.id.me_activity_wcyd)
    LinearLayout meActivityWcyd;
    @BindView(R.id.me_activity_wdgz)
    LinearLayout meActivityWdgz;
    @BindView(R.id.me_activity_student)
    LinearLayout meActivityStudent;

    Observer<MeUserBean> observer1 = new Observer<MeUserBean>() {


        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(MeUserBean meUserBean) {
            Log.i(TAG, "meUserBean: " + meUserBean.toString());
            String s = meUserBean.getAaData().get(0).getDegreeId();

            if (MeUserBean.AaDataBean.JG.equals(s)) {
                meActivityStudent.setVisibility(View.GONE);
                meActivityJigou.setVisibility(View.VISIBLE);
            } else if (MeUserBean.AaDataBean.Teacher.equals(s)) {
                meActivityStudent.setVisibility(View.GONE);
                meActivityJigou.setVisibility(View.VISIBLE);
            } else if (MeUserBean.AaDataBean.Student.equals(s)) {
                meActivityStudent.setVisibility(View.VISIBLE);
                meActivityJigou.setVisibility(View.GONE);
            }


        }
    };
    private Subscription suscription;

    public void getGRMessage() {
        suscription = NetWork.getMe().getMeGRMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my;
    }

    @Override
    public void initView() {
        setMeActivityHead();
        getGRMessage();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_wfbd, R.id.me_activity_hdsr, R.id.me_activity_ksjp, R.id.me_activity_wdpj, R.id.me_activity_wcyd, R.id.me_activity_wdgz})
    public void onClick(View v) {

        switch (v.getId()) {

            //我发布的
            case R.id.me_activity_wfbd:
                startActivity(new Intent(this, MeActivityWfbd.class));
                break;

            //活动收入
            case R.id.me_activity_hdsr:
                startActivity(new Intent(this, MeActivityHdsr.class));
                break;

            //开始检票
            case R.id.me_activity_ksjp:
                startActivity(new Intent(this, MeActivityJianp.class));
                break;

            //我的票券
            case R.id.me_activity_wdpj:
                startActivity(new Intent(this, MeActivityWdpj.class));
                break;

            //我参与的
            case R.id.me_activity_wcyd:
                startActivity(new Intent(this, MeActivityWcyd.class));
                break;

            //我的关注
            case R.id.me_activity_wdgz:
                startActivity(new Intent(this, MeActivityWdgz.class));
                break;
        }

    }

    //head
    public void setMeActivityHead() {
        meActivityHead.setTitle("我的活动");
        meActivityHead.setShow(true, false, false);
        meActivityHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
