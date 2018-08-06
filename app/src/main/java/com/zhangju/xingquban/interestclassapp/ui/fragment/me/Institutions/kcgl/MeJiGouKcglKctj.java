package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.Data;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;

public class MeJiGouKcglKctj extends BaseActivity {
    public static final String TAG = "MeJiGouKcglKctj";

    @BindView(R.id.me_jigou_kcgl_kctj_head)
    PublicHead meJigouKcglKctjHead;
    @BindView(R.id.work_institution_name)
    EditText workInstitutionName;
    @BindView(R.id.work_legal_name)
    EditText workLegalName;
    @BindView(R.id.work_course_unitPrice)
    EditText workCourseUnitPrice;
    @BindView(R.id.work_course_preferentialPrice)
    EditText workCoursePreferentialPrice;
    @BindView(R.id.work_course_teacher)
    EditText workCourseTeacher;
    @BindView(R.id.work_course_schoolBeginsNumber)
    EditText workCourseSchoolBeginsNumber;
    @BindView(R.id.work_street)
    EditText workStreet;
    @BindView(R.id.work_courseDescription)
    EditText workCourseDescription;
    @BindView(R.id.work_courseIntroduction)
    EditText workCourseIntroduction;
    @BindView(R.id.work_stMessage)
    LinearLayout workStMessage;
    @BindView(R.id.work_skfs)
    LinearLayout workSkfs;
    @BindView(R.id.work_skCity)
    LinearLayout workSkCity;
    @BindView(R.id.me_jigou_kcfm)
    ImageView meJigouKcfm;
    @BindView(R.id.text_St)
    TextView textSt;
    @BindView(R.id.work_skfs_text)
    TextView workSkfsText;
    @BindView(R.id.work_skCity_text)
    TextView workSkCityText;
    @BindView(R.id.btcommit)
    Button btcommit;
    @BindView(R.id.shitingimage)
    ImageView shitingimage;
    @BindView(R.id.image_jihou_you4)
    ImageView imageJihouYou4;
    @BindView(R.id.image_jihou_you5)
    ImageView imageJihouYou5;
    @BindView(R.id.shoukeshijian)
    ImageView shoukeshijian;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.ruzhu_work)
    LinearLayout ruzhuWork;
    @BindView(R.id.work_time)
    LinearLayout workTime;
    @BindView(R.id.time_text)
    TextView timeText;

    private CharSequence temp;
    private Subscription subscription;
    Observer<MeJiGouKcglKctjBean> observer1 = new Observer<MeJiGouKcglKctjBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(MeJiGouKcglKctjBean meJiGouKcglKctjBean) {
            Log.i(TAG, "meUserBean: " + meJiGouKcglKctjBean.toString());
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcgl_kctj;
    }

    @Override
    public void initView() {
        setMeJigouKcglKctjHead();
    }

    @Override
    public void initData() {

    }

    public void getData() {
        String s = workInstitutionName.getText().toString();
        String s1 = workLegalName.getText().toString();
        String SkPrice = workCourseUnitPrice.getText().toString();
        String YhPrice = workCoursePreferentialPrice.getText().toString();
        String SkTeacher = workCourseTeacher.getText().toString();
//        int allows = Integer.parseInt(workCourseSchoolBeginsNumber.getText().toString());
        String classRoom = workStreet.getText().toString();
        String descript = workCourseDescription.getText().toString();
        String summary = workCourseIntroduction.getText().toString();

        workCourseDescription.addTextChangedListener(mTextWatcher);
        workCourseIntroduction.addTextChangedListener(mTextWatcher2);
        Data data = new Data();

//        subscription = NetWork.getMe().lesson("id", s, "图片", s1, SkPrice, YhPrice, SkTeacher, true, allows, 2, "", classRoom, "2017-09-07", descript, summary)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer1);
    }

    @Override
    public void initListener() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                String s = data.getStringExtra("s");
                textSt.setText(s);
                break;
            case 1:
                String a = data.getStringExtra("a");
                workSkfsText.setText(a);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.work_stMessage, R.id.work_skfs, R.id.work_skCity, R.id.btcommit, R.id.work_time})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.work_stMessage:
                startActivityForResult(new Intent(this, MeJiGouKcglKctjStxx.class), 1);
                break;
            case R.id.work_skfs:
                startActivityForResult(new Intent(this, MeJiGouKcglKctjSKfs.class), 2);
                break;
            case R.id.work_skCity:
                startActivity(new Intent(new Intent(this, MeJiGouKcglKctjCity.class)));
                break;

            case R.id.work_time:
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(MeJiGouKcglKctj.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        timeText.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;

            case R.id.btcommit:
                getData();
                break;
        }
    }

    public void setMeJigouKcglKctjHead() {
        meJigouKcglKctjHead.setTitle("课程添加");
        meJigouKcglKctjHead.setShow(true, false, false);
        meJigouKcglKctjHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //判断EditText输入多少字
    TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            findTabTextNumber.setText(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = workCourseDescription.getSelectionStart();
            editEnd = workCourseDescription.getSelectionEnd();
//            findTabTextNumber.setText(temp.length() + "");
            if (temp.length() > 22) {
                ToastUtil.showToast("你输入的字数已经超过了限制！");
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                workCourseDescription.removeTextChangedListener(this);
                workCourseDescription.setText(s);
                workCourseDescription.addTextChangedListener(this);
                workCourseDescription.setSelection(tempSelection);
            }

        }
    };
    //判断EditText输入多少字
    TextWatcher mTextWatcher2 = new TextWatcher() {

        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            findTabTextNumber.setText(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = workCourseIntroduction.getSelectionStart();
            editEnd = workCourseIntroduction.getSelectionEnd();
//            findTabTextNumber.setText(temp.length() + "");
            if (temp.length() > 100) {
                ToastUtil.showToast("你输入的字数已经超过了限制！");
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                workCourseIntroduction.removeTextChangedListener(this);
                workCourseIntroduction.setText(s);
                workCourseIntroduction.addTextChangedListener(this);
                workCourseIntroduction.setSelection(tempSelection);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
