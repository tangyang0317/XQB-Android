package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCourse;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.AlbumManageFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.refactoring.activity.EditCourseDetailsActivity;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 课程添加或修改
 */
@ContentView(R.layout.act_add_course)
public class EditCourseActivity extends FastActivity {
    public static final String ARG_STR_ID = "id"; //可选课程，如果存在则是修改课程否则添加
    public static Activity instance;
    final int REQ_ADDRESS = 3;
    final int REQ_TEACH_COURSE = 4;
    final int REQUEST_CODE_CHOOSE_IMG = 5;
    @LocalData(ARG_STR_ID)
    String mId;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.courseName)
    EditText mCourseName;
    @Bind(R.id.courseCount)
    EditText mCourseCount;
    @Bind(R.id.courseLength)
    EditText mCourseLength;
    @Bind(R.id.coursePrice)
    EditText mCoursePrice;
    @Bind(R.id.vipPrice)
    EditText mVipPrice;
    @Bind(R.id.teacherName)
    EditText mTeacherName;
    @Bind(R.id.auditionInfo)
    TextView mAuditionInfo;
    @Bind(R.id.teachAddress)
    TextView mTeachAddress;
    @Bind(R.id.teachMethod)
    TextView mTeachMethod;
    @Bind(R.id.teachAddressTitle)
    TextView mTeachAddressTitle;
    @Bind(R.id.cover)
    ImageView mCover;
    @Bind(R.id.teachCourse)
    TextView mTeachCourse;

    int mAuditionType = -1;
    int mTeachMethodType = -1;
    String mAddress;
    String mLng;
    String mLat;
    String mCoverPath;
    String mAreaName;
    String mAreaCode;
    String mProvinceName;
    String mProvinceCode;
    String mCityId;
    String mCityName;
    String mCourseId;
    String mDescript;
    String mSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (!TextUtils.isEmpty(mId) && !"-1".equals(mId)) {
            mTitleBar.getTitle().setText("修改课程");
            requestCourse();
        }
    }

    /**
     * 如果是修改课程，请求课程详情
     */
    private void requestCourse() {
        Request request = Request.obtain(MeInterface.POST_COURSE_DETAIL);
        request.put("id", mId);
        request.setListener(new SimpleListener<Response<List<ResponseCourse>>>() {
            @Override
            public void onResponseListener(Request r, Response<List<ResponseCourse>> result) {
                if (result.success) {
                    ResponseCourse data = result.data.get(0);
                    mCourseName.append(TextUtils.isEmpty(data.name) ? "" : data.name);
                    mCourseCount.setText(data.courses);
                    mCourseLength.setText(data.timelength);
                    mCoursePrice.setText(data.price);
                    mAuditionInfo.setText(data.isCantry ? "可试听" : "不可试听");
                    mTeachMethod.setText(data.method);
                    mTeacherName.setText(data.teacherName);
                    mTeachAddress.setText(data.region);
                    mVipPrice.setText(data.vipPrice);
                    mTeachCourse.setText(data.catagoryName);
                    mCourseId = data.categoriesId;
                    mAuditionType = data.isCantry ? 1 : 0;
                    mAddress = data.region;
                    mAreaCode = data.areasId;
                    mProvinceCode = data.provinceId;
                    mCityId = data.cityId;
                    mAreaName = data.areasName;
                    mProvinceName = data.provinceName;
                    mCityName = data.cityName;
                    mLat = data.lat;
                    mLng = data.lng;
                    mCoverPath = data.picture;
                    mDescript = data.descript;
                    mSummary = data.summary;
                    if ("学生上门".equals(data.method))
                        mTeachMethodType = 1;
                    else if ("家教上门".equals(data.method))
                        mTeachMethodType = 2;
                    else mTeachMethodType = 3;
                    Glide.with(EditCourseActivity.this).load(data.picture).into(mCover);
                }
            }
        });
        net(request);
    }

    @Bind(R.id.coverLayout)
    private void selectCover() {
        Matisse.from(EditCourseActivity.this)
                .choose(MimeType.ofImage(), true)
                .capture(true)
                .maxSelectable(1)
                .captureStrategy(new CaptureStrategy(true, "com.zhangju.xingquban.fileprovider"))
                .countable(true)
                .showSingleMediaType(true)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE_IMG);

//        FastDialog.showListDialog(new String[]{"拍照", "从相册中选择"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) openCamera(new PhotoResultListener() {
//                    @Override
//                    public void onPhotoResult(String path) {
//                        Glide.with(EditCourseActivity.this).load(new File(path)).into(mCover);
//                        mCoverPath = path;
//                    }
//                });
//                else openAlbum(new PhotoResultListener() {
//                    @Override
//                    public void onPhotoResult(String path) {
//                        Glide.with(EditCourseActivity.this).load(new File(path)).into(mCover);
//                        mCoverPath = path;
//                    }
//                });
//            }
//        });
    }

    @Bind(R.id.auditionLayout)
    private void selectAudition() {
        new MaterialDialog.Builder(this)
                .title("是否允许试听")
                .items(new String[]{"不可以试听", "可以试听"})
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        mAuditionType = position;
                        if (mAuditionType == 0) {
                            mAuditionInfo.setText("不可以试听");
                        } else {
                            mAuditionInfo.setText("可以试听");
                        }
                        dialog.dismiss();
                    }
                })
                .autoDismiss(false)
                .show();
    }

    @Bind(R.id.teachAddressLayout)
    private void selectTeachAddress() {
        startActivityForResult(LocationActive.class, REQ_ADDRESS);
    }

    @Bind(R.id.teachMethodLayout)
    private void selectTeachMethod() {

        new MaterialDialog.Builder(this)
                .title("授课方式")
                .items(new String[]{"学生上门", "家教上门", "协商地点"})
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        mTeachMethodType = position + 1;
                        switch (mTeachMethodType) {
                            case 1:
                                mTeachMethod.setText("学生上门");
                                mTeachAddressTitle.setText("授课地点");
                                break;
                            case 2:
                                mTeachMethod.setText("家教上门");
                                mTeachAddressTitle.setText("所在地点");
                                break;
                            case 3:
                                mTeachMethod.setText("协商地点");
                                mTeachAddressTitle.setText("所在地点");
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .autoDismiss(false)
                .show();
    }

    @Bind(R.id.commit)
    private void commit() {
        final String courseName = mCourseName.getText().toString();
        final String courseCount = mCourseCount.getText().toString();
        final String courseLength = mCourseLength.getText().toString();
        final String coursePrice = mCoursePrice.getText().toString();
        final String teacherName = mTeacherName.getText().toString();
        final String vipPrice = mVipPrice.getText().toString();

        if (TextUtils.isEmpty(courseName)) {
            mCourseName.setError("请输入课程名称");
            mCourseName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(courseCount)) {
            mCourseCount.setError("请输入课程数量");
            mCourseCount.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(courseLength)) {
            mCourseLength.setError("请输入课时长度");
            mCourseLength.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(coursePrice)) {
            mCoursePrice.setError("请输入课程价格");
            mCoursePrice.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(vipPrice)) {
            mVipPrice.setError("请输入优惠后的价格");
            return;
        }
        if (TextUtils.isEmpty(teacherName)) {
            mTeacherName.setError("请输入教师姓名");
            mTeacherName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mTeachAddress.getText().toString())) {
            N.showSnackbarShort(mTeachAddress, "请输入授课地点");
            return;
        }
        if (mAuditionType == -1) {
            N.showSnackbarShort(mAuditionInfo, "请选择是否可试听");
            return;
        }
        if (mTeachMethodType == -1) {
            N.showSnackbarShort(mTeachMethod, "请选择教学类型");
            return;
        }
        if (TextUtils.isEmpty(mCoverPath)) {
            N.showSnackbarShort(mCover, "请上传课程封面");
            return;
        }
        if (TextUtils.isEmpty(mCourseId)) {
            N.showSnackbarShort(mTeachCourse, "请选择科目");
            return;
        }
        float price = Float.parseFloat(coursePrice);
        float discountPrice = Float.parseFloat(vipPrice);
        if (price <= 0) {
            N.showShort(this, "价格必须大于0元");
            return;
        }
        if (price <= discountPrice) {
            N.showShort(this, "优惠价必须小于原价");
            return;
        }
        HashMap<String, String> lessonMap = new HashMap<>();
        lessonMap.put("areasId", mAreaCode);
        lessonMap.put("allows", String.valueOf(0));
        lessonMap.put("cityCode", mCityId);
        lessonMap.put("provinceId", mProvinceCode);
        lessonMap.put("areasName", mAreaName);
        lessonMap.put("cityName", mCityName);
        lessonMap.put("courses", courseCount);
        lessonMap.put("customerId", UserManager.getInstance().getUser().id);
        lessonMap.put("isCantry", String.valueOf(mAuditionType));
        lessonMap.put("lat", mLat);
        lessonMap.put("lng", mLng);
        lessonMap.put("methodType", String.valueOf(mTeachMethodType));
        lessonMap.put("name", courseName);
        lessonMap.put("picture", mCoverPath);
        lessonMap.put("price", coursePrice);
        lessonMap.put("provinceName", mProvinceName);
        lessonMap.put("region", mAddress);
        lessonMap.put("resId", teacherName);
        lessonMap.put("teacherTimeId", UserManager.getInstance().getUser().teacherTimeId);
        lessonMap.put("timelength", courseLength);
        lessonMap.put("vipPrice", vipPrice);
        lessonMap.put("lessonDate", "2050-01-01");
        lessonMap.put("categoriesId", mCourseId);
        lessonMap.put("catagoryName", mTeachCourse.getText().toString());
        lessonMap.put("descript", mDescript);
        lessonMap.put("summary", mSummary);
        if (!TextUtils.isEmpty(mId) && !"-1".equals(mId)) {
            lessonMap.put("id", mId);
        }
        Intent intent = new Intent(this, EditCourseDetailsActivity.class);
        intent.putExtra("lessonMap", lessonMap);
        startActivity(intent);

    }

    @Bind(R.id.teachCourseLayout)
    private void selectTeachCourse() {
        Intent intent = new Intent(this, CourseChoiceActivity.class);
        intent.putExtra("type", false);
        startActivityForResult(intent, REQ_TEACH_COURSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == REQ_ADDRESS) {
            mAddress = data.getStringExtra("address");
            mLat = data.getStringExtra("lat");
            mLng = data.getStringExtra("lng");
            mAreaName = data.getStringExtra("areaName");
            mAreaCode = data.getStringExtra("areaCode");
            mProvinceName = data.getStringExtra("provinceName");
            mProvinceCode = data.getStringExtra("provinceCode");
            mCityId = data.getStringExtra("cityId");
            mCityName = data.getStringExtra("cityName");
            mTeachAddress.setText(mAddress);
        } else if (requestCode == REQ_TEACH_COURSE) {
            mCourseId = data.getStringExtra("subId");
            mTeachCourse.setText(data.getStringExtra("subName"));
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMG) {
            List<String> pathList = Matisse.obtainPathResult(data);
            if (pathList != null && pathList.size() > 0) {
                upLoadImg(pathList.get(0));
            }
        }
    }


    /***
     * 课程发布
     */
    private void upLoadImg(String filePath) {
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("files", new File(filePath));
        new HttpUtils().send(HttpRequest.HttpMethod.POST, UrlUtils.URL_UPLOAD_IMG, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type type = new TypeToken<BaseResponseBean<List<ResponseUploadImage>>>() {
                }.getType();
                BaseResponseBean<List<ResponseUploadImage>> listBaseResponseBean = new Gson().fromJson(responseInfo.result, type);
                if (listBaseResponseBean.isSuccess()) {
                    if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                        mCoverPath = listBaseResponseBean.getAaData().get(0).fileName;
                        Glide.with(EditCourseActivity.this).load(mCoverPath).into(mCover);
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ToastUtil.showToast(s);
            }
        });

    }


}
