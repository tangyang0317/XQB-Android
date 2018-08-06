package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/8/30.
 */

public class FindTiWenXqPlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FindTiWenXqPlBean mFindTiWenXqPlBean;
    private Context mContext;

    public FindTiWenXqPlAdapter(Context context, FindTiWenXqPlBean findTiWenXqPlBean) {
        this.mContext = context;
        this.mFindTiWenXqPlBean = findTiWenXqPlBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_findtwxq_pl, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final FindItemPLRecyclerAdapter findItemPLRecyclerAdapter = new FindItemPLRecyclerAdapter(mContext, mFindTiWenXqPlBean.getAaData().get(position));

        try {
            String name = mFindTiWenXqPlBean.getAaData().get(position).getSignName() == null ? "" : mFindTiWenXqPlBean.getAaData().get(position).getSignName().toString();
        //昵称    holder1.findTwxqPlName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object customerPic = mFindTiWenXqPlBean.getAaData().get(position).getCustomerPic();
        if ( customerPic!=null&&! customerPic.toString().isEmpty()){
            Glide.with(mContext).load(customerPic.toString()).into(holder1.findTwxqPlIcon);

        }

        holder1.findTwxqPlTime.setText(mFindTiWenXqPlBean.getAaData().get(position).getDateTime());
        holder1.findTwxqPlContent.setText(mFindTiWenXqPlBean.getAaData().get(position).getTitle());
        holder1.findTwxqPlRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
//        holder1.findTwxqPlRecycler.addItemDecoration(new SpaceItemDecoration2(DensityUtils.dp2px(mContext, 10)));
        holder1.findTwxqPlRecycler.setAdapter(findItemPLRecyclerAdapter);

    }

    @Override
    public int getItemCount() {
        return mFindTiWenXqPlBean.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.find_twxqPl_icon)
        CustomRoundView findTwxqPlIcon;
        @BindView(R.id.find_twxqPl_name)
        TextView findTwxqPlName;
        @BindView(R.id.find_twxqPl_time)
        TextView findTwxqPlTime;
        @BindView(R.id.find_twxqPl_content)
        TextView findTwxqPlContent;
        @BindView(R.id.find_twxqPl_recycler)
        RecyclerView findTwxqPlRecycler;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
