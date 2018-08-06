package com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fastlib.app.BottomDialog;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/1.
 * 分享
 */
public class ShareDialog extends BottomDialog{

    public static void showNow(AppCompatActivity activity){
        ShareDialog dialog=new ShareDialog();
        Bundle bundle=new Bundle();

        bundle.putInt(ARG_INT_LAYOUT_ID, R.layout.dialog_publish_active_done_invite);
        bundle.putInt(ARG_INT_COLOR,R.color.translucent_dialog);
        dialog.setArguments(bundle);
        activity.getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,dialog)
                .commit();
    }

    @Override
    protected void bindView(View v) {
        v.findViewById(R.id.shareToWechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.shareToMoments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.shareToXl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.shareToQQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.shareToBean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.generateQrImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .remove(ShareDialog.this)
                        .commit();
            }
        });
    }
}
