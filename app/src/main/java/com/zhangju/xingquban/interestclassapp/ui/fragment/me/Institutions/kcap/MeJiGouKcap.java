package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouKcap extends BaseActivity {
    public static final String TAG = "";

    @BindView(R.id.me_jg_kcap_head)
    PublicHead meJgKcapHead;

    @BindView(R.id.text12)
    CheckBox text12;
    @BindView(R.id.text13)
    CheckBox text13;
    @BindView(R.id.text14)
    CheckBox text14;
    @BindView(R.id.text15)
    CheckBox text15;
    @BindView(R.id.text16)
    CheckBox text16;
    @BindView(R.id.text17)
    CheckBox text17;
    @BindView(R.id.text18)
    CheckBox text18;

    @BindView(R.id.text22)
    CheckBox text22;
    @BindView(R.id.text23)
    CheckBox text23;
    @BindView(R.id.text24)
    CheckBox text24;
    @BindView(R.id.text25)
    CheckBox text25;
    @BindView(R.id.text26)
    CheckBox text26;
    @BindView(R.id.text27)
    CheckBox text27;
    @BindView(R.id.text28)
    CheckBox text28;

    @BindView(R.id.text32)
    CheckBox text32;
    @BindView(R.id.text33)
    CheckBox text33;
    @BindView(R.id.text34)
    CheckBox text34;
    @BindView(R.id.text35)
    CheckBox text35;
    @BindView(R.id.text36)
    CheckBox text36;
    @BindView(R.id.text37)
    CheckBox text37;
    @BindView(R.id.text38)
    CheckBox text38;

    CheckBox[] afternoonBefore = new CheckBox[]{text12, text13, text14, text15, text16, text17, text18};
    CheckBox[] afternoonBefore2 = new CheckBox[]{text22, text23, text24, text25, text26, text27, text28};
    CheckBox[] afternoonBefore3 = new CheckBox[]{text32, text33, text34, text35, text36, text37, text38};

    private Subscription subscription;
    Observer<Object> observer1 = new Observer<Object>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(Object o) {
            Log.i(TAG, "onNext: " + o);

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcap;
    }

    @Override
    public void initView() {
        setMeJgKcapHead();
    }

    @Override
    public void initData() {


        //case
        //if ischeck
        // text34.setC(f)
        //Hashmap<int boolean>
        //for(i 21){ hashmap.add(i,text32.getcheck())}
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void getTiWenData() {

    }

    public void setMeJgKcapHead() {
        meJgKcapHead.setTitle("课程添加");
        meJgKcapHead.setShow(true, false, false);
        meJgKcapHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < afternoonBefore.length; i++)
                    map.put("b_0_" + i, String.valueOf(afternoonBefore[i].isChecked()));
                for (int i = 0; i < afternoonBefore2.length; i++)
                    map.put("b_1_" + i, String.valueOf(afternoonBefore2[i].isChecked()));
                for (int i = 0; i < afternoonBefore3.length; i++)
                    map.put("b_2_" + i, String.valueOf(afternoonBefore3[i].isChecked()));

                subscription = NetWork.getMe().plan2(UserManager.getInstance().getUser().teacherTimeId, map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer1);
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
