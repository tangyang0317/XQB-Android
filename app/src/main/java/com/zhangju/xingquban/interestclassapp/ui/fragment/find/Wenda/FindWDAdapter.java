package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;

/**
 * Created by zsl on 2017/7/21.
 */

public class FindWDAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private FindWDBean mFindWDBean;
    private OnItemClickListener mOnItemClickListener = null;

//    public void setfindTWRecyler(List<FindWDBean.AaDataBean> list) {
//        this.mList = list;
//        Log.i(TAG, "setfindTWRecyler: " + mList);
//        notifyDataSetChanged();
//    }

    public void addData(FindWDBean findWDBean) {
        mFindWDBean.getAaData().addAll(findWDBean.getAaData());
        notifyDataSetChanged();
    }

    public FindWDBean getData() {
        return this.mFindWDBean;
    }

    //define interfacequestion
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public FindWDAdapter(Context context, FindWDBean findWDBean) {
        this.mContext = context;
        this.mFindWDBean = findWDBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_wenda_recylerview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //new 适配器 传入mContext 和 mlist,getpooton.getprcList Adapter是final
        final FindItemRecyclerAdapter findItemRecyclerAdapter = new FindItemRecyclerAdapter(mContext, mFindWDBean.getAaData().get(position));
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mContext).load(mFindWDBean.getAaData().get(position).getAuthorPicture()).into(holder1.find_wenda_item_icon);

        final String title = mFindWDBean.getAaData().get(position).getTitle();
        holder1.title.setText(title);
        holder1.find_wenda_shunfa.setText(mFindWDBean.getAaData().get(position).getLabel());
        holder1.find_wenda_item_day.setText(mFindWDBean.getAaData().get(position).getDateTime());
        holder1.find_wenda_item_name.setText(mFindWDBean.getAaData().get(position).getAuthorName());
        holder1.find_wenda_item_plnumber.setText(mFindWDBean.getAaData().get(position).getAnswers() + "");

        holder1.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
//        holder1.recyclerView.addItemDecoration(new SpaceItemDecoration2(DensityUtils.dp2px(mContext, 5)));
        holder1.recyclerView.setAdapter(findItemRecyclerAdapter);

        holder1.find_wenda_item_huida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FindTiWenXqHd.class);
                intent.putExtra("title", title);
                intent.putExtra("tag", position);
                mContext.startActivity(intent);
            }
        });


        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mFindWDBean.getAaData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image, find_wenda_item_icon;
        private TextView title, find_wenda_shunfa, find_wenda_item_day, find_wenda_item_name, find_wenda_item_plnumber, find_wenda_item_huida;
        private RecyclerView recyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.find_wenda_item_image);
            find_wenda_item_icon = (ImageView) itemView.findViewById(R.id.find_wenda_item_icon);
            title = (TextView) itemView.findViewById(R.id.find_wenda_item_title);
            find_wenda_shunfa = (TextView) itemView.findViewById(R.id.find_wenda_shunfa);
            find_wenda_item_day = (TextView) itemView.findViewById(R.id.find_wenda_item_day);
            find_wenda_item_name = (TextView) itemView.findViewById(R.id.find_wenda_item_name);
            find_wenda_item_plnumber = (TextView) itemView.findViewById(R.id.find_wenda_item_plnumber);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.find_wenda_item_recycler);
            find_wenda_item_huida = (TextView) itemView.findViewById(R.id.find_wenda_item_huida);
        }
    }
}
