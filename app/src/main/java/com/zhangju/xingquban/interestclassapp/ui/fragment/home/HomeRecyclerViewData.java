package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.near.LiveVideoNearAdapter;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.AdBannerBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.bean.LivePayBean;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NearJiGouJianjie;
import com.zhangju.xingquban.interestclassapp.http.BaseSubscriber;
import com.zhangju.xingquban.interestclassapp.http.MyNetManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ApplyToMemberActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CommentDetailActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.LiveWatchActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.MapNaviActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.NearAlbumManageActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.NearAllCommentActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.NearAllSjkcAcitivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.NearAllVideoLessonsAcitivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.mediscover.FindLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc.HomeDataMsfcAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc.HomeDataMsfcAll;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc.HomeDataMsfcXq;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.ppjs.HomeDataPpjs;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc.CurriculumXqActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc.HomeDataSjkcAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc.HomeDataSpkcAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc.HomeDataSpkcXq;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.wydp.HomeDataCommentAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.xczs.HomeDataXczsAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.zyzs.RefreshEvent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LivePayActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveVideoPlayerActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;
import com.zhangju.xingquban.interestclassapp.view.me.AlertDialogIOS;
import com.zhangju.xingquban.interestclassapp.view.scrollerview.ObservableScrollView;
import com.zhangju.xingquban.interestclassapp.view.scrollerview.ScrollViewListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeRecyclerViewData extends BaseActivity {
    @BindView(R.id.home_data_image)
    Banner homeDataImage;
    @BindView(R.id.home_data_teacher_icon)
    ImageView homeDataTeacherIcon;
    @BindView(R.id.home_data_tacher_name)
    TextView homeDataTacherName;
    @BindView(R.id.tv_teacher_liulan)
    TextView tvTeacherLiulan;
    @BindView(R.id.tv_teacher_fensi)
    TextView tvTeacherFensi;
    @BindView(R.id.home_data_tacher_start1)
    ImageView homeDataTacherStart1;
    @BindView(R.id.home_data_tacher_start2)
    ImageView homeDataTacherStart2;
    @BindView(R.id.home_data_tacher_start3)
    ImageView homeDataTacherStart3;
    @BindView(R.id.home_data_tacher_start4)
    ImageView homeDataTacherStart4;
    @BindView(R.id.home_data_tacher_start5)
    ImageView homeDataTacherStart5;
    @BindView(R.id.home_data_tacher_plNumber)
    TextView homeDataTacherPlNumber;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.home_data_tacher_address)
    TextView homeDataTacherAddress;
    @BindView(R.id.home_data_teacher_title)
    LinearLayout homeDataTeacherTitle;
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
    @BindView(R.id.home_data_message1)
    ImageView homeDataMessage1;
    @BindView(R.id.home_data_message)
    RelativeLayout homeDataMessage;
    @BindView(R.id.img_sjkc)
    ImageView imgSjkc;
    @BindView(R.id.all_sjkc)
    RelativeLayout allSjkc;
    @BindView(R.id.home_data_sj_recycler)
    RecyclerView homeDataSjRecycler;
    @BindView(R.id.home_data_qbkc)
    TextView homeDataQbkc;
    @BindView(R.id.home_data_qbkc_number)
    TextView homeDataQbkcNumber;
    @BindView(R.id.home_data_sjkc_number)
    RelativeLayout homeDataSjkcNumber;
    @BindView(R.id.home_data_sjkc_all)
    LinearLayout homeDataSjkcAll;
    @BindView(R.id.xczs_image)
    ImageView xczsImage;
    @BindView(R.id.home_data_xczs_recycler)
    RecyclerView homeDataXczsRecycler;
    @BindView(R.id.home_data_xczs_all)
    LinearLayout homeDataXczsAll;
    @BindView(R.id.img_zhibo)
    ImageView imgZhibo;
    @BindView(R.id.home_data_zhibo_img)
    ImageView homeDataZhiboImg;
    @BindView(R.id.home_data_alllive)
    RelativeLayout homeDataAlllive;
    @BindView(R.id.home_data_live_recycler)
    RecyclerView homeDataLiveRecycler;
    @BindView(R.id.ll_zhibo)
    LinearLayout llZhibo;
    @BindView(R.id.home_data_spkc)
    ImageView homeDataSpkc;
    @BindView(R.id.home_data_spkc_return)
    ImageView homeDataSpkcReturn;
    @BindView(R.id.home_data_spkc_vidoNumber)
    TextView homeDataSpkcVidoNumber;
    @BindView(R.id.home_data_spkc_number)
    LinearLayout homeDataSpkcNumber;
    @BindView(R.id.all_shipinkec)
    RelativeLayout allShipinkec;
    @BindView(R.id.home_data_spkc_recycler)
    RecyclerView homeDataSpkcRecycler;
    @BindView(R.id.home_data_spkc_all)
    LinearLayout homeDataSpkcAll;
    @BindView(R.id.home_data_msfc_qbls)
    ImageView homeDataMsfcQbls;
    @BindView(R.id.home_data_spkc_return1)
    ImageView homeDataSpkcReturn1;
    @BindView(R.id.home_data_msfcNumber)
    TextView homeDataMsfcNumber;
    @BindView(R.id.home_data_msfc_number)
    LinearLayout llhomeDataMsfcNumber;
    @BindView(R.id.home_data_msfc)
    RelativeLayout homeDataMsfc;
    @BindView(R.id.home_data_msfc_recycler)
    RecyclerView homeDataMsfcRecycler;
    @BindView(R.id.home_data_msfc_all)
    LinearLayout homeDataMsfcAll;
    @BindView(R.id.home_data_zb)
    ImageView homeDataZb;
    @BindView(R.id.home_data_live_city)
    TextView homeDataLiveCity;
    @BindView(R.id.home_data_live_title)
    TextView homeDataLiveTitle;
    @BindView(R.id.home_data_live_money)
    TextView homeDataLiveMoney;
    @BindView(R.id.home_data_live_gk_number)
    TextView homeDataLiveGkNumber;
    @BindView(R.id.home_data_live)
    TextView homeDataLive;
    @BindView(R.id.home_data_qbkc_live_number)
    TextView homeDataQbkcLiveNumber;
    @BindView(R.id.home_data_live_number)
    RelativeLayout homeDataLiveNumber;
    @BindView(R.id.home_data_live_all)
    LinearLayout homeDataLiveAll;
    @BindView(R.id.xiangceimg)
    ImageView xiangceimg;
    @BindView(R.id.tv_xiangce)
    TextView tvXiangce;
    @BindView(R.id.xc_right)
    ImageView xcRight;
    @BindView(R.id.all_xiangce)
    RelativeLayout allXiangce;
    @BindView(R.id.home_data_zyzs_Recycler)
    RecyclerView homeDataZyzsRecycler;
    @BindView(R.id.home_data_zyzs_all)
    LinearLayout homeDataZyzsAll;
    @BindView(R.id.home_data_image_rongyu)
    ImageView homeDataImageRongyu;
    @BindView(R.id.home_data_pinpaijs)
    LinearLayout homeDataPinpaijs;
    @BindView(R.id.home_data_wydp_number)
    TextView homeDataWydpNumber;
    @BindView(R.id.home_data_pj_wydp)
    RelativeLayout homeDataPjWydp;
    @BindView(R.id.home_data_recyclerDp)
    RecyclerView homeDataRecyclerDp;
    @BindView(R.id.home_data_pj_wydp_all)
    RelativeLayout homeDataPjWydpAll;
    @BindView(R.id.home_data_rzxqb_all)
    RelativeLayout homeDataRzxqbAll;
    @BindView(R.id.home_data_rzxqb_yy)
    LinearLayout homeDataRzxqbYy;
    @BindView(R.id.scroller_view)
    ObservableScrollView scrollerView;
    @BindView(R.id.home_data_return)
    LinearLayout homeDataReturn;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.home_data_fenx)
    LinearLayout homeDataFenx;
    @BindView(R.id.head_title)
    RelativeLayout headTitle;
    @BindView(R.id.home_data_xdp)
    LinearLayout homeDataXdp;
    @BindView(R.id.home_data_dhlx)
    LinearLayout homeDataDhlx;
    @BindView(R.id.shoucang)
    TextView shoucang;
    @BindView(R.id.home_data_gz)
    LinearLayout homeDataGz;
    @BindView(R.id.renzheng_icon)
    ImageView renzhengicon;
    @BindView(R.id.jieshao)
    TextView jieshao;

    @BindView(R.id.audio_play_back)
    ImageView audio_play_back;
    @BindView(R.id.fragment_play)
    FrameLayout fragmentPlay;
    @BindView(R.id.audio_jiecaoplayer)
    JCVideoPlayerStandard audioJiecaoplayer;
    @BindView(R.id.tv_ischarge_res)
    TextView tv_ischarge_res;
    @BindView(R.id.progress)
    FrameLayout progress;

    private HomeRecylerBean.AaDataBean homeXq;
    private BottomDialog2 bottomDialog2;
    private Subscription suscription;

    private HomeDataCommentAdapter homeDataCommentAdapter;
    private HomeDataSjkcAdapter homeDataSjkcAdapter;
    private HomeDataSpkcAdapter spkcAdapter;
    private HomeDataMsfcAdapter msfcAdapter;
    private HomeDataXczsAdapter homeDataXczsAdapter;
    private LiveVideoNearAdapter liveVideoNearAdapter;
    private List<String> imageslist = new ArrayList<>();
    private HomeDataTeacherBean teacherInfo;
    private Intent intent;
    private String mRoomId;
    private String SHARE_URL;
    private MyShareDialog mMyShareDialog;
    private String mShareName = "兴趣班";
    private String mShareSummary = "为兴趣而生,为梦想而来";
    private BannerHelper mBannerHelper;
    private boolean loadFailure = true; // 如果数据获取失败禁用全部点击事件

    @Override
    public int getLayout() {
        return R.layout.activity_home_recycler_view_data;
    }


    public static void launchActivity(Context activity, String id) {
        Intent intent = new Intent(activity, HomeRecyclerViewData.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    private String getOrgId() {
        if (getIntent() != null) {
            return getIntent().getStringExtra("id");
        }
        return "";
    }


    @Override
    public void initView() {
        shoucang.setEnabled(false);
        EventObserver.getInstance().subscribe(HomeRecyclerViewData.this, this);
        if (!TextUtils.isEmpty(getOrgId())) {
            progress.setVisibility(View.VISIBLE);
            HashMap<String, String> map = new HashMap<>();
            map.put("id", getOrgId());
            MyNetManager.getInstance().teacherLs(map, addSubscriber(new BaseSubscriber<NearDataBean>() {
                @Override
                public void onSuccess(NearDataBean bean) {
                    loadFailure = true;
                    progress.setVisibility(View.GONE);
                    homeXq = bean.getAaData().get(0);
                    getData();
                    initVideoBanner();
                }

                @Override
                public void onFailure(NearDataBean aaDataBean) {
                    loadFailure = false;
                    progress.setVisibility(View.GONE);
                    super.onFailure(aaDataBean);
                }

                @Override
                public void onError(Throwable e) {
                    loadFailure = false;
                    progress.setVisibility(View.GONE);
                    super.onError(e);
                }
            }));
        }
//        /*获取机构老师的详细信息*/
        msfcData();
        /*获取机构简介*/
        mMyShareDialog = new MyShareDialog(mContext);

    }

    private void initVideoBanner() {
        //默认
        audioJiecaoplayer.setUp("", JCVideoPlayer.CURRENT_STATE_NORMAL, "");
        audioJiecaoplayer.thumbImageView.setImageResource(R.drawable.back_play);
        loadVideoData();
    }

    @Event
    private void reciver(RefreshEvent refreshEvent) {
        /*获取机构老师的详细信息*/
        homeDataPjWydpAll.setVisibility(View.VISIBLE);
        msfcData();

    }

    private void initJgSummary() {
        final Request request = Request.obtain(INear.POST_JIGOUXIANGQING);
        request.put("customerId", teacherInfo.getAaData().get(0).getCustomerId());
        request.setListener(new SimpleListener<NearJiGouJianjie>() {

            @Override
            public void onResponseListener(Request r, NearJiGouJianjie result) {
                if (result.getAaData().size() == 0) {

                } else {
                    List<String> introList = result.getAaData().get(0).getIntroList();
                    if (introList != null && introList.size() > 1) {
                        mShareSummary = introList.get(1);
                    }
                    if (homeXq.getDegreeId().equals("2")) {//机构详情
                        homeDataPinpaijs.setVisibility(View.VISIBLE);
                    } else {
                        homeDataPinpaijs.setVisibility(View.VISIBLE);
                    }

                }
            }
        });
        request.start();
    }

    private void requestBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(homeDataImage);
        if ("2".equals(homeXq.getDegreeId())) {//机构详情
            mBannerHelper.loadBannerDate("6");
        } else {
            mBannerHelper.loadBannerDate("5");
        }

    }

    private void bindDataToView() {
        initJgSummary();
        requestBanner();
        bindRenzheng();
        /*名声风采*/
        binddataMsfc();
        /*私教课程*/
        SjkcData();
        /*视频课程*/
        shkcData();
        /*相册资源*/
        xczsData();
        /*评论*/
        comment();

        if (teacherInfo.getAaData().get(0).getChangepic() != null)
            for (int i = 0; i < teacherInfo.getAaData().get(0).getChangepic().size(); i++) {
                imageslist.add(teacherInfo.getAaData().get(0).getChangepic().get(i).getPictureurl());
            }
        //        advertismentHead();
    }

    private void bindRenzheng() {
        //资质认证 蓝V
        if (teacherInfo.getAaData().get(0).getQcAuth() == 2) {
            Glide.with(mContext).load(R.mipmap.home_data_rz).into(imgZzrz);
            renzhengicon.setVisibility(View.VISIBLE);
        } else {
            Glide.with(mContext).load(R.mipmap.find_huodong_dianda).into(imgZzrz);

        }
        if (homeXq.getDegreeId().equals("1")) {
            if (teacherInfo.getAaData().get(0).getRealnameAuth() == 2) {
                Glide.with(mContext).load(R.mipmap.home_data_rz).into(imgSfrz);
            } else {
                Glide.with(mContext).load(R.mipmap.find_huodong_dianda).into(imgSfrz);
            }

        } else {
            imgSfrz.setVisibility(View.GONE);
            tvSfrz.setVisibility(View.GONE);
        }
        //身份认证
        tvTeacherLiulan.setText("浏览" + teacherInfo.getAaData().get(0).getClickRate() + "");
        tvTeacherFensi.setText("粉丝" + teacherInfo.getAaData().get(0).getCollectionNum() + "");
        int range = homeXq.getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        if (range < 1000)
            homeDataTacherAddress.setText("距" + teacherInfo.getAaData().get(0).getAreasName() + range + "m");
        else
            homeDataTacherAddress.setText("距" + teacherInfo.getAaData().get(0).getAreasName() + dis + "km");
        if (teacherInfo.getAaData().get(0).isIsCollection())
            shoucang.setText("已收藏");
        else
            shoucang.setText("收藏");
    }


    //私教课程
    public void SjkcData() {
        if (teacherInfo.getAaData().get(0).getLessons().size() == 0)
            allSjkc.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeDataSjRecycler.setLayoutManager(linearLayoutManager);
        homeDataSjkcAdapter = new HomeDataSjkcAdapter(this, teacherInfo);
        homeDataSjRecycler.setAdapter(homeDataSjkcAdapter);
        homeDataSjkcAdapter.setOnItemClickListener(new HomeDataSjkcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HomeDataTeacherBean.AaDataBean.LessonsBean lessonsBea = teacherInfo.getAaData().get(0).getLessons().get(position);
                CurriculumXqActivity.lanuchActivity(HomeRecyclerViewData.this, lessonsBea.getId());
            }
        });

    }

    //视频课程
    public void shkcData() {
        if (homeXq.getVideoLesson().size() == 0)
            allShipinkec.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeDataSpkcRecycler.setLayoutManager(linearLayoutManager);
        spkcAdapter = new HomeDataSpkcAdapter(this, homeXq);
        homeDataSpkcRecycler.setAdapter(spkcAdapter);
        spkcAdapter.setOnItemClickListener(new HomeDataSpkcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(HomeRecyclerViewData.this, HomeDataSpkcXq.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(HomeDataSpkcXq.ARG_BEAN_SHIPINXQ, teacherInfo.getAaData().get(0).getVideoLesson().get
                        (position));
                bundle.putString(HomeDataSpkcXq.ARG_STRING_NAME, teacherInfo.getAaData().get(0).getSigname());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    Observer<HomeDataTeacherBean> observer = new Observer<HomeDataTeacherBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            //ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(HomeDataTeacherBean homeDataTeacherBean) {
            teacherInfo = homeDataTeacherBean;
            shoucang.setEnabled(true);
            bindDataToView();

        }
    };


    Observer<Object> homeDataSc = new Observer<Object>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            //ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(Object object) {
            Gson gson = new Gson();
            JSONObject jsonObject = null;
            try {

                jsonObject = JSON.parseObject(gson.toJson(object));
                JSONObject jso = jsonObject.getJSONObject("aaData");
                if (jso.getString("isCollections").equals("添加收藏成功"))
                    shoucang.setText("已收藏");
                else
                    shoucang.setText("收藏");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    //名师风采
    public void msfcData() {
        Location location = LocationManager.getInstance().getLocation();
        NetWork.getHomeBanner().getHomeDataTeacher(location.cityId, location.longitude, location.latitude, getOrgId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //视频直播
    private void bindataZhibo() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeDataLiveRecycler.setLayoutManager(linearLayoutManager);
        liveVideoNearAdapter = new LiveVideoNearAdapter(mContext, homeXq);
        homeDataLiveRecycler.setAdapter(liveVideoNearAdapter);
        liveVideoNearAdapter.setOnItemClickListener(new LiveVideoNearAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (UserManager.getInstance().isLogin()) {
                    getVideoInfo(homeXq.getLiveVdo().getId(), 0, 1);
                } else {
                    startActivity(new Intent(HomeRecyclerViewData.this, com.zhangju.xingquban.interestclassapp.refactor.me
                            .activity.LoginActivity.class));
                }

            }
        });
    }

    /**
     * 进入直播间
     *
     * @param roomId
     *///1 直播 2回播
    private void getVideoInfo(String roomId, final int position, final int type) {
        HttpUtils httpUtils = new HttpUtils();
        final String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        if (type == 2) {
            params.addBodyParameter("liveVdoId", roomId);
        } else {
            params.addBodyParameter("id", roomId);
        }
        String url = UrlUtils.URL_INITROOM;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;

                        LivePayBean bean = JSONObject.parseObject(json, LivePayBean.class);

                        try {
                            if (bean != null && bean.getAttachData() != null) {
                                boolean pay = bean.getAttachData().isPay();//是否支付费用
                                //当视频免费或者是已付费
                                Intent intent = new Intent();
                                //直播
                                if (type == 1) {
                                    String chatroomId = bean.getAaData().getChatroomId() == null ? "" : bean.getAaData()
                                            .getChatroomId();//
                                    String rtmpPullUrl = bean.getAaData().getChannels().getRtmpPullUrl() == null ? "" : bean
                                            .getAaData().getChannels().getRtmpPullUrl();
                                    String accid = bean.getAaData().getChatUser().getAccid() == null ? "" : bean.getAaData()
                                            .getChatUser().getAccid();
                                    String appKey = bean.getAttachData().getAppKey() == null ? "" : bean.getAttachData()
                                            .getAppKey();
                                    String chatToken = bean.getAaData().getChatUser().getToken() == null ? "" : bean.getAaData
                                            ().getChatUser().getToken();
                                    int stdCoin = bean.getAaData().getStdCoin();
                                    String followes = bean.getAaData().getChatUser().getFollowes() == null ? "" : bean
                                            .getAaData().getChatUser().getFollowes();


                                    if (pay) {
                                        intent.setClass(HomeRecyclerViewData.this, LiveWatchActivity.class);
                                    } else {
                                        double roomPrice = bean.getAttachData().getRoomPrice();
                                        double seeBalances = bean.getAttachData().getSeeBalances(); // 观看者的余额
                                        intent = new Intent(HomeRecyclerViewData.this, LivePayActivity.class);
                                        intent.putExtra("roomPrice", roomPrice);
                                        intent.putExtra("seeBalances", seeBalances);
                                    }
                                    intent.putExtra("stdCoin", stdCoin);
                                    intent.putExtra("chatToken", chatToken);
                                    intent.putExtra("accid", accid);
                                    intent.putExtra("appKey", appKey);
                                    intent.putExtra("rtmpPullUrl", rtmpPullUrl);
                                    intent.putExtra("chatroomId", chatroomId);
                                    intent.putExtra("mToken", token);
                                    intent.putExtra("myFollowes", followes);
                                    intent.putExtra("roomId", mRoomId);

                                    //录播
                                } else {
                                    String id = bean.getAaData().getId() == null ? "" : bean.getAaData().getId();
                                    String liveVdoUrl = bean.getAttachData().getLiveVdoUrl() == null ? "" : bean.getAttachData
                                            ().getLiveVdoUrl();
                                    String orig_video_key = bean.getAaData().getOrig_video_key() == null ? "" : bean.getAaData
                                            ().getOrig_video_key();
                                    boolean addOp = bean.getAaData().isAddOp();

                                    intent.setClass(HomeRecyclerViewData.this, LiveVideoPlayerActivity.class);
                                    intent.putExtra("id", id);
                                    intent.putExtra("url", liveVdoUrl + "/" + orig_video_key);
                                    intent.putExtra("isfocus", addOp);
                                }
                                startActivity(intent);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });
    }

    //名师风采
    private void binddataMsfc() {
        if (teacherInfo.getAaData().get(0).getReses().size() == 0) {
            homeDataMsfcAll.setVisibility(View.GONE);
        }
        homeDataMsfcNumber.setText(teacherInfo.getAaData().get(0).getResesNum() + "");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeDataMsfcRecycler.setLayoutManager(linearLayoutManager);
        msfcAdapter = new HomeDataMsfcAdapter(mContext, teacherInfo);
        homeDataMsfcRecycler.setAdapter(msfcAdapter);
        msfcAdapter.setOnItemClickListener(new HomeDataMsfcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(HomeRecyclerViewData.this, HomeDataMsfcXq.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("teacher", teacherInfo.getAaData().get(0).getReses().get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //相册展示
    public void xczsData() {
        if (teacherInfo.getAaData().get(0).getOrganAlbumFiles().size() == 0)
            allXiangce.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeDataZyzsRecycler.setLayoutManager(linearLayoutManager);
        if (homeDataXczsAdapter == null) {
            homeDataXczsAdapter = new HomeDataXczsAdapter(mContext, teacherInfo);
        }
        homeDataZyzsRecycler.setAdapter(homeDataXczsAdapter);

        homeDataXczsAdapter.setOnItemClickListener(new HomeDataXczsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                intent = new Intent(HomeRecyclerViewData.this, NearAlbumManageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("albumFiles", teacherInfo.getAaData().get(0));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //评论
    public void comment() {
        if (teacherInfo.getAaData().get(0).getCommentsList().size() == 0)
            homeDataPjWydpAll.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeDataRecyclerDp.setLayoutManager(linearLayoutManager);
        homeDataCommentAdapter = new HomeDataCommentAdapter(mContext, teacherInfo);
        homeDataRecyclerDp.setAdapter(homeDataCommentAdapter);
        homeDataCommentAdapter.setOnItemClickListener(new HomeDataCommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(HomeRecyclerViewData.this, CommentDetailActivity.class);
                intent.putExtra(CommentDetailActivity.ARG_SER_COMMENT, teacherInfo.getAaData().get(0).getCommentsList().get
                        (position));
                startActivity(intent);
                //                intent = new Intent(HomeRecyclerViewData.this, HomeDataWdplXq.class);
                //                bundle = new Bundle();
                //                bundle.putSerializable(HomeDataWdplXq.ARG_STRING_COMMENTID, teacherInfo.getAaData().get(0)
                // .getCommentsList().get(position).getId());
                //                intent.putExtras(bundle);
                //                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        scrollerView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                //Y轴偏移量
                float scrollY = scrollView.getScrollY();
                //按照滑动之后的变化率来变色
                /*	float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值*/
                float headerBarOffsetY = 300;
                float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
                headTitle.setBackgroundColor(Color.argb(230, 170, 170, 170));
                if (scrollY == 0) {
                    headTitle.setBackgroundColor(Color.argb(0, 210, 210, 210));
                    toolbarText.setVisibility(View.GONE);
                } else {
                    toolbarText.setVisibility(View.VISIBLE);
                }
                if (scrollY >= 330) {
                    headTitle.setBackgroundColor(Color.argb(230, 170, 170, 170));
                }
            }
        });
    }

    @Override
    protected void onPause() {
        audioJiecaoplayer.release();
        mBannerHelper.stopBanner();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(this, this);
        mBannerHelper.stopBanner();
        audioJiecaoplayer.release();
    }

    @OnClick({R.id.home_data_return, R.id.home_data_fenx, R.id.home_data_city, R.id.home_data_message, R.id.home_data_sjkc_number,
            R.id.home_data_spkc_number, R.id.home_data_msfc_all, R.id.home_data_live_number, R.id.home_data_rzxqb_all, R.id
            .home_data_pj_wydp, R.id.home_data_pinpaijs
            , R.id.home_data_xdp, R.id.home_data_gz, R.id.all_sjkc, R.id.home_data_dhlx, R.id.all_shipinkec, R.id.all_xiangce,
            R.id.home_data_alllive, R.id.home_data_rzxqb_yy

    })
    public void onClick(View v) {
        if (!loadFailure) {
            return;
        }
        switch (v.getId()) {
            //全部点评
            case R.id.home_data_pj_wydp:
                intent = new Intent(this, NearAllCommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(NearAllCommentActivity.ARG_STRING_DATA, homeXq);
                bundle.putSerializable(NearAllCommentActivity.ARG_STRING_TEACHER, teacherInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //入住商家
            case R.id.home_data_rzxqb_all:
                break;

            //名师风采数量
            case R.id.home_data_msfc_all:
                intent = new Intent(this, HomeDataMsfcAll.class);
                bundle = new Bundle();
                bundle.putSerializable(HomeDataMsfcAll.ARG_TEACHER_DATA, teacherInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;


            //信息
            case R.id.home_data_message:
                ToastUtil.showToast("敬请期待");
                break;

            //品牌介绍
            case R.id.home_data_pinpaijs:
                intent = new Intent(this, HomeDataPpjs.class);
                bundle = new Bundle();
                bundle.putSerializable(HomeDataPpjs.ARG_STRING_TEACHER, teacherInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //写点评
            case R.id.home_data_xdp:
                if (UserManager.getInstance().isLogin()) {
                    intent = new Intent(this, HomeDataXdp.class);
                    bundle = new Bundle();
                    bundle.putString(HomeDataXdp.ARG_STRING_ID, homeXq.getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity
                            .class));
                }


                break;

            //分享
            case R.id.home_data_fenx:
                if (homeXq.getDegreeId().equals("1")) { // 老师
                    SHARE_URL = "http://my.xqban.com/share/#/teacher/detail?id=" + getOrgId();
                    if (homeXq.getUsername() != null) {
                        mShareName = homeXq.getUsername();
                    }
                } else if (homeXq.getDegreeId().equals("2")) { // 机构
                    if (homeXq.getName() != null) {
                        mShareName = homeXq.getName();
                    }
                    SHARE_URL = "http://my.xqban.com/share/#/merchant/detail?id=" + getOrgId();
                }
                mMyShareDialog
                        .initShare(homeXq.getPicture(), SHARE_URL, mShareSummary, mShareName)
                        .show();
                break;

            //城市
            case R.id.home_data_city:

                intent = new Intent(this, MapNaviActivity.class);
                bundle = new Bundle();
                bundle.putDouble(MapNaviActivity.ARG_DOUBLE_LAT, homeXq.getLat());
                bundle.putDouble(MapNaviActivity.ARG_DOUBLE_LNG, homeXq.getLng());
                bundle.putString(MapNaviActivity.ARG_STRING_NAME, homeXq.getSigname());
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //收藏
            case R.id.home_data_gz:
                if (UserManager.getInstance().isLogin()) {
                    String resourceId = teacherInfo.getAaData().get(0).getId();
                    Request request = new Request("/collection/add.json");
                    request.put("collectionTypes", 3);
                    request.put("customerId", UserManager.getInstance().getUser().id);
                    request.put("resourcesId", resourceId);
                    request.put("teacherCustomerId", resourceId);
                    request.setListener(new SimpleListener<Response>() {

                        @Override
                        public void onResponseListener(Request r, Response result) {
                            if (result.success) {
                                teacherInfo.getAaData().get(0).setIsCollection(!teacherInfo.getAaData().get(0).isIsCollection());
                                shoucang.setText(teacherInfo.getAaData().get(0).isIsCollection() ? "已收藏" : "收藏");
                            }
                        }
                    });
                    request.start();
                    //                    suscription = NetWork.getHomeData().getHomeDataSc(UserManager.getInstance().getUser()
                    // .id,teacherInfo.getAaData().get(0).getId(), "3", teacherInfo.getAaData().get(0).getId())
                    //                            .subscribeOn(Schedulers.io())
                    //                            .observeOn(AndroidSchedulers.mainThread())
                    //                            .subscribe(homeDataSc);
                } else {
                    startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity
                            .class));
                }
                break;

            //返回
            case R.id.home_data_return:
                finish();
                break;
            //私教课程
            case R.id.all_sjkc:
                intent = new Intent(HomeRecyclerViewData.this, NearAllSjkcAcitivity.class);
                bundle = new Bundle();
                bundle.putSerializable(NearAllSjkcAcitivity.ARG_STRING_DATA, homeXq);
                bundle.putSerializable(NearAllSjkcAcitivity.ARG_STRING_TEACHER, teacherInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.home_data_dhlx:
                final String contactPhone = !TextUtils.isEmpty(teacherInfo.getAaData().get(0).getContactTel()) ?
                        teacherInfo.getAaData().get(0).getContactTel() :
                        teacherInfo.getAaData().get(0).getPhone();
                new AlertDialogIOS(HomeRecyclerViewData.this).builder().setTitle("提示")
                        .setMsg(contactPhone)
                        .setPositiveButton("呼叫", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                intent = new Intent();
                                intent.setAction(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + contactPhone));
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
            //全部视频课程
            case R.id.all_shipinkec:
                intent = new Intent(HomeRecyclerViewData.this, NearAllVideoLessonsAcitivity.class);
                bundle = new Bundle();
                bundle.putSerializable(NearAllVideoLessonsAcitivity.ARG_STRING_DATA, homeXq);
                bundle.putSerializable(NearAllVideoLessonsAcitivity.ARG_STRING_TEACHER, teacherInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.all_xiangce:
                intent = new Intent(HomeRecyclerViewData.this, NearAlbumManageActivity.class);
                bundle = new Bundle();
                bundle.putSerializable("albumFiles", teacherInfo.getAaData().get(0));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.home_data_alllive:
                if (UserManager.getInstance().isLogin())
                    startActivity(new Intent(HomeRecyclerViewData.this, FindLiveActivity.class));
                else
                    startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity
                            .class));
                break;
            case R.id.home_data_rzxqb_yy:
                //根据用户当前登录信息，跳转到申请老师机构页面
                //如果已经是商户或者老师则不必跳转
                if (UserManager.getInstance().getUser().degree.isOrganization || UserManager.getInstance().getUser().degree
                        .isTeacher) {
                } else {
                    Intent intent = new Intent(this, ApplyToMemberActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public void getData() {
        homeDataSpkcVidoNumber.setText(homeXq.getVideoLessonNum() + "");
        homeDataWydpNumber.setText(homeXq.getCommentCount() + "");

        if (homeXq.getLiveVdo() == null) {
            llZhibo.setVisibility(View.GONE);
        } else {
            bindataZhibo();
        }
        homeDataAddress.setText(homeXq.getClassRoom());
        homeDataTacherPlNumber.setText(homeXq.getCommentCount() + "");
        toolbarText.setVisibility(View.GONE);
        if (homeXq.getDegreeId().equals("1")) {
            toolbarText.setText("老师详情");
            homeDataTeacherTitle.setVisibility(View.VISIBLE);

            homeDataMessage.setVisibility(View.GONE);
            homeDataRzxqbAll.setVisibility(View.GONE);
            homeDataRzxqbYy.setVisibility(View.VISIBLE);
            homeDataPinpaijs.setVisibility(View.VISIBLE);
            jieshao.setText("老师介绍");
            homeDataLiveAll.setVisibility(View.GONE);
            homeDataMsfcAll.setVisibility(View.GONE);
            homeDataTeacherIcon.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(homeXq.getPicture()).into(homeDataTeacherIcon);
            homeDataTacherName.setText(homeXq.getUsername());
        }
        /*机构*/
        if (homeXq.getDegreeId().equals("2")) {
            toolbarText.setText("机构详情");
            homeDataTacherName.setText(homeXq.getName());
            homeDataMessage.setVisibility(View.VISIBLE);
            homeDataTeacherIcon.setVisibility(View.GONE);
            jieshao.setText("品牌介绍");
        }
        switch ((int) homeXq.getAvgComment()) {
            case 0:
                homeDataTacherStart1.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 1:
                homeDataTacherStart1.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 2:
                homeDataTacherStart1.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart2.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 3:
                homeDataTacherStart1.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart2.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart3.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                homeDataTacherStart5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 4:
                homeDataTacherStart1.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart2.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart3.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart4.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 5:
                homeDataTacherStart1.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart2.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart3.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart4.setImageResource(R.drawable.home_recyler_item_pingjia);
                homeDataTacherStart5.setImageResource(R.drawable.home_recyler_item_pingjia);
                break;
        }

    }

    public void loadVideoData() {
        LocationManager instance = LocationManager.getInstance();
        Location location = instance.getLocation();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("pageIndex", "1");
        params.addQueryStringParameter("pageSize", "10");
        params.addQueryStringParameter("advertPutInProvince", location.cityPid);
        params.addQueryStringParameter("advertPutInCity", location.cityId);
        if ("2".equals(homeXq.getDegreeId())) {//机构详情
            params.addQueryStringParameter("advertSection", "6");
        } else {
            params.addQueryStringParameter("advertSection", "5");
        }
        params.addQueryStringParameter("isPut", "1");
        params.addQueryStringParameter("status", "1");
        params.addQueryStringParameter("advertType", "2");
        params.addQueryStringParameter("advertClassification", "1");
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());

        String url = UrlUtils.URL_ADVERT_LIST;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        AdBannerBean bean = JSONObject.parseObject(json, AdBannerBean.class);
                        if (bean.isSuccess()) {
                            AdBannerBean.AaDataBean aaData = bean.getAaData();
                            if (aaData != null) {
                                List<AdBannerBean.AaDataBean.ListBean> list = aaData.getList();
                                if (list != null && list.size() > 0) {
                                    String advertUrl = list.get(0).getAdvertUrl();
                                    if (!TextUtils.isEmpty(advertUrl)) {
                                        fragmentPlay.setVisibility(View.VISIBLE);
                                        homeDataImage.setVisibility(View.INVISIBLE);
                                        audioJiecaoplayer.setUp(advertUrl, JCVideoPlayer.CURRENT_STATE_NORMAL, "");
//                                        audioJiecaoplayer.startVideo();
                                    }
                                } else {
                                    homeDataImage.setVisibility(View.VISIBLE);
                                    fragmentPlay.setVisibility(View.INVISIBLE);
                                }
                            }
                        } else {
                            ToastUtil.showToast(bean.getErrMsg().toString());
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });
    }
}