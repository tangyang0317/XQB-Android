package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.bean.near.OrderUserYuE;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zgl on 2017/11/24.
 */
@ContentView(R.layout.curriculum_order_pay)
public class CurriculumOrderPayActivity extends FastActivity implements View.OnClickListener {
    public static final String ARG_BEAN_DATA = "lessons";
    public static final String ARG_INT_NUM = "curriculum_num";
    @LocalData(ARG_BEAN_DATA)
    CurriculumBean.AaDataBean lessonsBea;
    @LocalData(ARG_INT_NUM)
    int ordernum;

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.img_kcpic)
    ImageView imgKcpic;
    @BindView(R.id.tv_kcname)
    TextView tvKcname;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.payByWechatCheck)
    RadioButton payByWechatCheck;
    @BindView(R.id.payByWechat)
    LinearLayout payByWechat;
    @BindView(R.id.payByAlipay)
    LinearLayout payByAlipay;
    @BindView(R.id.payByAlipayCheck)
    RadioButton payByAlipayCheck;
    @BindView(R.id.walletRemainTitle)
    TextView walletRemainTitle;
    @BindView(R.id.walletRemain)
    TextView walletRemain;
    @BindView(R.id.walletRemainLayout)
    LinearLayout walletRemainLayout;
    @BindView(R.id.walletRemainCheck)
    RadioButton walletRemainCheck;
    @BindView(R.id.commit)
    Button commit;
    @BindView(R.id.weixin_icon)
    ImageView weixinIcon;
    @BindView(R.id.alipay_icon)
    ImageView alipayIcon;
    @BindView(R.id.payByAlipayLayout)
    LinearLayout payByAlipayLayout;
    @BindView(R.id.yue_icon)
    ImageView yueIcon;
    @BindView(R.id.payByRemainLayout)
    LinearLayout payByRemainLayout;
    @BindView(R.id.quan_icon)
    ImageView quanIcon;
    @BindView(R.id.walletQuannTitle)
    TextView walletQuannTitle;
    @BindView(R.id.walletQuanRemain)
    TextView walletQuanRemain;
    @BindView(R.id.walletQuanLayout)
    LinearLayout walletQuanLayout;
    @BindView(R.id.quanCheck)
    RadioButton quanCheck;
    @BindView(R.id.payByquanLayout)
    LinearLayout payByquanLayout;


    private JSONObject jsonObjectdingdan;
    private JSONObject jsonObjectzhifu;

    private JSONArray jsonArray = new JSONArray();
    private JSONObject jso;
    private int price;
    private String payType;
    private String sign;
    private String appid;
    private String partnerId;
    private String prepayId;
    private String timestamp;
    private String packageValue;
    private String nonceStr;
    private String orderson;
    private OrderUserYuE orderUserYuE;
    private  int zhifuqian;
    @Override
    protected void alreadyPrepared() {
        bindDataToView();
        initRequestYue();
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void bindDataToView() {
        dingdan();
        //commit.setEnabled(false);
        Glide.with(this).load(lessonsBea.getPicture()).into(imgKcpic);
        tvKcname.setText(lessonsBea.getName());
        tvSummary.setText(lessonsBea.getDescript());


    }

    private void initRequestYue() {

        final Request request = Request.obtain(INear.POST_ORDERS_YUE);
        String token = UserManager.getInstance().getToken();
        request.addHeader("X-CustomToken", token);
        request.put("modelName", "orders");
        request.setListener(new SimpleListener<OrderUserYuE>() {
            @Override
            public void onResponseListener(Request r, OrderUserYuE result) {
                orderUserYuE=result;
                bindViewimg(result);
                walletRemain.setText("可用账号余额" + result.getAaData().get(0).getCoinNum());

            }

            @Override
            public void onErrorListener(Request r, String error) {

            }
        });
        net(request);

    }

    private void bindViewimg(OrderUserYuE result) {
        for (int i = 0; i < result.getAaData().size(); i++) {
            if (result.getAaData().get(i).getName().equals("余额")) {
                Glide.with(this).load(result.getAaData().get(i).getIcon()).into(yueIcon);
            } else if (result.getAaData().get(i).getName().equals("支付宝")) {
                Glide.with(this).load(result.getAaData().get(i).getIcon()).into(alipayIcon);
            } else if (result.getAaData().get(i).getName().equals("微信")) {
                Glide.with(this).load(result.getAaData().get(i).getIcon()).into(weixinIcon);
            } else if (result.getAaData().get(i).getName().equals("课程体验券")) {
                walletQuanRemain.setText(result.getAaData().get(i).getCoinNum()+"张，限线下课程200元以上使用");
            }

        }
    }

    private boolean getpayType() {
        if (payByWechatCheck.isChecked()) {
            payType = "qqpay";
            return true;
        } else if (payByAlipayCheck.isChecked()) {
            payType = "aipay";
            return true;
        } else if (walletRemainCheck.isChecked()) {
            payType = "balances";
            return true;
        } else {
            payType = "Error";
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Event
    private void zhifuCallBack(EventPayResult resul) {
        if (resul.isSuccess()) {
            Intent intent = new Intent(this, CurriculumOrderSucessActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(CurriculumOrderSucessActivity.ARG_STRING_ORDERNUM, orderson);
            bundle.putString(CurriculumOrderSucessActivity.ARG_STRING_PIC, lessonsBea.getPicture());
            bundle.putString(CurriculumOrderSucessActivity.ARG_STRING_NAME, lessonsBea.getName());
            bundle.putString(CurriculumOrderSucessActivity.ARG_STRING_DESC, lessonsBea.getDescript());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, CurriculumOrderErrorActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(CurriculumOrderErrorActivity.ARG_STRING_PIC, lessonsBea.getPicture());
            bundle.putString(CurriculumOrderErrorActivity.ARG_STRING_NAME, lessonsBea.getName());
            bundle.putString(CurriculumOrderErrorActivity.ARG_STRING_DESC, lessonsBea.getDescript());
            bundle.putSerializable(CurriculumOrderErrorActivity.ARG_STRING_ORDERNUM, orderson);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        }
    }

    @OnClick({R.id.commit,R.id.payByquanLayout, R.id.quanCheck,R.id.payByWechatCheck, R.id.walletRemainCheck, R.id.payByAlipayCheck, R.id.payByWechat, R.id.payByAlipayLayout,R.id.payByRemainLayout})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit:
                if (getpayType())
                    getOrderPayMoney();
                else
                    ToastUtil.showToast("请选择正确的支付方式");
                break;

            case R.id.payByWechat:
                payByWechatCheck.setChecked(true);
                walletRemainCheck.setChecked(false);
                payByAlipayCheck.setChecked(false);
                quanCheck.setChecked(false);
                break;
            case R.id.payByAlipayLayout:
                payByWechatCheck.setChecked(false);
                walletRemainCheck.setChecked(false);
                payByAlipayCheck.setChecked(true);
                quanCheck.setChecked(false);
                break;
            case R.id.payByRemainLayout:
                if (orderUserYuE.getAaData().get(0).getCoinNum()>0&&zhifuqian<=orderUserYuE.getAaData().get(0).getCoinNum()) {
                    payByWechatCheck.setChecked(false);
                    walletRemainCheck.setChecked(true);
                    payByAlipayCheck.setChecked(false);
                    quanCheck.setChecked(false);
                }else
                    ToastUtil.showToast("不可用");
                break;
            case R.id.payByquanLayout:
                if (orderUserYuE.getAaData().get(1).getCoinNum()>0){
                    payByWechatCheck.setChecked(false);
                    walletRemainCheck.setChecked(false);
                    payByAlipayCheck.setChecked(false);
                    quanCheck.setChecked(true);
                }else
                  ToastUtil.showToast("不可用");
                break;
            case R.id.walletRemainCheck:
                if (orderUserYuE.getAaData().get(0).getCoinNum()>0&&zhifuqian<=orderUserYuE.getAaData().get(0).getCoinNum()) {
                    payByWechatCheck.setChecked(false);
                    walletRemainCheck.setChecked(true);
                    payByAlipayCheck.setChecked(false);
                    quanCheck.setChecked(false);
                }else{
                    walletRemainCheck.setChecked(false);
                    ToastUtil.showToast("不可用");
                }

                break;
            case R.id.quanCheck:
                if (orderUserYuE.getAaData().get(1).getCoinNum()>0){
                    payByWechatCheck.setChecked(false);
                    walletRemainCheck.setChecked(false);
                    payByAlipayCheck.setChecked(false);
                    quanCheck.setChecked(true);
                }else{
                    quanCheck.setChecked(false);
                    ToastUtil.showToast("不可用");
                }
                break;
            default:
                Log.d(CurriculumOrderPayActivity.class.getName(), "不存在的点击事件");
                break;
        }
    }

    private void getOrderPayMoney() {
        try {
            String id = jsonObjectdingdan.getJSONObject("aaData").getString("id");
            // @param payType 支付方式
            Request request = Request.obtain(INear.POST_ORDERS_PAY);
            request.put("id", id);
            request.put("payType", payType);
            request.setListener(new SimpleListener<String>() {
                @Override
                public void onResponseListener(Request r, String result) {

                    try {
                        jsonObjectzhifu = new JSONObject(result);
                        jso = jsonObjectzhifu.getJSONObject("aaData");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (payByWechatCheck.isChecked()) {
                        //调起微信支付
                        try {
                            sign = jso.getString("sign");
                            partnerId = jso.getString("partnerid");
                            prepayId = jso.getString("prepayid");
                            timestamp = jso.getString("timestamp");
                            packageValue = jso.getString("packageValue");
                            nonceStr = jso.getString("noncestr");
                            appid = jso.getString("appid");
                            ThirdPartyUtils.getInstance(CurriculumOrderPayActivity.this).payByWechat(appid, partnerId, prepayId, timestamp, packageValue, nonceStr, sign);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else if (payByAlipayCheck.isChecked()) {
                        //调起支付宝支付
                        try {
                            sign = jso.getString("sign");
                            ThirdPartyUtils.getInstance(CurriculumOrderPayActivity.this).payByAli(CurriculumOrderPayActivity.this, sign);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else if (walletRemainCheck.isChecked()) {
                        //处理json 判断成功失败
                        //调起余额支付
                        try {
                            boolean ispay = jso.getBoolean("ispay");
                            if (ispay) {
                                Intent intent2 = new Intent(CurriculumOrderPayActivity.this, CurriculumOrderSucessActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(CurriculumOrderSucessActivity.ARG_STRING_ORDERNUM, orderson);
                                bundle.putString(CurriculumOrderSucessActivity.ARG_STRING_PIC, lessonsBea.getPicture());
                                bundle.putString(CurriculumOrderSucessActivity.ARG_STRING_NAME, lessonsBea.getName());
                                bundle.putString(CurriculumOrderSucessActivity.ARG_STRING_DESC, lessonsBea.getDescript());
                                intent2.putExtras(bundle);
                                startActivity(intent2);
                                finish();
                            } else {
                                String msg = jsonObjectzhifu.getJSONObject("errMsg").getString("sys");
                                ToastUtil.showToast(msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            net(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void dingdan() {

        JSONObject jo1 = new JSONObject();

        try {
            if (UserManager.getInstance().getUser().isMember) {
                jo1.put("price", lessonsBea.getVipPrice());
            } else {
                jo1.put("price", lessonsBea.getPrice());
            }

            jo1.put("lessonId", lessonsBea.getId());
            jo1.put("counts", ordernum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray.put(jo1);
        final Request request = Request.obtain(INear.POST_ORDER_CURRICULUM);
        request.put("applyName", UserManager.getInstance().getUser().username);
        request.put("phone", UserManager.getInstance().getUser().phone);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("json", jsonArray.toString());
        request.put("orderType", 3);
        request.setListener(new SimpleListener<String>() {
            @Override
            public void onResponseListener(Request r, String result) {
                //  Gson gson=new Gson();
                try {
                    jsonObjectdingdan = new JSONObject(result);

                    if (jsonObjectdingdan.getBoolean("success")) {
                        JSONObject jso = jsonObjectdingdan.getJSONObject("aaData");
                        orderson = jso.getString("sno");
                        zhifuqian=jso.getInt("payAmount");
                        commit.setText("确认支付 ￥" + jso.getDouble("payAmount"));
                    } else {
                        ToastUtil.showToast(jsonObjectdingdan.getJSONObject("errMsg").getString("sys"));
                        commit.setText(jsonObjectdingdan.getJSONObject("errMsg").getString("sys"));
                        commit.setEnabled(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               /* ToastUtil.showToast(result.toString());*/
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
            }
        });
        net(request);

    }
}
