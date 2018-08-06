package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/8/7.
 */

public class FindSeekChoicenessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FindSeekChoicenessBean> mList = new ArrayList<>();
    private Context mContext;
    private onRecyclerViewClickItemListener onRecyclerViewClickItemListener;

    public FindSeekChoicenessAdapter(Context context, List<FindSeekChoicenessBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seek_choiceness, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public void setRecyclerViewItemClickListener(onRecyclerViewClickItemListener onRecyclerViewClickItemListener) {
        this.onRecyclerViewClickItemListener = onRecyclerViewClickItemListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.seek_choiceness_lable.setText("活动");

//        holder1.seek_choiceness_title.setText("");
//        holder1.seek_choiceness_xqb_qzjb.setText("");
//        holder1.seek_choiceness_money.setText("");
//        holder1.seek_choiceness_mf.setText("");
//        holder1.seek_choiceness_ks_time.setText("");
//        holder1.seek_choiceness_city.setText("");
//        holder1.seek_choiceness_jz_time.setText("");

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewClickItemListener != null) {
                    onRecyclerViewClickItemListener.onItemClickListener(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface onRecyclerViewClickItemListener {
        public void onItemClickListener(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView seek_choiceness_imageView;
        private TextView seek_choiceness_lable, seek_choiceness_title, seek_choiceness_xqb_qzjb,
                seek_choiceness_money, seek_choiceness_mf, seek_choiceness_ks_time, seek_choiceness_city, seek_choiceness_jz_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            seek_choiceness_imageView = (ImageView) itemView.findViewById(R.id.seek_choiceness_imageView);
            seek_choiceness_lable = (TextView) itemView.findViewById(R.id.seek_choiceness_lable);
            seek_choiceness_title = (TextView) itemView.findViewById(R.id.seek_choiceness_title);
            seek_choiceness_xqb_qzjb = (TextView) itemView.findViewById(R.id.seek_choiceness_xqb_qzjb);
            seek_choiceness_money = (TextView) itemView.findViewById(R.id.seek_choiceness_money);
            seek_choiceness_mf = (TextView) itemView.findViewById(R.id.seek_choiceness_mf);
            seek_choiceness_ks_time = (TextView) itemView.findViewById(R.id.seek_choiceness_ks_time);
            seek_choiceness_city = (TextView) itemView.findViewById(R.id.seek_choiceness_city);
            seek_choiceness_jz_time = (TextView) itemView.findViewById(R.id.seek_choiceness_jz_time);
        }
    }
}
