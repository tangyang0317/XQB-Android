package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.SendVideoActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp.SendNewsActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp.SendPictureActivity;
import com.zhangju.xingquban.interestclassapp.util.BlurKit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sgfb on 2017/11/6.
 * 点击增加资源时弹出的选项
 */
@ContentView(R.layout.frag_select_add_source)
public class AddResourceSelectFragment extends FastFragment {

    @BindView(R.id.main)
    RelativeLayout main;
    Unbinder unbinder;

    public static AddResourceSelectFragment getInstance() {
        Bundle bundle = new Bundle();
        AddResourceSelectFragment fragment = new AddResourceSelectFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared() {
        BlurKit.init(getActivity());
        Bitmap bitmap = BlurKit.getInstance().getBitmap();
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        main.setBackground(bd);
    }


    @Bind(R.id.addVideo)
    private void addVideo() {
        startActivity(SendVideoActivity.class);
        finish();
    }

    @Bind(R.id.addImage)
    private void addImage() {
        startActivity(SendPictureActivity.class);
        finish();
    }

    @Bind(R.id.addInfo)
    private void addInfo() {
        startActivity(SendNewsActivity.class);
        finish();
    }

    @Bind(R.id.cancel)
    private void cancel() {
        finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}