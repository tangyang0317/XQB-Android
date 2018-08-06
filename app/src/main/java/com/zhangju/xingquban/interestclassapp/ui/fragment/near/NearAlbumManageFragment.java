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
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerAlbumPictureAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoAlbum;
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

public class NearAlbumManageFragment extends Fragment {

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
    private NaerAlbumPictureAdapter pictureAdapter;
    private ArrayList<String> piclist=new ArrayList<>();

    public void initData() {

    }

    public void initView() {

    }
    public static NearAlbumManageFragment getInstance(HomeDataTeacherBean.AaDataBean files){
        Bundle bundle=new Bundle();
        NearAlbumManageFragment fragment=new NearAlbumManageFragment();
        bundle.putSerializable("files",files);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void initListener() {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL,false);
                intent.putExtra(PreviewImageActivity.ARG_INT_INDEX,position);
                intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES,piclist);
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
        bindDataToView();
        initListener();
        return rootView;
    }
    private void bindDataToView(){
        bottomView.setVisibility(View.GONE);
        Request request=Request.obtain(MeInterface.POST_MEDIA_LIST);
        request.put("customerId", mfiles.getCustomerId());
        request.put("type",1);
        request.put("isPic",0);
        request.put("pageIndex",0);
        request.put("pageSize",1000); //20个item差不多可以塞满一页
        request.setListener(new SimpleListener<Response<List<ResponseVideoAlbum>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<ResponseVideoAlbum>> result) {
                if (result.success){
                    for (int i = 0; i <result.data.size() ; i++) {
                            piclist.add(result.data.get(i).picVideo);
                    }
                    pictureAdapter=new NaerAlbumPictureAdapter(getActivity(),piclist);

                    grid.setAdapter(pictureAdapter);
                }

            }
        });
        request.start();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}