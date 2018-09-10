package com.zhangju.xingquban.interestclassapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.AdBannerBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ApplyToMemberActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.VipActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessage;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.FindResource;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyQuestionMain;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.GlideImageLoader;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;
import com.zhangju.xingquban.interestclassapp.ui.main.MainActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by  liush on 2018/3/15.
 * @describe ${}
 */

public class BannerHelper {

    private List<String> images = new ArrayList<>();
    private final Context mContext;
    private Banner mBanner;
    private List<AdBannerBean.AaDataBean.ListBean> mBannerList;
    private String mSubject = null;
    private boolean isFrist = true;

    public BannerHelper(Context context) {
        this.mContext = context;
    }

    public BannerHelper init(Banner banner) {
        //设置图片加载器
        this.mBanner = banner;
        return this;
    }

    /**
     * "advertPutInProvince":"1",//投放省份    必填
     * "advertPutInCity":"1",//投放城市  必填
     * "advertPosition":"1",//投放位置
     * "advertChannel":"1",//投放频道
     * "advertSubject":"1",//投放科目
     * "advertClassification":"1",//广告分类 1Banner广告  2信息流广告 3贴片广告 根据要展现的分类来选  必填
     * "advertType":"1",//广告类型 1 图片 2视频 根据要展现的类型来选 必填
     * "isPut":"1",//上下架 1上架  -1下架  APP 查询只传1必填
     * "status":"1",//状态 1有效  -1无效 APP查询只传1必填
     * "pageIndex":"1",//当前页数 必填
     * "pageSize":"1",//每页条数 必填
     *
     * @param adSection
     */
    public void loadBannerDate(String adSection, boolean isAll) {
        LocationManager instance = LocationManager.getInstance();
        Location location = instance.getLocation();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("pageIndex", "1");
        params.addQueryStringParameter("pageSize", "0");
        if (!isAll) {
            params.addQueryStringParameter("advertPutInProvince", location.cityPid);
            params.addQueryStringParameter("advertPutInCity", location.cityId);
            if (!TextUtils.isEmpty(mSubject)) {
                params.addQueryStringParameter("advertSubject", mSubject);
            }
        }
        params.addQueryStringParameter("advertSection", adSection);
        params.addQueryStringParameter("isPut", "1");
        params.addQueryStringParameter("status", "1");
        params.addQueryStringParameter("advertType", "1");
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
                                mBannerList = aaData.getList();
                                if (mBannerList.size() > 0) {
                                    images.clear();
                                    for (AdBannerBean.AaDataBean.ListBean listBean : mBannerList) {
                                        String advertUrl = listBean.getAdvertUrl();
                                        images.add(advertUrl);
                                    }
                                    initBanner();
                                } else {
                                    if (isFrist) {
                                        isFrist = false;
                                        loadBannerDate("3", true);
                                    }
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

    public void loadBannerDate(String adSection) {
        loadBannerDate(adSection, false);
    }

    /*public void loadVideoData() {
        LocationManager instance = LocationManager.getInstance();
        Location location = instance.getLocation();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("pageIndex", "1");
        params.addQueryStringParameter("pageSize", "10");
        params.addQueryStringParameter("advertPutInProvince", location.cityPid);
        params.addQueryStringParameter("advertPutInCity", location.cityId);
        params.addQueryStringParameter("advertSection", "6");
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
                                mBannerList = aaData.getList();
                                if (mBannerList.size() > 0) {
                                    for (AdBannerBean.AaDataBean.ListBean listBean : mBannerList) {
                                        String advertUrl = listBean.getAdvertUrl();
                                        images.add(advertUrl);
                                    }
                                    initBanner();
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
    }*/

    private void initBanner() {
        if (mBanner == null) {
            return;
        }
        mBanner.update(images);
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //显示圆形指示器
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(5000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setSelected(true);
        //banner设置方法全部调用完毕时最后调用
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                AdBannerBean.AaDataBean.ListBean listBean = mBannerList.get(position);
                String advertGoToType = listBean.getAdvertGoToType();
                initGoto(advertGoToType, listBean);
            }
        });

        mBanner.start();
    }

    /**
     * "1": "特殊广告位lauch",
     * "2": "机构",
     * "3": "老师",
     * "4": "活动",
     * "5": "活动列表",
     * "6": "直播",
     * "7": "直播列表",
     * "8": "资源",
     * "9": "资源列表",
     * "10": "课程",
     * "11": "课程列表",
     * "12": "问答",
     * "13": "问答列表",
     * "14": "信息平台",
     * "15": "信息平台列表",
     * "16": "网课",
     * "17": "网课列表",
     * "18": "申请成为老师/机构",
     * "19": "会员界面",
     * "20": "跳转下载app",
     * "21": "跳转到打开某app到特定页面",
     * "22": "外部落地页面链接"
     *
     * @param advertGoToType
     * @param listBean
     */
    private void initGoto(String advertGoToType, AdBannerBean.AaDataBean.ListBean listBean) {
        if (advertGoToType == null) {
            return;
        }
        String advertGoToUrl = listBean.getAdvertGoToUrl();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (advertGoToType) {
            case "1":
                if (advertGoToUrl != null) {
                    intent.setClass(mContext, WebViewActivity.class);
                    intent.putExtra(WebViewActivity.ARG_URL, advertGoToUrl);
                    mContext.startActivity(intent);
                }
                break;
            case "2":
            case "3":
                HomeRecyclerViewData.launchActivity(mContext, listBean.getAdvertGoToSourceId());
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
            case "7":
                intent.setClass(mContext, MainActivity.class);
                intent.putExtra(MainActivity.ARG_INT_JUMP_PAGE, 2);
                mContext.startActivity(intent);
                break;
            case "8":
            case "9":
                intent.setClass(mContext, FindResource.class);
                mContext.startActivity(intent);
                break;
            case "10":
            case "11":
                intent.setClass(mContext, CurriculumActivity.class);
                mContext.startActivity(intent);
                break;
            case "12":
            case "13":
                intent.setClass(mContext, MyQuestionMain.class);
                mContext.startActivity(intent);
                break;
            case "14":
            case "15":
                intent.setClass(mContext, FindMessage.class);
                mContext.startActivity(intent);
                break;
            case "16":
                break;
            case "17":
                break;
            case "18": // 申请老师机构
                intent.setClass(mContext, ApplyToMemberActivity.class);
                mContext.startActivity(intent);
                break;
            case "19":
                intent.setClass(mContext, VipActivity.class);
                mContext.startActivity(intent);
                break;
            case "20":
                break;
            case "21":
                break;
            case "22":
              /*  intent.setClass(mContext, WebViewActivity.class);
                if (advertGoToUrl != null) {
                    intent.putExtra(WebViewActivity.ARG_URL, advertGoToUrl);
                }*/
                break;
            default:
                if (advertGoToUrl != null) {
                    intent.setClass(mContext, WebViewActivity.class);
                    intent.putExtra(WebViewActivity.ARG_URL, advertGoToUrl);
                    mContext.startActivity(intent);
                }
                break;
        }
    }

    public void stopBanner() {
        mBanner.stopAutoPlay();
    }

    public BannerHelper setSubject(String subject) {
        mSubject = subject;
        return this;
    }
}
