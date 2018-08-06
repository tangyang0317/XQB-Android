package com.zhangju.xingquban.interestclassapp.view.livestream;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;
import com.zhangju.xingquban.interestclassapp.view.textView.MagicTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @Created by  liush on 2017/2/21.
 * @describe ${展示礼物动画}
 */

public class LiveGiftView {
    private LinearLayout mGiftContent;
    private Context      mContext;

    // 动画相关
    private static NumAnim giftNumAnim;
    private static TranslateAnimation mInAnim;
    private static TranslateAnimation mOutAnim;

    private             List<View> giftViewCollection = new ArrayList<View>();
    private             String     mUsername1         = "";
    private             String     mToken             = "";
    public static final String     TAG                = "LiveGiftView";

    /**
     * 创建送礼动画效果实例
     *
     * @param context 上下文
     * @param view    礼物动画的占位布局,必须为LinearLayout
     */
    public LiveGiftView(Context context, View view) {
        this.mContext = context;
        this.mGiftContent = (LinearLayout) view;
        this.mToken = UserManager.getInstance().getToken();
    }

    public void initView() {
        mInAnim = (TranslateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.gift_in);
        mOutAnim = (TranslateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.gift_out);
        giftNumAnim = new NumAnim();
        clearTiming();
    }

    public void showGift(String userIcon, String username, String giftName, String giftIcon) {
//        Log.d(TAG, "showGift: 当前礼物的发送者" + username + giftName + giftIcon);

        View giftView = mGiftContent.findViewWithTag(giftName + "," + mToken);
        if (giftView == null) {/*该用户不在礼物显示列表*/
            if (mGiftContent.getChildCount() > 2) {/*如果正在显示的礼物的个数超过两个，那么就移除最后一次更新时间比较长的*/
                View giftView1 = mGiftContent.getChildAt(0);
                //                CustomRoundView picTv1 = (CustomRoundView) giftView1.findViewById(R.id
                //                        .criv_gift_user_avatar);
                RelativeLayout picTv1 = (RelativeLayout) giftView1.findViewById(R.id.rlparent);

                long lastTime1 = (Long) picTv1.getTag();

                View giftView2 = mGiftContent.getChildAt(1);
                //                CustomRoundView picTv2 = (CustomRoundView) giftView2.findViewById(R.id
                //                        .criv_gift_user_avatar);
                RelativeLayout picTv2 = (RelativeLayout) giftView2.findViewById(R.id.rlparent);

                long lastTime2 = (Long) picTv2.getTag();
                if (lastTime1 > lastTime2) {/*如果第二个View显示的时间比较长*/
                    removeGiftView(1);
                } else {/*如果第一个View显示的时间长*/
                    removeGiftView(0);
                }
            }

            giftView = addGiftView();/*获取礼物的View的布局*/
            giftView.setTag(giftName + "," + mToken);/*设置view标识*/
            RelativeLayout rlGiftHead = (RelativeLayout) giftView.findViewById(R.id.rlparent);
            CustomRoundView crvGiftAvatar = (CustomRoundView) giftView.findViewById(R.id.criv_gift_user_avatar);
            final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.mtv_gift_num);
            TextView tvGiftUserName = (TextView) giftView.findViewById(R.id.tv_gift_user_name);
            TextView tvGiftText = (TextView) giftView.findViewById(R.id.tv_gift_text);
            CustomRoundView crvGiftIcon = (CustomRoundView) giftView.findViewById(R.id.criv_gift_icon);

            /*找到数量控件*/
            giftNum.setText("x1");/*设置礼物数量*/
            rlGiftHead.setTag(System.currentTimeMillis());/*设置时间标记*/
            giftNum.setTag(1);/*给数量控件设置标记*/

            tvGiftUserName.setText(username);
            tvGiftText.setText("送出了" + giftName);
            Glide.with(mContext)
                    .load(giftIcon)
                    .into(crvGiftIcon);
            Glide.with(mContext)
                    .load(userIcon)
                    .into(crvGiftAvatar);

            mGiftContent.addView(giftView);/*将礼物的View添加到礼物的ViewGroup中*/
            mGiftContent.invalidate();/*刷新该view*/
            giftView.startAnimation(mInAnim);/*开始执行显示礼物的动画*/
            mInAnim.setAnimationListener(new Animation.AnimationListener() {/*显示动画的监听*/
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    giftNumAnim.start(giftNum);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {/*该用户在礼物显示列表*/
            //            CustomRoundView crvheadimage = (CustomRoundView) giftView.findViewById(R.id
            //                    .criv_gift_user_avatar);/*找到头像控件*/
            RelativeLayout rlGiftHead = (RelativeLayout) giftView.findViewById(R.id.rlparent);

            MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.mtv_gift_num);
            /*找到数量控件*/
            int showNum = (Integer) giftNum.getTag() + 1;
            giftNum.setText("x" + showNum);
            giftNum.setTag(showNum);
            rlGiftHead.setTag(System.currentTimeMillis());
            giftNumAnim.start(giftNum);
        }
    }

    /**
     * 删除礼物view
     */
    private void removeGiftView(final int index) {
        final View removeView = mGiftContent.getChildAt(index);
        mOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mGiftContent.removeViewAt(index);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeView.startAnimation(mOutAnim);
            }
        });
    }

    /**
     * 添加礼物view,(考虑垃圾回收)
     */
    private View addGiftView() {
        View view = null;
        if (giftViewCollection.size() <= 0) {
            /*如果垃圾回收中没有view,则生成一个*/
            view = LayoutInflater.from(mContext).inflate(R.layout.item_live_gift, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                    .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = 10;
            view.setLayoutParams(lp);
            mGiftContent.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View view) {
                }

                @Override
                public void onViewDetachedFromWindow(View view) {
                    giftViewCollection.add(view);
                }
            });
        } else {
            view = giftViewCollection.get(0);
            giftViewCollection.remove(view);
        }
        return view;
    }

    /**
     * 定时清除礼物
     */
    private void clearTiming() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int count = mGiftContent.getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = mGiftContent.getChildAt(i);
                    RelativeLayout rlGiftHead = (RelativeLayout) view.findViewById(R.id.rlparent);

                    //                    CustomRoundView crvheadimage = (CustomRoundView) view.findViewById(R.id
                    //                            .criv_gift_user_avatar);
                    long nowtime = System.currentTimeMillis();
                    long upTime = (Long) rlGiftHead.getTag();
                    if ((nowtime - upTime) >= 5000) {
                        removeGiftView(i);
                        return;
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 3000);
    }

}
