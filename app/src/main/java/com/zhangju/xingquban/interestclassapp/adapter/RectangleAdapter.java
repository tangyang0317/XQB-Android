package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/10/20.
 */
//资源发布   图片视频Adapter
public class RectangleAdapter extends BaseRecycleViewAdapter {

    private int VIDEO_TYPE = 0X111;
    private int PICTURE_TYPE = 0X222;
    private int FOOTER_TYPE = 0X333;


    private int type;//1视频     2图片
    private View viewFooter;
    private List<String> mPicList;
    private List<String> mListTime;

    public RectangleAdapter(Context c, List<String> mPicList, int type, View viewFooter) {
        super(c);
        this.mPicList = mPicList;
        this.type = type;
        this.viewFooter = viewFooter;
    }

    public RectangleAdapter(Context c, List<String> mPicList, List<String> mListTime, int type, View viewFooter) {
        super(c);
        this.mPicList = mPicList;
        this.mListTime = mListTime;
        this.type = type;
        this.viewFooter = viewFooter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIDEO_TYPE) {
            return new VideoViewHolder(resIdToView(parent, R.layout.video_item));
        } else if (viewType == PICTURE_TYPE) {
            return new PictureViewHolder(resIdToView(parent, R.layout.picture_item));
        } else if (viewType == FOOTER_TYPE) {
            return new FootViewHolder(viewFooter);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoViewHolder) {
            VideoViewHolder viewHolder = (VideoViewHolder) holder;
            viewHolder.onBind();
        } else if (holder instanceof PictureViewHolder) {
            PictureViewHolder viewHolder = (PictureViewHolder) holder;
            viewHolder.onBind();
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder viewHolder = (FootViewHolder) holder;
            viewHolder.onBind();
        }

    }

    @Override
    public int getItemCount() {
        return mPicList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mPicList.size()) {
            return FOOTER_TYPE;
        }
        if (type == 1) {
            return VIDEO_TYPE;
        } else if (type == 2) {
            return PICTURE_TYPE;
        }
        return TYPE_INVAILED;
    }

    //视频
    class VideoViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.image_video)
        ImageView imageVideo;
        @BindView(R.id.pic_delete)
        View picDelete;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();

            String pic = mPicList.get(pos);
            Glide.with(c).load(pic.isEmpty() ? R.mipmap.photo : pic).into(imageVideo);
            String s = mListTime.get(pos);
            tvTime.setText(s);


            //删除图片
            picDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPicList.remove(pos);
                    notifyDataSetChanged();
                }
            });
        }
    }

    //图片
    class PictureViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.photo_image)
        ImageView photoImage;
        @BindView(R.id.pic_delete)
        View picDelete;

        public PictureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();

            if (mPicList.size() > 0) {
                String pic = mPicList.get(pos) == null ? "" : mPicList.get(pos);
                Glide.with(c).load(pic.isEmpty() ? R.mipmap.photo : pic).into(photoImage);
            }
            getPicList.getPicList(mPicList);

            //删除图片
            picDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPicList.remove(pos);
                    notifyDataSetChanged();
                }
            });

        }

    }

    private getPicList getPicList;

    public void setGetPicList(RectangleAdapter.getPicList getPicList) {
        this.getPicList = getPicList;
    }

    public interface getPicList {
        void getPicList(List<String> mlist);
    }

    //Footer
    class FootViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.photo_image)
        ImageView photoImage;


        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            Glide.with(c).load(R.mipmap.home_data_xdp_image).into(photoImage);


        }
    }


}
