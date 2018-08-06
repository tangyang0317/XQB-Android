package com.zhangju.xingquban.interestclassapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;

/**
 * Created by ydw on 2017/11/15.
 */

public class ShareDialog extends Dialog{

    private Context mContext;
    private Display display;


    RelativeLayout tv_share_qq_Zone;
    RelativeLayout tv_share_wechat;
    RelativeLayout tv_share_wechat_zone;
    RelativeLayout tv_share_qq;
    RelativeLayout tv_share_weibo;
    TextView tv_share_cancel;


    public ShareDialog(@NonNull Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public ShareDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.ActionSheetDialogStyle);
        setContentView( R.layout.share_layout);

        tv_share_qq_Zone = (RelativeLayout) findViewById(R.id.rl_qq_zone);
        tv_share_wechat = (RelativeLayout) findViewById(R.id.rl_wechat);
        tv_share_wechat_zone = (RelativeLayout) findViewById(R.id.rl_wechat_zone);
        tv_share_qq = (RelativeLayout) findViewById(R.id.rl_qq);
        tv_share_weibo = (RelativeLayout) findViewById(R.id.rl_sina);
        tv_share_cancel = (TextView) findViewById(R.id.tv_share_cancel);
        Window dialogWindow = this.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);


        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);


        tv_share_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog.this.dismiss();
            }
        });
    }



    public void QQZone(View.OnClickListener clickListener){
        tv_share_qq_Zone.setOnClickListener(clickListener);
    }
    public void wechat(View.OnClickListener clickListener){
        tv_share_wechat.setOnClickListener(clickListener);
    }
    public void wechat_zone(View.OnClickListener clickListener){
        tv_share_wechat_zone.setOnClickListener(clickListener);
    }
    public void  qq(View.OnClickListener clickListener){
        tv_share_qq.setOnClickListener(clickListener);
    }
    public void weibo(View.OnClickListener clickListener){
        tv_share_weibo.setOnClickListener(clickListener);
    }
    public void cacle(View.OnClickListener clickListener){
        this.dismiss();
    }
}
