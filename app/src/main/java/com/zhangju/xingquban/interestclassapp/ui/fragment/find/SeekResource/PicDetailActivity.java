package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
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
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源图片详情预览
 */
@ContentView(R.layout.activity_pic_detail)
public class PicDetailActivity extends FastActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.imageViewpager)
    ViewPager vp;
    @BindView(R.id.swipereimagerefresh)
    SwipeRefreshRecyclerView swipeAudioDeatailrefresh;
    @BindView(R.id.audio_back)
    ImageView audioBack;
    @BindView(R.id.audio_share)
    ImageView audioShare;
    @BindView(R.id.image_next)
    ImageButton imageNext;
    @BindView(R.id.tv_position)
    TextView tvPosition;

    @BindView(R.id.audio_detail_comment)
    RelativeLayout audioDetailComment;
    @BindView(R.id.audio_detail_prise)
    TextView audioDetailPrise;
    @BindView(R.id.audio_detail_collect)
    TextView audioDetailCollect;

    //topview
    TextView tv_title;
    TextView tv_price;
    TextView tv_play_count;
    TextView tv_video_comment_num;
    TextView tv_video_love_num;
    RoundImageView image_head;
    TextView tv_nick_name;
    RecyclerView recycler_res_list;
    TextView tv_title_Count;
    LinearLayout line_state;

    int isPrise = 0;//是否点赞
    int isCollect = 0;//是否收藏
    private int pageIndex = 0;
    private int total = 0;
    private String resId;//资源id
    boolean isfresh;

    private View mAduioView;
    private String types = "picture";//类型
    private AudioDetailAdapter adapter;

    List<ResDetailBean.AaDataBean> mCommentList = new ArrayList<>();//评论集合

    private List<String> mPicList = new ArrayList<>();//图片集合


    //    http://my.xqban.com/admnxzcmr/resources/share?types=picture&id=41896&cityId=
    private ShareDialog shareDialog;
    private String sharetitle = "资源";
    private String shareid = "";
    private String cityId = "";
    private String shareUrl = "http://my.xqban.com/admnxzcmr/resources/share?types=";
    private String image = "";
    private ArrayList<String> mlist = new ArrayList<>();
    private String shareContent = "内容";
    private MyShareDialog mMyShareDialog;

    @Override
    protected void alreadyPrepared() {
        getLayoutHeadView();
        getIntentData();
        initShare();
        cityId = LocationManager.getInstance().getLocation().cityId;
    }

    private void initShare() {
        mMyShareDialog = new MyShareDialog(this);
    }

    private void getIntentData() {
        resId = getIntent().getStringExtra("id");
        setAudioDetailAdapter();
        getDetailTopData();

    }

    private void setAudioDetailAdapter() {
        adapter = new AudioDetailAdapter(PicDetailActivity.this, mCommentList, mAduioView);
        swipeAudioDeatailrefresh.setLayoutManager(new LinearLayoutManager(PicDetailActivity.this));
        swipeAudioDeatailrefresh.setAdapter(adapter);

        swipeAudioDeatailrefresh.setOnListLoadListener(this);
        swipeAudioDeatailrefresh.setOnRefreshListener(this);
        swipeAudioDeatailrefresh.autoRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void getLayoutHeadView() {
        mAduioView = LayoutInflater.from(PicDetailActivity.this).inflate(R.layout.res_detail_top, null);
        tv_title = (TextView) mAduioView.findViewById(R.id.tv_title);
        tv_price = (TextView) mAduioView.findViewById(R.id.tv_price);
        tv_play_count = (TextView) mAduioView.findViewById(R.id.tv_play_count);
        tv_video_comment_num = (TextView) mAduioView.findViewById(R.id.tv_video_comment_num);
        tv_video_love_num = (TextView) mAduioView.findViewById(R.id.tv_video_love_num);
        tv_nick_name = (TextView) mAduioView.findViewById(R.id.tv_nick_name);
        image_head = (RoundImageView) mAduioView.findViewById(R.id.image_head);
        recycler_res_list = (RecyclerView) mAduioView.findViewById(R.id.recycler_res_list);
        tv_title_Count = (TextView) mAduioView.findViewById(R.id.tv_title_Count);
        line_state = (LinearLayout) mAduioView.findViewById(R.id.line_state);
        line_state.setVisibility(View.GONE);
        tv_price.setVisibility(View.GONE);

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
                adapter.notifyDataSetChanged();
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
        if (swipeAudioDeatailrefresh != null) {
            swipeAudioDeatailrefresh.setLoading(false);
            swipeAudioDeatailrefresh.setRefreshing(false);
            swipeAudioDeatailrefresh.setEnabledLoad(true);
        }
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
                        int clickRate = dataBean.getClickRate();//播放次数
                        int likes = dataBean.getLikes();//喜欢
                        int commentCounts = dataBean.getCommentCounts();//评论数
                        String author = dataBean.getAuthor() == null ? "" : dataBean.getAuthor();//昵称
                        int isCharge = dataBean.getIsCharge();//0不收费
                        double price = dataBean.getPrice();
                        isPrise = dataBean.getThumb();//0 没点赞  1点赞
                        isCollect = dataBean.getResourceExit();//0 没点赞  1点赞
                        sharetitle = title;
                        shareContent = dataBean.getSummary();
                        image = dataBean.getTitlePicture() == null ? dataBean.getAuthorPicture() : dataBean.getTitlePicture();

                        setDrawableLeft(isCollect == 1 ? R.drawable.shoucang : R.drawable.resource_collect_normal, audioDetailCollect);
                        setDrawableLeft(isPrise == 1 ? R.drawable.dianzan : R.drawable.resource_good_normal, audioDetailPrise);

                        String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//头像

                        tv_title.setText(title);
                        tv_play_count.setText(clickRate + "次播放");
                        tv_nick_name.setText(author);
                        tv_video_comment_num.setText(commentCounts + "");
                        tv_video_love_num.setText(likes + "");
                        Glide.with(PicDetailActivity.this).load(authorPicture).placeholder(R.drawable.app_icon).dontTransform().dontAnimate().into(image_head);
                        if (isCharge == 0) {
                            tv_price.setText("免费");
                        } else {
                            tv_price.setText("" + price);
                        }

                        mPicList.clear();
                        List<ResDeatailTopBean.AaDataBean.PictureListBeans> pictureList = dataBean.getPictureList();
                        for (ResDeatailTopBean.AaDataBean.PictureListBeans listBeans : pictureList) {
                            mPicList.add(listBeans.getFileUrl());
                            shareid = listBeans.getResourcesId();///
                        }
                        setViewPager();//viewpager设置

                        final List<ResDeatailTopBean.AaDataBean.TwoListBean> twoList = dataBean.getTwoList();

                        ResVideoListAdapter resVideoListAdapter = new ResVideoListAdapter(PicDetailActivity.this, twoList);
                        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(PicDetailActivity.this);
                        scrollLinearLayoutManager.setScrollEnabled(false);
                        recycler_res_list.setLayoutManager(scrollLinearLayoutManager);

                        recycler_res_list.setAdapter(resVideoListAdapter);
                        resVideoListAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                            @Override
                            public void onItemClickListener(int position, View v) {
                                String id = twoList.get(position).getId() == null ? "" : twoList.get(position).getId();

                                Intent intent = new Intent(PicDetailActivity.this, PicDetailActivity.class);

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


    //viewpager 设置
    private void setViewPager() {
        vp.setAdapter(new MyViewpagerAdapter(PicDetailActivity.this, mPicList, vp));
        tvPosition.setText(1 + "/" + mPicList.size());

//        setAutoviewpager();


    }


    @OnClick({R.id.audio_back, R.id.audio_share, R.id.image_next, R.id.audio_detail_comment, R.id.audio_detail_prise, R.id.audio_detail_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.audio_back:
                finish();
                break;
            case R.id.audio_share:
                String shareUrl = "http://my.xqban.com/share/#/resource/photo?id=" + shareid;
                mMyShareDialog.initShare(image, shareUrl, shareContent, sharetitle);
                mMyShareDialog.show();
                break;
            case R.id.image_next:
                if (mPicList.size() > 0) {
                    currentPosition = (currentPosition + 1) % mPicList.size();
                    handler.obtainMessage().sendToTarget();
                }
                break;
            case R.id.audio_detail_comment:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(PicDetailActivity.this, LoginActivity.class));
                } else {
                    Intent intent = new Intent(PicDetailActivity.this, ResSendActivity.class);
                    intent.putExtra("id", resId);
                    startActivity(intent);
                    isfresh = true;
                }
                break;
            case R.id.audio_detail_prise:

                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(PicDetailActivity.this, LoginActivity.class));
                } else {
                    addPrise();
                }
                break;
            case R.id.audio_detail_collect:

                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(PicDetailActivity.this, LoginActivity.class));
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
                    setDrawableLeft(isCollect == 1 ? R.drawable.shoucang : R.drawable.resource_collect_normal, audioDetailCollect);
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

        View viewshare = LayoutInflater.from(PicDetailActivity.this).inflate(R.layout.res_share_dialog, null);
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

    //setDrawble
    public void setDrawableLeft(int resId, TextView textView) {
        Drawable drawable = PicDetailActivity.this.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }


    int currentPosition = 0;
//    private ScheduledExecutorService scheduledExecutorService;

    class MyViewpagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {


        ArrayList<View> views;
        ViewPager mViewPager;
        Context mContext;

        public MyViewpagerAdapter(Context mContext, List<String> datas, ViewPager viewPager) {
            this.mContext = mContext;
            this.mViewPager = viewPager;
            views = new ArrayList<>();
////        如果数据大于一条
//            if (datas.size() > 1) {
////            添加最后一页到第一页
//                datas.add(0, datas.get(datas.size() - 1));
////            添加第一页(经过上行的添加已经是第二页了)到最后一页
//                datas.add(datas.get(1));
//            }
            for (String data : datas) {
                views.add(getItemView(data));
            }

            viewPager.setAdapter(this);
            viewPager.addOnPageChangeListener(this);
            viewPager.setCurrentItem(0, false);
        }

        @Override
        public int getCount() {
            return mPicList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(views.get(position));

            //处理点击操作事件
            View view = views.get(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PicDetailActivity.this, PreviewImageActivity.class);
                    intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL, false);
                    intent.putExtra(PreviewImageActivity.ARG_INT_INDEX, position);
                    intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES, (ArrayList) mPicList);
                    startActivity(intent);
                }
            });

            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        private ViewGroup.LayoutParams layoutParams;

        protected View getItemView(String data) {
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(PicDetailActivity.this).load(data).into(imageView);

            return imageView;

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            tvPosition.setText(position + 1 + "/" + mPicList.size());


        }

        @Override
        public void onPageScrollStateChanged(int state) {

//        若viewpager滑动未停止，直接返回
//            if (state != ViewPager.SCROLL_STATE_IDLE) return;
//        若当前为第一张，设置页面为倒数第二张
//            if (currentPosition == 0) {
//                mViewPager.setCurrentItem(views.size() - 2, false);
//            } else if (currentPosition == views.size() - 1) {
////        若当前为倒数第一张，设置页面为第二张
//                mViewPager.setCurrentItem(0, false);
//            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    ///启动自动轮播
//    private void setAutoviewpager() {
//        if (mPicList.size() > 0) {
//            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//            scheduledExecutorService.scheduleAtFixedRate(new ViewPagerTask(), 1, 5, TimeUnit.SECONDS);
//        }
//    }


    @Override
    protected void onDestroy() {
//        if (scheduledExecutorService != null) {
//            scheduledExecutorService.shutdown();
//        }
        super.onDestroy();
    }

    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            currentPosition = (currentPosition + 1) % mPicList.size();
            handler.obtainMessage().sendToTarget();

        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //设定viewPager当前页面
            vp.setCurrentItem(currentPosition);
        }
    };
}
