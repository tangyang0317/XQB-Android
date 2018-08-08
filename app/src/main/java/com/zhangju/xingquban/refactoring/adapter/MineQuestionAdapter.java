package com.zhangju.xingquban.refactoring.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的问答推荐
 *
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName MineQuestionRecommondAdapter
 * @Author tangyang
 * @DATE 2018/8/7
 **/
public class MineQuestionAdapter extends BaseQuickAdapter<QuestionMainBean.AaDataBean, BaseViewHolder> {

    public MineQuestionAdapter() {
        super(R.layout.item_question_main, new ArrayList<QuestionMainBean.AaDataBean>());
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionMainBean.AaDataBean item) {
        helper.addOnClickListener(R.id.item_question_answer);

        TextView itemQuestionTitle = helper.getView(R.id.item_question_title);
        RecyclerView itemQuestionRecyclerView = helper.getView(R.id.item_question_recyclerView);
        TextView itemQuestionType = helper.getView(R.id.item_question_type);
        TextView itemQuestionTime = helper.getView(R.id.item_question_time);
        CustomRoundView itemQuestionHeadPic = helper.getView(R.id.item_question_head_pic);
        TextView itemQuestionName = helper.getView(R.id.item_question_name);
        TextView itemQuestionCount = helper.getView(R.id.item_question_count);
        LinearLayout itemQuestionAnswer = helper.getView(R.id.item_question_answer);

        String authorName = item.getAuthorName() == null ? "" : item.getAuthorName();//作者
        final String title = item.getTitle() == null ? "" : item.getTitle();//标题
        String addUserTime = item.getAddUserTime() == null ? "" : item.getAddUserTime();//
        String time = addUserTime.substring(0, addUserTime.indexOf(" "));//时间
        String label = item.getLabel() == null ? "" : item.getLabel();//标签
        int answers = item.getAnswers();//评论数
        String authorPicture = item.getAuthorPicture() == null ? "" : item.getAuthorPicture();//头像
        itemQuestionTitle.setText(title);
        itemQuestionType.setText(label);
        itemQuestionTime.setText(time);
        itemQuestionCount.setText(answers + "");
        itemQuestionName.setText(authorName);
        Glide.with(mContext).load(authorPicture).placeholder(R.drawable.app_icon).dontTransform().dontAnimate().into(itemQuestionHeadPic);

        List<QuestionMainBean.AaDataBean.PicStrBeans> picStr = item.getPicStr();
        List<String> mImageList = new ArrayList<>();
        for (QuestionMainBean.AaDataBean.PicStrBeans beans : picStr) {
            mImageList.add(beans.getPicture());
        }
        //图片
        ImageAdapter imageAdapter = new ImageAdapter(mContext, mImageList);
        ScrollGridManager manager = new ScrollGridManager(mContext, 3);
        manager.setScrollEnabled(false);
        itemQuestionRecyclerView.setLayoutManager(manager);
        itemQuestionRecyclerView.setAdapter(imageAdapter);
    }
}
