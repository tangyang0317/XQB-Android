package com.zhangju.xingquban.interestclassapp.ui.fragment.near;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerAlbumVideoAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoAlbum;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.ShipinBofangActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sgfb on 2017/11/6.
 * 机构管理中的相册管理内容模块
 */

public class NearAlbumVideoManageFragment extends Fragment {

    @BindView(R.id.upload)
    TextView upload;
    @BindView(R.id.delete)
    TextView delete;
    @BindView(R.id.bottomView)
    FrameLayout bottomView;
    @BindView(R.id.grid)
    GridView grid;
    Unbinder unbinder;
    private Bundle bundle;
    private HomeDataTeacherBean.AaDataBean mfiles;
    private ArrayList<String> piclist=new ArrayList<>();
    private ArrayList<String> videoname=new ArrayList<>();
    private ArrayList<String> videourl=new ArrayList<>();
    private NaerAlbumVideoAdapter naerAlbumVideoAdapter;

    public void initData() {

    }


    public int getMyLayout() {
        return R.layout.frag_album_manage;
    }


    private void initView() {
        bottomView.setVisibility(View.GONE);
        final Request request=Request.obtain(MeInterface.POST_MEDIA_LIST);
        request.put("customerId",mfiles.getCustomerId());
        request.put("type",1);
        request.put("isPic",2);
        request.put("pageIndex",0);
        request.put("pageSize",1000); //20个item差不多可以塞满一页
        request.setListener(new SimpleListener<Response<List<ResponseVideoAlbum>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseVideoAlbum>> result) {
                if (result.success){
                    for (int i = 0; i <result.data.size() ; i++) {

                            piclist.add(result.data.get(i).videoTitlePic);
                            videoname.add(result.data.get(i).title);
                            videourl.add(result.data.get(i).picVideo);
                    }
                    naerAlbumVideoAdapter=new NaerAlbumVideoAdapter(getActivity(),piclist);

                    grid.setAdapter(naerAlbumVideoAdapter);
                }
            }
        });
        request.start();

    }
    public static NearAlbumVideoManageFragment getInstance(HomeDataTeacherBean.AaDataBean files){
        Bundle bundle=new Bundle();
        NearAlbumVideoManageFragment fragment=new NearAlbumVideoManageFragment();
        bundle.putSerializable("files",files);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void initListener() {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), ShipinBofangActivity.class);
                intent.putExtra(ShipinBofangActivity.ARG_STRING_NAME,videoname.get(position));
                intent.putExtra(ShipinBofangActivity.ARG_STRING_URL,videourl.get(position));
                startActivity(intent);
            }
        });

    }


    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_album_manage, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        bundle=this.getArguments();
        mfiles= (HomeDataTeacherBean.AaDataBean) bundle.getSerializable("files");

        initView();
        initListener();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}