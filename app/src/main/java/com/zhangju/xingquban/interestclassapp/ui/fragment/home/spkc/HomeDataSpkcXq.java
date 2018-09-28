package com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.adapter.near.VideoCommentAdapter;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.PayIdBean;
import com.zhangju.xingquban.interestclassapp.bean.PayTypeBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NeatVideoComment;
import com.zhangju.xingquban.interestclassapp.bean.near.VideoLessonsBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.ResInterface;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.RecycleViewDivider;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


@ContentView(R.layout.activity_home_data_spkx_xq)
public class HomeDataSpkcXq extends FastActivity {
    public static final String ARG_BEAN_SHIPINXQ = "shipin";
    public static final String ARG_STRING_NAME = "teacher_name";
    @LocalData(ARG_BEAN_SHIPINXQ)
    HomeDataTeacherBean.AaDataBean.VideoLessonBean videoLessonBean;
    @LocalData(ARG_STRING_NAME)
    String teahername;
    @Bind(R.id.home_data_spkc_Head)
    PublicHead homeDataSpkcHead;
    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @Bind(R.id.tv_ischarge_res)
    TextView tv_ischarge_res;
    @Bind(R.id.video_name)
    TextView videoName;
    @Bind(R.id.rl_miaoshu)
    RelativeLayout rlMiaoshu;
    @Bind(R.id.rl_xiaoliang)
    RelativeLayout rlXiaoliang;
    @Bind(R.id.video_summary)
    TextView videoSummary;
    @Bind(R.id.teacher_name)
    RelativeLayout teacherName;
    @Bind(R.id.tv_xq)
    TextView tvXq;
    @Bind(R.id.ine_xq)
    View ineXq;
    @Bind(R.id.rl_xiangq)
    RelativeLayout rlXiangq;
    @Bind(R.id.tv_pl)
    TextView tvPl;
    @Bind(R.id.line_pl)
    View linePl;
    @Bind(R.id.rl_pinglun)
    RelativeLayout rlPinglun;
    @Bind(R.id.shipin_xiangqing)
    TextView shipinXiangqing;
    @Bind(R.id.rec_sp_comment)
    RecyclerView recSpComment;
    @Bind(R.id.edt_comment)
    EditText edtComment;
    @Bind(R.id.commit_commnet)
    Button commitCommnet;
    @Bind(R.id.ll_comment_add)
    LinearLayout llCommentAdd;
    @Bind(R.id.tv_xiaoliang)
    TextView xiaoliang;
    @Bind(R.id.comment_num)
    TextView comment_num;
    @Bind(R.id.ll_zhifumoney)
    LinearLayout zhifumoney;
    @Bind(R.id.zhifu)
    TextView zhifu;

    private VideoLessonsBean lessonsBean2;
    private boolean isBofang = false;
    private NeatVideoComment videoComment;
    private VideoCommentAdapter videoCommentAdapter;
    boolean isPay;//是否付钱
    private MyShareDialog mMyShareDialog;
    private String mId;

    @Override
    protected void alreadyPrepared() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        edtComment.clearFocus();
        edtComment.setText(null);
        // @param payType 支付方式
        Request request = Request.obtain(INear.POST_VIDEOLESS);
        mId = videoLessonBean.getId();
        request.put("id", mId);
        request.setListener(new SimpleListener<VideoLessonsBean>() {

            @Override
            public void onResponseListener(Request r, VideoLessonsBean result) {
                lessonsBean2 = result;
                videoplayer.backButton.setVisibility(View.GONE);
                videoplayer.setUp(""
                        , JCVideoPlayerStandard.CURRENT_STATE_NORMAL, "");
                /*请求评论列表*/
                ineXq.setVisibility(View.VISIBLE);
                linePl.setVisibility(View.GONE);
                recSpComment.setVisibility(View.GONE);
                shipinXiangqing.setVisibility(View.VISIBLE);
                llCommentAdd.setVisibility(View.GONE);
                edtComment.clearFocus();
                edtComment.setText(null);
                recSpComment.addItemDecoration(new RecycleViewDivider(
                        HomeDataSpkcXq.this, LinearLayoutManager.VERTICAL, 33, getResources().getColor(R.color.white)));

                initVideoCommnet();
                initPlayer();
                bindDataToView();
                initShare();
            }
        });
        request.start();


    }

    private void initPlayer() {

        //videoplayer.batteryTimeLayout.setVisibility(View.GONE);
        if (lessonsBean2.getAaData().get(0).getIsCharge() == 1) {
            if (lessonsBean2.getAaData().get(0).isIsPayed()) {
                //免费
                isBofang = true;
                isPay = true;
                zhifumoney.setVisibility(View.GONE);
                tv_ischarge_res.setVisibility(View.GONE);
                videoplayer.backButton.setVisibility(View.GONE);
                videoplayer.setUp(lessonsBean2.getAaData().get(0).getVideoStr()
                        , JCVideoPlayerStandard.CURRENT_STATE_NORMAL);
                Glide.with(this).load(lessonsBean2.getAaData().get(0).getVideoTitlePic()).into(videoplayer.thumbImageView);
            } else {
                if (lessonsBean2.getAaData().get(0).getCustomerId().equals(UserManager.getInstance().getUser().id)) {
                    //免费
                    isBofang = true;
                    isPay = true;
                    zhifumoney.setVisibility(View.GONE);
                    tv_ischarge_res.setVisibility(View.GONE);
                    videoplayer.backButton.setVisibility(View.GONE);
                    videoplayer.setUp(lessonsBean2.getAaData().get(0).getVideoStr()
                            , JCVideoPlayerStandard.CURRENT_STATE_NORMAL);
                    Glide.with(this).load(lessonsBean2.getAaData().get(0).getVideoTitlePic()).into(videoplayer.thumbImageView);
                } else {
                    //支付
                    isBofang = false;
                    isPay = false;
                    zhifumoney.setVisibility(View.VISIBLE);
                    videoplayer.backButton.setVisibility(View.GONE);
                    tv_ischarge_res.setVisibility(View.VISIBLE);
                    zhifu.setText("立即支付：¥" + lessonsBean2.getAaData().get(0).getPrice());
                    Glide.with(this).load(lessonsBean2.getAaData().get(0).getVideoTitlePic()).into(videoplayer.thumbImageView);
                }
            }

        } else {
            //免费
            isBofang = true;
            isPay = true;
            zhifumoney.setVisibility(View.GONE);
            tv_ischarge_res.setVisibility(View.GONE);
            videoplayer.backButton.setVisibility(View.GONE);
            videoplayer.setUp(lessonsBean2.getAaData().get(0).getVideoStr()
                    , JCVideoPlayerStandard.CURRENT_STATE_NORMAL);
            Glide.with(this).load(lessonsBean2.getAaData().get(0).getVideoTitlePic()).into(videoplayer.thumbImageView);
        }
    }

    private void bindDataToView() {
        videoName.setText(lessonsBean2.getAaData().get(0).getTitle());
        xiaoliang.setText(lessonsBean2.getAaData().get(0).getClickRate() + "");
        videoSummary.setText(teahername);
        shipinXiangqing.setText(lessonsBean2.getAaData().get(0).getComment());

        ineXq.setVisibility(View.VISIBLE);
        linePl.setVisibility(View.GONE);

        shipinXiangqing.setVisibility(View.VISIBLE);
        homeDataSpkcHead.setTitle("课程详情");
        homeDataSpkcHead.setShow(true, true, false);
        homeDataSpkcHead.setImgSearch(R.drawable.nav_btn_share_red);
        homeDataSpkcHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        homeDataSpkcHead.setImgSeachRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyShareDialog.show();
            }
        });
    }

    private void initVideoCommnet() {
        final Request request = Request.obtain(INear.POST_VIDEO_COMMENT);
        request.put("organVideoId", lessonsBean2.getAaData().get(0).getId());
        request.setListener(new SimpleListener<NeatVideoComment>() {

            @Override
            public void onResponseListener(Request r, NeatVideoComment result) {
                videoComment = result;
                comment_num.setText(result.getAaData().size() + "");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeDataSpkcXq.this, LinearLayoutManager
                        .VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                recSpComment.setLayoutManager(linearLayoutManager);
                videoCommentAdapter = new VideoCommentAdapter(HomeDataSpkcXq.this, videoComment);
                recSpComment.setAdapter(videoCommentAdapter);

            }
        });
        net(request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }

    @Bind(R.id.commit_commnet)
    private void commitCommnet() {
        if (UserManager.getInstance().isLogin()) {
            if (TextUtils.isEmpty(edtComment.getText().toString())) {
                edtComment.setError("请输入内容");
                edtComment.requestFocus();
                return;
            }
            Request request = Request.obtain(INear.POST_VIDEO_ADDCOMMNENT);
            request.put("customerId", UserManager.getInstance().getUser().id);
            request.put("customerName", UserManager.getInstance().getUser().username);
            request.put("customerPicture", UserManager.getInstance().getUser().picture);
            request.put("organVideoId", lessonsBean2.getAaData().get(0).getId());
            request.put("score", 0);
            request.put("title", edtComment.getText().toString());
            request.setListener(new SimpleListener<Object>() {

                @Override
                public void onResponseListener(Request r, Object result) {
                    edtComment.clearFocus();
                    edtComment.setText(null);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edtComment.getWindowToken(), 0);
                    initVideoCommnet();
                }
            });
            net(request);
        } else {
            startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity.class));
        }

    }

    @Bind(R.id.zhifu)
    private void zhifus() {
        if (UserManager.getInstance().isLogin()) {
            getResPrice(lessonsBean2.getAaData().get(0).getPrice());
        } else {
            startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity.class));
        }
    }

    @Bind(R.id.tv_ischarge_res)
    private void shwoText() {
        if (UserManager.getInstance().isLogin()) {
            getResPrice(lessonsBean2.getAaData().get(0).getPrice());

        } else {
            startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity.class));
        }
    }

    @Bind(R.id.rl_xiangq)
    private void showXq() {
        ineXq.setVisibility(View.VISIBLE);
        linePl.setVisibility(View.GONE);
        recSpComment.setVisibility(View.GONE);
        shipinXiangqing.setVisibility(View.VISIBLE);
        llCommentAdd.setVisibility(View.GONE);

    }

    @Bind(R.id.rl_pinglun)
    private void showComment() {
        ineXq.setVisibility(View.GONE);
        linePl.setVisibility(View.VISIBLE);
        recSpComment.setVisibility(View.VISIBLE);
        shipinXiangqing.setVisibility(View.GONE);
        if (isBofang) {
            llCommentAdd.setVisibility(View.VISIBLE);
        } else {
            llCommentAdd.setVisibility(View.GONE);
            zhifumoney.setVisibility(View.VISIBLE);
        }
    }


    //获取资源收费价格
    private void getResPrice(final double price) {
        final Request request = Request.obtain(ResInterface.POST_RES_PAY_TYPE);
        request.put("modelName", "organVideo");
        request.setListener(new SimpleListener<Response<List<PayTypeBean>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<PayTypeBean>> result) {
                boolean success = result.success;
                if (success) {
                    List<PayTypeBean> payTypeBeen = result.data;
                    PayDialog(payTypeBeen, price);
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);


    }

    //获取订单id
    JSONArray jsonArray = new JSONArray();
    String payId;

    private void getPayId(double price) {
        final Request request = Request.obtain(ResInterface.POST_RES_PAY_ID);
        UserManager instance = UserManager.getInstance();
        String token = instance.getToken();
        User user = instance.getUser();
        String id = user.id;
        String signame = user.signame;

        boolean isMember = user.isMember;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("price", price);
            jsonObject.put("organVideoId", lessonsBean2.getAaData().get(0).getId());
            jsonObject.put("counts", 1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jsonArray.put(jsonObject);

        request.put("customerId", id);//用户id
        request.put("applyName", signame);
        request.put("json", jsonArray.toString());
        //1 代表会员价  0非会员价
        if (isMember) {
            request.put("memberPrice", 1);
        } else {
            request.put("memberPrice", 0);
        }
        request.put("orderType", "2");
        request.put("phone", UserManager.getInstance().getUser().phone);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<PayIdBean>>() {

            @Override
            public void onResponseListener(Request r, Response<PayIdBean> result) {
                if (result.success) {
                    PayIdBean data = result.data;
                    if (data != null) {
                        payId = data.getId();//订单id
                        toPay();
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

    @Event
    private void ePayResult(EventPayResult payResult) {
        if (payResult.isSuccess()) {
            startBofang();
        }

    }

    /*    @Event
        private void LoginRefresh(LoginRefresh refresh){
            if(UserManager.getInstance().getUser().id.equals(videoLessonBean.getCustomerId())){
                startBofang();
            }
        }*/
    private void startBofang() {
        payDialog.dismiss();
        tv_ischarge_res.setVisibility(View.GONE);
        //余额支付完成
        isPay = true;
        isBofang = true;

        zhifumoney.setVisibility(View.GONE);
        tv_ischarge_res.setVisibility(View.GONE);
        videoplayer.backButton.setVisibility(View.GONE);
        videoplayer.setUp(lessonsBean2.getAaData().get(0).getVideoStr()
                , JCVideoPlayerStandard.CURRENT_STATE_NORMAL);
        videoplayer.startVideo();
        Glide.with(HomeDataSpkcXq.this).load(lessonsBean2.getAaData().get(0).getVideoTitlePic()).into(videoplayer.thumbImageView);

    }

    //支付
    private Dialog payDialog;
    ImageView image_balance, image_wechat, image_alipay;
    TextView tv_balance_money, tv_to_pay;
    RadioButton mBalanceCheck, mWechatCheck, mAlipayCheck;
    LinearLayout line_alipay, line_wechat, line_balance;


    private void PayDialog(final List<PayTypeBean> payTypeBeen, final double price) {
        payDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        View payView = LayoutInflater.from(HomeDataSpkcXq.this).inflate(R.layout.pay_resource_money, null);
        View payLayout = payView.findViewById(R.id.payLayout);
        ImageView icon = (ImageView) payView.findViewById(R.id.icon);
        TextView coinType = (TextView) payView.findViewById(R.id.coinType);
        TextView text = (TextView) payView.findViewById(R.id.text);
        final RadioButton radioButton = (RadioButton) payView.findViewById(R.id.radioButton);
        image_balance = (ImageView) payView.findViewById(R.id.image_balance);
        image_wechat = (ImageView) payView.findViewById(R.id.image_wechat);
        image_alipay = (ImageView) payView.findViewById(R.id.image_alipay);
        tv_balance_money = (TextView) payView.findViewById(R.id.tv_balance_money);
        tv_to_pay = (TextView) payView.findViewById(R.id.tv_to_pay);
        mBalanceCheck = (RadioButton) payView.findViewById(R.id.radiobutton_balance);
        mWechatCheck = (RadioButton) payView.findViewById(R.id.radiobutton_weixin);
        mAlipayCheck = (RadioButton) payView.findViewById(R.id.radiobutton_alipay);

        line_alipay = (LinearLayout) payView.findViewById(R.id.line_alipay);
        line_wechat = (LinearLayout) payView.findViewById(R.id.line_wechat);
        line_balance = (LinearLayout) payView.findViewById(R.id.line_balance);


        List<String> mNames = new ArrayList<>();
        //支付判断
        if (payTypeBeen != null) {
            for (int i = 0; i < payTypeBeen.size(); i++) {
                String name = payTypeBeen.get(i).getName();
                mNames.add(name);
            }
        }
        if (!mNames.contains("兴趣豆")) {
            payLayout.setVisibility(View.GONE);
        }
        if (!mNames.contains("余额")) {
            line_balance.setVisibility(View.GONE);
        }
        if (!mNames.contains("微信")) {
            line_wechat.setVisibility(View.GONE);
        }
        if (!mNames.contains("支付宝")) {
            line_alipay.setVisibility(View.GONE);
        }

        line_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double coinNum = 0.0;
                if (payTypeBeen != null) {
                    for (int i = 0; i < payTypeBeen.size(); i++) {
                        if (payTypeBeen.get(i).getName().equals("余额")) {
                            coinNum = payTypeBeen.get(i).getCoinNum();
                            break;
                        }

                    }
                }

                double floor = Math.floor(coinNum);

                String num = String.valueOf(floor);
                String substring = num.substring(0, num.indexOf("."));
                int parseInt = Integer.parseInt(substring);
                if (parseInt >= price) {
                    mBalanceCheck.setChecked(true);
                } else {
                    ToastUtil.showToast("余额不足，请用其它方式支付");
                }

            }
        });

        payLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(true);
            }
        });
        line_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWechatCheck.setChecked(true);
            }
        });
        line_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlipayCheck.setChecked(true);
            }
        });

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mWechatCheck.setChecked(false);
                    mAlipayCheck.setChecked(false);
                    mBalanceCheck.setChecked(false);
                }
            }
        });
        mWechatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBalanceCheck.setChecked(false);
                    mAlipayCheck.setChecked(false);
                    radioButton.setChecked(false);
                }
            }
        });
        mAlipayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBalanceCheck.setChecked(false);
                    mWechatCheck.setChecked(false);
                    radioButton.setChecked(false);
                }
            }
        });
        mBalanceCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mWechatCheck.setChecked(false);
                    mAlipayCheck.setChecked(false);
                    radioButton.setChecked(false);
                }
            }
        });
        tv_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mWechatCheck.isChecked() && !mBalanceCheck.isChecked() && !mAlipayCheck.isChecked() && !radioButton
                        .isChecked()) {
                    ToastUtil.showToast("请选择支付方式");
                    return;
                } else {
                    //是否登录
                    if (!UserManager.getInstance().isLogin()) {
                        startActivity(new Intent(HomeDataSpkcXq.this, LoginActivity.class));
                    } else {
                        getPayId(price);
                    }
                }
            }
        });

        if (payTypeBeen != null) {
            for (PayTypeBean typeBean : payTypeBeen) {
                String name = typeBean.getName() == null ? "" : typeBean.getName();
                String iconStr = typeBean.getIcon() == null ? "" : typeBean.getIcon();
                double coinNum = typeBean.getCoinNum();
                if (name.equals("余额")) {
                    tv_balance_money.setText("可用账号余额" + coinNum);
                    Glide.with(HomeDataSpkcXq.this).load(iconStr).into(image_balance);
                } else if (name.equals("微信")) {
                    Glide.with(HomeDataSpkcXq.this).load(iconStr).into(image_wechat);
                } else if (name.equals("支付宝")) {
                    Glide.with(HomeDataSpkcXq.this).load(iconStr).into(image_alipay);
                } else if ("兴趣豆".equals(name)) {
                    text.setText(String.format(Locale.getDefault(), "可用兴趣豆%s", coinNum));
                    Glide.with(HomeDataSpkcXq.this).load(iconStr).into(icon);
                }
            }
        }
        tv_to_pay.setText("确认支付" + price + "元");
        payDialog.setContentView(payView);
        //获取当前Activity所在的窗体
        Window dialogWindow = payDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);


        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        payDialog.show();
    }

    private void toPay() {
        final Request request = Request.obtain(MeInterface.POST_ORDER_PAY);

        boolean mBalanceCheckChecked = mBalanceCheck.isChecked();
        final boolean mWechatCheckChecked = mWechatCheck.isChecked();
        final boolean mAlipayCheckChecked = mAlipayCheck.isChecked();

        if (mBalanceCheckChecked) {
            request.put("payType", "balances");
        } else if (mWechatCheckChecked) {
            request.put("payType", "qqpay");
        } else if (mAlipayCheckChecked) {
            request.put("payType", "aipay");
        } else {
            request.put("payType", "stdbean");
        }
        request.put("id", payId);
        request.setListener(new SimpleListener<Response<ResponseExchange>>() {

            @Override
            public void onResponseListener(Request r, Response<ResponseExchange> result) {

                boolean success = result.success;
                if (success) {
                    ResponseExchange data = result.data;

                    if (mWechatCheckChecked) {
                        ThirdPartyUtils.getInstance(HomeDataSpkcXq.this).payByWechat(data.appid, data.partnerid, data.prepayid,
                                data.timestamp, data.packageValue, data.noncestr, data.sign);

                    } else if (mAlipayCheckChecked) {
                        ThirdPartyUtils.getInstance(HomeDataSpkcXq.this).payByAli(HomeDataSpkcXq.this, data.sign);

                        //余额支付
                    } else {
                        payDialog.dismiss();
                        tv_ischarge_res.setVisibility(View.GONE);
                        //余额支付完成
                        isPay = true;
                        isBofang = true;
                        zhifumoney.setVisibility(View.GONE);
                        tv_ischarge_res.setVisibility(View.GONE);
                        videoplayer.backButton.setVisibility(View.GONE);
                        videoplayer.setUp(lessonsBean2.getAaData().get(0).getVideoStr()
                                , JCVideoPlayerStandard.CURRENT_STATE_NORMAL);
                        videoplayer.startVideo();
                        Glide.with(HomeDataSpkcXq.this).load(lessonsBean2.getAaData().get(0).getVideoTitlePic()).into
                                (videoplayer.thumbImageView);
                        llCommentAdd.setVisibility(View.VISIBLE);

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


    private void initShare() {
        final ArrayList<String> mlist = new ArrayList<>();
        final String image = lessonsBean2.getAaData().get(0).getVideoTitlePic();
        final String shareUrl = MyApp.URL + "/share/#/video/detail?id=" + mId;
        final String types = "video";
        final String shareid = lessonsBean2.getAaData().get(0).getId();
        final String cityId = LocationManager.getInstance().getLocation().cityId;
        final String sharetitle = lessonsBean2.getAaData().get(0).getTitle();
        final String shareContent = lessonsBean2.getAaData().get(0).getComment();

        mMyShareDialog = new MyShareDialog(mContext);
        mMyShareDialog.initShare(image, shareUrl, shareContent, sharetitle);
    }

}
