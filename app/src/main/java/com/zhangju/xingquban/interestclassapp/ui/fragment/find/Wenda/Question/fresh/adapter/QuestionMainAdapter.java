package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyAnswerActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/16.
 * 我的问答最新和推荐adapter
 */

public class QuestionMainAdapter extends BaseRecycleViewAdapter {

    List<QuestionMainBean.AaDataBean> mainBeanList;


    public QuestionMainAdapter(Context c, List<QuestionMainBean.AaDataBean> mainBeanList) {
        super(c);
        this.mainBeanList = mainBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionMainViewHolder(resIdToView(parent, R.layout.item_question_main));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        QuestionMainViewHolder questionMainViewHolder = (QuestionMainViewHolder) holder;
        questionMainViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mainBeanList.size();
    }

    class QuestionMainViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.item_question_title)
        TextView itemQuestionTitle;
        @BindView(R.id.item_question_recyclerView)
        RecyclerView itemQuestionRecyclerView;
        @BindView(R.id.item_question_type)
        TextView itemQuestionType;
        @BindView(R.id.item_question_time)
        TextView itemQuestionTime;
        @BindView(R.id.item_question_head_pic)
        CustomRoundView itemQuestionHeadPic;
        @BindView(R.id.item_question_name)
        TextView itemQuestionName;
        @BindView(R.id.item_question_count)
        TextView itemQuestionCount;
        @BindView(R.id.item_question_answer)
        LinearLayout itemQuestionAnswer;
        public QuestionMainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos,itemView);
            final QuestionMainBean.AaDataBean dataBean = mainBeanList.get(pos);
            String authorName = dataBean.getAuthorName()==null?"":dataBean.getAuthorName();//作者
            final String title = dataBean.getTitle()==null?"":dataBean.getTitle();//标题
            String addUserTime = dataBean.getAddUserTime()==null?"":dataBean.getAddUserTime();//
            String time = addUserTime.substring(0, addUserTime.indexOf(" "));//时间
            String label = dataBean.getLabel()==null?"":dataBean.getLabel();//标签
            int answers = dataBean.getAnswers();//评论数
            String authorPicture = dataBean.getAuthorPicture()==null?"":dataBean.getAuthorPicture();//头像


            itemQuestionTitle.setText(title);
            itemQuestionType.setText(label);
            itemQuestionTime.setText(time);
            itemQuestionCount.setText(answers+"");
            itemQuestionName.setText(authorName);
            Glide.with(c).load(authorPicture).placeholder(R.drawable.app_icon).dontTransform().dontAnimate().into(itemQuestionHeadPic);

            List<QuestionMainBean.AaDataBean.PicStrBeans> picStr = dataBean.getPicStr();
            List<String> mImageList=new ArrayList<>();
            for (QuestionMainBean.AaDataBean.PicStrBeans beans : picStr) {
                mImageList.add(beans.getPicture());
            }

            //图片
            ImageAdapter imageAdapter=new ImageAdapter(c,mImageList);
            ScrollGridManager manager=new ScrollGridManager(c,3);
            manager.setScrollEnabled(false);
            itemQuestionRecyclerView.setLayoutManager(manager);
            itemQuestionRecyclerView.setAdapter(imageAdapter);

            itemQuestionAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = dataBean.getId();
                    Intent intent=new Intent(c, MyAnswerActivity.class);
                    intent.putExtra("data",dataBean);
                    intent.putExtra("type",1);
                    c.startActivity(intent);
                }
            });

        }
    }
}
