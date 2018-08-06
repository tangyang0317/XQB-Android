package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fastlib.app.BottomDialog;
import com.zhangju.xingquban.R;

/**
 * Created by Administrator on 2017/12/13.
 * 订单更多弹出式
 */
public class OrderMoreDialog extends BottomDialog{
    static final String ARG_STR_HINT_MESSAGE="hintMessage";
    private View.OnClickListener mListener;

    public static OrderMoreDialog getInstance(Context context,String hintMessage){
        Bundle bundle=new Bundle();
        bundle.putInt(BottomDialog.ARG_INT_LAYOUT_ID,R.layout.dialog_bottom_order_more);
        bundle.putInt(BottomDialog.ARG_INT_COLOR,context.getResources().getColor(R.color.translucent_dialog));
        bundle.putString(ARG_STR_HINT_MESSAGE,hintMessage);
        OrderMoreDialog dialog=new OrderMoreDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    protected void bindView(View v){
        TextView hintTv= (TextView) v.findViewById(R.id.cancelOrder);
        hintTv.setText(getArguments().getString(ARG_STR_HINT_MESSAGE));
        v.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        v.findViewById(R.id.cancelOrder).setOnClickListener(mListener);
    }

    public void setListener(View.OnClickListener listener){
        mListener=listener;
    }
}
