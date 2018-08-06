package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollLinearLayoutManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.QuestionDetailAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion.MyQuestion;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by hqf 2017//11/16
 * 我的问答--最新和推荐的详情
 */
@ContentView(R.layout.activity_question_detail)
public class QuestionDetailActivity
        extends FastActivity {

    @BindView(R.id.item_question_title)
    TextView       itemQuestionTitle;
    @BindView(R.id.tv_content)
    TextView       tvContent;
    @BindView(R.id.item_question_recyclerView)
    RecyclerView   itemQuestionRecyclerView;
    @BindView(R.id.item_question_type)
    TextView       itemQuestionType;
    @BindView(R.id.item_question_time)
    TextView       itemQuestionTime;
    @BindView(R.id.item_question_name)
    TextView       itemQuestionName;
    @BindView(R.id.item_question_count)
    TextView       itemQuestionCount;
    @BindView(R.id.quesion_detail_recyclerview)
    RecyclerView   quesionDetailRecyclerview;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.linear_question)
    LinearLayout   linearQuestion;
    @BindView(R.id.image_collect)
    ImageView      imageCollect;
    @BindView(R.id.collection_click)
    RelativeLayout collectionClick;
    @BindView(R.id.image_edit)
    ImageView      imageEdit;
    @BindView(R.id.iv_share)
    ImageView      mIvShare;
    boolean isCollect;

    String questionId;//提问id
    private List<QuestionMainBean.AaDataBean> mDetailList = new ArrayList<>();//我的问答详情数据集
    private QuestionDetailAdapter detailAdapter;

    QuestionMainBean.AaDataBean serializableExtra;
    private MyShareDialog mMyShareDialog;

    @Override
    protected void alreadyPrepared() {
        setQuestionDetailAdapter();
        serializableExtra = (QuestionMainBean.AaDataBean) getIntent().getSerializableExtra("data");
        int type = getIntent().getIntExtra("type", 0);// 2代表从我的回答过来
        try {
            if (serializableExtra != null) {
                if (type == 2) {
                    questionId = serializableExtra.getQuestionTitle().getId();//问题id
                    isCollect = serializableExtra.isCollection();
                } else if (type == 3) {
                    questionId = serializableExtra.getQuestion().getId();
                    isCollect = serializableExtra.isCollection();

                } else {
                    questionId = serializableExtra.getId();
                    isCollect = serializableExtra.isCollection();
                }
                getTQuestionDetail();//获取提问数据
                getTwoAnswerData();//获取列表详情
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void getTQuestionDetail() {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_RECOMMEND);
        String token = UserManager.getInstance().getToken();
        request.put("id", questionId);
        request.put("isRelease", "1");
        request.put("isNew", "1");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<QuestionMainBean>() {
            @Override
            public void onResponseListener(Request r, QuestionMainBean result) {
                boolean success = result.isSuccess();
                if (success) {
                    try {
                        List<QuestionMainBean.AaDataBean> data = result.getAaData();
                        if (data != null && data.size() > 0) {
                            QuestionMainBean.AaDataBean serializableExtras = data.get(0);
                            serializableExtra = serializableExtras;

                            String title = serializableExtra.getTitle() == null ? "" : serializableExtra.getTitle();//标题
                            String qExplain = serializableExtra.getQExplain() == null ? "" : serializableExtra.getQExplain();//说明
                            List<QuestionMainBean.AaDataBean.PicStrBeans> picList = serializableExtra.getPicStr();
                            String label = serializableExtra.getLabel() == null ? "" : serializableExtra.getLabel();//标签
                            String addUserTime = serializableExtra.getAddUserTime() == null ? "" : serializableExtra
                                    .getAddUserTime();
                            String time = addUserTime.substring(0, addUserTime.indexOf(" "));//时间
                            int answers = serializableExtra.getAnswers();//回复数
                            String authorName = serializableExtra.getAuthorName() == null ? "" : serializableExtra
                                    .getAuthorName();//来自于xx
                            isCollect = serializableExtra.isCollection();//是否收藏

                            setCollectState();

                            itemQuestionTitle.setText(title);
                            tvContent.setText(qExplain);
                            itemQuestionType.setText(label);
                            itemQuestionTime.setText(time);
                            itemQuestionCount.setText(answers + "个回答");
                            itemQuestionName.setText("来自于" + authorName + "提问");

                            List<String> mImageList = new ArrayList<>();
                            for (QuestionMainBean.AaDataBean.PicStrBeans beans : picList) {
                                mImageList.add(beans.getPicture());
                            }
                            //图片
                            ImageAdapter imageAdapter = new ImageAdapter(QuestionDetailActivity.this, mImageList);
                            ScrollGridManager manager = new ScrollGridManager(QuestionDetailActivity.this, 3);
                            manager.setScrollEnabled(false);
                            itemQuestionRecyclerView.setLayoutManager(manager);
                            itemQuestionRecyclerView.setAdapter(imageAdapter);

                            mMyShareDialog = new MyShareDialog(mContext);
                            String shareImage = null;
                            if (picList.size() > 0) {
                                shareImage = picList.get(0).getPicture();
                            }
                            String shareUrl = MyApp.URL + "/share/#/questions?id=" + questionId;
                            String shareContent = serializableExtra.getQExplain();
                            String shareTitle = serializableExtra.getTitle();
                            mMyShareDialog.initShare(shareImage, shareUrl, shareContent, shareTitle);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }

    boolean isFirst = true;

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirst) {
            getTwoAnswerData();
            isFirst = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isFirst = false;
    }

    private void setQuestionDetailAdapter() {
        detailAdapter = new QuestionDetailAdapter(QuestionDetailActivity.this, mDetailList);
        ScrollLinearLayoutManager manager = new ScrollLinearLayoutManager(QuestionDetailActivity.this);
        manager.setScrollEnabled(false);
        quesionDetailRecyclerview.setLayoutManager(manager);
        quesionDetailRecyclerview.setAdapter(detailAdapter);

    }

    /**
     * 问题id
     *
     * @param
     */
    private void getTwoAnswerData() {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_TWO_ANSWER);
        String token = UserManager.getInstance().getToken();
        request.put("questionId", questionId);
        request.put("isRelease", "1");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<QuestionMainBean>() {

            @Override
            public void onResponseListener(Request r, QuestionMainBean result) {
                boolean success = result.isSuccess();
                if (success) {
                    List<QuestionMainBean.AaDataBean> data = result.getAaData();
                    mDetailList.clear();
                    if (data != null && data.size() > 0) {
                        mDetailList.addAll(data);
                    }
                }
                detailAdapter.notifyDataSetChanged();

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

    @OnClick({R.id.back, R.id.collection_click, R.id.image_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.back:
                finish();
                break;
            //收藏处理
            case R.id.collection_click:
                addcollect();
                break;
            //编辑
            case R.id.image_edit:
                Intent intent = new Intent(QuestionDetailActivity.this, MyAnswerActivity.class);
                intent.putExtra("data", serializableExtra);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
        }
    }

    //是否收藏状态显示
    private void setCollectState() {
        imageCollect.setImageResource(isCollect ? R.drawable.comment_iwantcomment_star_selected : R.drawable
                .comment_iwantcomment_star_normal);
    }

    //收藏接口处理
    private void addcollect() {
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
                    if (isCollect) {
                        isCollect = false;

                    } else {
                        isCollect = true;

                    }
                    setCollectState();
                }

            }


            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }
}
