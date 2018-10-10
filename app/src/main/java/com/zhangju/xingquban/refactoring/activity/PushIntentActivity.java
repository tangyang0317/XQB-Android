package com.zhangju.xingquban.refactoring.activity;

import android.content.Intent;
import android.text.TextUtils;

import com.fastlib.app.FastActivity;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.refactoring.bean.PushJsonBean;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @packageName com.zhangju.xingquban.refactoring.activity
 * @FileName PushIntentActivity
 * @Author tangyang
 * @DATE 2018/10/10
 **/
public class PushIntentActivity extends FastActivity {

    @Override
    protected void alreadyPrepared() {
        PushJsonBean.ExtrasBean pushJsonBean = (PushJsonBean.ExtrasBean) getIntent().getSerializableExtra("data");
        if (pushJsonBean != null) {
            openNotification(pushJsonBean);
        }
    }


    private void openNotification(PushJsonBean.ExtrasBean pushJsonBean) {
        if (pushJsonBean != null) {
            if ("3".equals(pushJsonBean.getPushInfotype())) {
                //跳转到资源
                String resId = pushJsonBean.getTypeId();
                if (!TextUtils.isEmpty(resId)) {
                    getResourceDetailsTopData(resId);
                }
            }
        }
    }


    /***
     * 获取资源详情数据
     */
    private void getResourceDetailsTopData(String resId) {
        NetWork.getReources().getResourceDetailsTopData(resId, String.valueOf(0), String.valueOf(10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topDataObserva);
    }


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
                    String types = dataBean.getTypes();
                    String id = dataBean.getId();
                    if (types.equals("video")) {
                        Intent intent = new Intent(PushIntentActivity.this, AudioDetailActivity.class);
                        intent.putExtra("types", "video");
                        intent.putExtra("resId", id);
                        startActivity(intent);
                        PushIntentActivity.this.finish();
                    } else if (types.equals("picture")) {
                        ResourcePictureDetailsActivity.launcherThis(PushIntentActivity.this, id);
                        PushIntentActivity.this.finish();
                    } else if (types.equals("article")) {
                        Intent intent = new Intent(PushIntentActivity.this, NewsDetailActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        PushIntentActivity.this.finish();
                    } else if (types.equals("audio")) {
                        Intent intent = new Intent(PushIntentActivity.this, AudioDetailActivity.class);
                        intent.putExtra("types", "audio");
                        intent.putExtra("resId", id);
                        startActivity(intent);
                        PushIntentActivity.this.finish();
                    }
                }
            }
        }
    };

}
