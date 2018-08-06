package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapter;
import com.fastlib.adapter.SingleAdapter;
import com.fastlib.base.OldViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.DensityUtils;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoAlbum;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 机构管理中的相册管理适配器
 */
public class AlbumManageAdapter extends SingleAdapter<ResponseVideoAlbum,Response<List<ResponseVideoAlbum>>> {
    private DeleteFlagChangeCallback mCallback;
    private boolean isShowDelete;
    private int mType;

    public AlbumManageAdapter(Context context,int type){
        super(context, R.layout.item_album_manage,false);
        mType=type;
        mRequest.put("isPic",mType);
        refresh();
    }

    @Override
    public Request generateRequest(){
        Request request=Request.obtain(MeInterface.POST_MEDIA_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("type",1);
        request.put("iDisplayStart",0);
        request.put("iDisplayLength",20); //20个item差不多可以塞满一页
        return request;
    }

    @Override
    public void binding(int position, final ResponseVideoAlbum data, OldViewHolder holder){
        View videoIcon=holder.getView(R.id.videoIcon);
        final CheckBox delete=holder.getView(R.id.delete);

        Glide.with(mContext).load(mType==0?data.picVideo:data.videoTitlePic).into((ImageView)holder.getView(R.id.image));
        videoIcon.setVisibility(mType==2?View.VISIBLE:View.GONE);
        holder.setVisibility(R.id.deleteLayout,isShowDelete?View.VISIBLE: View.GONE);
        delete.setOnCheckedChangeListener(null);
        delete.setChecked(data.deleteFlag);
        holder.setOnClickListener(R.id.deleteLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteFlag=!delete.isChecked();
                delete.setChecked(data.deleteFlag);
                if(mCallback!=null) mCallback.callback(data,delete.isChecked());
            }
        });
    }

    @Override
    public List<ResponseVideoAlbum> translate(Response<List<ResponseVideoAlbum>> result) {
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("iDisplayStart",20);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("iDisplayStart",0);
    }

    public void setShowDelete(boolean deleteFlag){
        isShowDelete=deleteFlag;
        if(!deleteFlag){
            for(ResponseVideoAlbum data:mData)
                data.deleteFlag=false;
        }
        notifyDataSetChanged();
    }

    public DeleteFlagChangeCallback getmCallback() {
        return mCallback;
    }

    public void setmCallback(DeleteFlagChangeCallback mCallback) {
        this.mCallback = mCallback;
    }

    public void deleteSelectImage(){
        final List<ResponseVideoAlbum> needDeleteList=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        for(ResponseVideoAlbum data:mData) {
            if (data.deleteFlag) {
                needDeleteList.add(data);
                sb.append(data.id).append(",");
            }
        }
        if(sb.length()<=0) return;
        sb.deleteCharAt(sb.length()-1);
        Request request=new Request(MeInterface.POST_MEDIA_DELETE);
        request.put("ids",sb.toString());
        request.setListener(new SimpleListener<Response>(){
            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    mData.removeAll(needDeleteList);
                    notifyDataSetChanged();
                }
            }
        });
        request.start();
    }

    public interface DeleteFlagChangeCallback{
        void callback(ResponseVideoAlbum videoAlbum,boolean isChecked);
    }
}