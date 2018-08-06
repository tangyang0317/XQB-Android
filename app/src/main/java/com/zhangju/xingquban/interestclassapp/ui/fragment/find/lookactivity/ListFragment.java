package com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hqf on 2017/11/22.
 * 找活动--列表fragment
 */
@ContentView(R.layout.look_hot_frag)
public class ListFragment extends FastFragment {
    @BindView(R.id.recyclerview_look_activity)
    RecyclerView recyclerviewLookActivity;
    Unbinder unbinder;

    @Override
    protected void alreadyPrepared() {

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
