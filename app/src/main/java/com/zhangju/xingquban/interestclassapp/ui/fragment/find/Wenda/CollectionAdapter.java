package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/10/17.
 */
//收藏列表adapter
public class CollectionAdapter extends BaseRecycleViewAdapter {

    private List<FindSCBean.AaDataBean> findSCBean;

    public CollectionAdapter(Context c, List<FindSCBean.AaDataBean> findSCBean) {
        super(c);
        this.findSCBean = findSCBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CollectHolder(resIdToView(parent, R.layout.item_findwdsc_tiwen));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectHolder holder1= (CollectHolder) holder;
        holder1.onBind();


    }

    public void addData(FindSCBean findWDBean) {
        findSCBean.addAll(findWDBean.getAaData());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return findSCBean.size()  ;
    }

    class CollectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_find_tiwen_title)
        TextView itemFindTiwenTitle;
        @BindView(R.id.item_find_tiwen_number)
        TextView itemFindTiwenNumber;
        int pos;


        void onBind(){
            pos=getLayoutPosition();
            Object title = findSCBean.get(pos).getQuestion().getTitle()==null?"":findSCBean.get(pos).getQuestion().getTitle();
            List<FindSCBean.AaDataBean.QuestionBean.AnswerBean> answersList = findSCBean.get(pos).getQuestion().getAnswersList();
            String label=answersList.size()>0?answersList.get(0).getTitle():"";

            itemFindTiwenTitle.setText(title.toString());
            itemFindTiwenNumber.setText(label );
            registerOnItemClickListener(pos,itemView);
        }

        public CollectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
