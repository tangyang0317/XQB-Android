package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

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
 * Created by ydw on 2017/10/18.
 */
//发布城市选择Adapter
public class FindCityAdapter extends BaseRecycleViewAdapter {

    private List<String> mlist;
    int mSelectPosition;


    public FindCityAdapter(Context c, List<String> mlist) {
        super(c);
        this.mlist = mlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(resIdToView(parent, R.layout.find_item_city));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CityViewHolder holder1 = (CityViewHolder) holder;

        holder1.onBind();
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_city)
        TextView tvItemCity;

        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        int pos;

        void onBind() {
            pos = getLayoutPosition();
            tvItemCity.setText(mlist.get(pos));
            registerOnItemClickListener(pos, itemView);

            if (mSelectPosition == pos) {
                tvItemCity.setTextColor(c.getResources().getColor(R.color.white));
                tvItemCity.setBackground(c.getResources().getDrawable(R.drawable.juxingbg_colorman_bg ));
            } else {
                tvItemCity.setTextColor(c.getResources().getColor(R.color.grid_default));
                tvItemCity.setBackground(c.getResources().getDrawable(R.drawable.juxingbg_colorhui));
            }


        }
    }

    public void setPosition(int position) {
        this.mSelectPosition = position;
        notifyDataSetChanged();
    }


}
