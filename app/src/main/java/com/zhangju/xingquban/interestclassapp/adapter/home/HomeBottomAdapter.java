package com.zhangju.xingquban.interestclassapp.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.util.FormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/7.
 */
//首页底部数据Adapter

public class HomeBottomAdapter extends BaseRecycleViewAdapter {


    private List<NearDataBean.AaDataBean> mHomeList;

    public HomeBottomAdapter(Context c, List<NearDataBean.AaDataBean> mHomeList) {
        super(c);
        this.mHomeList = mHomeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(resIdToView(parent, R.layout.item_home_bottom));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
        homeViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mHomeList.size();

    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        int pos;

        @BindView(R.id.image_pic)
        ImageView imagePic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ratingBar_star)
        RatingBar ratingBarStar;
        @BindView(R.id.tv_course_name)
        TextView tvCourseName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_address_area)
        TextView tv_address_area;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_certify)
        TextView tv_certify;
        @BindView(R.id.tv_video_show)
        TextView tv_video_show;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);//item点击
            NearDataBean.AaDataBean dataBean = mHomeList.get(pos);

            String picture = dataBean.getPicture() == null ? "" : dataBean.getPicture();
            String name = dataBean.getName() == null ? "" : dataBean.getName();//标题
            int range = dataBean.getRange();
            String intDivision = FormatUtils.getIntDivision(range, 1000);//距离
            int star = (int) dataBean.getAvgComment();//评分
            String areasName = dataBean.getAreasName() == null ? "" : dataBean.getAreasName();//区域
            String catagoryName = dataBean.getCatagoryName() == null ? "" : dataBean.getCatagoryName();//类型名称

            if (dataBean.getMinVipPrice()==0.0){
                tv_price.setVisibility(View.GONE);
            }
            else {
                tv_price.setVisibility(View.VISIBLE);
                tv_price.setText("￥"+dataBean.getMinVipPrice() + "");
            }

           /* Object minVipPrice = dataBean.getMinVipPrice()==0.0 ? "" : dataBean.getMinVipPrice();
            if (minVipPrice.toString().isEmpty() || minVipPrice.toString().equals("0.0")) {
                tv_price.setVisibility(View.GONE);
            } else {
                tv_price.setVisibility(View.VISIBLE);
                tv_price.setText("¥" + minVipPrice.toString());
            }*/

            List<NearDataBean.AaDataBean.LessonsBean> lessons = dataBean.getLessons();
            if (lessons != null && lessons.size() > 0) {
                String lessonName = lessons.get(0).getName() == null ? "" : lessons.get(0).getName();//课程名


                tvContent.setVisibility(lessonName.isEmpty() ? View.GONE : View.VISIBLE);

                tvContent.setText(lessonName);

            } else {
                tvContent.setVisibility(View.GONE);
            }
            List<NearDataBean.AaDataBean.VideoLessonBean> videoLesson = dataBean.getVideoLesson();
            if (videoLesson.size()>0){
                tv_video_show.setVisibility(View.VISIBLE);
            }else {
                tv_video_show.setVisibility(View.GONE);
            }
            int qcAuth = dataBean.getQcAuth();
            if (qcAuth==2){
                tv_certify.setVisibility(View.VISIBLE);
            }else {
                tv_certify.setVisibility(View.GONE);
            }

            ratingBarStar.setRating(star);
            Glide.with(c).load(picture).into(imagePic);
            tvTitle.setText(name);


            tv_address_area.setText(areasName + intDivision + "km");
            tvCourseName.setText(catagoryName);


        }
    }
}
