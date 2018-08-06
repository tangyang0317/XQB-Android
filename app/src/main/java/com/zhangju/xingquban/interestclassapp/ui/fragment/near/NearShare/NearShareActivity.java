package com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearShare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.db.FastDatabase;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.near.HotSearchAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.SearchRecordAdapter;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.near.SearchRecord;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.DistrictActivity_Copy;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@ContentView(R.layout.activity_near_share)
public class NearShareActivity extends FastActivity {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.mytitle)
    SearchView mytitle;
    @Bind(R.id.sousuo)
    TextView sousuo;
    @Bind(R.id.toolbar)
    RelativeLayout toolbar;
    @Bind(R.id.rec_remen)
    RecyclerView recRemen;
    @Bind(R.id.rec_ss_jilu)
    RecyclerView recSsJilu;
    @Bind(R.id.clear_cache)
    TextView clearCache;

    private HotSearchAdapter hotSearchAdapter;
    private SearchRecordAdapter searchRecordAdapter;
    private NearDataBean mnearDataBean;
    private List<SearchRecord> searchRecordList=new ArrayList<>();
    @Override
    protected void alreadyPrepared() {
        mytitle.setIconifiedByDefault(false);
        mytitle.setQueryHint("搜索老师/机构");
        mytitle.setFocusable(false);
        if(mytitle==null){
            return;
        }else {
            SearchView.SearchAutoComplete textView = ( SearchView.SearchAutoComplete) mytitle.findViewById(R.id.search_src_text);
            textView.setTextSize(13);

        }
       /* searchRecordList=FastDatabase.getDefaultInstance(this).get(SearchRecord.class);

        if(searchRecordList==null)
            searchRecordList=new ArrayList<>();*/
        if(FastDatabase.getDefaultInstance(this).get(SearchRecord.class)==null){
            /*第一次读取数据库为空，不做处理*/
        }else {
            searchRecordList=FastDatabase.getDefaultInstance(this).get(SearchRecord.class); //默认取出整张Person表的数据
        }

        GridLayoutManager gridLayoutManage=new GridLayoutManager(this,3);
        recRemen.setLayoutManager(gridLayoutManage);
        recRemen.addItemDecoration(new RecycleViewDivider(
                NearShareActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.gray)));
        recRemen.addItemDecoration(new RecycleViewDivider(
                NearShareActivity.this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray)));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recSsJilu.setLayoutManager(linearLayoutManager);
        recSsJilu.addItemDecoration(new RecycleViewDivider(NearShareActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.gray)));
        Collections.reverse(searchRecordList);
        searchRecordAdapter=new SearchRecordAdapter(NearShareActivity.this,searchRecordList);
        recSsJilu.setAdapter(searchRecordAdapter);
        requestData();
        initListen();
    }
    private void initListen(){
        searchRecordAdapter.setOnItemClickListener(new SearchRecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mytitle.clearFocus();
                Intent intent = new Intent(NearShareActivity.this, DistrictActivity_Copy.class);
                Bundle bundle = new Bundle();
                bundle.putString("allStringQuery", searchRecordList.get(position).searchContent);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        mytitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mytitle.clearFocus();
                Intent intent = new Intent(NearShareActivity.this, DistrictActivity_Copy.class);
                Bundle bundle = new Bundle();
                bundle.putString("allStringQuery", query);
                intent.putExtras(bundle);
                /*搜索记录存入数据库*/
                SearchRecord searchRecord=new SearchRecord();
                searchRecord.searchContent=query;
                FastDatabase.getDefaultInstance(NearShareActivity.this).saveOrUpdate(searchRecord);
                /*数据库录入完成*/
                startActivity(intent);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void requestData(){
        Location location= LocationManager.getInstance().getLocation();
        int areaId=310000;
        try{
            areaId=Integer.parseInt(location.cityId);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        NetWork.getNear().getNearData(location.longitude,location.latitude, 0, "10",null,null,areaId,null,null,null,null)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    //获取默认数据
    private Observer<NearDataBean> observer = new Observer<NearDataBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(NearDataBean nearDataBean) {
            mnearDataBean=nearDataBean;
            hotSearchAdapter=new HotSearchAdapter(NearShareActivity.this,nearDataBean);
            recRemen.setAdapter(hotSearchAdapter);
            hotSearchAdapter.setOnItemClickListener(new HotSearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(NearShareActivity.this, DistrictActivity_Copy.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("allStringQuery", mnearDataBean.getAaData().get(position).getSigname());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    };
    @Bind(R.id.back)
    private void back(){
        finish();
    }
    @Bind(R.id.clear_cache)
    private void setClearCache(){
        try{
            FastDatabase.getDefaultInstance(this).delete(SearchRecord.class);
            searchRecordList.clear();
            searchRecordList=FastDatabase.getDefaultInstance(this).get(SearchRecord.class); //默认取出整张Person表的数据
            Collections.reverse(searchRecordList);
            searchRecordAdapter=new SearchRecordAdapter(NearShareActivity.this,searchRecordList);
            recSsJilu.setAdapter(searchRecordAdapter);
            ToastUtil.showToast("清除成功");
        }catch (Exception e){
            ToastUtil.showToast("清除失败");
        }

    }
}
