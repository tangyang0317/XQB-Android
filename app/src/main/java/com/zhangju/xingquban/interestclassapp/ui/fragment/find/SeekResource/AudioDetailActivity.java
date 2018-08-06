package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.zhangju.xingquban.interestclassapp.adapter.AudioDetailAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.ResVideoListAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.PayIdBean;
import com.zhangju.xingquban.interestclassapp.bean.PayTypeBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDetailBean;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollLinearLayoutManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author hqf
 *         create by 2017//11/11
 *         音频资源详情
 */
@ContentView(R.layout.activity_audio_detail)
public class AudioDetailActivity
        extends FastActivity
        implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.audio_back)
    ImageView                audioBack;
    @BindView(R.id.audio_share)
    ImageView                audioShare;

    @BindView(R.id.audio_play_back)
    ImageView                audio_play_back;
    @BindView(R.id.fragment_play)
    FrameLayout              fragmentPlay;
    @BindView(R.id.audio_jiecaoplayer)
    JCVideoPlayerStandard    audioJiecaoplayer;
    @BindView(R.id.tv_ischarge_res)
    TextView                 tv_ischarge_res;

    @BindView(R.id.swipeAudioDeatailrefresh)
    SwipeRefreshRecyclerView swipeAudioDeatailrefresh;
    @BindView(R.id.audio_detail_comment)
    RelativeLayout           audioDetailComment;
    @BindView(R.id.audio_detail_prise)
    TextView                 audioDetailPrise;
    @BindView(R.id.audio_detail_collect)
    TextView                 audioDetailCollect;
    @BindView(R.id.tv_pay_money)
    TextView                 tvPayMoney;
    @BindView(R.id.rela_bottom)
    RelativeLayout           rela_bottom;

    //topview

    TextView       tv_title;
    TextView       tv_price;
    TextView       tv_play_count;
    TextView       tv_video_comment_num;
    TextView       tv_video_love_num;
    RoundImageView image_head;
    TextView       tv_nick_name;
    RecyclerView   recycler_res_list;
    TextView       tv_title_Count;

    String url;//资源地址


    private String resId;//资源id
    private int pageIndex = 0;
    private int total     = 0;
    private View               mAduioView;
    private AudioDetailAdapter adapter;

    int isPrise   = 0;//是否点赞
    int isCollect = 0;//是否收藏

    private String types = "video";//类型
    boolean isfresh;
    boolean isPay;//是否付钱

    private String            sharetitle   = "资源";
    private String            shareid      = "";
    private String            cityId       = "";
    private String            image        = "";
    private ArrayList<String> mlist        = new ArrayList<>();
    private String            shareContent = "";


    //    http://my.xqban.com/admnxzcmr/resources/share?types=video&id=41901&cityId=

    List<ResDetailBean.AaDataBean> mCommentList = new ArrayList<>();//评论集合
    private MyShareDialog mMyShareDialog;


    @Override
    protected void alreadyPrepared() {
        cityId = LocationManager.getInstance().getLocation().cityId;
        getLayoutHeadView();
        getIntentData();
    }

    private void getLayoutHeadView() {
        mAduioView = LayoutInflater.from(AudioDetailActivity.this).inflate(R.layout.res_detail_top, null);
        tv_title = (TextView) mAduioView.findViewById(R.id.tv_title);
        tv_price = (TextView) mAduioView.findViewById(R.id.tv_price);
        tv_play_count = (TextView) mAduioView.findViewById(R.id.tv_play_count);
        tv_video_comment_num = (TextView) mAduioView.findViewById(R.id.tv_video_comment_num);
        tv_video_love_num = (TextView) mAduioView.findViewById(R.id.tv_video_love_num);
        tv_nick_name = (TextView) mAduioView.findViewById(R.id.tv_nick_name);
        image_head = (RoundImageView) mAduioView.findViewById(R.id.image_head);
        recycler_res_list = (RecyclerView) mAduioView.findViewById(R.id.recycler_res_list);
        tv_title_Count = (TextView) mAduioView.findViewById(R.id.tv_title_Count);

    }


    private void setAudioDetailAdapter() {

        adapter = new AudioDetailAdapter(AudioDetailActivity.this, mCommentList, mAduioView);
        swipeAudioDeatailrefresh.setLayoutManager(new LinearLayoutManager(AudioDetailActivity.this));
        swipeAudioDeatailrefresh.setAdapter(adapter);

        swipeAudioDeatailrefresh.setOnListLoadListener(this);
        swipeAudioDeatailrefresh.setOnRefreshListener(this);
        swipeAudioDeatailrefresh.autoRefresh();
    }

    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swipeAudioDeatailrefresh.setLoading(false);
            swipeAudioDeatailrefresh.setEnabledLoad(false);
            return;
        }
        getDetailCommentData();

    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        getDetailCommentData();
        new SimpleDateFormat("yyyy年MM月dd日");
    }

    private void getIntentData() {

        resId = getIntent().getStringExtra("resId");
        types = getIntent().getStringExtra("types");

        setAudioDetailAdapter();
        getDetailTopData();

        //默认
        audioJiecaoplayer.setUp("", JCVideoPlayer.CURRENT_STATE_NORMAL, "");
        audioJiecaoplayer.thumbImageView.setImageResource(R.drawable.back_play);

        initShare();


    }

    private void initShare() {
        mMyShareDialog = new MyShareDialog(this);
    }

    //获取资源收费价格
    private void getResPrice(final double price) {
        final Request request = Request.obtain(ResInterface.POST_RES_PAY_TYPE);
        String token = UserManager.getInstance().getToken();
        request.put("modelName", "resources");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<List<PayTypeBean>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<PayTypeBean>> result) {

                boolean success = result.success;
                if (success) {
                    List<PayTypeBean> payTypeBeen = result.data;
                    PayDialog(payTypeBeen, price);
                    getPayId(price);
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);


    }

    //获取详细数据
    private void getDetailCommentData() {
        final Request request = Request.obtain(ResInterface.POST_RES_COMMENT);
        String token = UserManager.getInstance().getToken();
        request.put("resourcesId", resId);
        request.put("pageIndex", pageIndex);
        request.put("pageSize", "10");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<ResDetailBean>() {

            @Override
            public void onResponseListener(Request r, ResDetailBean result) {
                boolean success = result.isSuccess();
                if (success) {
                    total = result.getTotal();
                    List<ResDetailBean.AaDataBean> beanList = result.getAaData();

                    if (pageIndex == 0) {
                        mCommentList.clear();
                    }
                    if (beanList != null && beanList.size() > 0) {
                        int rsCount = beanList.get(0).getRsCount();
                        tv_title_Count.setText("网友评论(" + rsCount + ")");
                        mCommentList.addAll(beanList);
                    }

                }
                adapter.notifyItemRangeChanged(1, mCommentList.size() - 1);
                setSwipe();

            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                setSwipe();
            }
        });
        net(request);

    }

    private void setSwipe() {
        swipeAudioDeatailrefresh.setLoading(false);
        swipeAudioDeatailrefresh.setRefreshing(false);
        swipeAudioDeatailrefresh.setEnabledLoad(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isfresh) {
            swipeAudioDeatailrefresh.autoRefresh();
            isfresh = false;
        }

    }

    ResDeatailTopBean.AaDataBean dataBean;

    //获取顶部详细数据
    private void getDetailTopData() {
        final Request request = Request.obtain("get", ResInterface.POST_RES_TOP);
        String token = UserManager.getInstance().getToken();
        request.put("id", resId);
        request.put("pageIndex", "0");
        request.put("pageSize", "1");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<ResDeatailTopBean>() {
            @Override
            public void onResponseListener(Request r, ResDeatailTopBean result) {

                boolean success = result.isSuccess();
                if (success) {
                    List<ResDeatailTopBean.AaDataBean> databean = result.getAaData();
                    if (databean != null && databean.size() > 0) {

                        dataBean = databean.get(0);
                        String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
                        int clickRate = dataBean.getClickRate();//播放次数
                        int likes = dataBean.getCollectionCounts();//喜欢
                        int commentCounts = dataBean.getCommentCounts();//评论数
                        String author = dataBean.getAuthor() == null ? "" : dataBean.getAuthor();//昵称
                        int isCharge = dataBean.getIsCharge();//0不收费
                        double price = dataBean.getPrice();
                        isPrise = dataBean.getThumb();//0 没点赞  1点赞
                        isCollect = dataBean.getResourceExit();//0 没点赞  1点赞
                        image = dataBean.getTitlePicture();
                        sharetitle = title;
                        shareContent = dataBean.getSummary();
                        boolean isPayed = dataBean.isIsPayed();


                        List<ResDeatailTopBean.AaDataBean.VideoListBean> videoList = dataBean.getVideoList();
                        if (videoList != null && videoList.size() > 0) {
                            url = videoList.get(0).getFileUrl();
                            shareid = videoList.get(0).getResourcesId();
                        }

                        setDrawableLeft(isCollect == 1 ? R.drawable.shoucang : R.drawable.resource_collect_normal,
                                audioDetailCollect);
                        setDrawableLeft(isPrise == 1 ? R.drawable.dianzan : R.drawable.resource_good_normal, audioDetailPrise);

                        String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//头像

                        tv_title.setText(title);
                        tv_play_count.setText(clickRate + "次播放");
                        tv_nick_name.setText(author);
                        tv_video_comment_num.setText(commentCounts + "");
                        tv_video_love_num.setText(likes + "");
                        Glide.with(AudioDetailActivity.this).load(authorPicture).placeholder(R.drawable.app_icon).dontTransform
                                ().dontAnimate().into(image_head);
                        if (isCharge == 0) {
                            isPay = true;
                            tv_price.setText("免费");
                            tv_ischarge_res.setVisibility(View.GONE);//背景
                        } else {
                            tv_price.setText("¥" + price);
                            User user = UserManager.getInstance().getUser();

                            String id = user.id == null ? "" : user.id;

                            String customerId = dataBean.getCustomerId();
                            if (id.equals(customerId)) {
                                isPay = true;
                                tv_ischarge_res.setVisibility(View.GONE);
                            } else {
                                if (isPayed) {
                                    isPay = true;
                                    tv_ischarge_res.setVisibility(View.GONE);
                                } else {
                                    getResPrice(price);//获取付费数据
                                    tv_ischarge_res.setVisibility(View.VISIBLE);
                                    isPay = false;

                                    tvPayMoney.setVisibility(View.VISIBLE);
                                    rela_bottom.setVisibility(View.GONE);
                                }

                            }

                        }
                        startPlay();
                        final List<ResDeatailTopBean.AaDataBean.TwoListBean> twoList = dataBean.getTwoList();

                        ResVideoListAdapter resVideoListAdapter = new ResVideoListAdapter(AudioDetailActivity.this, twoList);
                        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(AudioDetailActivity
                                .this);
                        scrollLinearLayoutManager.setScrollEnabled(false);
                        recycler_res_list.setLayoutManager(scrollLinearLayoutManager);

                        recycler_res_list.setAdapter(resVideoListAdapter);
                        resVideoListAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                            @Override
                            public void onItemClickListener(int position, View v) {
                                String id = twoList.get(position).getId() == null ? "" : twoList.get(position).getId();

                                JCVideoPlayer.releaseAllVideos();
                                JCVideoPlayer.clearSavedProgress(AudioDetailActivity.this, url);

                                Intent intent = new Intent(AudioDetailActivity.this, AudioDetailActivity.class);
                                intent.putExtra("resId", id);
                                intent.putExtra("types", types);
                                startActivity(intent);

                                finish();
                            }
                        });

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.audio_back, R.id.tv_ischarge_res, R.id.tv_pay_money, R.id.audio_share, R.id.audio_detail_comment, R.id
            .audio_detail_prise, R.id.audio_detail_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.audio_back:
                finish();
                JCVideoPlayer.releaseAllVideos();
                JCVideoPlayer.clearSavedProgress(AudioDetailActivity.this, url);
                break;
            case R.id.audio_share:
                String shareUrl = "http://my.xqban.com/share/#/resource/video?id=" + shareid;
                mMyShareDialog.initShare(image, shareUrl, shareContent, sharetitle);
                mMyShareDialog.show();
                break;
            case R.id.audio_detail_comment:

                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(AudioDetailActivity.this, LoginActivity.class));
                } else {
                    Intent intent = new Intent(AudioDetailActivity.this, ResSendActivity.class);
                    intent.putExtra("id", resId);
                    startActivity(intent);
                    isfresh = true;
                }

                break;
            case R.id.audio_detail_prise:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(AudioDetailActivity.this, LoginActivity.class));
                } else {
                    addPrise();
                }

                break;
            case R.id.audio_detail_collect:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(AudioDetailActivity.this, LoginActivity.class));
                } else {
                    addCollect();
                }

                break;

            case R.id.tv_pay_money:

                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(AudioDetailActivity.this, LoginActivity.class));


                } else {
                    payDialog.show();
                }

                //付钱
                break;
            case R.id.tv_ischarge_res:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(AudioDetailActivity.this, LoginActivity.class));


                } else {
                    payDialog.show();
                }


                break;

        }
    }

    //开始播放
    private void startPlay() {
        if (isPay) {
            audioJiecaoplayer.setUp(url, JCVideoPlayer.CURRENT_STATE_NORMAL, "");
            audioJiecaoplayer.startVideo();
        } else {
            audioJiecaoplayer.setUp(url, JCVideoPlayer.CURRENT_STATE_NORMAL, "");
        }
        if (!types.equals("video")) {
            audio_play_back.setVisibility(View.VISIBLE);
        } else {
            audio_play_back.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        JCVideoPlayer.releaseAllVideos();
        JCVideoPlayer.clearSavedProgress(AudioDetailActivity.this, url);
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //点赞1122
    private void addPrise() {
        final Request request = Request.obtain(ResInterface.POST_RES_ISPRISE);
        String token = UserManager.getInstance().getToken();
        request.put("resourcesId", resId);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {
            @Override
            public void onResponseListener(Request r, Response result) {
                if (result.success) {
                    if (isPrise == 0) {
                        isPrise = 1;
                    } else {
                        isPrise = 0;
                    }
                    setDrawableLeft(isPrise == 1 ? R.drawable.dianzan : R.drawable.resource_good_normal, audioDetailPrise);
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
            jsonObject.put("resourceId", dataBean.getId());
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
        request.put("orderType", "4");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<PayIdBean>>() {

            @Override
            public void onResponseListener(Request r, Response<PayIdBean> result) {
                if (result.success) {
                    PayIdBean data = result.data;
                    if (data != null) {
                        payId = data.getId();//订单id
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


            payDialog.dismiss();
            tv_ischarge_res.setVisibility(View.GONE);
            isPay = true;
            tvPayMoney.setVisibility(View.GONE);
            rela_bottom.setVisibility(View.VISIBLE);
            startPlay();
        }

    }

    //收藏
    private void addCollect() {
        final Request request = Request.obtain(ResInterface.POST_RES_ISCOLLECT);
        UserManager instance = UserManager.getInstance();
        String token = instance.getToken();
        User user = instance.getUser();
        String id = user.id;
        request.put("customerId", id);//用户id
        request.put("resourcesId", resId);
        request.put("types", types);
        request.put("collectionTypes", "2");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response result) {
                if (result.success) {
                    if (isCollect == 0) {
                        isCollect = 1;
                    } else {
                        isCollect = 0;
                    }
                    setDrawableLeft(isCollect == 1 ? R.drawable.shoucang : R.drawable.resource_collect_normal,
                            audioDetailCollect);
                }

            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);

    }

    //支付
    private Dialog payDialog;
    ImageView image_balance, image_wechat, image_alipay;
    TextView tv_balance_money, tv_to_pay;
    RadioButton mBalanceCheck, mWechatCheck, mAlipayCheck;
    LinearLayout line_alipay, line_wechat, line_balance;


    private void PayDialog(final List<PayTypeBean> payTypeBeen, final double price) {
        payDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        View payView = LayoutInflater.from(AudioDetailActivity.this).inflate(R.layout.pay_resource_money, null);

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


        mWechatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBalanceCheck.setChecked(false);
                    mAlipayCheck.setChecked(false);
                }
            }
        });
        mAlipayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBalanceCheck.setChecked(false);
                    mWechatCheck.setChecked(false);
                }
            }
        });
        mBalanceCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mWechatCheck.setChecked(false);
                    mAlipayCheck.setChecked(false);
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
            }
        });


        tv_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mWechatCheck.isChecked() && !mBalanceCheck.isChecked() && !mAlipayCheck.isChecked()) {
                    ToastUtil.showToast("请选择支付方式");
                    return;
                } else {
                    //是否登录
                    if (!UserManager.getInstance().isLogin()) {
                        startActivity(new Intent(AudioDetailActivity.this, LoginActivity.class));
                    } else {
                        toPay();
                    }

                }
            }
        });

        if (payTypeBeen != null) {
            for (PayTypeBean typeBean : payTypeBeen) {
                String name = typeBean.getName() == null ? "" : typeBean.getName();
                String icon = typeBean.getIcon() == null ? "" : typeBean.getIcon();
                if (name.equals("余额")) {
                    double coinNum = typeBean.getCoinNum();
                    tv_balance_money.setText("可用账号余额" + coinNum);
                    Glide.with(AudioDetailActivity.this).load(icon).into(image_balance);
                } else if (name.equals("微信")) {
                    Glide.with(AudioDetailActivity.this).load(icon).into(image_wechat);
                } else if (name.equals("支付宝")) {
                    Glide.with(AudioDetailActivity.this).load(icon).into(image_alipay);
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

    }

    private void toPay() {
        final Request request = Request.obtain(MeInterface.POST_ORDER_PAY);
        String token = UserManager.getInstance().getToken();

        boolean mBalanceCheckChecked = mBalanceCheck.isChecked();
        final boolean mWechatCheckChecked = mWechatCheck.isChecked();
        final boolean mAlipayCheckChecked = mAlipayCheck.isChecked();

        if (mBalanceCheckChecked) {
            request.put("payType", "balances");
        } else if (mWechatCheckChecked) {
            request.put("payType", "qqpay");
        } else {
            request.put("payType", "aipay");
        }
        request.put("id", payId);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<ResponseExchange>>() {

            @Override
            public void onResponseListener(Request r, Response<ResponseExchange> result) {

                boolean success = result.success;
                if (success) {
                    ResponseExchange data = result.data;

                    if (mWechatCheckChecked) {
                        ThirdPartyUtils.getInstance(AudioDetailActivity.this).payByWechat(data.appid, data.partnerid, data
                                .prepayid, data.timestamp, data.packageValue, data.noncestr, data.sign);

                    } else if (mAlipayCheckChecked) {
                        ThirdPartyUtils.getInstance(AudioDetailActivity.this).payByAli(AudioDetailActivity.this, data.sign);

                        //余额支付
                    } else {
                        payDialog.dismiss();
                        tv_ischarge_res.setVisibility(View.GONE);
                        isPay = true;
                        tvPayMoney.setVisibility(View.GONE);
                        rela_bottom.setVisibility(View.VISIBLE);
                        startPlay();
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


    //setDrawble
    public void setDrawableLeft(int resId, TextView textView) {
        Drawable drawable = AudioDetailActivity.this.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

}
