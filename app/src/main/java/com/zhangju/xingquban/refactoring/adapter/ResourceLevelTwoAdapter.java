package com.zhangju.xingquban.refactoring.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;

import java.util.ArrayList;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName ResourceLevelTwoAdapter
 * @Author tangyang
 * @DATE 2018/10/9
 **/
public class ResourceLevelTwoAdapter extends BaseQuickAdapter<ResDeatailTopBean.AaDataBean.TwoListBean, BaseViewHolder> {

    public ResourceLevelTwoAdapter() {
        super(R.layout.item_res_detail, new ArrayList<ResDeatailTopBean.AaDataBean.TwoListBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ResDeatailTopBean.AaDataBean.TwoListBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvNewsNum = helper.getView(R.id.tv_news_num);
        TextView tvNewsLoveNum = helper.getView(R.id.tv_news_love_num);
        TextView tvNewsTime = helper.getView(R.id.tv_news_time);
        ImageView imgNewsBack = helper.getView(R.id.img_news_back);


        String title = item.getTitle() == null ? "" : item.getTitle();//标题
        String titlePicture = item.getTitlePicture() == null ? "" : item.getTitlePicture();//背景图
        int likes = item.getCollectionCounts();//关注
        String addUserTime = item.getAddUserTime() == null ? "" : item.getAddUserTime();//时间
        int commentCounts = item.getCommentCounts();//评论数
        int isLike = item.getIsLike();//0未  1关注
        setDrawableLeft(mContext, isLike == 0 ? R.mipmap.home_data_spkc_x : R.mipmap.home_data_spkc_red_xh, tvNewsLoveNum);
        if (!addUserTime.isEmpty()) {
            String substring = addUserTime.substring(0, addUserTime.indexOf(" "));
            tvNewsTime.setText(substring);
        }
        tvTitle.setText(title);
        tvNewsNum.setText(commentCounts + "");
        tvNewsLoveNum.setText(likes + "");
        Glide.with(mContext).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into(imgNewsBack);
    }


    //setDrawble
    public void setDrawableLeft(Context context, int resId, TextView textView) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }
}
