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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.AudioDetailAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.ResVideoListAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDetailBean;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollLinearLayoutManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.util.html.RichText;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ContentView(R.layout.activity_news_detail)
public class NewsDetailActivity
        extends FastActivity
        implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.audio_back)
    ImageView                audioBack;
    @BindView(R.id.audio_share)
    ImageView                audioShare;
    @BindView(R.id.swipenewsrefresh)
    SwipeRefreshRecyclerView swipeAudioDeatailrefresh;

    @BindView(R.id.audio_detail_comment)
    RelativeLayout audioDetailComment;
    @BindView(R.id.audio_detail_prise)
    TextView       audioDetailPrise;
    @BindView(R.id.audio_detail_collect)
    TextView       audioDetailCollect;

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
    TextView       tv__news_title;
    TextView       tv_news_summary;
    TextView       tv_news_name;
    RoundImageView image_top_head;
    TextView       tv_news_time;
    RichText       rich_text;
    LinearLayout   line_state;
    LinearLayout   line_news;


    int isPrise   = 0;//是否点赞
    int isCollect = 0;//是否收藏
    private int pageIndex = 0;
    private int total     = 0;
    private String resId;//资源id
    boolean isfresh;

    private View mAduioView;
    private String types = "article";//类型
    private AudioDetailAdapter adapter;

    List<ResDetailBean.AaDataBean> mCommentList = new ArrayList<>();//评论集合
    //    http://my.xqban.com/admnxzcmr/resources/share?types=article&id=41891&cityId=

    private String            sharetitle   = "资源";
    private String            shareid      = "";
    private String            cityid       = "";
    private String            shareUrl     = "http://my.xqban.com/admnxzcmr/resources/share?types=";
    private String            image        = "";
    private ArrayList<String> mlist        = new ArrayList<>();
    private String            shareContent = "";
    private MyShareDialog mMyShareDialog;


    @Override
    protected void alreadyPrepared() {
        getLayoutHeadView();
        getIntentData();
        cityid = LocationManager.getInstance().getLocation().cityId;
    }

    private void getIntentData() {
        resId = getIntent().getStringExtra("id");
        setAudioDetailAdapter();
        getDetailTopData();
        initShare();
    }

    private void initShare() {
        mMyShareDialog = new MyShareDialog(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void setAudioDetailAdapter() {
        adapter = new AudioDetailAdapter(NewsDetailActivity.this, mCommentList, mAduioView);
        swipeAudioDeatailrefresh.setLayoutManager(new LinearLayoutManager(NewsDetailActivity.this));
        swipeAudioDeatailrefresh.setAdapter(adapter);

        swipeAudioDeatailrefresh.setOnListLoadListener(this);
        swipeAudioDeatailrefresh.setOnRefreshListener(this);
        swipeAudioDeatailrefresh.autoRefresh();
    }

    private void getLayoutHeadView() {
        mAduioView = LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.res_detail_top, null);
        tv_title = (TextView) mAduioView.findViewById(R.id.tv_title);
        tv_price = (TextView) mAduioView.findViewById(R.id.tv_price);
        tv_play_count = (TextView) mAduioView.findViewById(R.id.tv_play_count);
        tv_video_comment_num = (TextView) mAduioView.findViewById(R.id.tv_video_comment_num);
        tv_video_love_num = (TextView) mAduioView.findViewById(R.id.tv_video_love_num);
        tv_nick_name = (TextView) mAduioView.findViewById(R.id.tv_nick_name);
        image_head = (RoundImageView) mAduioView.findViewById(R.id.image_head);
        recycler_res_list = (RecyclerView) mAduioView.findViewById(R.id.recycler_res_list);

        tv_title_Count = (TextView) mAduioView.findViewById(R.id.tv_title_Count);
        tv__news_title = (TextView) mAduioView.findViewById(R.id.tv__news_title);
        tv_news_time = (TextView) mAduioView.findViewById(R.id.tv_news_time);
        tv_news_name = (TextView) mAduioView.findViewById(R.id.tv_news_name);
        tv_news_summary = (TextView) mAduioView.findViewById(R.id.tv_news_summary);
        image_top_head = (RoundImageView) mAduioView.findViewById(R.id.image_top_head);
        line_state = (LinearLayout) mAduioView.findViewById(R.id.line_state);
        line_news = (LinearLayout) mAduioView.findViewById(R.id.line_news);
        rich_text = (RichText) mAduioView.findViewById(R.id.rich_text);
        line_news.setVisibility(View.VISIBLE);
        line_state.setVisibility(View.GONE);
        tv_price.setVisibility(View.GONE);

    }

    @OnClick({R.id.audio_back, R.id.audio_share, R.id.audio_detail_comment, R.id.audio_detail_prise, R.id.audio_detail_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.audio_back:
                finish();
                break;
            case R.id.audio_share:
                String shareUrl = "http://my.xqban.com/share/#/resource/detail?id=" + shareid;
                mMyShareDialog.initShare(image, shareUrl, shareContent, sharetitle);
                mMyShareDialog.show();
                break;
            case R.id.audio_detail_comment:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(NewsDetailActivity.this, LoginActivity.class));
                } else {
                    Intent intent = new Intent(NewsDetailActivity.this, ResSendActivity.class);
                    intent.putExtra("id", resId);
                    startActivity(intent);
                    isfresh = true;
                }

                break;
            case R.id.audio_detail_prise:

                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(NewsDetailActivity.this, LoginActivity.class));
                } else {
                    addPrise();
                }

                break;
            case R.id.audio_detail_collect:


                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(NewsDetailActivity.this, LoginActivity.class));
                } else {
                    addCollect();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isfresh) {
            swipeAudioDeatailrefresh.autoRefresh();
            isfresh = false;
        }
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
                //                adapter.notifyDataSetChanged();
                adapter.notifyItemRangeChanged(1, mCommentList.size());

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

                        ResDeatailTopBean.AaDataBean dataBean = databean.get(0);
                        String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
                        int likes = dataBean.getCollectionCounts();
                        int commentCounts = dataBean.getCommentCounts();//评论数
                        String author = dataBean.getAuthor() == null ? "" : dataBean.getAuthor();//昵称
                        int isCharge = dataBean.getIsCharge();//0不收费
                        double price = dataBean.getPrice();
                        isPrise = dataBean.getThumb();//0 没点赞  1点赞
                        isCollect = dataBean.getResourceExit();//0 没点赞  1点赞
                        String addUserTime = dataBean.getAddUserTime() == null ? "" : dataBean.getAddUserTime();//时间

                        String summary = dataBean.getSummary() == null ? "" : dataBean.getSummary();//描述
                        String subtitle = dataBean.getAuthor();//标题2

                        List<ResDeatailTopBean.AaDataBean.PictureListBeans> pictureList = dataBean.getPictureList();
                        sharetitle = title;
                        shareContent = dataBean.getSummary();
                        image = dataBean.getTitlePicture() == null ? dataBean.getAuthorPicture() : dataBean.getTitlePicture();


                        setDrawableLeft(isCollect == 1 ? R.drawable.shoucang : R.drawable.resource_collect_normal,
                                audioDetailCollect);
                        setDrawableLeft(isPrise == 1 ? R.drawable.dianzan : R.drawable.resource_good_normal, audioDetailPrise);

                        String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//头像

                        tv_news_name.setText(subtitle);
                        tv_news_summary.setText(summary);
                        tv_news_time.setText(addUserTime);
                        tv__news_title.setText(title);


                        tv_nick_name.setText(author);
                        tv_video_comment_num.setText(commentCounts + "");
                        tv_video_love_num.setText(likes + "");

                        Glide.with(NewsDetailActivity.this).load(authorPicture).placeholder(R.drawable.app_icon).dontTransform
                                ().dontAnimate().into(image_head);
                        if (!authorPicture.isEmpty()) {
                            Glide.with(NewsDetailActivity.this).load(authorPicture).placeholder(R.drawable.app_icon)
                                    .dontTransform().dontAnimate().into(image_top_head);
                        }
                        if (isCharge == 0) {
                            tv_price.setText("免费");
                        } else {
                            tv_price.setText("" + price);
                        }
                        StringBuilder stringBuilder = new StringBuilder();

                        if (pictureList.size() > 0) {
                            for (ResDeatailTopBean.AaDataBean.PictureListBeans pictureListBeans : pictureList) {
                                shareid = pictureListBeans.getResourcesId();//shareid
                                String fileUrl = pictureListBeans.getFileUrl();
                                stringBuilder.append("<img src=\"" +
                                        fileUrl +
                                        "\" />" +
                                        "<p>");
                            }
                        }

                        rich_text.setRichText(stringBuilder.toString());

                        final List<ResDeatailTopBean.AaDataBean.TwoListBean> twoList = dataBean.getTwoList();

                        ResVideoListAdapter resVideoListAdapter = new ResVideoListAdapter(NewsDetailActivity.this, twoList);
                        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(NewsDetailActivity
                                .this);
                        scrollLinearLayoutManager.setScrollEnabled(false);
                        recycler_res_list.setLayoutManager(scrollLinearLayoutManager);

                        recycler_res_list.setAdapter(resVideoListAdapter);
                        resVideoListAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                            @Override
                            public void onItemClickListener(int position, View v) {
                                String id = twoList.get(position).getId() == null ? "" : twoList.get(position).getId();

                                Intent intent = new Intent(NewsDetailActivity.this, NewsDetailActivity.class);
                                intent.putExtra("id", id);
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

    //setDrawble
    public void setDrawableLeft(int resId, TextView textView) {
        Drawable drawable = NewsDetailActivity.this.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }


    //点赞
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


    //分享提示框
    private Dialog dialog;

    TextView tv_share_qq_Zone;
    TextView tv_share_wechat;
    TextView tv_share_wechat_zone;
    TextView tv_share_qq;
    TextView tv_share_weibo;
    TextView tv_share_cancel;

    private void shareDialog() {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);

        View viewshare = LayoutInflater.from(NewsDetailActivity.this).inflate(R.layout.res_share_dialog, null);
        tv_share_qq_Zone = (TextView) viewshare.findViewById(R.id.tv_share_qq_Zone);
        tv_share_wechat = (TextView) viewshare.findViewById(R.id.tv_share_wechat);
        tv_share_wechat_zone = (TextView) viewshare.findViewById(R.id.tv_share_wechat_zone);
        tv_share_qq = (TextView) viewshare.findViewById(R.id.tv_share_qq);
        tv_share_weibo = (TextView) viewshare.findViewById(R.id.tv_share_weibo);
        tv_share_cancel = (TextView) viewshare.findViewById(R.id.tv_share_cancel);


        dialog.setContentView(viewshare);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);


        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialog.show();//显示对话框
    }


}
