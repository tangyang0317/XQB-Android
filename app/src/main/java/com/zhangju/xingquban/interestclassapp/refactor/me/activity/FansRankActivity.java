package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.google.gson.internal.LinkedTreeMap;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseAttach;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FansRankAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseFansReward;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/8.
 * 粉丝贡献榜
 */
@ContentView(R.layout.act_fans_rank)
public class FansRankActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.coinCount)
    TextView mCoinCount;
    @Bind(R.id.list)
    RecyclerView mList;
    FansRankAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter=new FansRankAdapter(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestFansRank();
    }

    /**
     * 请求粉丝贡献列表
     */
    private void requestFansRank(){
        Request request=Request.obtain(MeInterface.POST_FANS_REWARD);
        request.setListener(new SimpleListener<ResponseAttach<List<ResponseFansReward>,Object>>(){

            @Override
            public void onResponseListener(Request r, ResponseAttach<List<ResponseFansReward>,Object> result) {
                if(result.success) {
                    try{
                        LinkedTreeMap<String,Double> treeMap= (LinkedTreeMap<String, Double>) result.attachData;
                        Double stdCoin=treeMap.get("stdCoin");
                        mCoinCount.setText(String.format(Locale.getDefault(),"收到的兴趣币数：%s",stdCoin.toString()));
                    }catch (ClassCastException e){
                        e.printStackTrace();
                    }
                    mAdapter.setData(result.data);
                }
                else N.showShort(FansRankActivity.this,"请求粉丝贡献失败");
            }
        });
        net(request);
    }
}
