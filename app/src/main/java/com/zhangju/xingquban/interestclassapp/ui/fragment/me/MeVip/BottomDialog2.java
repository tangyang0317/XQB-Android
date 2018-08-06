package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


/**
 * Created by sgfb on 17/7/25.
 */
public class BottomDialog2 extends Fragment {
    public static final String ARG_INT_LAYOUT_ID = "layoutId";
    public static final String ARG_INT_IN_ANIM = "inAnim";
    public static final String ARG_INT_BG_COLOR = "bgColor";

    private View mBlackBg;
    private View mContentView;
    private Callback mCallback;

    /**
     * 获取底部弹窗实例
     *
     * @param layoutId  布局id
     * @param enterAnim 进入时内容布局的动画id
     * @param bgColor   背景色码
     * @return 底部弹窗组件
     */
    public static BottomDialog2 getInstance(int layoutId, int enterAnim, int bgColor, Callback callback) {
        BottomDialog2 fragment = new BottomDialog2();
        Bundle bundle = new Bundle();

        bundle.putInt(ARG_INT_LAYOUT_ID, layoutId);
        bundle.putInt(ARG_INT_IN_ANIM, enterAnim);
        bundle.putInt(ARG_INT_BG_COLOR, bgColor);
        fragment.setArguments(bundle);
        fragment.mCallback = callback;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout root = new FrameLayout(getContext());
        mBlackBg = new View(getContext());
        mContentView = inflater.inflate(getArguments().getInt(ARG_INT_LAYOUT_ID), null);
        FrameLayout.LayoutParams blackBgLp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams contentLp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        contentLp.gravity = Gravity.BOTTOM;
        mBlackBg.setLayoutParams(blackBgLp);
        mBlackBg.setBackgroundColor(getArguments().getInt(ARG_INT_BG_COLOR));
        mBlackBg.setAnimation(generateAlphaAnim());
        mContentView.setAnimation(AnimationUtils.loadAnimation(getContext(), getArguments().getInt(ARG_INT_IN_ANIM)));
        mContentView.setLayoutParams(contentLp);

        if (mCallback != null)
            mCallback.bind(mContentView);
        root.addView(mBlackBg);
        root.addView(mContentView);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBlackBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .remove(BottomDialog2.this)
                        .commit();
            }
        });
        return root;
    }

    public void show(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, this)
                .commit();
    }

    public Animation generateAlphaAnim() {
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(230);
        animation.setFillAfter(true);
        return animation;
    }

    public void setRemove() {
        getFragmentManager().beginTransaction()
                .remove(BottomDialog2.this)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getFragmentManager().beginTransaction()
                            .remove(BottomDialog2.this)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    public interface Callback {
        void bind(View v);
    }
}