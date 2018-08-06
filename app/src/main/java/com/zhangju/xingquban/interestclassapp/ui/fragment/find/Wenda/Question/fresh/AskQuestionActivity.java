package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.SlideTouchHelper;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.QuestionMainAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.QuestionPopAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion.MyQuestion;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/16.
 * 我的问答--我的提问列表
 */

@ContentView(R.layout.fragment_recommend)
public class AskQuestionActivity extends FastActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.pub_head)
    PublicHead pubHead;
    @BindView(R.id.swiperecommendrefresh)
    SwipeRefreshRecyclerView swiperecommendrefresh;

    private List<QuestionMainBean.AaDataBean> questionList = new ArrayList<>();
    private QuestionPopAdapter questionMainAdapter;

    boolean isRefresh;

    @Override
    protected void alreadyPrepared() {
        setpubHead();
        setAskQuestionAdapter();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh){
            isRefresh=false;
            swiperecommendrefresh.autoRefresh();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRefresh=true;
    }


    private void setAskQuestionAdapter() {
        swiperecommendrefresh.setOnRefreshListener(this);
        swiperecommendrefresh.setOnListLoadListener(this);
        swiperecommendrefresh.autoRefresh();


        questionMainAdapter = new QuestionPopAdapter(AskQuestionActivity.this, questionList,1);
        swiperecommendrefresh.setLayoutManager(new LinearLayoutManager(AskQuestionActivity.this));
        swiperecommendrefresh.setAdapter(questionMainAdapter);
        swiperecommendrefresh.getScrollView().addOnItemTouchListener(new SlideTouchHelper(questionMainAdapter));

        questionMainAdapter.setOnClickListenner(new QuestionPopAdapter.OnClickListenner() {
            @Override
            public void setonListenner(int pos) {
                QuestionMainBean.AaDataBean dataBean = questionList.get(pos);
                Intent intent = new Intent(AskQuestionActivity.this, QuestionDetailActivity.class);
                intent.putExtra("data",dataBean);
                startActivity(intent);
            }
        });

        questionMainAdapter.setDeleteitem(new QuestionPopAdapter.Deleteitem() {
            @Override
            public void deleteItem(int postion) {
                String id = questionList.get(postion).getId();
                toDeleteitem(id,postion);
            }
        });

    }


    private void toDeleteitem(String id, final int pos) {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_DELETE_Question);
        final String token = UserManager.getInstance().getToken();

        request.put("id", id);

        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<QuestionMainBean>() {

            @Override
            public void onResponseListener(Request r, QuestionMainBean result) {
                if (result.isSuccess()) {
                    questionList.remove(pos);
                }
                questionMainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }
    @Override
    public void onListLoad() {
        getAskDataList();
    }

    @Override
    public void onRefresh() {
        getAskDataList();
    }
    private void getAskDataList() {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_RECOMMEND);
        final String token = UserManager.getInstance().getToken();
        String customerId = UserManager.getInstance().getUser().id;
        request.put("customerId", customerId);
        request.put("isRelease", 1);
        request.put("isNew", 1);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<QuestionMainBean>() {

            @Override
            public void onResponseListener(Request r, QuestionMainBean result) {
                boolean success = result.isSuccess();
                if (success) {
                    List<QuestionMainBean.AaDataBean> data = result.getAaData();
                    questionList.clear();
                    if (data != null && data.size() > 0) {
                        questionList.addAll(data);
                    }
                }
                questionMainAdapter.notifyDataSetChanged();
                setSwipeEnable();

            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }

    private void setSwipeEnable() {
        swiperecommendrefresh.setRefreshing(false);
        swiperecommendrefresh.setLoading(false);
        swiperecommendrefresh.setEnabledLoad(true);
    }

    private void setpubHead() {
        pubHead.setVisibility(View.VISIBLE);
        pubHead.setTitle("我的提问");
        pubHead.setRightTitle("");
        pubHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


}
