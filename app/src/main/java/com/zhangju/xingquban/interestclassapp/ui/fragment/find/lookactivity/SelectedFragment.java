package com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity.bean.LooklistDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hqf on 2017/11/22.
 * 找活动--精选fragment
 */
@ContentView(R.layout.look_hot_frag)
public class SelectedFragment extends FastFragment {
    @BindView(R.id.recyclerview_look_activity)
    RecyclerView recyclerviewLookActivity;
    Unbinder unbinder;

    @Override
    protected void alreadyPrepared() {
        getSelectedList();

    }

    private void getSelectedList() {
        final Request request = Request.obtain(LookActiviInterface.POST_LOOK_SELECTED);
        String token = UserManager.getInstance().getToken();
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<LooklistDataBean>() {
            @Override
            public void onResponseListener(Request r, LooklistDataBean result) {
                boolean success = result.isSuccess();

                try {
                    if (success) {
                        List<LooklistDataBean.AaDataBean> data = result.getAaData();

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);

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
