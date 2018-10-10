package com.zhangju.xingquban.refactoring.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDetailBean;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.GlideImageLoader;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;
import com.zhangju.xingquban.refactoring.adapter.CommentAdapter;
import com.zhangju.xingquban.refactoring.adapter.ResourceLevelTwoAdapter;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhangju.xingquban.refactoring.view.KeyMapDailog;
import com.zhangju.xingquban.refactoring.view.XQBLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @packageName com.zhangju.xingquban.refactoring.activity
 * @FileName ResourcePictureDetailsActivity
 * @Author tangyang
 * @DATE 2018/9/28
 **/
@ContentView(R.layout.act_resource_details)
public class ResourcePictureDetailsActivity extends FastActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.resourceSwipeRefreshLayout)
    SwipeRefreshLayout resourceSwipeRefreshLayout;
    @Bind(R.id.resourceRecycleView)
    RecyclerView resourceRecycleView;
    @Bind(R.id.commentLayout)
    LinearLayout commentLayout;
    @Bind(R.id.praisedLayout)
    private LinearLayout praisedLayout;
    @Bind(R.id.collectionLayout)
    private LinearLayout collectionLayout;
    private CommentAdapter commentAdapter;
    private View headView;
    @Bind(R.id.praisedImg)
    private ImageView praisedImg;
    @Bind(R.id.collectionImg)
    private ImageView collectionImg;
    private TextView resourceDetailsTitileTxt, resourceContentTxt, commentCountTxt;
    private LinearLayout levelTwoResourceLayout;
    private RecyclerView levelTowResourceRecycleView;
    private Banner resourceBanner;
    private int pageIndex = 0, pageSize = 10;
    private String types = "picture";//类型
    private int isPrise = 0, isCollect = 0;

    private String sharetitle = "资源";
    private String shareid = "";
    private String image = "";
    private String shareContent = "内容";
    private MyShareDialog mMyShareDialog;


    public static void launcherThis(Context context, String resourceId) {
        Intent intent = new Intent(context, ResourcePictureDetailsActivity.class);
        intent.putExtra("id", resourceId);
        context.startActivity(intent);
    }


    private String getResourceId() {
        if (getIntent() != null) {
            return getIntent().getStringExtra("id");
        }
        return "";
    }


    @Override
    protected void alreadyPrepared() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initShare();
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourcePictureDetailsActivity.this.finish();
            }
        });

        titleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareUrl = "http://my.xqban.com/share/#/resource/photo?id=" + shareid;
                mMyShareDialog.initShare(image, shareUrl, shareContent, sharetitle);
                mMyShareDialog.show();
            }
        });

        headView = LayoutInflater.from(this).inflate(R.layout.view_resource_details_header, null);
        resourceDetailsTitileTxt = headView.findViewById(R.id.resourceDetailsTitileTxt);
        resourceContentTxt = headView.findViewById(R.id.resourceContentTxt);
        commentCountTxt = headView.findViewById(R.id.commentCountTxt);
        resourceBanner = headView.findViewById(R.id.home_banner);
        levelTwoResourceLayout = headView.findViewById(R.id.levelTwoResourceLayout);
        levelTowResourceRecycleView = headView.findViewById(R.id.levelTowResourceRecycleView);


        commentAdapter = new CommentAdapter();
        resourceRecycleView.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter.addHeaderView(headView);
        resourceRecycleView.setAdapter(commentAdapter);
        commentAdapter.setOnLoadMoreListener(this, resourceRecycleView);
        commentAdapter.setLoadMoreView(new XQBLoadMoreView());
        resourceSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        resourceSwipeRefreshLayout.setOnRefreshListener(this);

        getResourceDetailsTopData();
        getResourceDetailsComment();

        commentLayout.setOnClickListener(this);
        praisedLayout.setOnClickListener(this);
        collectionLayout.setOnClickListener(this);

    }

    private void initShare() {
        mMyShareDialog = new MyShareDialog(this);
    }


    KeyMapDailog dialog = new KeyMapDailog("请输入您的评论", new KeyMapDailog.SendBackListener() {
        @Override
        public void sendBack(String inputText) {
            //TODO  点击发表后业务逻辑
            resourceComment(inputText);
        }
    });


    Observer<BaseResponseBean<List<ResDeatailTopBean.AaDataBean>>> topDataObserva = new Observer<BaseResponseBean<List<ResDeatailTopBean.AaDataBean>>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseResponseBean<List<ResDeatailTopBean.AaDataBean>> listBaseResponseBean) {
            if (listBaseResponseBean.isSuccess()) {
                if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                    ResDeatailTopBean.AaDataBean dataBean = listBaseResponseBean.getAaData().get(0);
                    String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
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
                    if (isPrise == 1) {
                        praisedImg.setImageResource(R.drawable.dianzan);
                    } else {
                        praisedImg.setImageResource(R.drawable.resource_good_normal);
                    }
                    if (isCollect == 1) {
                        collectionImg.setImageResource(R.drawable.shoucang);
                    } else {
                        collectionImg.setImageResource(R.drawable.resource_collect_normal);
                    }
                    resourceDetailsTitileTxt.setText(title);
                    resourceContentTxt.setText(dataBean.getSummary());
                    if (dataBean.getPictureList() != null) {
                        List<String> images = new ArrayList<>();
                        for (int i = 0; i < dataBean.getPictureList().size(); i++) {
                            images.add(dataBean.getPictureList().get(i).getFileUrl());
                            shareid = dataBean.getPictureList().get(i).getResourcesId();
                        }
                        resourceBanner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        resourceBanner.setImages(images);
                        //显示圆形指示器
                        resourceBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        resourceBanner.setDelayTime(5000);
                        resourceBanner.isAutoPlay(true);
                        resourceBanner.setIndicatorGravity(BannerConfig.CENTER);
                        resourceBanner.setSelected(true);
                        resourceBanner.start();
                    }
                    commentCountTxt.setText("网友评论(" + commentCounts + ")");

                    if (dataBean.getTwoList() != null && dataBean.getTwoList().size() > 0) {
                        levelTwoResourceLayout.setVisibility(View.VISIBLE);
                        ResourceLevelTwoAdapter resourceLevelTwoAdapter = new ResourceLevelTwoAdapter();
                        levelTowResourceRecycleView.setLayoutManager(new LinearLayoutManager(ResourcePictureDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
                        levelTowResourceRecycleView.setAdapter(resourceLevelTwoAdapter);
                        resourceLevelTwoAdapter.setNewData(dataBean.getTwoList());
                        resourceLevelTwoAdapter.notifyDataSetChanged();
                    } else {
                        levelTwoResourceLayout.setVisibility(View.GONE);
                    }
                }
            }

        }
    };

    Observer<BaseResponseBean<List<ResDetailBean.AaDataBean>>> commentDataObserver = new Observer<BaseResponseBean<List<ResDetailBean.AaDataBean>>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseResponseBean<List<ResDetailBean.AaDataBean>> listBaseResponseBean) {
            if (listBaseResponseBean != null) {
                if (pageIndex == 0) {
                    resourceSwipeRefreshLayout.setRefreshing(false);
                    if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                        if (listBaseResponseBean.getAaData().size() < 10) {
                            commentAdapter.loadMoreComplete();
                            commentAdapter.loadMoreEnd();
                            commentAdapter.setNewData(listBaseResponseBean.getAaData());
                        } else {
                            commentAdapter.setEnableLoadMore(true);
                            commentAdapter.setNewData(listBaseResponseBean.getAaData());
                        }
                    } else {
                        commentAdapter.loadMoreEnd();
                    }
                } else {
                    if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                        commentAdapter.loadMoreComplete();
                        commentAdapter.addData(listBaseResponseBean.getAaData());
                    } else {
                        commentAdapter.loadMoreComplete();
                        commentAdapter.loadMoreEnd();
                        commentAdapter.addData(listBaseResponseBean.getAaData());
                    }
                }
            }
        }
    };


    Observer<BaseResponseBean<Object>> praisedObserver = new Observer<BaseResponseBean<Object>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseResponseBean<Object> objectBaseResponseBean) {
            if (objectBaseResponseBean != null && objectBaseResponseBean.isSuccess()) {
                if (isPrise == 0) {
                    isPrise = 1;
                } else {
                    isPrise = 0;
                }
                if (isPrise == 1) {
                    praisedImg.setImageResource(R.drawable.dianzan);
                } else {
                    praisedImg.setImageResource(R.drawable.resource_good_normal);
                }
            }
        }
    };

    Observer<BaseResponseBean<Object>> collectionObserver = new Observer<BaseResponseBean<Object>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseResponseBean<Object> objectBaseResponseBean) {
            if (objectBaseResponseBean != null && objectBaseResponseBean.isSuccess()) {
                if (isCollect == 0) {
                    isCollect = 1;
                } else {
                    isCollect = 0;
                }

                if (isCollect == 1) {
                    collectionImg.setImageResource(R.drawable.shoucang);
                } else {
                    collectionImg.setImageResource(R.drawable.resource_collect_normal);
                }
            }
        }
    };

    Observer<BaseResponseBean<Object>> commentObserver = new Observer<BaseResponseBean<Object>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseResponseBean<Object> objectBaseResponseBean) {
            if (objectBaseResponseBean != null && objectBaseResponseBean.isSuccess()) {
                dialog.hideProgressdialog();
                ToastUtil.showToast("评论成功");
                dialog.dismiss();
                pageIndex = 0;
                getResourceDetailsComment();
            }
        }
    };

    /***
     * 获取资源详情数据
     */
    private void getResourceDetailsTopData() {
        NetWork.getReources().getResourceDetailsTopData(getResourceId(), String.valueOf(pageIndex), String.valueOf(pageSize))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topDataObserva);
    }

    /****
     * 获取资源详情评论列表
     */
    private void getResourceDetailsComment() {
        NetWork.getReources().getResourceDetailsCommentData(getResourceId(), String.valueOf(pageIndex), String.valueOf(pageSize))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentDataObserver);
    }


    /***
     * 点赞
     */
    private void resourcePraised() {
        NetWork.getReources().resourcePraised(getResourceId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(praisedObserver);
    }

    /***
     * 收藏
     */
    private void resourceCollection() {
        NetWork.getReources().userCollection(UserManager.getInstance().getUser().id, getResourceId(), types, "2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(collectionObserver);
    }


    /***
     * 资源评论
     * @param content
     */
    private void resourceComment(String content) {
        NetWork.getReources().resourceComment(getResourceId(), content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentObserver);
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        getResourceDetailsComment();
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        getResourceDetailsComment();
    }

    @Override
    public void onClick(View view) {
        if (view == commentLayout) {
            dialog.show(getSupportFragmentManager(), "dialog");
        } else if (view == praisedLayout) {
            resourcePraised();
        } else if (view == collectionLayout) {
            resourceCollection();
        }
    }
}
