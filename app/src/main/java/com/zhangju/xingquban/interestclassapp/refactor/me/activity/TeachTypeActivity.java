package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/6.
 * 授课方式选择
 */
@ContentView(R.layout.act_teach_method_type)
public class TeachTypeActivity extends FastActivity{
    public static final String RES_INT_TEACH_METHOD_TYPE ="teachMethodType"; //返回授课方式 1,学生上门 2家教上门 3协商地点

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.studentToSchool)
    private void selectStudentToSchool(){
        itemSelected(1);
    }

    @Bind(R.id.teacherToHome)
    private void selectTeacherToHome(){
        itemSelected(2);
    }

    @Bind(R.id.appointAddress)
    private void selectAppointAddress(){
        itemSelected(3);
    }

    private void itemSelected(int type){
        Intent intent=new Intent();
        intent.putExtra(RES_INT_TEACH_METHOD_TYPE,type);
        setResult(RESULT_OK,intent);
        finish();
    }
}
