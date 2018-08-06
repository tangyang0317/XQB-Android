package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.bean.OrderRefresh;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zgl on 2017/11/24.
 */
@ContentView(R.layout.curriculum_order)
public class CurriculumOrderActivity extends FastActivity implements View.OnClickListener{
    public static final  String ARG_BEAN_DATA ="lessons";
    @LocalData(ARG_BEAN_DATA)
    CurriculumBean.AaDataBean lessonsBea;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.img_kcpic)
    ImageView imgKcpic;
    @BindView(R.id.tv_kcname)
    TextView tvKcname;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.img_jian)
    ImageView imgJian;
    @BindView(R.id.img_juan)
    ImageView imgJuan;
    @BindView(R.id.img_jians)
    ImageView imgJians;
    @BindView(R.id.tv_kcprice)
    TextView tvKcprice;
    @BindView(R.id.tv_kcprice_all)
    TextView tvKcpriceAll;
    @BindView(R.id.tv_ph)
    TextView tvPh;
    @BindView(R.id.tv_phonenum)
    TextView tvPhonenum;
    @BindView(R.id.tv_order_commit)
    TextView tvOrderCommit;

    private int ordernum=1;
    private JSONArray jsonArray=new JSONArray();
    @Override
    protected void alreadyPrepared() {
        bindDataToView();
    }
    private void bindDataToView(){
       Glide.with(this).load(lessonsBea.getPicture()).into(imgKcpic);
        tvKcname.setText(lessonsBea.getName());
        tvSummary.setText(lessonsBea.getDescript());
        tvOrderNum.setText(ordernum+"");


        tvKcprice.setText("￥"+lessonsBea.getVipPrice()+"");

        tvKcpriceAll.setText("￥"+lessonsBea.getVipPrice()*ordernum+"");

        tvPhonenum.setText(UserManager.getInstance().getUser().phone);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.back,R.id.img_add,R.id.img_jian,R.id.tv_order_commit})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.img_add:
                if(ordernum<=1) break;
                ordernum--;
                tvOrderNum.setText(ordernum+"");

                tvKcprice.setText("￥"+lessonsBea.getVipPrice()*ordernum+"");

                    tvKcpriceAll.setText("￥"+lessonsBea.getVipPrice()*ordernum+"");

                break;
            case R.id.img_jian:
                ordernum++;
                tvOrderNum.setText(ordernum+"");

                tvKcprice.setText("￥"+lessonsBea.getVipPrice()*ordernum+"");
                tvKcpriceAll.setText("￥"+lessonsBea.getVipPrice()*ordernum+"");

            break;
            case R.id.tv_order_commit:

                Intent intent=new Intent(this,CurriculumOrderPayActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable(CurriculumOrderPayActivity.ARG_BEAN_DATA,lessonsBea);
                bundle.putInt( CurriculumOrderPayActivity.ARG_INT_NUM,ordernum);
                intent.putExtras(bundle);
                startActivity(intent);
                EventObserver.getInstance().sendEvent(CurriculumOrderActivity.this,new OrderRefresh());
               // dingdan();
                break;
        }
    }
    private void dingdan(){
        if(ordernum<=0){

            return;
        }
        JSONObject jo1 = new JSONObject(true);
        jo1.fluentPut("price",lessonsBea.getVipPrice());
        jo1.fluentPut("lessonId", lessonsBea.getId());
        jo1.fluentPut("counts", ordernum);
        jsonArray.add(jo1);
        Request request=Request.obtain(INear.POST_ORDER_CURRICULUM);
        request.put("applyName",UserManager.getInstance().getUser().username);
        request.put("phone",UserManager.getInstance().getUser().phone);
        request.put("customerId",UserManager.getInstance().getUser().id);
        request.put("json",jsonArray.toJSONString());
        request.put("orderType",3);
        request.setListener(new SimpleListener() {
            @Override
            public void onResponseListener(Request r, Object result) {

            }
        });
        net(request);

    }
}
