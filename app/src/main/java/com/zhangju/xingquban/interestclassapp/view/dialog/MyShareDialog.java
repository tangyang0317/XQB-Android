package com.zhangju.xingquban.interestclassapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Created by wxy
 * on 2017/12/12.
 */

public class MyShareDialog
        implements View.OnClickListener {
    private Context mContext;
    private String mShareUrl = "";
    private Dialog mDialog;
    private View   mWeChatBtn;
    private View   mWeChatCircleBtn;
    private View   mSinaBtn;
    private View   mQQBtn;
    private View   mQQZoneBtn;
    private String mImageUrl;
    private String mTitleUrl;
    private String mText;
    private String mTitle;

    public MyShareDialog(Context context) {
        this(context, R.style.my_dialog);
        this.mContext = context;
    }

    public MyShareDialog(Context context, int themeResId) {
        mDialog = new Dialog(context, themeResId);
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout rootLl = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.share_layout, null);
        mDialog.setContentView(rootLl);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        dialogWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        rootLl.findViewById(R.id.tv_share_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mWeChatBtn = rootLl.findViewById(R.id.rl_wechat);
        mWeChatCircleBtn = rootLl.findViewById(R.id.rl_wechat_zone);
        mSinaBtn = rootLl.findViewById(R.id.rl_sina);
        mQQBtn = rootLl.findViewById(R.id.rl_qq);
        mQQZoneBtn = rootLl.findViewById(R.id.rl_qq_zone);
        mWeChatBtn.setOnClickListener(this);
        mWeChatCircleBtn.setOnClickListener(this);
        mSinaBtn.setOnClickListener(this);
        mQQBtn.setOnClickListener(this);
        mQQZoneBtn.setOnClickListener(this);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //将设置好的属性set回去
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        String url = mShareUrl;
        String name = null;
        switch (v.getId()) {
            case R.id.rl_wechat:
                name = Wechat.NAME;
                break;
            case R.id.rl_wechat_zone:
                name = WechatMoments.NAME;
                break;
            case R.id.rl_qq:
                name = QQ.NAME;
                break;
            case R.id.rl_qq_zone:
                name = QZone.NAME;
                break;
            case R.id.rl_sina:
                name = SinaWeibo.NAME;
                break;
            default:
                break;
        }

        OnekeyShare oks = new OnekeyShare();
        oks.setImageUrl(mImageUrl == null ? UrlUtils.URL_LOGO : mImageUrl);
        oks.setTitleUrl(mTitleUrl == null ? "" : mTitleUrl);
        oks.setText(mText == null ? "兴趣班" : mText);
        oks.setTitle(mTitle == null ? "兴趣班" : mTitle);
        oks.setUrl(mTitleUrl);
        oks.setPlatform(name);
        oks.show(mContext);
    }


    public MyShareDialog initShare(String imageUrl, String titleUrl, String text, String title) {
        mImageUrl = imageUrl;
        mTitleUrl = titleUrl;
        mText = text;
        mTitle = title;
        return this;
    }

    public MyShareDialog setShareUrl(String url) {
        mShareUrl = url;
        return this;
    }


    public Dialog getDialog() {
        return mDialog;
    }

    public MyShareDialog show() {
        mDialog.show();
        return this;
    }


    public View getWeChatBtn() {
        return mWeChatBtn;
    }

    public View getWeChatCircleBtn() {
        return mWeChatCircleBtn;
    }


    public View getSinaBtn() {
        return mSinaBtn;
    }


    public View getQQBtn() {
        return mQQBtn;
    }

    public View getQQZoneBtn() {
        return mQQZoneBtn;
    }

    public void dismiss() {
        mDialog.dismiss();
    }

}
