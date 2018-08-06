package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.view.swipedelete.SwipeLayout;
import com.zhangju.xingquban.interestclassapp.view.swipedelete.SwipeLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/17.
 * 我的问答--pop当中各个列表通用adapter
 */

public class QuestionPopAdapter extends BaseRecycleViewAdapter {

    private List<QuestionMainBean.AaDataBean> questionList;
    private int type;


    public QuestionPopAdapter(Context c, List<QuestionMainBean.AaDataBean> questionList, int type) {
        super(c);
        this.questionList = questionList;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopViewHolder(resIdToView(parent, R.layout.item_listview));
    }

    //    item_question_pop
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PopViewHolder popViewHolder = (PopViewHolder) holder;
        popViewHolder.onBind();

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class PopViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id._tv_title)
        TextView TvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.swipe_delete)
        TextView swipeDelete;

        public PopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            QuestionMainBean.AaDataBean dataBean = questionList.get(pos);
            String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
            String content = dataBean.getqExplain() == null ? "" : dataBean.getqExplain();//内容

            if (type == 1) {

                TvTitle.setText(title);
                tvContent.setText(content);


            } else if (type == 2) {
                QuestionMainBean.AaDataBean.QuestionTitle questionTitle = dataBean.getQuestionTitle();
                if (questionTitle != null) {
                    String title1 = questionTitle.getTitle() == null ? "" : questionTitle.getTitle();//标题
                    TvTitle.setText(title1);
                    tvContent.setText(title);
                }
            } else if (type == 3) {
                QuestionMainBean.AaDataBean.QuestionTitle questionTitles = dataBean.getQuestionTitle();
                if (questionTitles != null) {
                    String title1 = questionTitles.getTitle() == null ? "" : questionTitles.getTitle();//标题
                    TvTitle.setText(title);
                    tvContent.setText(title1);
                }


                //我的收藏
            } else if (type == 4) {
                QuestionMainBean.AaDataBean.Question question = dataBean.getQuestion();
                if (question != null) {
                    String title2 = question.getTitle() == null ? "" : question.getTitle();
                    String explain2 = question.getqExplain() == null ? "" : question.getqExplain();

                    TvTitle.setText(title2);
                    tvContent.setText(explain2);
                }
            }

            swipeDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteitem.deleteItem(pos);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenner.setonListenner(pos);
                }
            });
        }

    }

    private Deleteitem deleteitem;

    public void setDeleteitem(Deleteitem deleteitem) {
        this.deleteitem = deleteitem;
    }

    public interface Deleteitem {
        void deleteItem(int postion);

    }

    private OnClickListenner onClickListenner;

    public void setOnClickListenner(OnClickListenner onClickListenner) {
        this.onClickListenner = onClickListenner;
    }

    public interface OnClickListenner {
        void setonListenner(int pos);
    }


}
