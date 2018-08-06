package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc.MeJiGouSpkcSpjBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeJiGouXcglSpBean extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private MeJiGouSpkcSpjBean mMeJiGouSpkcSpjBean;
    private Context mContext;
    private ImageType mType;
    private boolean deleteFlag = false;
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

    public MeJiGouXcglSpBean(Context context, MeJiGouSpkcSpjBean meJiGouSpkcSpjBean) {
        this.mContext = context;
        this.mMeJiGouSpkcSpjBean = meJiGouSpkcSpjBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_me_jg_gridview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final ViewHolder holder1 = (ViewHolder) holder;
        holder1.meJjglXcglPlay.setVisibility(mType == ImageType.IMAGE ? View.GONE : View.VISIBLE);
        holder1.meJjglXcglFalse.setVisibility(deleteFlag ? View.VISIBLE : View.GONE);
        Glide.with(mContext).load(mMeJiGouSpkcSpjBean.getAaData().get(position).getVideoTitlePic()).into(holder1.homeDataSpkcallImage);

        holder1.meJjglXcglFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bean.delete = !bean.delete;
//                holder1.meJjglXcglFalse.setImageResource(bean.delete ? R.mipmap.me_jjgl_xcgl_true : R.mipmap.me_jjgl_xcgl_false);
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMeJiGouSpkcSpjBean.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_data_spkcall_image)
        ImageView homeDataSpkcallImage;
        @BindView(R.id.me_jjgl_xcgl_play)
        ImageView meJjglXcglPlay;
        @BindView(R.id.me_jjgl_xcgl_false)
        ImageView meJjglXcglFalse;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void deleteSelectImage() {
        List<GridViewImageView.Bean> needDelete = new ArrayList<>();
//        for (GridViewImageView.Course b : list)
//            if (b.delete)
//                needDelete.add(b);
//        for (GridViewImageView.Course b : needDelete)
////            list.remove(b);
        notifyDataSetChanged();
    }

    public void canDelete(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
//        if (!deleteFlag && list != null) {
//            for (GridViewImageView.Course b : list)
//                b.delete = false;
//        }
        notifyDataSetChanged();
    }

    public void setData(List<String> list) {
        for (String image : list) {
            GridViewImageView.Bean bean = new GridViewImageView.Bean();
            bean.image = image;
//            this.list.add(bean);
        }
        notifyDataSetChanged();
    }

    public enum ImageType {
        IMAGE,
        VIDEO
    }
}
