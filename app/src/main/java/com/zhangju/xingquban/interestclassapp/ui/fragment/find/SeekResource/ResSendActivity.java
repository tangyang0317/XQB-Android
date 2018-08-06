package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资源评论发送页面
 */
@ContentView(R.layout.activity_res_send)
public class ResSendActivity extends FastActivity {

    @BindView(R.id.audio_back)
    ImageView audioBack;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.edit_content)
    EditText editContent;

    private String resId;//资源id
    private String content;//评论内容

    @Override
    protected void alreadyPrepared() {
        resId = getIntent().getStringExtra("id");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.audio_back, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.audio_back:
                finish();
                break;
            case R.id.tv_commit:
                content = editContent.getText().toString().trim();
                if (content.isEmpty()) {
                    ToastUtil.showToast("评论内容不能为空");
                } else {
                    commitMessageInfo();
                }
                break;
        }
    }

    //提交评论内容
    private void commitMessageInfo() {
        final Request request = Request.obtain(ResInterface.POST_RES_MESSAGE);
        String token = UserManager.getInstance().getToken();
        request.put("resourcesId", resId);
        request.put("summary", content);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response result) {

                boolean success = result.success;
                if (success) {
                  finish();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);

    }
}
