package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.MultiTypeAdapter;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Listener;
import com.fastlib.net.Request;
import com.fastlib.utils.DensityUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCollection;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCompatStore;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/10/31.
 * 全部类型收藏
 */
public class AllCollectionAdapter extends MultiTypeAdapter implements Listener<Response<List<ResponseCollection>>, Object, Object> {
    protected StoreGroup mStoreGroup;
    protected VideoAndRadioGroup mVideoAndRadioGroup;
    protected ImageTypeGroup mImageGroup;
    protected InformationGroup mInformationGroup;
    private Request mRequest;
    private SwipeRefreshLayout mRefresh;
    private boolean isMore, isRefresh, isShowDelete = false;
    private List<ResponseCompatStore> mCompatData; //预留以兼容跳转店铺数据

    public AllCollectionAdapter(Context context) {
        super(context);
        addGroup(mStoreGroup = new StoreGroup());
        addGroup(mVideoAndRadioGroup = new VideoAndRadioGroup());
        addGroup(mImageGroup = new ImageTypeGroup());
        addGroup(mInformationGroup = new InformationGroup());
        mRequest = Request.obtain(MeInterface.POST_COLLECTION_LIST);
        mRequest.setListener(this);
        mRequest.put("customerId", UserManager.getInstance().getUser().id);
        mRequest.put("pageIndex", 0);
        mRequest.put("pageSize", 10);
        refresh();
    }

    public void refresh() {
        isMore = true;
        isRefresh = true;
        mRequest.put("pageIndex", 0);
        mRequest.start();
    }

    private void more() {
        isRefresh = false;
        mRequest.increment("pageIndex", 1);
        mRequest.start();
    }

    public void addData(List<ResponseCollection> list) {
        if (list == null) return;
        for (ResponseCollection collection : list) {
            if (collection.collectionTypes == 2) {
                if (collection.resources == null) break; //丢弃错误的资源收藏数据
                switch (collection.resources.types) {
                    case "picture":
                        mImageGroup.addData(collection);
                        break;
                    case "article":
                        mInformationGroup.addData(collection);
                        break;
                    default:
                        mVideoAndRadioGroup.addData(collection);
                        break;
                }
            } else if (collection.collectionTypes == 3) {
                mStoreGroup.addData(collection);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onRawData(Request r, byte[] data) {
        //适配
    }

    @Override
    public void onTranslateJson(Request r, String json) {
        Gson gson = new Gson();
        Response<List<ResponseCompatStore>> compatListResult = gson.fromJson(json, new TypeToken<Response<List<ResponseCompatStore>>>() {
        }.getType());
        mCompatData = compatListResult.data;
    }

    @Override
    public void onResponseListener(Request r, Response<List<ResponseCollection>> result, Object result2, Object cookedResult) {
        if (mRefresh != null) mRefresh.setRefreshing(false);
        if (result.success) {
            if (isRefresh) {
                mImageGroup.clear();
                mInformationGroup.clear();
                mVideoAndRadioGroup.clear();
                mStoreGroup.clear();
            }
            if (result.data == null || result.data.isEmpty()) isMore = false;
            else addData(result.data);
        }
    }

    @Override
    public void onErrorListener(Request r, String error) {
        if (mRefresh != null) mRefresh.setRefreshing(false);
    }

    public void setRefresh(SwipeRefreshLayout refresh) {
        mRefresh = refresh;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
        notifyDataSetChanged();
    }

    class StoreGroup extends RecyclerGroup<ResponseCollection> {

        @Override
        protected void binding(final int positionOfRecyclerView, int positionOfGroup, final ResponseCollection data, CommonViewHolder holder) {
            if (data.teacherTime == null) return;
            ResponseCollection.CollectionStore resource = data.teacherTime;
            RatingBar ratingBar = holder.getView(R.id.ratingBar);

            holder.setText(R.id.title, resource.name);
            holder.setText(R.id.type, resource.catagoryName);
            holder.setText(R.id.price, "￥" + data.LessonsPrice);

            if (data.customer != null) {
                Glide.with(mContext).load(data.customer.picture).error(R.mipmap.me_touxiang).into((ImageView) holder.getView(R.id.cover));
                holder.setText(R.id.location, data.customer.areasName);
            }
            ratingBar.setRating(resource.star);
            if (positionOfRecyclerView == getCount() - 1 && isMore)
                more();
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeRecyclerViewData.launchActivity(mContext, mCompatData.get(positionOfRecyclerView).teacherTime.getId());
                }
            });
            final CheckBox deleteCheck = holder.getView(R.id.deleteFlag);
            View contentView = holder.getView(R.id.contentView);

            deleteCheck.setOnCheckedChangeListener(null);
            deleteCheck.setChecked(data.isDeleteChecked);
            deleteCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.isDeleteChecked = isChecked;
                }
            });
            if (isShowDelete)
                contentView.setTranslationX(deleteCheck.getWidth() + DensityUtils.dp2px(mContext, 32));
            else contentView.setTranslationX(0);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_collection_store;
        }
    }

    class VideoAndRadioGroup extends RecyclerGroup<ResponseCollection> {

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, final ResponseCollection data, CommonViewHolder holder) {
            if (data.resources == null) return;
            final ResponseCollection.CollectionResource resource = data.resources;
            holder.setText(R.id.title, resource.title);
            holder.setText(R.id.price, String.format(Locale.getDefault(), resource.price == 0 ? "免费" : "￥%s", resource.price));
            holder.setText(R.id.playCount, String.format(Locale.getDefault(), "%s播放", resource.clickRate));
            holder.setText(R.id.authorName, resource.author);
            holder.setText(R.id.commentCount, Integer.toString(resource.commentCounts));
            holder.setText(R.id.collectionCount, Integer.toString(resource.collectionCounts));
            Glide.with(mContext).load(resource.titlePicture).into((ImageView) holder.getView(R.id.cover));
            Glide.with(mContext).load(resource.authorPicture).into((ImageView) holder.getView(R.id.avatar));
            if (positionOfRecyclerView == getCount() - 1 && isMore)
                more();
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AudioDetailActivity.class);
                    intent.putExtra("types", resource.types);
                    intent.putExtra("resId", resource.id);
                    mContext.startActivity(intent);
                }
            });
            CheckBox deleteFlag = holder.getView(R.id.deleteFlag);
            View contentView = holder.getView(R.id.contentView);

            deleteFlag.setOnCheckedChangeListener(null);
            deleteFlag.setChecked(data.isDeleteChecked);
            deleteFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.isDeleteChecked = isChecked;
                }
            });
            if (isShowDelete)
                contentView.setTranslationX(deleteFlag.getWidth() + DensityUtils.dp2px(mContext, 32));
            else contentView.setTranslationX(0);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_collection_res;
        }
    }

    class ImageTypeGroup extends RecyclerGroup<ResponseCollection> {

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, final ResponseCollection data, CommonViewHolder holder) {
            final ResponseCollection.CollectionResource resource = data.resources;
            holder.setText(R.id.title, resource.title);
            holder.setText(R.id.commentCount, Integer.toString(resource.commentCounts));
            holder.setText(R.id.collectionCount, Integer.toString(resource.collectionCounts));
            holder.setText(R.id.date, resource.addUserTime);
            List<ResponseCollection.PictureRes> pictureList = data.resourcesPictureList;
            if (pictureList != null && !pictureList.isEmpty()) {
                Glide.with(mContext).load(pictureList.get(0).fileUrl).into((ImageView) holder.getView(R.id.cover));
                if (pictureList.size() > 1)
                    Glide.with(mContext).load(pictureList.get(1).fileUrl).into((ImageView) holder.getView(R.id.cover2));
                if (pictureList.size() > 2)
                    Glide.with(mContext).load(pictureList.get(2).fileUrl).into((ImageView) holder.getView(R.id.cover3));
            }
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PicDetailActivity.class);
                    intent.putExtra("id", resource.id);
                    mContext.startActivity(intent);
                }
            });
            CheckBox deleteFlag = holder.getView(R.id.deleteFlag);
            View contentView = holder.getView(R.id.contentView);

            deleteFlag.setOnCheckedChangeListener(null);
            deleteFlag.setChecked(data.isDeleteChecked);
            deleteFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.isDeleteChecked = isChecked;
                }
            });
            if (isShowDelete)
                contentView.setTranslationX(deleteFlag.getWidth() + DensityUtils.dp2px(mContext, 32));
            else contentView.setTranslationX(0);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_collection_image;
        }
    }

    class InformationGroup extends RecyclerGroup<ResponseCollection> {

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, final ResponseCollection data, CommonViewHolder holder) {
            final ResponseCollection.CollectionResource resource = data.resources;
            holder.setText(R.id.title, resource.title);
            holder.setText(R.id.commentCount, Integer.toString(resource.commentCounts));
            holder.setText(R.id.collectionCount, Integer.toString(resource.collectionCounts));
            holder.setText(R.id.date, resource.addUserTime);
            if (data.resourcesPictureList != null && !data.resourcesPictureList.isEmpty()) {
                Glide.with(mContext).load(data.resourcesPictureList.get(0).fileUrl).into((ImageView) holder.getView(R.id.cover));
            }
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("id", resource.id);
                    mContext.startActivity(intent);
                }
            });
            CheckBox deleteFlag = holder.getView(R.id.deleteFlag);
            View contentView = holder.getView(R.id.contentView);

            deleteFlag.setOnCheckedChangeListener(null);
            deleteFlag.setChecked(data.isDeleteChecked);
            deleteFlag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.isDeleteChecked = isChecked;
                }
            });
            if (isShowDelete)
                contentView.setTranslationX(deleteFlag.getWidth() + DensityUtils.dp2px(mContext, 32));
            else contentView.setTranslationX(0);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_collection_information;
        }
    }
}