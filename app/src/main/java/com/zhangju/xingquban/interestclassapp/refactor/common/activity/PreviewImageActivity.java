package com.zhangju.xingquban.interestclassapp.refactor.common.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.base.AbsPreviewImageActivity;

import java.io.File;

/**
 * Created by sgfb on 2017/10/26.
 * 多图预览
 */
public class PreviewImageActivity extends AbsPreviewImageActivity{
    public static final String ARG_BOOL_IS_LOCAL="isLocal";
    private boolean isLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLocal=getIntent().getBooleanExtra(ARG_BOOL_IS_LOCAL,false);
    }

    @Override
    protected void loadImage(ImageView imageView, String data){
        Glide.with(this).load(isLocal?new File(data):data).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void indexChanged(int index){

    }
}
