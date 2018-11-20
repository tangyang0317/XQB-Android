package com.zhangju.xingquban.interestclassapp.ui.fragment.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessage;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.FindResource;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyQuestionMain;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.mediscover.FindLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.DistrictActivity_Copy;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.test.TestMyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by zsl on 2017/6/14.
 */
//
public class FindFragment extends BaseFragment {

    @BindView(R.id.find_resource)
    RelativeLayout findResource;
    @BindView(R.id.find_teacher)
    RelativeLayout findTeacher;
    @BindView(R.id.find_institution)
    RelativeLayout findInstitution;
    @BindView(R.id.find_live)
    RelativeLayout findLive;
    @BindView(R.id.find_wenda)
    RelativeLayout findWenda;
    @BindView(R.id.find_activity)
    RelativeLayout findActivity;
    @BindView(R.id.find_message)
    RelativeLayout findMessage;
    Unbinder unbinder;
    @BindView(R.id.tv_new_look)
    TextView tvNewLook;


    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.find_fragment;

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.find_resource, R.id.tv_new_look, R.id.find_teacher, R.id.find_institution, R.id.find_live, R.id.find_wenda,
            R.id.find_activity, R.id.find_message})
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), DistrictActivity_Copy.class);
        switch (v.getId()) {
            //资源
            case R.id.find_resource:
                startActivity(new Intent(getActivity(), FindResource.class));
                break;

            //找老师
            case R.id.find_teacher:
                Bundle bundle = new Bundle();
                bundle.putString("bannerType","10");
                bundle.putInt("degreeid", 1);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //找机构
            case R.id.find_institution:
                Bundle bundle1 = new Bundle();
                bundle1.putString("bannerType","11");
                bundle1.putInt("degreeid", 2);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;

            //找直播
            case R.id.find_live:

                ToastUtil.showToast("系统更新维护，暂不可用");

//                if (!UserManager.getInstance().isLogin()) {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
//                } else {
//                    startActivity(new Intent(getActivity(), FindLiveActivity.class));
//                }

                break;
            //我的问答
            case R.id.find_wenda:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), MyQuestionMain.class));
                }

                break;
            //找活动
            case R.id.find_activity:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    N.showShort(getContext(),"敬请期待");
//                    startActivity(new Intent(getActivity(), FindActiveActivity.class));
                }

                break;
            //信息平台
            case R.id.find_message:
                startActivity(new Intent(getActivity(), FindMessage.class));
                break;
            //测试
            case R.id.tv_new_look:
                startActivity(new Intent(getActivity(), TestMyActivity.class));
                break;
        }
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
