package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen;

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
 * Created by ydw on 2017/10/13.
 */

public class FindWdLabelAdapter extends BaseRecycleViewAdapter {

    private List<String> mlist;
    int position=-1;


    public FindWdLabelAdapter(Context c, List<String> mlist) {
        super(c);
        this.mlist = mlist;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LablelViewHolder(resIdToView(parent, R.layout.item_label));

    }

    public void setSelected(int position ) {
        this.position = position;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LablelViewHolder lablelViewHolder = (LablelViewHolder) holder;
        lablelViewHolder.onBind();
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class LablelViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.label_text)
        TextView label_text;
        int pos;

        public LablelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void onBind() {
            //adapter2
            pos = getLayoutPosition();
            label_text.setText(mlist.get(pos));
            registerOnItemClickListener(pos, itemView);
            if (pos == position) {
                label_text.setTextColor(c.getResources().getColor(R.color.color_main));
                label_text.setBackground(c.getResources().getDrawable(R.drawable.label_red));
            }else {
                label_text.setTextColor(c.getResources().getColor(R.color.gray));
                label_text.setBackground(c.getResources().getDrawable(R.drawable.label_gray));
            }
        }
    }
}
