package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.zxing.activity.CaptureActivity;

/**
 * Created by sgfb on 2017/11/15.
 * 活动主页面
 */
@ContentView(R.layout.act_active)
public class ActiveActivity extends FastActivity{
    final int REQ_QRCODE=1;

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.teacherLine)
    View mTeacherLine;
    @Bind(R.id.studentLine)
    View mStudentLine;
    @Bind(R.id.typeHint)
    TextView mTypeHint;

    @Override
    protected void alreadyPrepared(){
        User user= UserManager.getInstance().getUser();

        mName.setText(user.signame);
        Glide.with(this).load(user.picture).into(mAvatar);
        if(user.degree!=null){
            if(user.degree.isTeacher||user.degree.isOrganization){
                mTypeHint.setText("主办方");
                mTeacherLine.setVisibility(View.VISIBLE);
            }
            else{
                mTypeHint.setText("参与者");
                mStudentLine.setVisibility(View.VISIBLE);
            }
        }
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.publish)
    private void startPublish(){
        startActivity(ActivePublishedActivity.class);
    }

    @Bind(R.id.activeIncome)
    private void toActiveIncome(){
        startActivity(ActiveIncomeActivity.class);
    }

    @Bind(R.id.startCheckTicket)
    private void startCheckTicket(){
        Intent intent=new Intent(ActiveActivity.this,CaptureActivity.class);
        intent.putExtra("type",true);
        startActivityForResult(intent,REQ_QRCODE);
//        startActivityForResult(CaptureActivity.class,REQ_QRCODE);
    }

    @Bind(R.id.tickets)
    private void openMyTickets(){
        startActivity(MyTicketActivity.class);
    }

    @Bind(R.id.takePartIn)
    private void openTakePartIn(){
        startActivity(ActiveTakePartInActivity.class);
    }

    @Bind(R.id.focus)
    private void openFocus(){
        startActivity(ActiveFocusActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_QRCODE&&resultCode==RESULT_OK){
            requestCheckTicketCode(data.getStringExtra(CaptureActivity.RES_STR_CODE));
        }
    }

    /**
     * 验证二维码字符
     * @param code 扫描成功返回的字符
     */
    private void requestCheckTicketCode(String code){
        System.out.println("扫码回调:"+code);
        Request request=Request.obtain(MeInterface.POST_CHECK_TICKET);
        request.put("qcode",code);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success) N.showShort(ActiveActivity.this, "验证成功");
            }
        });
        net(request);
    }
}