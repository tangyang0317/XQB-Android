package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.DensityUtils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCollection;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;

import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 老师/机构类型的收藏
 */
public class StoreCollectionAdapter extends SingleAdapterForRecycler<ResponseCollection, Response<List<ResponseCollection>>> {
    private boolean isShowDelete;

    public StoreCollectionAdapter(Context context) {
        super(context, R.layout.item_collection_store);
    }

    @Override
    public Request generateRequest() {
        Request request = Request.obtain(MeInterface.POST_COLLECTION_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("collectionTypes", 3);
        request.put("pageIndex", 0);
        request.put("pageSize", 10);
        return request;
    }

    @Override
    public void binding(int position, final ResponseCollection data, CommonViewHolder holder) {
        if (data.teacherTime == null) return;
        final ResponseCollection.CollectionStore resource = data.teacherTime;
        RatingBar ratingBar = holder.getView(R.id.ratingBar);

        holder.setText(R.id.title, resource.name);
        holder.setText(R.id.type, resource.catagoryName);
        holder.setText(R.id.price, "￥" + data.LessonsPrice);

        if (data.customer != null) {
            Glide.with(mContext).load(data.customer.picture).error(R.mipmap.me_touxiang).into((ImageView) holder.getView(R.id.cover));
            holder.setText(R.id.location, data.customer.areasName);
        }
        ratingBar.setRating(resource.star);

        final CheckBox deleteCheck = holder.getView(R.id.deleteFlag);
        View contentView = holder.getView(R.id.contentView);

        deleteCheck.setOnCheckedChangeListener(null);
        deleteCheck.setChecked(data.isDeleteChecked);
        deleteCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.isDeleteChecked = isChecked;
            }
        });
        if (isShowDelete) {
            contentView.setTranslationX(deleteCheck.getWidth() + DensityUtils.dp2px(mContext, 32));
        } else {
            contentView.setTranslationX(0);
        }
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                requestStoreData(v, resource.id);
            }
        });
    }

    /**
     * 调起店铺接口返回后跳转
     *
     * @param id 店铺id
     */
    private void requestStoreData(final View view, String id) {
        Request request = new Request("/teacher/ls.json");
        request.put("id", id);
        request.put("lat", LocationManager.getInstance().getLocation().latitude);
        request.put("lng", LocationManager.getInstance().getLocation().longitude);
        request.setListener(new SimpleListener<NearDataBean>() {

            @Override
            public void onResponseListener(Request r, NearDataBean result) {
                view.setEnabled(true);
                if (result.isSuccess() && result.getAaData() != null && !result.getAaData().isEmpty()) {
                    HomeRecyclerViewData.launchActivity(mContext, result.getAaData().get(0).getId());
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                view.setEnabled(true);
            }
        });
        request.start();
    }

    @Override
    public List<ResponseCollection> translate(Response<List<ResponseCollection>> result) {
        if (result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("pageIndex", 1);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("pageIndex", 0);
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
        if (isShowDelete && mData != null) {
            for (ResponseCollection collection : mData) {
                collection.isDeleteChecked = false;
            }
        }
        notifyDataSetChanged();
    }
}