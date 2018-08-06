package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.app.FastDialog;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseResource;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 资源管理适配器
 */
public class ResourceManageAdapter extends SingleAdapterForRecycler<ResponseResource,Response<List<ResponseResource>>>{
    private int isCharge;

    public ResourceManageAdapter(Context context,int isCharge){
        super(context, R.layout.item_resource,false);
        this.isCharge=isCharge;
        mRequest.put("isCharge",isCharge);
    }

    @Override
    public Request generateRequest(){
        Request request=new Request(MeInterface.POST_RESOURCE_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
//        request.put("pageIndex",1);
//        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, final ResponseResource data, final CommonViewHolder holder){
        GridView photos=holder.getView(R.id.photos);
        ImageAdapter adapter= (ImageAdapter) photos.getTag();
        final List<String> list=new ArrayList<>();

        if("video".equals(data.types)){
            list.add(data.titlePicture);
        }
        else{
            if(data.pictureList!=null)
                for(ResponseResource.Picture picture:data.pictureList)
                    list.add(picture.fileUrl);
        }
        if(adapter==null){
            adapter=new ImageAdapter(mContext,list,false);
            photos.setTag(adapter);
        }
        else adapter.setData(list);
        photos.setAdapter(adapter);
        holder.setText(R.id.title,data.title);
        holder.setText(R.id.date,data.addUserTime);
        holder.setText(R.id.visitCount,Integer.toString(data.clickRate));
        holder.setText(R.id.commentCount,Integer.toString(data.commentCounts));
        holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastDialog.showMessageDialog("删除之后无法恢复，请谨慎操作！",true).show(((AppCompatActivity) mContext).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestDeleteResources(data.id);
                    }
                });
            }
        });
        final View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent;
                if("picture".equals(data.types)){
                    intent=new Intent(mContext,PicDetailActivity.class);
                    intent.putExtra("id",data.id);
                }
                else if("article".equals(data.types)){
                    intent=new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("id",data.id);
                }
                else{
                    intent=new Intent(mContext,AudioDetailActivity.class);
                    intent.putExtra("types",data.types);
                    intent.putExtra("resId",data.id);
                }
                mContext.startActivity(intent);
            }
        };
        holder.setOnClickListener(listener);
        adapter.setOnItemClickListener(listener);
        photos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onClick(view);
            }
        });
    }

    @Override
    public List<ResponseResource> translate(Response<List<ResponseResource>> result){
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        isMore=false;
//        request.increment("pageIndex",1);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        isMore=false;
//        request.put("pageIndex",1);
    }

    /**
     * 删除指定id资源
     * @param id 资源id
     */
    private void requestDeleteResources(String id){
        Request request=new Request(MeInterface.POST_DELETE_RESOURCE);
        request.put("id",id);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success) refresh();
            }
        });
        request.start();
    }
}