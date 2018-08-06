package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.jgjj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeJiGouJgjjWb extends BaseActivity {


    @BindView(R.id.me_jg_jgjjTj_wb_head)
    PublicHead meJgJgjjTjWbHead;
    @BindView(R.id.me_jg_jgjjTj_wb_edit)
    EditText meJgJgjjTjWbEdit;


    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_jgjj_wb;
    }

    @Override
    public void initView() {
        setMeJgJgjjTjWbHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setMeJgJgjjTjWbHead() {
        meJgJgjjTjWbHead.setTitle("编辑文本");
        meJgJgjjTjWbHead.setShow(true, false, true);
        meJgJgjjTjWbHead.setRightTitle("完成");
        meJgJgjjTjWbHead.setRightTextColor(R.color.color_main);
        meJgJgjjTjWbHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meJgJgjjTjWbHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = meJgJgjjTjWbEdit.getText().toString();
                if (s.length() > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("s", s);
                    intent.putExtras(bundle);
                    setResult(110, intent);
                    finish();
                } else {
                    ToastUtil.showToast("请输入内容");
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
