package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;

import com.fastlib.adapter.MultiTypeAdapter;
import com.fastlib.net.Request;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCollection;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 * 收藏里资源类型适配器
 */
public class ResCollectionAdapter2 extends AllCollectionAdapter{

    public ResCollectionAdapter2(Context context){
        super(context);
        removeGroup(mStoreGroup);
    }

    @Override
    public void onResponseListener(Request r, Response<List<ResponseCollection>> result, Object result2, Object cookedResult) {
        super.onResponseListener(r, result, result2, cookedResult);
    }
}