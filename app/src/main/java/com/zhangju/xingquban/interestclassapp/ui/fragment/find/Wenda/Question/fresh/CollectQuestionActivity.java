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
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.QuestionPopAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion.MyQuestion;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Query;

/**
 * Created by hqf on 2017/11/16.
 * 我的收藏列表
 */
@ContentView(R.layout.fragment_recommend)

public class CollectQuestionActivity extends FastActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshAdapterView.OnListLoadListener {
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

        questionMainAdapter = new QuestionPopAdapter(CollectQuestionActivity.this, questionList,4);
        swiperecommendrefresh.setLayoutManager(new LinearLayoutManager(CollectQuestionActivity.this));
        swiperecommendrefresh.setAdapter(questionMainAdapter);
        swiperecommendrefresh.getScrollView().addOnItemTouchListener(new SlideTouchHelper(questionMainAdapter));
        questionMainAdapter.setOnClickListenner(new QuestionPopAdapter.OnClickListenner() {
            @Override
            public void setonListenner(int pos) {
                QuestionMainBean.AaDataBean dataBean = questionList.get(pos);
                Intent intent = new Intent(CollectQuestionActivity.this, QuestionDetailActivity.class);
                intent.putExtra("data",dataBean);
                intent.putExtra("type",3);
                startActivity(intent);
            }
        });
        questionMainAdapter.setDeleteitem(new QuestionPopAdapter.Deleteitem() {
            @Override
            public void deleteItem(int postion) {
                String id = questionList.get(postion).getQuestion().getId();
                deletecollect(id,postion);
            }
        });

    }

    private void setpubHead() {
        pubHead.setVisibility(View.VISIBLE);
        pubHead.setTitle("我的收藏");
        pubHead.setRightTitle("");
        pubHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //收藏接口处理
    private void deletecollect(String questionId, final int position) {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_COLLECT);
        String token = UserManager.getInstance().getToken();
        String id = UserManager.getInstance().getUser().id;
        request.put("questionId", questionId);
        request.put("customerId", id);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response result) {
                boolean success = result.success;
                if (success) {
                    questionList.remove(position);

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {
        getAskDataList();
    }

    @Override
    public void onListLoad() {
        getAskDataList();
    }

    private void getAskDataList() {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_ADD_COLLECT_ME);
        final String token = UserManager.getInstance().getToken();
        String customerId = UserManager.getInstance().getUser().id;
        request.put("customerId", customerId);
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


}
