package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.jgjj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhangju.xingquban.R;

import java.util.List;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * Created by zsl on 2017/8/28.
 */

public class MeJiGouJgjjTjAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MeJiGouJgjjTjBean> mList;
    private Context mContext;
    private final int TEXT_VIEW = 0;
    private final int IMAGE_VIEW = 1;

    /*public void addPhotoView(List<MeJiGouJgjjTjBean> list) {
      mList.addAll(list);
        notifyDataSetChanged();
    }*/

    public MeJiGouJgjjTjAdapter(Context context, List<MeJiGouJgjjTjBean> list) {
        this.mContext = context;
        this.mList = list;
        Log.i(TAG, "MeJiGouJgjjTjAdapter: " + list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TEXT_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mejgtj_bgtext, parent, false);
            return new TextViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mejgtj_bg, parent, false);
            return new ImageViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (mList.get(position).getType() == 0) {
            return TEXT_VIEW;
        } else if (mList.get(position).getType() == 1) {
            return IMAGE_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.me_jg_jgjjTj_text.setText(mList.get(position).getContent());
            textViewHolder.me_jg_jgjjTj_delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            Glide.with(mContext).load(mList.get(position).getContent()).skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .dontAnimate().into(imageViewHolder.me_jg_jgjjTj_image);
            imageViewHolder.me_jg_jgjjTj_delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView me_jg_jgjjTj_text;
        private ImageView me_jg_jgjjTj_delect;

        public TextViewHolder(View itemView) {
            super(itemView);
            me_jg_jgjjTj_text = (TextView) itemView.findViewById(R.id.me_jg_jgjjTj_text);
            me_jg_jgjjTj_delect = (ImageView) itemView.findViewById(R.id.me_jg_jgjjTj_delect);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView me_jg_jgjjTj_image, me_jg_jgjjTj_delect;

        public ImageViewHolder(View itemView) {
            super(itemView);
            me_jg_jgjjTj_image = (ImageView) itemView.findViewById(R.id.me_jg_jgjjTj_image);
            me_jg_jgjjTj_delect = (ImageView) itemView.findViewById(R.id.me_jg_jgjjTj_delect);

        }
    }
}
