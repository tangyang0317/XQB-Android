package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.KeyBoardUtils;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.FileHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FeedbackPhotosAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

/**
 * Created by sgfb on 2017/10/26.
 * 用户反馈
 */
@ContentView(R.layout.act_feedback)
public class FeedbackActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.content)
    EditText mContent;
    @Bind(R.id.contact)
    EditText mContact;
    @Bind(R.id.photos)
    GridView mPhotos;
    FeedbackPhotosAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mPhotos.setAdapter(mAdapter=new FeedbackPhotosAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastDialog.showListDialog(new String[]{"拍照","从手机相册选择"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0) openCamera(new PhotoResultListener() {
                            @Override
                            public void onPhotoResult(String path) {
                                mAdapter.addData(path);
                            }
                        }, FileHelper.getCacheTempFile(FeedbackActivity.this,Environment.DIRECTORY_PICTURES,".jpg",false).getAbsolutePath());
                        else openAlbum(new PhotoResultListener() {
                            @Override
                            public void onPhotoResult(String path) {
                                mAdapter.addData(path);
                            }
                        });
                    }
                });
            }
        }));
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
    }

    private void commit(){
        String content=mContent.getText().toString();
        String contact=mContact.getText().toString();

        KeyBoardUtils.closeKeybord(this);
        if(TextUtils.isEmpty(content)){
            N.showSnackbarShort(mContent,"请输入内容");
            return;
        }
        if(TextUtils.isEmpty(contact)){
            N.showSnackbarShort(mContact,"请输入联系方式");
            return;
        }
        loading();
        Request request=Request.obtain(MeInterface.POST_FEEDBACK);
        request.put("summary",content);
        request.put("contact",contact);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result){
                dismissLoading();
                if(result.success){
                    N.showShort(FeedbackActivity.this,"发送反馈成功");
                    finish();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }
}
