package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/16.
 * 我的问答--详情下面adapter
 */

public class QuestionDetailAdapter extends BaseRecycleViewAdapter {
    private List<QuestionMainBean.AaDataBean> mDetailList;


    public QuestionDetailAdapter(Context c, List<QuestionMainBean.AaDataBean> mDetailList) {
        super(c);
        this.mDetailList = mDetailList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailViewHolder(resIdToView(parent, R.layout.item_question_detail_two));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DetailViewHolder detailViewHolder= (DetailViewHolder) holder;
        detailViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mDetailList.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.item_question_head_pic)
        CustomRoundView itemQuestionHeadPic;
        @BindView(R.id.tv_time_and_name)
        TextView tvTimeAndName;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.item_two_recyclerview)
        RecyclerView itemTwoRecyclerview;

        public DetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            QuestionMainBean.AaDataBean data = mDetailList.get(pos);
            String title = data.getTitle() == null ? "" : data.getTitle();//标题
            String time = data.getDateTime()==null?"":data.getDateTime();//时间
            String authorPicture = data.getCustomerPic()==null?"":data.getCustomerPic();//头像
            String authorName = data.getSignName()==null?"":data.getSignName();//名字
            List<QuestionMainBean.AaDataBean.PicStrBeans> picStr = data.getPicStr();

            Glide.with(c).load(authorPicture).placeholder(R.drawable.app_icon).dontTransform().dontAnimate().into(itemQuestionHeadPic);
            tvTitle.setText(title);
            tvTimeAndName.setText(authorName+"\n"+time);



            List<String> mImageList=new ArrayList<>();
            for (QuestionMainBean.AaDataBean.PicStrBeans beans : picStr) {
                mImageList.add(beans.getPicture());
            }

            //图片
            ImageAdapter imageAdapter=new ImageAdapter(c,mImageList);
            ScrollGridManager manager=new ScrollGridManager(c,3);
            manager.setScrollEnabled(false);
            itemTwoRecyclerview.setLayoutManager(manager);
            itemTwoRecyclerview.setAdapter(imageAdapter);

        }
    }
}
