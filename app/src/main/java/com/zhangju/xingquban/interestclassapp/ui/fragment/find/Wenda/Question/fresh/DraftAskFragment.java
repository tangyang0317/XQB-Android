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
import butterknife.Unbinder;

/**
 * Created by hqf on 2017/11/17.
 * 我的问答--问答草稿列表
 */
@ContentView(R.layout.fragment_recommend)
public class DraftAskFragment extends FastFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshAdapterView.OnListLoadListener {
    @BindView(R.id.pub_head)
    PublicHead pubHead;
    @BindView(R.id.swiperecommendrefresh)
    SwipeRefreshRecyclerView swiperecommendrefresh;
    Unbinder unbinder;


    private List<QuestionMainBean.AaDataBean> questionList = new ArrayList<>();
    private QuestionPopAdapter questionMainAdapter;

    boolean isRefresh;

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) {
            isRefresh = false;
            swiperecommendrefresh.autoRefresh();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isRefresh = true;

    }

    @Override
    protected void alreadyPrepared() {
        setAskQuestionAdapter();
    }

    private void setAskQuestionAdapter() {
        swiperecommendrefresh.setOnRefreshListener(this);
        swiperecommendrefresh.setOnListLoadListener(this);
        swiperecommendrefresh.autoRefresh();

        questionMainAdapter = new QuestionPopAdapter(getActivity(), questionList, 3);
        swiperecommendrefresh.setLayoutManager(new LinearLayoutManager(getActivity()));
        swiperecommendrefresh.setAdapter(questionMainAdapter);
        swiperecommendrefresh.getScrollView().addOnItemTouchListener(new SlideTouchHelper(questionMainAdapter));
        questionMainAdapter.setOnClickListenner(new QuestionPopAdapter.OnClickListenner() {
            @Override
            public void setonListenner(int pos) {
                QuestionMainBean.AaDataBean questionTitle = questionList.get(pos);
                Intent intent = new Intent(getActivity(), MyAnswerActivity.class);
                intent.putExtra("data", questionTitle);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

        questionMainAdapter.setDeleteitem(new QuestionPopAdapter.Deleteitem() {
            @Override
            public void deleteItem(int postion) {
                String id = questionList.get(postion).getId();
                toDeleteitem(id, postion);
            }
        });



    }


    private void toDeleteitem(String id, final int pos) {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_DELETE_ANSWER);
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
    public void onRefresh() {
        getAskDataList();
    }

    @Override
    public void onListLoad() {
        getAskDataList();
    }

    private void getAskDataList() {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_TWO_ANSWER);
        final String token = UserManager.getInstance().getToken();
        String customerId = UserManager.getInstance().getUser().id;
        request.put("customerId", customerId);
        request.put("isRelease", 2);
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
