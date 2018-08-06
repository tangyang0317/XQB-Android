package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeRecylerAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgFunctionAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/3.
 * 机构管理
 */
@ContentView(R.layout.act_org_manager)
public class OrgManagerActivity extends FastActivity{
    List<Pair<Integer,String>> mFunctions=new ArrayList<>();
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.gridView)
    RecyclerView mGrid;
    OrgFunctionAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        User user=UserManager.getInstance().getUser();
//        Pair<Integer,String> classManagerPair=Pair.create(R.mipmap.me_jg_kcap,"课程安排");

        mName.setText(user.signame);
        Glide.with(this).load(user.picture).dontAnimate().dontTransform().into(mAvatar);
        mGrid.setLayoutManager(new GridLayoutManager(this,3));
        mFunctions.add(Pair.create(R.mipmap.me_jg_kcgl,"课程管理"));
//        mFunctions.add(classManagerPair);
        mFunctions.add(Pair.create(R.mipmap.me_jg_xcgl,"相册管理"));
        mFunctions.add(Pair.create(R.mipmap.me_jg_spkc,"视频课程"));
        mFunctions.add(Pair.create(R.mipmap.me_jg_jgjj,"机构简介"));
//        if(!user.degree.isTeacher)
//            mFunctions.remove(classManagerPair);
        mGrid.setAdapter(mAdapter=new OrgFunctionAdapter(this,mFunctions));
        mAdapter.setmListener(new HomeRecylerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Pair<Integer,String> pair=mFunctions.get(position);
                switch (pair.second){
                    case "课程管理":openClassManager();break;
//                    case "课程安排":openCourseManager();break;
                    case "相册管理":openImageManager();break;
                    case "视频课程":
                        openVideoManager();break;
                    case "机构简介":
                        openOrgProfile();break;
                    default:System.out.println("点击了一个不存在的功能模块");break;
                }
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openClassManager(){
        startActivity(ClassManagerActivity.class);
    }

    private void openCourseManager(){

    }

    private void openImageManager(){
        startActivity(AlbumManageActivity.class);
    }

    private void openVideoManager(){
        startActivity(CourseVideoActivity.class);
    }

    private void openOrgProfile(){
        startActivity(OrgProfileActivity.class);
    }
}
