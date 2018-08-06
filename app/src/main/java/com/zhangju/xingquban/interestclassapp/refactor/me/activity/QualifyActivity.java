package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.fastlib.utils.TimeUtil;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by sgfb on 2017/11/16.
 * 资质认证
 */
@ContentView(R.layout.act_upload_qualify)
public class QualifyActivity extends FastActivity{
    public static final String ARG_INT_TYPE="type";  //1身份认证 2资格认证 3平台认证

    @LocalData(ARG_INT_TYPE)
    int mType;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.license)
    ImageView mLicense;
    @Bind(R.id.upload)
    TextView mUpload;
    @Bind(R.id.idCode)
    EditText mIdCode;
    @Bind(R.id.idCodeLayout)
    View mIdCodeLayout;
    String mImagePath;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        mIdCodeLayout.setVisibility(mType==1?View.VISIBLE:View.INVISIBLE);
    }

    private void commit(){
        final String idCode=mIdCode.getText().toString();
        if(mType==1){
            if(TextUtils.isEmpty(idCode)) {
                N.showShort(this,"身份证号不能为空");
                return;
            }
        }
        if(TextUtils.isEmpty(mImagePath)){
            N.showShort(this,"请上传认证照片");
            return;
        }
        loading();
        startTask(Task.begin(mImagePath)
                .next(new Action<String, Request>() {

                    @Override
                    protected Request execute(String param) throws Throwable {
                        return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files",new File(param))
                                .putHeader("X-CustomToken", UserManager.getInstance().getToken());
                    }
                })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if(!listResponse.success||listResponse.data==null||listResponse.data.isEmpty())
                            stopTask();
                    }
                })
                .next(new Action<Response<List<ResponseUploadImage>>, Request>() {

                    @Override
                    protected Request execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                        User user = UserManager.getInstance().getUser();
                        Request request=null;
                        if(mType==1){
                            request=Request.obtain(MeInterface.POST_QUALITY_REALNAME);
                            request.put("checked", 1);
                            request.put("customerId", user.id);
                            request.put("teacherId", user.teacherTimeId);
                            request.put("username", user.signame);
                            request.put("uidCard", idCode);
                            request.put("uidCardImg",param.data.get(0).fileName);
                        }
                        else if(mType==2){
                            request=Request.obtain(MeInterface.POST_QUALITY_QC);
                            request.put("customerId",user.id);
                            request.put("teacherTimeId",user.teacherTimeId);
                            request.put("companyName",user.map==null?user.username:user.map.username);
                            request.put("qCertificate",user.degree==null?"未知":(user.degree.isTeacher?"教师":"机构"));
                            request.put("certImg",param.data.get(0).fileName);
                            request.put("checked",1);
                        }
                        else if(mType==3){
                            request= Request.obtain(MeInterface.POST_QUALITY_PLATFORM);
                            request.put("sno",user.teacherTimeId+user.id);
                            request.put("teacherTimeId",user.teacherTimeId);
                            request.put("customerId",user.id);
                            request.put("name","平台认证");
                            request.put("parta","上海掌聚文化传媒有限公司");
                            request.put("pic",param.data.get(0).fileName);
                            request.put("partbTel",user.phone);
                            request.put("partbAddress",user.address);
                            request.put("signDate", TimeUtil.dateToString(new Date(),"yyyy-MM-dd"));
                            request.put("effDate",TimeUtil.dateToString(new Date(),"yyyy-MM-dd"));
                            request.put("endDate","2099-12-31");
                            request.put("status",1);
                        }
                        return request;
                    }
                })
                .next(new NetAction<Response>() {

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        if(response.success){
                            N.showShort(QualifyActivity.this,"申请已提交");
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }, ThreadType.MAIN), new NoReturnAction<Throwable>() {
            @Override
            public void executeAdapt(Throwable param) {
                param.printStackTrace();
            }
        }, new EmptyAction() {
            @Override
            protected void executeAdapt() {
                dismissLoading();
            }
        });
    }

    @Bind(R.id.upload)
    private void upload(){
        FastDialog.showListDialog(new String[]{"拍照","从手机相册选择"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) openCamera(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        changeLicense(path);
                    }
                });
                else openAlbum(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        changeLicense(path);
                    }
                });
            }
        });
    }

    private void changeLicense(String path){
        mImagePath=path;
        mLicense.setImageBitmap(ImageUtil.getThumbBitmap(path,1000));
        mUpload.setText("重新上传");
    }
}
