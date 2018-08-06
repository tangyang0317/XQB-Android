package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.MultiChooseBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.SlideTouchHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CustomerCourseFixCountAdapterForRecycler;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseTeach;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sgfb on 2017/11/3.
 * 课程管理界面
 */
@ContentView(R.layout.act_class_manager)
public class ClassManagerActivity extends FastActivity{
    final int REQ_CHOICE_COURSE=1;
    final int REQ_ADD_COURSE=2;
    final int REQ_DISPLAY_COURSE=3; //显示课程列表可能会有删除事件

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.teachClasses)
    TextView mTeachClass;
    @Bind(R.id.customCourse)
    RecyclerView mCustomCourse;
//    CustomerCourseFixCountAdapter mAdapter;
    CustomerCourseFixCountAdapterForRecycler mAdapter;

    @Override
    protected void alreadyPrepared() {
        mCustomCourse.setLayoutManager(new LinearLayoutManager(this));
        mCustomCourse.setAdapter(mAdapter=new CustomerCourseFixCountAdapterForRecycler(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
//        mCustomCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(ClassManagerActivity.this,EditCourseActivity.class);
//                intent.putExtra(EditCourseActivity.ARG_STR_ID,mAdapter.getItem(position).id);
//                startActivity(intent);
//            }
//        });
        mCustomCourse.addOnItemTouchListener(new SlideTouchHelper(mAdapter));
        mAdapter.setDeleteLessonCallback(new CustomerCourseFixCountAdapterForRecycler.DelLessonCallback() {
            @Override
            public void successDel() {
                requestTeach();
            }
        });
        requestTeach();
    }

    /**
     * 获取教授课程
     */
    private void requestTeach(){
        Request request=Request.obtain("get", MeInterface.GET_TEACH_LIST);
        request.put("id", UserManager.getInstance().getUser().teacherTimeId);
        request.setListener(new SimpleListener<Response<List<ResponseTeach>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseTeach>> result) {
                if(result.success&&result.data!=null&&!result.data.isEmpty()){
                    mTeachClass.setText(result.data.get(0).catagoryName);
                    mAdapter.setData(result.data.get(0).lessons);
                }
            }
        });
        net(request);
    }

    @Bind(R.id.teachClassesLayout)
    private void selectTeachClasses(){
        Intent intent=new Intent(this,CourseChoiceActivity.class);
        String[] selectedTeachClasses=mTeachClass.getText().toString().split(",");
        ArrayList<String> selectedTeachClassList=new ArrayList<>(selectedTeachClasses.length);

        for(String selectedTeachClass:selectedTeachClasses)
            selectedTeachClassList.add(selectedTeachClass);
        intent.putExtra("type",true);
        intent.putExtra(CourseChoiceActivity.ARG_LIST_STR_NAME,selectedTeachClassList);
        startActivityForResult(intent,REQ_CHOICE_COURSE);
    }

    /**
     * 课程添加
     */
    @Bind(R.id.addTeachClass)
    private void addTeachClass(){
        startActivityForResult(EditCourseActivity.class,REQ_ADD_COURSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK) return;
        if(requestCode==REQ_CHOICE_COURSE){
            List<MultiChooseBean> list= (List<MultiChooseBean>) data.getSerializableExtra("subject");
            StringBuilder sb=new StringBuilder();
            if(list!=null&&!list.isEmpty()){
                for(MultiChooseBean bean:list)
                    sb.append(bean.getId()).append(",");
                if(sb.length()>0)
                sb.deleteCharAt(sb.length()-1);
            }
            Request request=Request.obtain(MeInterface.GET_CHANGE_TEACH_COURSES);
            request.put("id",UserManager.getInstance().getUser().teacherTimeId);
            request.put("catagoriesId",sb.toString());
            request.setListener(new SimpleListener<Response>(){

                @Override
                public void onResponseListener(Request r, Response result) {
                    if(result.success)
                        requestTeach();
                }
            });
            net(request);
        }
        else if(requestCode==REQ_ADD_COURSE){
            requestTeach();
        }
        else if(requestCode==REQ_DISPLAY_COURSE){
            mAdapter.setData((List<ResponseOrder.Lesson>) data.getSerializableExtra(CourseListActivity.ARG_SER_COURSE_LIST));
        }
    }

    @Bind(R.id.moreCustomCourse)
    private void moreCustomCourse(){
        Intent intent=new Intent(this,CourseListActivity.class);
        intent.putExtra(CourseListActivity.ARG_SER_COURSE_LIST,(ArrayList<ResponseOrder.Lesson>)mAdapter.getData());
        startActivityForResult(intent,REQ_DISPLAY_COURSE);
    }
}