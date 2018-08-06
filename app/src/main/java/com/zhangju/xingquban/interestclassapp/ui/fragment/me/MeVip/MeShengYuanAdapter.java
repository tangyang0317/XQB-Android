package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip;

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
 * Created by zsl on 2017/8/11.
 */

public class MeShengYuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MeShengYuanBean> mList = new ArrayList<>();
    private Context mContext;

    public MeShengYuanAdapter(Context context, List<MeShengYuanBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_message, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.find_message_data_title.setText("钢琴考级需要高级教师辅导");
        holder1.find_message_data_nr.setText("本人有一男孩18个月，目前钢琴考级,(急需专业高级)钢琴老师辅导，联系电话13411111111");
        holder1.find_message_data_biaoqian.setText("音乐");
//        holder1.find_message_data_time.setText("");
//        holder1.find_message_data_name.setText("");
        holder1.find_message_data_gkNumber.setText("72");
//        holder1.find_message_data_city.setText("");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView find_message_data_title, find_message_data_nr, find_message_data_biaoqian, find_message_data_time, find_message_data_name, find_message_data_gkNumber, find_message_data_city;
        private ImageView find_message_data_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            find_message_data_title = (TextView) itemView.findViewById(R.id.find_message_data_title);
            find_message_data_nr = (TextView) itemView.findViewById(R.id.find_message_data_nr);
            find_message_data_biaoqian = (TextView) itemView.findViewById(R.id.find_message_data_biaoqian);

            find_message_data_time = (TextView) itemView.findViewById(R.id.find_message_data_time);
            find_message_data_name = (TextView) itemView.findViewById(R.id.find_message_data_name);
            find_message_data_gkNumber = (TextView) itemView.findViewById(R.id.find_message_data_gkNumber);
            find_message_data_city = (TextView) itemView.findViewById(R.id.find_message_data_city);

        }
    }
}
