package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.utils.DensityUtils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCollection;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/10/30.
 * 资源类型收藏
 */
public class ResCollectionAdapter extends SingleAdapterForRecycler<ResponseCollection,Response<List<ResponseCollection>>>{
    private boolean isShowDelete=false;

    public ResCollectionAdapter(Context context){
        super(context, R.layout.item_collection_res);
    }

    @Override
    public Request generateRequest() {
        Request request=Request.obtain(MeInterface.POST_COLLECTION_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("collectionTypes",2);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, final ResponseCollection data, CommonViewHolder holder){
        if(data.resources ==null) return;
        final ResponseCollection.CollectionResource resource=data.resources;
        holder.setText(R.id.title,resource.title);
        holder.setText(R.id.price,String.format(Locale.getDefault(),resource.price==0?"免费":"￥%s",resource.price));
        holder.setText(R.id.playCount,String.format(Locale.getDefault(),"%s播放",resource.clickRate));
        holder.setText(R.id.authorName,resource.author);
        holder.setText(R.id.commentCount,Integer.toString(resource.commentCounts));
        holder.setText(R.id.collectionCount,Integer.toString(resource.collectionCounts));
        Glide.with(mContext).load(resource.titlePicture).into((ImageView)holder.getView(R.id.cover));
        Glide.with(mContext).load(resource.authorPicture).error(R.mipmap.me_touxiang).into((ImageView)holder.getView(R.id.avatar));


        final CheckBox deleteCheck=holder.getView(R.id.deleteFlag);
        View contentView=holder.getView(R.id.contentView);

        deleteCheck.setOnCheckedChangeListener(null);
        deleteCheck.setChecked(data.isDeleteChecked);
        deleteCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.isDeleteChecked=isChecked;
            }
        });
        if(isShowDelete){
            contentView.setTranslationX(deleteCheck.getWidth()+ DensityUtils.dp2px(mContext,32));
        }
        else{
            contentView.setTranslationX(0);
        }
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowDelete) deleteCheck.setChecked(deleteCheck.isChecked());
            }
        });
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=null;
                if("picture".equals(resource.types)){
                    intent=new Intent(mContext,AudioDetailActivity.class);
                    intent.putExtra("id",resource.id);
                }
                else if("article".equals(resource.types)){
                    intent=new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("id",resource.id);
                }
                else{
                    intent=new Intent(mContext,AudioDetailActivity.class);
                    intent.putExtra("types",resource.types);
                    intent.putExtra("resId",resource.id);
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public List<ResponseCollection> translate(Response<List<ResponseCollection>> result) {
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("pageIndex",1);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("pageIndex",0);
    }

    public void setShowDelete(boolean showDelete){
        isShowDelete=showDelete;
        if(isShowDelete&&mData!=null){
            for(ResponseCollection collection:mData){
                collection.isDeleteChecked=false;
            }
        }
        notifyDataSetChanged();
    }
}