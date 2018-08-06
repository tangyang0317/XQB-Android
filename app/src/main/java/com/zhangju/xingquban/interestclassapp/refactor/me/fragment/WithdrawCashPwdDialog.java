package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.PasswordCheckbox;

/**
 * Created by sgfb on 17/1/23.
 * 输入提现密码
 */
public class WithdrawCashPwdDialog extends DialogFragment{
    public static final String ARG_STR_TITLE="title";
    public static final String ARG_STR_MESSAGE ="content";
    private OnClickListener mListener;
    private TextView mMoney;
    private EditText mPass;
    private ViewGroup mCheckboxGroup;
    private int mPassIndex=-1;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_withdraw_password,null);
        mPass= (EditText) view.findViewById(R.id.pass);
        mCheckboxGroup= (ViewGroup) view.findViewById(R.id.checkboxGroup);
        mMoney= (TextView) view.findViewById(R.id.money);
        mMoney.setText(getArguments().getString(ARG_STR_MESSAGE));
        ((TextView)view.findViewById(R.id.title)).setText(getArguments().getString(ARG_STR_TITLE));
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dismiss();
            }
        });
        mPass.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    System.out.print("add ");
                    ++mPassIndex;
                    if(mPassIndex>=mCheckboxGroup.getChildCount())
                        mPassIndex=mCheckboxGroup.getChildCount()-1;
                    else{
                        PasswordCheckbox box= (PasswordCheckbox) ((ViewGroup)mCheckboxGroup.getChildAt(mPassIndex)).getChildAt(0);
                        box.setChecked(true);
                    }
                }
                else{
                    System.out.print("sub ");
                    --mPassIndex;
                    if(mPassIndex<0)
                        mPassIndex=-1;
                    PasswordCheckbox box=(PasswordCheckbox) ((ViewGroup)mCheckboxGroup.getChildAt(mPassIndex+1)).getChildAt(0);
                    box.setChecked(false);
                }
                System.out.println("index:"+mPassIndex);
            }

            @Override
            public void afterTextChanged(Editable s){
                if(s.length()>=6){
                    if(mListener!=null) {
                        mListener.onComplete(mPass.getText().toString());
                        mListener=null;
                    }
                    dismiss();
                }
            }
        });
        return new AlertDialog.Builder(getContext())
                .setView(view)
                .create();
    }

    public void show(FragmentManager fm, OnClickListener l){
        mListener=l;
        show(fm,"specialDialog");
    }

    public void clearPass(){
        mPassIndex=-1;
        for(int i=0;i<mCheckboxGroup.getChildCount();i++)
            ((PasswordCheckbox)((ViewGroup)mCheckboxGroup.getChildAt(i)).getChildAt(0)).setChecked(false);
    }

    public void setListener(OnClickListener l){
        mListener=l;
    }

    public interface OnClickListener{
        void onComplete(String pass);
    }
}