package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrderActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zgl on 2017/11/24.
 */
@ContentView(R.layout.curriculum_ordersucess_pay)
public class CurriculumHomeOrderSucessActivity extends FastActivity implements View.OnClickListener {
    public static final String ARG_BEAN_DATA = "lessons";
    public static final String ARG_STRING_ORDERNUM = "order_num";
    @LocalData(ARG_BEAN_DATA)
    HomeDataTeacherBean.AaDataBean.LessonsBean lessonsBea;
    @LocalData(ARG_STRING_ORDERNUM)
    String ordernum;
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.tv_order_sucess)
    TextView tvOrderSucess;
    @BindView(R.id.tv_jxgoumai)
    TextView tvJxgoumai;
    @BindView(R.id.center)
    View center;
    @BindView(R.id.tv_godingdan)
    TextView tvGodingdan;
    @BindView(R.id.re_dingdan)
    RelativeLayout reDingdan;
    @BindView(R.id.img_kcpic)
    ImageView imgKcpic;
    @BindView(R.id.tv_kcname)
    TextView tvKcname;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.order_num)
    TextView orderNum;



    @Override
    protected void alreadyPrepared() {
        bindDataToView();

    }

    private void bindDataToView() {
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Glide.with(this).load(lessonsBea.getPicture()).into(imgKcpic);
        tvKcname.setText(lessonsBea.getName());
        tvSummary.setText(lessonsBea.getDescript());
        orderNum.setText(ordernum);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tv_jxgoumai,R.id.tv_godingdan})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_jxgoumai:
                finish();
                break;
            case R.id.tv_godingdan:
                if(UserManager.getInstance().isLogin()){
                    startActivity(new Intent(this, OrderActivity.class));
                }
                break;
        }
    }



}
