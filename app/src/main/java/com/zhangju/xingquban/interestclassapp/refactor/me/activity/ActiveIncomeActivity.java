package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActiveIncome;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/15.
 * 活动收入
 */
@ContentView(R.layout.act_active_income)
public class ActiveIncomeActivity extends FastActivity{
    public static final String ARG_STR_ACTIVE_ID="activeId"; //如果不存在就是总收入

    @LocalData(ARG_STR_ACTIVE_ID)
    String mActiveId;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.income)
    TextView mIncomeCount;
    @Bind(R.id.settle)
    TextView mSettle;
    @Bind(R.id.pendingSettle)
    TextView mPendingSettle;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestActiveIncome();
    }

    /**
     * 请求收入接口
     */
    private void requestActiveIncome(){
        Request request=Request.obtain(MeInterface.POST_ACTIVE_INCOME);
        request.put("customerId", UserManager.getInstance().getUser().id);
        if(!TextUtils.isEmpty(mActiveId))
            request.put("activityId",mActiveId);
        request.setListener(new SimpleListener<Response<ResponseActiveIncome>>(){

            @Override
            public void onResponseListener(Request r, Response<ResponseActiveIncome> result){
                DecimalFormat df=new DecimalFormat("##.##");
                if(result.success){
                    mIncomeCount.setText(String.format(Locale.getDefault(),"￥ %s",df.format(result.data.counts)));
                    mSettle.setText(String.format(Locale.getDefault(),"￥ %s",df.format(result.data.finalCash)));
                    mPendingSettle.setText(String.format(Locale.getDefault(),"￥ %s",df.format(result.data.marchCash)));
                }
            }
        });
        net(request);
    }
}