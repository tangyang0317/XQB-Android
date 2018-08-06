package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.google.gson.internal.LinkedHashTreeMap;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActiveIncome;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.BusinessOrderFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.ResInterface;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.zxing.activity.CaptureActivity;

import java.text.DecimalFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商户验证码
 */
@ContentView(R.layout.activity_vip_vertify)
public class VipVertifyActivity extends FastActivity {

    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.vip_edit)
    EditText vipEdit;
    @BindView(R.id.vip_delete)
    ImageView vipDelete;
    @BindView(R.id.vip_scan)
    ImageView vipScan;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    protected void alreadyPrepared() {
        initTitle();
        intiEdit();

    }

    private void intiEdit() {
        vipEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString();
                if (!s1.isEmpty()) {
                    vipDelete.setVisibility(View.VISIBLE);
                } else {
                    vipDelete.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initTitle() {
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.vip_delete, R.id.vip_scan, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vip_delete:
                vipEdit.setText("");
                vipDelete.setVisibility(View.INVISIBLE);
                break;
            case R.id.vip_scan:
                Intent intent = new Intent(VipVertifyActivity.this, CaptureActivity.class);
                intent.putExtra("type", false);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_commit:

                VertifyCode(vipEdit.getText().toString().trim());

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String code = data.getStringExtra(CaptureActivity.RES_STR_CODE);
            vipEdit.setText(code);
            if (code != null && !code.isEmpty()){
                vipDelete.setVisibility(View.VISIBLE);
            } else {
                vipDelete.setVisibility(View.INVISIBLE);
            }
            VertifyCode(code);
        }
    }

    /**
     * 验证码验证
     *
     * @param qcode
     */
    private void VertifyCode(String qcode) {

        final Request request = Request.obtain(MeInterface.POST_CHECK_VIP_CODE);
        final String token = UserManager.getInstance().getToken();
        request.put("qcode", qcode);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response response) {
                boolean success = response.success;
                LinkedHashTreeMap errMsg = response.errMsg;
                Object sys = errMsg.get("sys")==null?"":errMsg.get("sys");
                if (success) {
                    ToastUtil.showToast("验证成功");
                    EventObserver.getInstance().sendEvent(VipVertifyActivity.this,new EventRefresh(BusinessOrderFragment.class));
                    finish();
                }else {
                    ToastUtil.showToast(sys.toString());
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
