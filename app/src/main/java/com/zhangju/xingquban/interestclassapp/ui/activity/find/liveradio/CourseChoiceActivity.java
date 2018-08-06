package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.LiveSubAdapter;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.bean.live.MultiChooseBean;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//直播科目筛选
public class CourseChoiceActivity extends AppCompatActivity {
    public static final String ARG_LIST_STR_NAME = "listName";

    private String TAG = " 直播科目筛选";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.live_subject_list)
    RecyclerView liveSubjectList;

    private LiveSubAdapter liveSubAdapter;
    private List<NearSubjectBean.AaDataBean> subjectList = new ArrayList<>();//科目数据
    private LoadingDialog dialog;

    private String typeName;
    private String typeId;
    private String subname;
    private String subId;

    private boolean chooseType;//选择模式处理  单选    多选

    ArrayList<MultiChooseBean> multiChooseBeanList = new ArrayList<>();//多选回调


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_choice);
        ButterKnife.bind(this);

        dialog = new LoadingDialog(CourseChoiceActivity.this);
        dialog.setLoading("请稍后...");

        chooseType = getIntent().getBooleanExtra("type", false);

        getsubCondition();//获取科目数据
        setSubChoiceAdapter();//设置科目Adapter

    }

    private void setSubChoiceAdapter() {
        liveSubAdapter = new LiveSubAdapter(CourseChoiceActivity.this, subjectList, chooseType);
        liveSubjectList.setLayoutManager(new LinearLayoutManager(CourseChoiceActivity.this));
        liveSubjectList.setAdapter(liveSubAdapter);
        liveSubAdapter.setSecondPosition(new LiveSubAdapter.SecondPosition() {
            @Override
            public void getSecondPosition(int position, int secondPos) {
                liveSubAdapter.setFirstSelected(position, secondPos);
            }
        });

        //数据获取
        liveSubAdapter.setSubData(new LiveSubAdapter.SubData() {
            @Override
            public void getSubData(String firstName, String firstId, String secondName, String secondId) {
                typeName = firstName;
                typeId = firstId;
                subname = secondName;
                subId = secondId;
            }
        });
    }


    @OnClick({R.id.back, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.back:
                finish();
                break;
            //提交科目
            case R.id.commit:
                //选择判断回调
                if (!chooseType) {
                    if (subname != null && !subname.isEmpty()) {
                        Intent intent = new Intent();
                        intent.putExtra("typeName", typeName);
                        intent.putExtra("typeId", typeId);
                        intent.putExtra("subName", subname);
                        intent.putExtra("subId", subId);
                        setResult(RESULT_OK, intent);
                    }
                } else {
                    //多选回调
                    for (int i = 0; i < subjectList.size(); i++) {
                        List<NearSubjectBean.AaDataBean.ChildsBean> childs = subjectList.get(i).getChilds();

                        for (NearSubjectBean.AaDataBean.ChildsBean child : childs) {
                            boolean selected = child.isSelected();
                            String id = child.getId();
                            String name = child.getName();

                            if (selected) {
                                multiChooseBeanList.add(new MultiChooseBean(id, name));
                            }
                        }
                    }
                    //EventBus传递集合数据
                    Intent intent = new Intent();
                    intent.putExtra("subject", multiChooseBeanList);
                    setResult(RESULT_OK, intent);

                }
                finish();
                break;
        }
    }

    //获取科目筛选条件
    private void getsubCondition() {
        dialog.show();
        NetWork.getNearSubject().getKemuAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerSubject);
    }

    //获取所有科目的信息
    private Observer<NearSubjectBean> observerSubject = new Observer<NearSubjectBean>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onNext=======: ");
            dialog.dismiss();
        }

        @Override
        public void onError(Throwable e) {
            dialog.dismiss();
        }

        @Override
        public void onNext(NearSubjectBean mnearSubjectBean) {
            if (mnearSubjectBean.getAaData() == null) return;
            try {
                List<NearSubjectBean.AaDataBean> item = mnearSubjectBean.getAaData();

                //已选中处理
                List<String> selectedList = getIntent().getStringArrayListExtra(ARG_LIST_STR_NAME);
                if (selectedList != null && item != null) {
                    for (NearSubjectBean.AaDataBean data : item) {
                        if (data.getChilds() != null) {
                            for (NearSubjectBean.AaDataBean.ChildsBean childs : data.getChilds()) {
                                if (selectedList.contains(childs.getName())) {
                                    selectedList.remove(childs.getName());
                                    childs.setSelected(true);
                                    if (selectedList.isEmpty()) break;
                                }
                            }
                            if (selectedList.isEmpty()) break;
                        }
                    }
                }
                subjectList.addAll(item);
                liveSubAdapter.notifyDataSetChanged();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Log.e(TAG, "onNext: ");
            dialog.dismiss();
        }
    };
}
