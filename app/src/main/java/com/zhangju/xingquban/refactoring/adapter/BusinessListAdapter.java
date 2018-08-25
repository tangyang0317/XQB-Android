package com.zhangju.xingquban.refactoring.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.util.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页底部数据列表
 *
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName BusinessListAdapter
 * @Author tangyang
 * @DATE 2018/8/2
 **/
public class BusinessListAdapter extends BaseQuickAdapter<NearDataBean.AaDataBean, BaseViewHolder> {

    public BusinessListAdapter() {
        super(R.layout.item_home_bottom, new ArrayList<NearDataBean.AaDataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, NearDataBean.AaDataBean dataBean) {
        ImageView imagePic = helper.getView(R.id.image_pic);
        TextView tvTitle = helper.getView(R.id.tv_title);
        RatingBar ratingBarStar = helper.getView(R.id.ratingBar_star);
        TextView tvCourseName = helper.getView(R.id.tv_course_name);
        TextView tvContent = helper.getView(R.id.tv_content);
        TextView tv_address_area = helper.getView(R.id.tv_address_area);
        TextView tv_price = helper.getView(R.id.tv_price);
        TextView tv_certify = helper.getView(R.id.tv_price);
        TextView tv_video_show = helper.getView(R.id.tv_video_show);


        String picture = dataBean.getPicture() == null ? "" : dataBean.getPicture();
        String name = dataBean.getName() == null ? "" : dataBean.getName();//标题
        int range = dataBean.getRange();
        String intDivision = FormatUtils.getIntDivision(range, 1000);//距离
        int star = (int) dataBean.getAvgComment();//评分
        String areasName = dataBean.getAreasName() == null ? "" : dataBean.getAreasName();//区域
        String catagoryName = dataBean.getCatagoryName() == null ? "" : dataBean.getCatagoryName();//类型名称

        if (dataBean.getMinVipPrice() == 0.0) {
            tv_price.setVisibility(View.GONE);
        } else {
            tv_price.setVisibility(View.VISIBLE);
            tv_price.setText("￥" + dataBean.getMinVipPrice() + "");
        }
        List<NearDataBean.AaDataBean.LessonsBean> lessons = dataBean.getLessons();
        if (lessons != null && lessons.size() > 0) {
            String lessonName = lessons.get(0).getName() == null ? "" : lessons.get(0).getName();//课程名


            tvContent.setVisibility(lessonName.isEmpty() ? View.GONE : View.VISIBLE);

            tvContent.setText(lessonName);

        } else {
            tvContent.setVisibility(View.GONE);
        }
        List<NearDataBean.AaDataBean.VideoLessonBean> videoLesson = dataBean.getVideoLesson();
        if (videoLesson != null && videoLesson.size() > 0) {
            tv_video_show.setVisibility(View.VISIBLE);
        } else {
            tv_video_show.setVisibility(View.GONE);
        }
        int qcAuth = dataBean.getQcAuth();
        if (qcAuth == 2) {
            tv_certify.setVisibility(View.VISIBLE);
        } else {
            tv_certify.setVisibility(View.GONE);
        }

        ratingBarStar.setRating(star);
        Glide.with(mContext).load(picture).into(imagePic);
        tvTitle.setText(name);


        tv_address_area.setText(areasName + intDivision + "km");
        tvCourseName.setText(catagoryName);


    }
}
