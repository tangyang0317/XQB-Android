package com.zhangju.xingquban.interestclassapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;


/**
 * Created by liush on 2016/12/1 0001.
 *
 * @用于录入价格的dialog
 */
public class SetNumDialog extends Dialog {


    private Context mContext;
    private OnNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private OnYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    //确定文本和取消文本的显示内容

    private String yesStr, noStr;
    private Button   yes;
    private Button   no;
    private EditText etNum;


    public SetNumDialog(Context context) {
        super(context, R.style.SetNumDialog);
        this.mContext = context;
    }

    public void setYesOnclickListener(String str, OnYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public void setNoOnclickListener(String str, OnNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_setnum_dialog);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 获取用户输入的值
     */
    public int getPrice() {

        String num = etNum.getText().toString().trim();
        if (TextUtils.isEmpty(etNum.getText().toString().trim())) {
            return 0;
        } else {
            try {
                return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                etNum.setText("");
                ToastUtil.showToast(mContext,"输入的金额非法，请重新输入");
                return -1;
            }
        }
    }


    private void initView() {
        yes = (Button) findViewById(R.id.btn_live_dialog_yes);
        no = (Button) findViewById(R.id.btn_live_dialog_no);
        etNum = (EditText) findViewById(R.id.et_live_dialog_setnum);

        String livePrice = SpUtil.getString(mContext, "setLivePrice");
        if (livePrice != null) {
            etNum.setText(livePrice);
            yes.setTextColor(Color.parseColor("#1d95d4"));
            yes.setEnabled(true);
        }
        etNum.addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s)) {
                yes.setTextColor(Color.parseColor("#1d95d4"));
                yes.setEnabled(true);
            } else {
                yes.setTextColor(Color.parseColor("#999999"));
                yes.setEnabled(false);
            }
        }
    };


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface OnYesOnclickListener {
        public void onYesClick();
    }

    public interface OnNoOnclickListener {
        public void onNoClick();
    }
}
