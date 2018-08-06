package com.zhangju.xingquban.interestclassapp.ui.fragment.home.wydp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.adapter.near.CommentZanPicAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NearJigouCommentAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.SecondCommentAdapter;
import com.zhangju.xingquban.interestclassapp.bean.Comment_all;
import com.zhangju.xingquban.interestclassapp.bean.UserBean.SecondCommend;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_home_data_wdpl_xq)
public class HomeDataWdplXq extends FastActivity {
    public static final String ARG_STRING_COMMENTID = "commnet_id";

    @LocalData(ARG_STRING_COMMENTID)
    String commentid;
    @Bind(R.id.comment_rec)
    RecyclerView comment_rec;
    @Bind(R.id.home_data_wdplXq_Head)
    PublicHead homeDataWdplXqHead;
    @Bind(R.id.comment_rec)
    RecyclerView commentRec;
    @Bind(R.id.zan_num)
    TextView zanNum;
    @Bind(R.id.comment_num)
    TextView commentNum;
    @Bind(R.id.home_data_wddp_dzRecycler)
    RecyclerView homeDataWddpDzRecycler;
    @Bind(R.id.home_data_wddp_plRecycler)
    RecyclerView homeDataWddpPlRecycler;
    @Bind(R.id.edt_comment)
    EditText commnetSummary;

    @Bind(R.id.img_zan)
    ImageView imgzan;

    private NearJigouCommentAdapter nearJigouCommentAdapter;
    private CommentZanPicAdapter zanAdapter;
    private SecondCommentAdapter secondAdapter;


    public void setHomeDataWdplXqHead() {

        homeDataWdplXqHead.setTitle("评论详情");
        homeDataWdplXqHead.setShow(true, false, false);
        homeDataWdplXqHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        homeDataWddpPlRecycler.addItemDecoration(new RecycleViewDivider(
                HomeDataWdplXq.this, LinearLayoutManager.VERTICAL, 33, getResources().getColor(R.color.white)));
    }

    @Override
    protected void alreadyPrepared() {
        setHomeDataWdplXqHead();
        requestCommnet();
    }

    private void requestCommnet() {
        Request request = Request.obtain(INear.POST_COMMNET_ALL);
        request.put("id", commentid);


        request.setListener(new SimpleListener<Response<List<Comment_all>>>() {
            @Override
            public void onResponseListener(Request r, Response<List<Comment_all>> result) {

                bindDataToView(result);

            }
        });
        net(request);
    }

    private void bindDataToView(Response<List<Comment_all>> response) {
        ArrayList<String> zanpic=new ArrayList<>();
        /*设置点赞*/
        if(response.data.get(0).isIsThumb()){
            Glide.with(HomeDataWdplXq.this).load(R.drawable.dianzan).into(imgzan);
        }else {
            Glide.with(HomeDataWdplXq.this).load(R.mipmap.home_data_wdpl_dz_false).into(imgzan);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeDataWdplXq.this,LinearLayoutManager.VERTICAL, false);
        comment_rec.setLayoutManager(linearLayoutManager);
        nearJigouCommentAdapter = new NearJigouCommentAdapter(HomeDataWdplXq.this, response.data.get(0));
        comment_rec.setAdapter(nearJigouCommentAdapter);
        zanNum.setText(response.data.get(0).getThumbNum()+"");
        commentNum.setText(response.data.get(0).getCommentCommentsNum()+"");

        for (int i = 0; i <response.data.get(0).getThumbNum(); i++) {
            zanpic.add(response.data.get(0).getThumbComments().get(i).getCustomerPicture());
        }
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomeDataWdplXq.this,LinearLayoutManager.HORIZONTAL, false);
        /*點讚*/
        homeDataWddpDzRecycler.setLayoutManager(linearLayoutManager2);
        zanAdapter=new CommentZanPicAdapter(HomeDataWdplXq.this,zanpic);
        homeDataWddpDzRecycler.setAdapter(zanAdapter);
        /*二級評論*/
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(HomeDataWdplXq.this,LinearLayoutManager.VERTICAL, false) ;
        homeDataWddpPlRecycler.setLayoutManager(linearLayoutManager3);

        secondAdapter=new SecondCommentAdapter(HomeDataWdplXq.this,response.data.get(0));
        homeDataWddpPlRecycler.setAdapter(secondAdapter);
    }

    @Bind(R.id.commit_commnet)
    private void commit(){
        if(UserManager.getInstance().isLogin()){
            if(TextUtils.isEmpty(commnetSummary.getText().toString())){
                commnetSummary.setError("请输入内容");
                commnetSummary.requestFocus();
                return;
            }
            Request request = Request.obtain(INear.POST_COMMEMT_COMM);
            request.put("customerId", UserManager.getInstance().getUser().id);
            request.put("customerName", UserManager.getInstance().getUser().username);
            request.put("customerPicture", UserManager.getInstance().getUser().picture);
            request.put("teacherCommentId", commentid);//资源id
            request.put("summary",commnetSummary.getText().toString());//评论内容
            request.setListener(new SimpleListener<Response<SecondCommend>>() {
                @Override
                public void onResponseListener(Request r, Response<SecondCommend> result) {
                    ToastUtil.showToast("发布评论成功");
                    commnetSummary.clearFocus();
                    commnetSummary.setText(null);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(commnetSummary.getWindowToken(), 0) ;
                    requestCommnet();
                }
            });
            net(request);
        }else
            startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity.class));

    }
    @Bind(R.id.img_zan)
    private void commitZan(){
        if (UserManager.getInstance().isLogin()){
            Request request = Request.obtain(INear.POST_COMMENT_ZAN);
            request.put("customerId", UserManager.getInstance().getUser().id);
            request.put("customerPicture", UserManager.getInstance().getUser().picture);
            request.put("teacherCommentId", commentid);//资源id
            request.put("jsessionid", UserManager.getInstance().getUser().id);
            request.setListener(new SimpleListener<Response<SecondCommend>>() {
                @Override
                public void onResponseListener(Request r, Response<SecondCommend> result) {

                    requestCommnet();
                }
            });
            net(request);
        }else {
            startActivity(new Intent(this, com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity.class));
        }

    }
}
