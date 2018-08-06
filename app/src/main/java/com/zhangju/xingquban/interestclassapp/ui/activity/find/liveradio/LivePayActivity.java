package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;/*
package com.interest.dhy.interestclassapp.ui.activity.find.liveradio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

*/
/**
 * Created by liush on 2016/11/30 0030.
 *
 * @直播付费页面
 *//*

public class LivePayActivity
        extends Activity
        implements View.OnClickListener {

    private ImageView    mIvAvatar;
    private TextView     mTvName;
    private TextView     mTvPrice;
    private TextView     mTvInto;
    private LinearLayout mLlmain;
    private View         mPayView;

    private ProgressDialog progressDialog;


    */
/**
     * 支付宝
     *//*

    // 商户PID
    public static final  String PARTNER      = "2088121490046212";
    // 商户收款账号
    public static final  String SELLER       = "zhangju_vip@sina.com";
    // 商户私钥，pkcs8格式
    public static final  String RSA_PRIVATE  = "";
    // 支付宝公钥
    public static final  String RSA_PUBLIC   = "";
    private static final int    SDK_PAY_FLAG = 1;
    */
/*
     * 微信支付
     *//*

    private IWXAPI api;
    int payFlag;


    private String token;
    private String mAppKey;
    private String mAccid;
    private String mChatroomId;
    private String mChatToken;
    private String mVideoPath; //拉流地址


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    */
/**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     *//*

                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    String total_fee = payResult.getTotal_fee();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        System.out.println(resultInfo);
                        Toast.makeText(LivePayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        goWatch();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(LivePayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(LivePayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private Context        mContext;
    private double         mSeeBalances; // 用户余额
    private float          mRoomPrice; // 直播间价格
    private ImageView      mIvExit;
    private RelativeLayout mRlBalance;
    private RelativeLayout mRlWeiChat;
    private RelativeLayout mRlAlipay;
    private RelativeLayout mRlCoupon;
    private TextView       mTvPay;

    private RadioGroup  mRgPays;
    private RadioButton mRbBalance;
    private RadioButton mRbWeichat;
    private RadioButton mRbAlipay;
    private RadioButton mRbCoupon;
    private TextView    mTvBalance;
    private String      mStdCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_pay);
        mContext = this;

        initView();
        initData();
        initRadioButton();
        getPayTypes();
    }


    private void goWatch() {
        Intent intent = new Intent(LivePayActivity.this, LiveWatchActivity.class);
        intent.putExtra("chatToken", mChatToken);
        intent.putExtra("accid", mAccid);
        intent.putExtra("appKey", mAppKey);
        intent.putExtra("rtmpPullUrl", mVideoPath);
        intent.putExtra("chatroomId", mChatroomId);
        intent.putExtra("mToken", token);
        intent.putExtra("stdCoin", mStdCoin);
        startActivity(intent);
        finish();
    }

    private void initData() {
        Intent intent = getIntent();
        mChatroomId = intent.getStringExtra("chatroomId");
        mVideoPath = intent.getStringExtra("rtmpPullUrl");
        mAccid = intent.getStringExtra("accid");
        mAppKey = intent.getStringExtra("appKey");
        mChatToken = intent.getStringExtra("chatToken");
        mStdCoin = intent.getStringExtra("stdCoin");
        mSeeBalances = intent.getDoubleExtra("seeBalances", 0); // 用户当前余额 String型

        token = intent.getStringExtra("mToken");
        mRoomPrice = intent.getFloatExtra("roomPrice", -1);

        // 传递微信支付需要的数据
        SpUtil.putString(this, "PayFlag", "live");
        SpUtil.putString(this, "chatroomId", mChatroomId);
        SpUtil.putString(this, "rtmpPullUrl", mVideoPath);
        SpUtil.putString(this, "accid", mAccid);
        SpUtil.putString(this, "appKey", mAppKey);
        SpUtil.putString(this, "chatToken", mChatToken);
        SpUtil.putString(this, "mToken", token);
        SpUtil.putString(this, "liveStdCoin", mStdCoin);

        mTvPrice.setText(mRoomPrice + "");
        if (mSeeBalances < mRoomPrice) { // 如果余额不足就让显示灰色,并不可点击
            mTvBalance.setTextColor(Color.rgb(179, 179, 179));
            mRlBalance.setEnabled(false);
            mRbBalance.setEnabled(false);
        }
        mTvBalance.setText("当前余额 :" + mSeeBalances);
    }

    private void initView() {
        // 主界面布局
        mLlmain = (LinearLayout) findViewById(R.id.ll_live_pay_main);
        mIvAvatar = (ImageView) findViewById(R.id.iv_live_pay_avatar);
        mTvName = (TextView) findViewById(R.id.tv_live_pay_name);
        mTvPrice = (TextView) findViewById(R.id.tv_live_pay_price);
        mTvInto = (TextView) findViewById(R.id.tv_live_pay_btn);
        mIvExit = (ImageView) findViewById(R.id.iv_live_pay_exit);
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


        mIvExit.setOnClickListener(this);
        mTvInto.setOnClickListener(this);
        mPayView.setOnClickListener(this);
        mRlBalance.setOnClickListener(this);
        mRlWeiChat.setOnClickListener(this);
        mRlCoupon.setOnClickListener(this);
        mRlAlipay.setOnClickListener(this);
        mTvPay.setOnClickListener(this);
    }

    @Override
    public void onComplete(View v) {
        switch (v.getId()) {
            case R.id.tv_live_pay_btn:
                PopupWindow popupWindow = MyPopupWindow.getInstance().makePopupWindow(this, mPayView);
                popupWindow.showAtLocation(mLlmain, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.rl_live_pay_balance: // 余额支付
                payFlag = 4;
                */
/*toPay("balances", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean result) {
                        if (success) {
                            LivePayBean.AaDataBean aaData = result.getAaData();
                            if (aaData != null) {
                                boolean ispay = aaData.isIspay();
                                if (ispay) {
                                    ToastUtil.showToast(mContext, "支付成功");
                                    goWatch();
                                } else {
                                    ToastUtil.showToast(mContext, "余额不足,支付失败");
                                }
                            }
                        }
                    }
                });*//*

                mRbBalance.setChecked(true);
                break;
            case R.id.rl_live_pay_weiChat: // 微信
                payFlag = 3;
                */
/*toPay("qqpay", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean result) {

                    }
                });*//*

                mRbWeichat.setChecked(true);
                break;
            case R.id.rl_live_pay_aliPay: // 支付宝
                payFlag = 1;
                */
/*toPay("aipay", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean bean) {
                        if (success) {

                        }
                    }
                });*//*

                mRbAlipay.setChecked(true);
                break;
            case R.id.rl_live_pay_coupon: // 优惠券:
                payFlag = 7;

                mRbCoupon.setChecked(true);
                break;

            case R.id.iv_live_pay_exit: //退出
                finish();
                break;
            case R.id.tv_live_pay_affirm: // 确认支付
                payAffirm();

                break;
        }
    }

    */
/**
     * 确认支付
     *//*

    private void payAffirm() {
        switch (payFlag) {
            case 7:     // 门票优惠券
                toPay("coupon", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean result) {
                        if (success) {
                            LivePayBean.AaDataBean aaData = result.getAaData();
                            if (aaData != null) {
                                boolean ispay = aaData.isIspay();
                                if (ispay) {
                                    ToastUtil.showToast(mContext, "支付成功");
                                    goWatch();
                                } else {
                                    String s = result.getErrMsg().toString();
                                    ToastUtil.showToast(mContext, s);
                                }
                            }
                        }
                    }
                });
                break;
            case 4:     // 余额
                toPay("balances", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean result) {
                        if (success) {
                            LivePayBean.AaDataBean aaData = result.getAaData();
                            if (aaData != null) {
                                boolean ispay = aaData.isIspay();
                                if (ispay) {
                                    ToastUtil.showToast(mContext, "支付成功");
                                    goWatch();
                                } else {
                                    String s = result.getErrMsg().toString();
                                    ToastUtil.showToast(mContext, s);
                                }
                            }
                        }
                    }
                });
                break;
            case 3:     // 微信
                toPay("qqpay", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean result) {

                    }
                });
                break;
            case 1:     // 支付宝
                toPay("aipay", new SimpleCallback<LivePayBean>() {
                    @Override
                    public void onResult(boolean success, LivePayBean bean) {
                        if (success) {

                        }
                    }
                });
                break;
            default:
                ToastUtil.showToast(mContext, "请选择支付方式");
                break;
        }
    }

    private void initRadioButton() {
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

    */
/**
     * 支付宝支付
     *
     * @param sign
     *//*

    private void startAliPay(final String sign) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(LivePayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(sign, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    */
/**
     * 微信支付
     *
     * @param mData
     *//*

    protected void startWxPay(LivePayBean.AaDataBean mData) {
        api = WXAPIFactory.createWXAPI(this, mData.getAppid());
        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = mData.getAppid();
        req.partnerId = mData.getPartnerid();
        req.prepayId = mData.getPrepayid();
        req.nonceStr = mData.getNoncestr();
        req.timeStamp = mData.getTimestamp();
        req.packageValue = mData.getPackageValue();
        req.sign = mData.getSign();
        req.extData = "app data"; // optional
        Toast.makeText(LivePayActivity.this, "正常调起微信支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    */
/**
     * 支付
     *//*

    private void toPay(String keyname, final SimpleCallback<LivePayBean> callback) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken",token);
        params.addBodyParameter("chatroomId", mChatroomId);
        params.addBodyParameter("keyname", keyname);
        String url = UrlUtils.URL_PAYTV2;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        progressDialog.dismiss();
                        String json = responseInfo.result;
                        LivePayBean bean = JSONObject.parseObject(json, LivePayBean.class);
                        if (bean.isSuccess()) {
                            callback.onResult(true, bean);
                            LivePayBean.AaDataBean data = bean.getAaData();
                            if (data == null)
                                return;

                            switch (payFlag) {
                                case 1: // 支付宝
                                    if (data.getSign() != null) {
                                        startAliPay(data.getSign());//支付宝支付
                                    }
                                    break;
                                case 3: // 微信
                                    startWxPay(data);//微信支付
                                    break;
                                case 4: // 余额

                                    break;
                                case 7: // 优惠券

                                    break;
                            }
                        } else {
                            String s = bean.getErrMsg().toString();
                            ToastUtil.showToast(mContext, s);
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        progressDialog.dismiss();
                        ToastUtil.showToast(mContext, "请求失败,请检查网络连接");
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        progressDialog = new ProgressDialog(LivePayActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("正在请求...");
                        progressDialog.show();
                    }
                });
    }

    */
/**
     * 请求网络获取支付类型
     *//*

    public void getPayTypes() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken",token);
        params.addBodyParameter("model", String.valueOf(1)); // 1 代表当前直播间的支付请求
        String url = UrlUtils.URL_PAYTYPE;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LivePayTypeBean bean = JSONObject.parseObject(json, LivePayTypeBean.class);
                        // 这里拿到的数据以后再做处理  目前暂时使用写死的
                        List<LivePayTypeBean.AaDataBean> aaDatas = bean.getAaData();
                        */
/*for (LivePayTypeBean.AaDataBean aaData : aaDatas) {
                            String keyname = aaData.getKeyname();
                        }*//*

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

}
*/
