package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.refactoring.utils.SoftKeyboardUtil;

/**
 * Created by sgfb on 2017/11/1.
 * 增加活动特色文本输入
 */
@ContentView(R.layout.act_publish_active_add_text_feature)
public class AddFeatureTextActivity extends FastActivity {
    public static final String ARG_RES_INT_POSITION = "position";
    public static final String ARG_RES_STR_TEXT = "text"; //接受和返回文本数据

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.content)
    EditText mContent;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ARG_RES_STR_TEXT, mContent.getText().toString());
                intent.putExtra(ARG_RES_INT_POSITION, getIntent().getIntExtra(ARG_RES_INT_POSITION, 0));
                setResult(RESULT_OK, intent);
                SoftKeyboardUtil.hideSoftKeyboard(AddFeatureTextActivity.this);
                finish();
            }
        });
    }
}
