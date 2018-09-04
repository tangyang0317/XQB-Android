package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumOrderActivity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */
public class ExperienceAdapter extends SingleAdapterForRecycler<CurriculumBean.AaDataBean, Response<List<CurriculumBean.AaDataBean>>> {

    public ExperienceAdapter(Context context) {
        super(context, R.layout.item_curriculum_data_sjkc);
    }

    @Override
    public Request generateRequest() {
        Location location = LocationManager.getInstance().getLocation();
        return new Request("/lesson/ls.json")
                .put("lng", location.longitude)
                .put("lat", location.latitude)
                .put("cityId", location.cityId)
                .put("sortJson", "[{\"field\":\"vipPrice\",\"isAsc\":\"true\"}]")
                .put("pageIndex", 0)
                .put("pageSize", 10);
    }

    @Override
    public void binding(final int position, final CurriculumBean.AaDataBean data, CommonViewHolder holder) {
        Glide.with(mContext).load(data.getPicture()).error(R.drawable.rec_seize_bitmao).into((ImageView) holder.getView(R.id.home_data_sjkc_image));
        holder.setText(R.id.home_data_sjkc_title, data.getName());

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        String p = decimalFormat.format(data.getVipPrice());//format 返回的是字符串
        holder.setText(R.id.home_data_sjkc_money, p);

        //米转公里
        int range = data.getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        if (data.getAreasName() == null)
            holder.setText(R.id.tv_juli, dis + "km");
        else
            holder.setText(R.id.tv_juli, data.getAreasName() + dis + "km");
        holder.setOnClickListener(R.id.textView12, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurriculumOrderActivity.lanuchActivity(mContext, data.getId());
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public List<CurriculumBean.AaDataBean> translate(Response<List<CurriculumBean.AaDataBean>> result) {
        return result.data;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("pageIndex", 10);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("pageIndex", 0);
    }
}