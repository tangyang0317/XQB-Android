package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.fastlib.app.EventObserver;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCreateVideoFolder;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

/**
 * Created by sgfb on 2017/11/21.
 * 创建文件夹弹窗
 */
public class CreateVideoFloderDialog extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_vidoe_folder,null);
        final EditText name= (EditText) view.findViewById(R.id.name);

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString())) {
                    N.showShort(getContext(), "请输入文件夹名");
                    return;
                }
                requestCreateVideoFolder(name.getText().toString());
            }
        });
        return new AlertDialog.Builder(getContext()).setView(view).create();
    }

    /**
     * 请求创建视频文件夹
     */
    private void requestCreateVideoFolder(String name){
        Request request=new Request(MeInterface.POST_CREATE_VIDEO_FOLDER);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("files",name);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result){
                if(result.success){
                    EventObserver.getInstance().sendEvent(getContext(),new EventCreateVideoFolder());
                    dismiss();
                }
            }
        });
        request.start();
    }
}