package com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fastlib.app.BottomDialog;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.FastFragment;
import com.fastlib.app.PhotoResultListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddFeatureTextActivity;

/**
 * Created by sgfb on 2017/11/1.
 * 增加活动特色底部弹框
 */
public class AddFeatureDialog extends BottomDialog{
    public static final int REQ_ADD_FEATURE_TEXT=1;
    private Object mHost;
    private PhotoResultListener mListener;
    private int mPosition=0;

    public static AddFeatureDialog getInstance(AppCompatActivity activity){
        AddFeatureDialog dialog=new AddFeatureDialog();
        Bundle bundle=new Bundle();

        bundle.putInt(ARG_INT_COLOR,activity.getResources().getColor(R.color.translucent_dialog));
        bundle.putInt(ARG_INT_LAYOUT_ID, R.layout.dialog_publish_active_add_feature);
        dialog.setArguments(bundle);
        return dialog;
    }

    public void show(int position,Object host,PhotoResultListener listener){
        mPosition=position;
        mHost=host;
        mListener=listener;
        FragmentActivity activity;
        if(host instanceof AppCompatActivity) activity= (AppCompatActivity) host;
        else if(host instanceof Fragment) activity=((Fragment)host).getActivity();
        else throw new IllegalArgumentException("host 的参数必须是Activity或者Fragment");
        activity.getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,this)
                .commit();
    }

    @Override
    protected void bindView(View v) {
        v.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        v.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        v.findViewById(R.id.selectText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddFeatureTextActivity.class);
                intent.putExtra(AddFeatureTextActivity.ARG_RES_INT_POSITION,mPosition);
                if(mHost instanceof Activity)
                    ((Activity)mHost).startActivityForResult(intent,REQ_ADD_FEATURE_TEXT);
                else if(mHost instanceof Fragment)
                    ((Fragment)mHost).startActivityForResult(intent,REQ_ADD_FEATURE_TEXT);
                else throw new IllegalArgumentException("host 的参数必须是Activity或者Fragment");
                finish();
            }
        });
        v.findViewById(R.id.selectImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastDialog.showListDialog(new String[]{"拍照","从相册中选择"}).show(getChildFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0) {
                            if(mHost instanceof FastActivity)
                                ((FastActivity)mHost).openCamera(mListener);
                            else if(mHost instanceof FastFragment)
                                ((FastFragment)mHost).openCamera(mListener);
                            else throw new IllegalArgumentException("host 的参数必须是Activity或者Fragment");
                        }
                        else {
                            if(mHost instanceof FastActivity)
                                ((FastActivity)mHost).openAlbum(mListener);
                            else if(mHost instanceof FastFragment)
                                ((FastFragment)mHost).openAlbum(mListener);
                            else throw new IllegalArgumentException("host 的参数必须是Activity或者Fragment");
                        }
                        finish();
                    }
                });
            }
        });
    }

    private void finish(){
        getFragmentManager().beginTransaction()
                .remove(AddFeatureDialog.this)
                .commit();
    }
}
