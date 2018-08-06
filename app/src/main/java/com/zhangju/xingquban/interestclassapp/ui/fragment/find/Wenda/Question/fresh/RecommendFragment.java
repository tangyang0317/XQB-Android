package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.QuestionMainAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion.MyQuestion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author hqf
 *         create by 2017/11/16
 *         发现--我的问答推荐Fragment
 */
@ContentView(R.layout.fragment_recommend)
public class RecommendFragment extends FastFragment implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swiperecommendrefresh)
    SwipeRefreshRecyclerView swiperecommendrefresh;
    private QuestionMainAdapter questionMainAdapter;
    private List<QuestionMainBean.AaDataBean> mainBeanList=new ArrayList<>();


    Unbinder unbinder;
    boolean isFirst=true;
    @Override
    protected void alreadyPrepared() {
        setRecommendAdapter();
    }

    private void setRecommendAdapter() {
        swiperecommendrefresh.setOnRefreshListener(this);
        swiperecommendrefresh.setOnListLoadListener(this);
        swiperecommendrefresh.autoRefresh();

        questionMainAdapter=new QuestionMainAdapter(getActivity(),mainBeanList);
        swiperecommendrefresh.setLayoutManager(new LinearLayoutManager(getActivity()));
        swiperecommendrefresh.setAdapter(questionMainAdapter);

        questionMainAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                QuestionMainBean.AaDataBean dataBean = mainBeanList.get(position);
                Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
                intent.putExtra("data",dataBean);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst){
            swiperecommendrefresh.autoRefresh();
            isFirst=true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isFirst=false;
    }
    @Override
    public void onListLoad() {
        getRecommendData();

    }

    @Override
    public void onRefresh() {
        getRecommendData();
    }
    /**
     * 获取推荐数据
     */
    private void getRecommendData() {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_RECOMMEND);
        String token = UserManager.getInstance().getToken();
        request.put("isRelease", 1);
        request.put("isNew", "0");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<QuestionMainBean>() {

            @Override
            public void onResponseListener(Request r, QuestionMainBean result) {
                boolean success = result.isSuccess();
                if (success){
                    List<QuestionMainBean.AaDataBean> data = result.getAaData();
                    mainBeanList.clear();
                    if (data!=null&&data.size()>0){
                        mainBeanList.addAll(data);
                    }
                }
                questionMainAdapter.notifyDataSetChanged();
                setSwipeEnable();

            }



            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                setSwipeEnable();

            }
        });
        net(request);

    }

    private void setSwipeEnable() {
        swiperecommendrefresh.setRefreshing(false);
        swiperecommendrefresh.setLoading(false);
        swiperecommendrefresh.setEnabledLoad(true);
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
