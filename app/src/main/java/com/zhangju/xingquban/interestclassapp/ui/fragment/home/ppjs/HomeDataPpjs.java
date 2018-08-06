package com.zhangju.xingquban.interestclassapp.ui.fragment.home.ppjs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.bean.near.NearJiGouJianjie;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.MapNaviActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zzhoujay.richtext.RichText;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.activity_home_data_ppjs)
public class HomeDataPpjs extends FastActivity {
    public static final String ARG_STRING_TEACHER = "TEACHER_DATA";
    @LocalData(ARG_STRING_TEACHER)
    HomeDataTeacherBean teacherBean;

    @BindView(R.id.home_data_ppjs_head)
    PublicHead homeDataPpjsHead;
    @BindView(R.id.headpic)
    ImageView headpic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.home_recyclerview_item_huiyuan)
    ImageView homeRecyclerviewItemHuiyuan;
    @BindView(R.id.fensishu)
    TextView fensishu;
    @BindView(R.id.kechengshu)
    TextView kechengshu;
    @BindView(R.id.home_data_wz)
    ImageView homeDataWz;
    @BindView(R.id.home_data_address)
    TextView homeDataAddress;
    @BindView(R.id.home_data_city)
    RelativeLayout homeDataCity;
    @BindView(R.id.img_sfrz)
    ImageView imgSfrz;
    @BindView(R.id.tv_sfrz)
    TextView tvSfrz;
    @BindView(R.id.img_ptrz)
    ImageView imgPtrz;
    @BindView(R.id.tv_ptrz)
    TextView tvPtrz;
    @BindView(R.id.img_zzrz)
    ImageView imgZzrz;
    @BindView(R.id.tv_zzrz)
    TextView tvZzrz;
    @BindView(R.id.home_data_attestation)
    LinearLayout homeDataAttestation;
    @BindView(R.id.summary)
    TextView summary;

    private Intent intent;
    private Bundle bundle;

    public void setHomeDataPpjsHead() {
        homeDataPpjsHead.setTitle("机构简介");
        homeDataPpjsHead.setShow(true, false, false);
        homeDataPpjsHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void alreadyPrepared() {
        setHomeDataPpjsHead();
        Glide.with(HomeDataPpjs.this).load(teacherBean.getAaData().get(0).getPicture()).into(headpic);
        name.setText(teacherBean.getAaData().get(0).getName());
        summary.setText(teacherBean.getAaData().get(0).getSummary());
        bindRenzheng();
        initRequest();
    }
    private void initRequest(){
        final Request request = Request.obtain(INear.POST_JIGOUXIANGQING);
        request.put("customerId", teacherBean.getAaData().get(0).getCustomerId());
        request.setListener(new SimpleListener<NearJiGouJianjie>(){

            @Override
            public void onResponseListener(Request r, NearJiGouJianjie result) {
                if(result.getAaData().size()>0){
                    String jianjie= result.getAaData().get(0).getIntro();
                    String[] tags=jianjie.split("#");
                    List<String> fuwenben= Arrays.asList(tags);
                    StringBuffer ss=new StringBuffer();
                    for (int i = 0; i < fuwenben.size(); i++) {
                        if(fuwenben.get(i).substring(0,1).equals("h")){
                            ss.append("<img src=\""+fuwenben.get(i)+"\">");
                        }else {
                            ss.append("<p>"+fuwenben.get(i)+"</p>");
                        }
                    }
                    String finWenben=ss.toString();
                    RichText.from(finWenben).autoFix(true) // 是否自动修复，默认true
                           .into(summary);
                }else {
                    summary.setText("");
                }

            }
        });
        net(request);
    }
    private void bindRenzheng() {
        //资质认证
        if (teacherBean.getAaData().get(0).getQcAuth() == 2) {
            Glide.with(this).load(R.mipmap.home_data_rz).into(imgZzrz);
        } else {
            Glide.with(this).load(R.mipmap.find_huodong_dianda).into(imgZzrz);
        }
        if (teacherBean.getAaData().get(0).getDegreeId().equals("1")) {
            if (teacherBean.getAaData().get(0).getRealnameAuth() == 2) {
                Glide.with(this).load(R.mipmap.home_data_rz).into(imgSfrz);
            } else {
                Glide.with(this).load(R.mipmap.find_huodong_dianda).into(imgSfrz);
            }

        } else {
            imgSfrz.setVisibility(View.GONE);
            tvSfrz.setVisibility(View.GONE);
        }
        //身份认证
        kechengshu.setText("共"+teacherBean.getAaData().get(0).getLessons().size()+"个课程");
        fensishu.setText(teacherBean.getAaData().get(0).getCollectionNum()+"粉丝");
        homeDataAddress.setText(teacherBean.getAaData().get(0).getClassRoom());

    }
    @Bind(R.id.home_data_city)
    private void navigation(){
        intent = new Intent(this, MapNaviActivity.class);
        bundle = new Bundle();
        bundle.putDouble(MapNaviActivity.ARG_DOUBLE_LAT, teacherBean.getAaData().get(0).getLat());
        bundle.putDouble(MapNaviActivity.ARG_DOUBLE_LNG, teacherBean.getAaData().get(0).getLng());
        bundle.putString(MapNaviActivity.ARG_STRING_NAME, teacherBean.getAaData().get(0).getSigname());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
