package com.zhangju.xingquban.interestclassapp.ui.fragment.live;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.LivePayAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.PayTypeBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ExchangeFunBeanActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseBoundAccount;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.LiveWatchActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.ResInterface;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyPopupWindow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create  by hqf 2017/11/21
 * 直播付费页面
 */

@ContentView(R.layout.activity_live_pay)
public class LivePayActivity extends FastActivity implements View.OnClickListener {

    @BindView(R.id.live_pay_close)
    ImageView livePayClose;
    @BindView(R.id.live_image_head)
    RoundImageView liveImageHead;
    @BindView(R.id.live_user_name)
    TextView liveUserName;
    @BindView(R.id.live_pay_money)
    TextView livePayMoney;
    @BindView(R.id.tv_live_pay_btn)
    TextView tvLivePayBtn;
    @BindView(R.id.ll_live_pay_main)
    RelativeLayout llLivePayMain;

    private String chatroomId;
    private String rtmpPullUrl;
    private String accid;
    private String appKey;
    private String chatToken;
    private int stdCoin;
    private Double mSeeBalances;
    private double roomPrice;

    private String userToken;
    private String userPic;
    private String userName;
    private String roomName;
    private String roomPic;

    private View mPayView;






    private RelativeLayout mRlBalance;
    private RelativeLayout mRlWeiChat;
    private RelativeLayout mRlAlipay;
    private RelativeLayout mRlCoupon;
    private TextView mTvPay;

    private RadioGroup mRgPays;
    private RadioButton mRbBalance;
    private RadioButton mRbWeichat;
    private RadioButton mRbAlipay;
    private RadioButton mRbCoupon;
    private TextView mTvBalance;
    private String mStdCoin;

    int payFlag;
    private RelativeLayout mLlmain;

    @Override
    protected void alreadyPrepared() {
        userToken = UserManager.getInstance().getToken();
        userPic = UserManager.getInstance().getUser().picture;
        userName = UserManager.getInstance().getUser().signame;

        getIntentData();

    }

    private void getIntentData() {
        roomPic = getIntent().getStringExtra("roomPic");
        roomName = getIntent().getStringExtra("roomName");
        chatroomId = getIntent().getStringExtra("chatroomId");
        rtmpPullUrl = getIntent().getStringExtra("rtmpPullUrl");
        accid = getIntent().getStringExtra("accid");
        appKey = getIntent().getStringExtra("appKey");
        chatToken = getIntent().getStringExtra("chatToken");
        stdCoin = getIntent().getIntExtra("stdCoin", 0);
        mStdCoin = String.valueOf(getIntent().getIntExtra("stdCoin", 0));
        mSeeBalances = getIntent().getDoubleExtra("seeBalances", 0);//用户余额
        roomPrice = getIntent().getDoubleExtra("roomPrice", 0);

        Glide.with(LivePayActivity.this).load(roomPic).into(liveImageHead);//头像
        liveUserName.setText(roomName);
        livePayMoney.setText(roomPrice + "");

        getResPrice();

        mLlmain = (RelativeLayout) findViewById(R.id.ll_live_pay_main);

        // 弹窗布局
        mPayView = getLayoutInflater().inflate(R.layout.popupwindow_live_pay1, null); // 支付门票
        mRlBalance = (RelativeLayout) mPayView.findViewById(R.id.rl_live_pay_balance);
        mTvBalance = (TextView) mPayView.findViewById(R.id.tv_live_pay_balance);
        mRlWeiChat = (RelativeLayout) mPayView.findViewById(R.id.rl_live_pay_weiChat);
        mRlAlipay = (RelativeLayout) mPayView.findViewById(R.id.rl_live_pay_aliPay);
        mRlCoupon = (RelativeLayout) mPayView.findViewById(R.id.rl_live_pay_coupon);
        mTvPay = (TextView) mPayView.findViewById(R.id.tv_live_pay_affirm);

        //RadioGroup
        mRgPays = (RadioGroup) mPayView.findViewById(R.id.rg_live_pay);
        mRbBalance = (RadioButton) mPayView.findViewById(R.id.rb_live_pay_balance);
        mRbWeichat = (RadioButton) mPayView.findViewById(R.id.rb_live_pay_weichat);
        mRbAlipay = (RadioButton) mPayView.findViewById(R.id.rb_live_pay_alipay);
        mRbCoupon = (RadioButton) mPayView.findViewById(R.id.rb_live_pay_coupon);


        mPayView.setOnClickListener(this);
        tvLivePayBtn.setOnClickListener(this);
        mRlBalance.setOnClickListener(this);
        mRlWeiChat.setOnClickListener(this);
        mRlCoupon.setOnClickListener(this);
        mRlAlipay.setOnClickListener(this);
        mTvPay.setOnClickListener(this);


        if (mSeeBalances < roomPrice) { // 如果余额不足就让显示灰色,并不可点击
            mTvBalance.setTextColor(Color.rgb(179, 179, 179));
            mRlBalance.setEnabled(false);
            mRbBalance.setEnabled(false);
        }
        mTvBalance.setText("当前余额 :" + mSeeBalances);

        mRgPays.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_live_pay_balance:
                        payFlag = 4;
                        break;
                    case R.id.rb_live_pay_weichat:
                        payFlag = 3;
                        break;
                    case R.id.rb_live_pay_alipay:
                        payFlag = 1;
                        break;
                    case R.id.rb_live_pay_coupon:
                        payFlag = 7;
                        break;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.live_pay_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_pay_close:
                finish();
                break;

        }
    }

    //获取资源收费价格
    private void getResPrice() {
        final Request request = Request.obtain(ResInterface.POST_RES_PAY_TYPE);
        String token = UserManager.getInstance().getToken();
        request.put("model", "1");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<List<PayTypeBean>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<PayTypeBean>> result) {

                boolean success = result.success;
                if (success) {
                    List<PayTypeBean> payTypeBeen = result.data;

//                    PayDialog(payTypeBeen);动态支付。。。。
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);


    }








    //支付结果回调
    @Event
    private void ePayResult(EventPayResult payResult) {

        if (payResult.isSuccess()) {
            goWatch();
        }

    }

    //兴趣豆充值返回
    boolean isCoupon;

    @Override
    protected void onResume() {
        super.onResume();
        if (isCoupon) {
            isCoupon = false;
            getResPrice();

        }
    }

    //
    private void toPay(String type) {
        final Request request = Request.obtain(ResInterface.POST_LIVE_PAY);
        final String token = UserManager.getInstance().getToken();
        request.put("keyname", type);
        request.put("chatroomId", chatroomId);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<ResponseExchange>>() {

            @Override
            public void onResponseListener(Request r, Response<ResponseExchange> result) {

                boolean success = result.success;
                if (success) {
                    ResponseExchange data = result.data;

                    if (payFlag==3) {
                        ThirdPartyUtils.getInstance(LivePayActivity.this).payByWechat(data.appid, data.partnerid, data.prepayid, data.timestamp, data.packageValue, data.noncestr, data.sign);

                    } else if ( payFlag == 1) {
                        ThirdPartyUtils.getInstance(LivePayActivity.this).payByAli(LivePayActivity.this, data.sign);

                        //余额支付
                    } else {

                        goWatch();

                    }

                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }


    private void goWatch() {
        Intent intent = new Intent(LivePayActivity.this, LiveWatchActivity.class);
        intent.putExtra("chatToken", chatToken);
        intent.putExtra("accid", accid);
        intent.putExtra("appKey", appKey);
        intent.putExtra("rtmpPullUrl", rtmpPullUrl);
        intent.putExtra("chatroomId", chatroomId);
        intent.putExtra("mToken", userToken);
        intent.putExtra("stdCoin", stdCoin);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_live_pay_btn:
                PopupWindow popupWindow = MyPopupWindow.getInstance().makePopupWindow(this, mPayView);
                popupWindow.showAtLocation(mLlmain, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.rl_live_pay_balance: // 余额支付
                payFlag = 4;

                mRbBalance.setChecked(true);
                break;
            case R.id.rl_live_pay_weiChat: // 微信
                payFlag = 3;

                mRbWeichat.setChecked(true);
                break;
            case R.id.rl_live_pay_aliPay: // 支付宝
                payFlag = 1;

                mRbAlipay.setChecked(true);
                break;
            case R.id.rl_live_pay_coupon: // 优惠券:
                payFlag = 7;

                mRbCoupon.setChecked(true);
                break;

            case R.id.tv_live_pay_affirm: // 确认支付
                payAffirm();

                break;

        }
    }

    private void payAffirm() {
        switch (payFlag) {
            case 7:     // 门票优惠券
                toPay("coupon");

                break;
            case 4:     // 余额

                toPay("balances");

                break;
            case 3:     // 微信
                toPay("qqpay");

                break;
            case 1:     // 支付宝
                toPay("aipay");

                break;
            default:
                ToastUtil.showToast("请选择支付方式");
                break;
        }
    }
}
