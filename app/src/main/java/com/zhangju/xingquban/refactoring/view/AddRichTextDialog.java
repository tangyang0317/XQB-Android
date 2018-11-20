package com.zhangju.xingquban.refactoring.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fastlib.app.BottomDialog;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.refactoring.interfacs.BottomSheetDialogListener;

/**
 * @packageName com.zhangju.xingquban.refactoring.view
 * @FileName AddRichTextDialog
 * @Author tangyang
 * @DATE 2018/10/31
 **/
public class AddRichTextDialog extends BottomDialog {

    public static final int REQ_ADD_FEATURE_TEXT = 1;
    private Object mHost;
    private BottomSheetDialogListener mListener;
    private int mPosition = 0;

    public static AddRichTextDialog getInstance(AppCompatActivity activity) {
        AddRichTextDialog dialog = new AddRichTextDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_INT_COLOR, activity.getResources().getColor(R.color.translucent_dialog));
        bundle.putInt(ARG_INT_LAYOUT_ID, R.layout.dialog_publish_active_add_feature);
        dialog.setArguments(bundle);
        return dialog;
    }

    public void show(int position, Object host, BottomSheetDialogListener listener) {
        mPosition = position;
        mHost = host;
        mListener = listener;
        FragmentActivity activity;
        if (host instanceof AppCompatActivity) activity = (AppCompatActivity) host;
        else if (host instanceof Fragment) activity = ((Fragment) host).getActivity();
        else throw new IllegalArgumentException("host 的参数必须是Activity或者Fragment");
        activity.getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, this)
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
                mListener.onTextClickListenner();
                finish();
            }
        });
        v.findViewById(R.id.selectImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onImageClickListenner();
                finish();
            }
        });
    }

    private void finish() {
        getFragmentManager().beginTransaction()
                .remove(AddRichTextDialog.this)
                .commit();
    }
}
