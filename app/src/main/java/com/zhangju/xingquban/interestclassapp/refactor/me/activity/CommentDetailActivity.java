package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.KeyBoardUtils;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.LikerAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.SubCommentAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseComment;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/10/27.
 * 评论详情
 */
@ContentView(R.layout.act_comment_detail)
public class CommentDetailActivity extends FastActivity{
    public static final String ARG_SER_COMMENT="comment";

    @LocalData(ARG_SER_COMMENT)
    ResponseComment mData;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.toComment)
    EditText mComment;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.cover)
    ImageView mRecommendCover;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.ratingBar)
    RatingBar mRatingBar;
    @Bind(R.id.comment)
    TextView mContent;
    @Bind(R.id.recommendTitle)
    TextView mRecommendTitle;
    @Bind(R.id.recommendContent)
    TextView mRecommendContent;
    @Bind(R.id.recommendPrice)
    TextView mRecommendPrice;
    @Bind(R.id.visitCount)
    TextView mVisitCount;
    @Bind(R.id.thumbCount)
    TextView mThumbCount;
    @Bind(R.id.commentCount)
    TextView mCommentCount;
    @Bind(R.id.linkers)
    RecyclerView mLinker;
    @Bind(R.id.subComment)
    RecyclerView mSubComment;
    @Bind(R.id.photos)
    GridView mPhotos;
    ImageAdapter mAdapter;
    LikerAdapter mLikerAdapter;
    SubCommentAdapter mSubCommentAdapter;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        mPhotos.setAdapter(mAdapter=new ImageAdapter(this,new ArrayList<String>(),false));
        requestCommentDetail();
    }

    /**
     * 请求评论详情
     */
    private void requestCommentDetail(){
        Request request=Request.obtain(MeInterface.POST_COMMENT_LIST);
        request.put("id",mData.id);
        request.setListener(new SimpleListener<Response<List<ResponseComment>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseComment>> result) {
                if(result.success){
                    mData=result.data.get(0);
                    mName.setText(mData.customerName);
                    mContent.setText(mData.summary);
                    mRatingBar.setRating(mData.levels);
                    mVisitCount.setText(String.format(Locale.getDefault(),"浏览%s",formatNum(mData.display)));
                    mThumbCount.setText(formatNum(mData.thumbNum));
                    mCommentCount.setText(formatNum(mData.commentCommentsNum));
                    mThumbCount.setCompoundDrawablesRelativeWithIntrinsicBounds(mData.isThumb?R.mipmap.home_data_wdpl_dz_true:R.mipmap.home_data_wdpl_dz_false,0,0,0);
                    if(mData.lessons!=null&&!mData.lessons.isEmpty()){
                        ResponseOrder.Lesson lesson=mData.lessons.get(0);
                        mRecommendTitle.setText(lesson.name);
                        mRecommendContent.setText(lesson.descript);
                        mRecommendPrice.setText(String.format(Locale.getDefault(),"￥%s",lesson.price));
                        Glide.with(CommentDetailActivity.this).load(lesson.picture).into(mRecommendCover);
                    }
                    mAdapter.setData(mData.picList);
                    Glide.with(CommentDetailActivity.this).load(mData.customerPicture).into(mAvatar);
                    mLinker.setLayoutManager(new LinearLayoutManager(CommentDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    mLinker.setAdapter(mLikerAdapter=new LikerAdapter(CommentDetailActivity.this,convertLinkerList(mData)));
                    mSubComment.setAdapter(mSubCommentAdapter=new SubCommentAdapter(CommentDetailActivity.this,mData.commentComments));
                }
            }
        });
        net(request);
    }

    @Bind(R.id.sendComment)
    private void sendComment(){
        String comment=mComment.getText().toString();
        if(TextUtils.isEmpty(comment)) return;
        Request request=Request.obtain(MeInterface.POST_COMMENT_SEND);
        User user=UserManager.getInstance().getUser();
        request.put("customerId",user.id);
        request.put("customerName",user.signame);
        request.put("customerPicture",user.picture);
        request.put("summary",comment);
        request.put("teacherCommentId",mData.id);
        request.setListener(new SimpleListener<Response<ResponseComment.SubComment>>(){

            @Override
            public void onResponseListener(Request r, Response<ResponseComment.SubComment> result) {
                if(result.success) {
                    mSubCommentAdapter.addData(result.data);
                    mComment.setText(null);
                    KeyBoardUtils.closeKeybord(mComment);
                }
            }
        });
        net(request);
    }

    @Bind(R.id.thumbCount)
    private void changeThumb(){
        Request request=Request.obtain(MeInterface.POST_COMMENT_CHANGE_THUMB);
        request.put("customerId",UserManager.getInstance().getUser().id);
        request.put("customerPicture",UserManager.getInstance().getUser().picture);
        request.put("teacherCommentId",mData.id);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success)
                    requestCommentDetail();
            }
        });
        net(request);
    }

    private List<String> convertLinkerList(ResponseComment comment){
        List<String> list=new ArrayList<>();
        if(comment==null||comment.thumbComments==null||comment.thumbComments.isEmpty()) return list;
        for(ResponseComment.ThumbUser liker:comment.thumbComments)
            list.add(liker.customerPicture);
        return list;
    }

    private String formatNum(int num){
        if(num>10000) return num/10000+"万";
        return String.valueOf(num);
    }
}