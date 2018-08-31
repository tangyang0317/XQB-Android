package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.lidroid.xutils.HttpUtils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.bean.live.MultiChooseBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.SlideTouchHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CustomerCourseFixCountAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CustomerCourseFixCountAdapterForRecycler;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseTeach;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.refactoring.adapter.ClassListAdapter;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhangju.xingquban.refactoring.entity.CategoryBean;
import com.zhangju.xingquban.refactoring.entity.LessonBean;
import com.zhangju.xingquban.refactoring.entity.LessonsManagerBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sgfb on 2017/11/3.
 * 课程管理界面
 */
@ContentView(R.layout.act_class_manager)
public class ClassManagerActivity extends FastActivity implements View.OnClickListener {
    final int REQ_CHOICE_COURSE = 1;
    final int REQ_ADD_COURSE = 2;
    final int REQ_DISPLAY_COURSE = 3; //显示课程列表可能会有删除事件

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.classListRecycleView)
    RecyclerView classListRecycleView;

    private View head;
    private TextView mTeachClass;
    private LinearLayout teachClassesLayout;
    private ClassListAdapter classListAdapter;

    @Override
    protected void alreadyPrepared() {
        classListRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        classListAdapter = new ClassListAdapter();
        head = LayoutInflater.from(this).inflate(R.layout.view_lesson_manage_header, null);
        mTeachClass = head.findViewById(R.id.teachClasses);
        teachClassesLayout = head.findViewById(R.id.teachClassesLayout);
        classListAdapter.addHeaderView(head);
        classListRecycleView.setAdapter(classListAdapter);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(EditCourseActivity.class, REQ_ADD_COURSE);
            }
        });

        /**
         * item 点击事件
         */
        classListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LessonBean lessonBean = (LessonBean) adapter.getItem(position);
                Intent intent = new Intent(ClassManagerActivity.this, EditCourseActivity.class);
                intent.putExtra(EditCourseActivity.ARG_STR_ID, lessonBean.getId());
                startActivity(intent);
            }
        });


        /***
         * item 子View 点击事件
         */
        classListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final LessonBean lessonBean = (LessonBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.delLessonTxt:
                        /*******删除课程*******/
                        new MaterialDialog.Builder(ClassManagerActivity.this)
                                .title("课程删除后不能恢复，你确定要删除该课程吗？")
                                .positiveText("确定")
                                .negativeText("取消")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                        delLesson(lessonBean.getId());
                                    }
                                })
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();

                        break;
                }
            }
        });
        teachClassesLayout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestTeach();
    }

    /**
     * 获取教授课程
     */
    private void requestTeach() {
        NetWork.getMe().getLessonManager(UserManager.getInstance().getUser().teacherTimeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /****
     * 删除课程
     * @param id
     */
    private void delLesson(final String id) {
        NetWork.getMe().deleteLesson(id, UserManager.getInstance().getUser().teacherTimeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseBean<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponseBean<String> stringBaseResponseBean) {
                        ToastUtil.showToast("课程删除成功");
                        if (classListAdapter != null && classListAdapter.getData() != null) {
                            for (int i = 0; i < classListAdapter.getData().size(); i++) {
                                if (classListAdapter.getData().get(i).getId().equals(id)) {
                                    classListAdapter.getData().remove(i);
                                    classListAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
    }

    /***
     * 获取类别数据
     */
    Observer<BaseResponseBean<List<LessonsManagerBean>>> observer = new Observer<BaseResponseBean<List<LessonsManagerBean>>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(BaseResponseBean<List<LessonsManagerBean>> baseResponseBean) {
            if (baseResponseBean != null && baseResponseBean.getAaData() != null) {
                List<LessonBean> lessonBeans = baseResponseBean.getAaData().get(0).getLessons();
                String teachClasses = baseResponseBean.getAaData().get(0).getCatagoryName();
                if (lessonBeans != null && lessonBeans.size() > 0) {
                    classListAdapter.setNewData(lessonBeans);
                    mTeachClass.setText(teachClasses);
                }
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == REQ_CHOICE_COURSE) {
            List<MultiChooseBean> list = (List<MultiChooseBean>) data.getSerializableExtra("subject");
            StringBuilder sb = new StringBuilder();
            if (list != null && !list.isEmpty()) {
                for (MultiChooseBean bean : list)
                    sb.append(bean.getId()).append(",");
                if (sb.length() > 0)
                    sb.deleteCharAt(sb.length() - 1);
            }
            Request request = Request.obtain(MeInterface.GET_CHANGE_TEACH_COURSES);
            request.put("id", UserManager.getInstance().getUser().teacherTimeId);
            request.put("catagoriesId", sb.toString());
            request.setListener(new SimpleListener<Response>() {

                @Override
                public void onResponseListener(Request r, Response result) {
                    if (result.success)
                        requestTeach();
                }
            });
            net(request);
        } else if (requestCode == REQ_ADD_COURSE) {
            requestTeach();
        } else if (requestCode == REQ_DISPLAY_COURSE) {
//            mAdapter.setData((List<ResponseOrder.Lesson>) data.getSerializableExtra(CourseListActivity.ARG_SER_COURSE_LIST));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == teachClassesLayout) {
            Intent intent = new Intent(this, CourseChoiceActivity.class);
            String[] selectedTeachClasses = mTeachClass.getText().toString().split(",");
            ArrayList<String> selectedTeachClassList = new ArrayList<>(selectedTeachClasses.length);

            for (String selectedTeachClass : selectedTeachClasses)
                selectedTeachClassList.add(selectedTeachClass);
            intent.putExtra("type", true);
            intent.putExtra(CourseChoiceActivity.ARG_LIST_STR_NAME, selectedTeachClassList);
            startActivityForResult(intent, REQ_CHOICE_COURSE);
        }
    }

//    @Bind(R.id.moreCustomCourse)
//    private void moreCustomCourse() {
//        Intent intent = new Intent(this, CourseListActivity.class);
//        intent.putExtra(CourseListActivity.ARG_SER_COURSE_LIST, (ArrayList<ResponseOrder.Lesson>) mAdapter.getData());
//        startActivityForResult(intent, REQ_DISPLAY_COURSE);
//    }
}