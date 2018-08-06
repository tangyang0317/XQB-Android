package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/1.
 */
//直播科目二级item
public class LiveSubItemAdapter extends BaseRecycleViewAdapter {


    private NearSubjectBean.AaDataBean childList;


    public LiveSubItemAdapter(Context c, NearSubjectBean.AaDataBean childList ) {
        super(c);
        this.childList = childList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SubItemViewholder(resIdToView(parent, R.layout.find_item_city));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SubItemViewholder subItemViewholder = (SubItemViewholder) holder;
        subItemViewholder.onBind();
    }

    @Override
    public int getItemCount() {
        return childList.getChilds().size();
    }

    class SubItemViewholder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.tv_item_city)
        TextView tvItemCity;

        public SubItemViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void onBind() {
            pos = getLayoutPosition();

            List<NearSubjectBean.AaDataBean.ChildsBean> childs = childList.getChilds();

            String name = childs.get(pos).getName() == null ? "" : childs.get(pos).getName();
            tvItemCity.setTextSize(14);

            tvItemCity.setText(name);
            registerOnItemClickListener(pos, itemView);
            //单选判断

            boolean selected = childs.get(pos).isSelected();
            if (selected) {
                tvItemCity.setTextColor(selected ? Color.RED : Color.BLACK);
                tvItemCity.setBackground(c.getResources().getDrawable(R.drawable.juxingbg_colorhui));
            } else {
                tvItemCity.setTextColor(Color.BLACK);
                tvItemCity.setBackgroundColor(Color.WHITE);
            }




        }
    }
}
