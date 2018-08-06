package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl.MeJiGouXcglScspSpjBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeJiGouXcglSpjAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private MeJiGouXcglScspSpjBean mMeJiGouSpkcSpjBean;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

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

    public MeJiGouXcglSpjAdapter2(Context context, MeJiGouXcglScspSpjBean meJiGouSpkcSpjBean) {
        this.mContext = context;
        this.mMeJiGouSpkcSpjBean = meJiGouSpkcSpjBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_me_jg_gridview2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final ViewHolder holder1 = (ViewHolder) holder;
//        Glide.with(mContext).load(mMeJiGouSpkcSpjBean.getAaData().get(position).getVideoTitlePic()).into(holder1.itemGridview2Image);

        holder1.itemGridview2Number.setText(mMeJiGouSpkcSpjBean.getAaData().get(position).getOrganAlbumFilesNum() + "");
        holder1.itemGridview2Title.setText(mMeJiGouSpkcSpjBean.getAaData().get(position).getFiles());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMeJiGouSpkcSpjBean.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_gridview2_image)
        ImageView itemGridview2Image;
        @BindView(R.id.item_gridview2_number)
        TextView itemGridview2Number;
        @BindView(R.id.item_gridview2_title)
        TextView itemGridview2Title;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
