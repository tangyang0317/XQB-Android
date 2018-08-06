package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.utils.Utils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCheckCourse;

import java.util.Locale;

/**
 * Created by Administrator on 2017/12/14.
 * 二维码全屏Dialog
 */
public class QrCodeDialog extends DialogFragment{
    public static final String ARG_STR_CODE="code";
    private float mOldBrightness;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog_qrcode,null);
        String code=getArguments().getString(ARG_STR_CODE);
        String title=String.format(Locale.getDefault(),"课程号：%s",code);

        ((TextView)view.findViewById(R.id.code)).setText(Utils.getTextSomeOtherColor(4,title.length(),title,getResources().getColor(R.color.EF4E4C)));
        ((ImageView)view.findViewById(R.id.codeImage)).setImageBitmap(generateCodesIcon(code));
        return new AlertDialog.Builder(getContext()).setView(view).create();
    }

    /**
     * 生成二维码icon
     * @param code
     */
    private Bitmap generateCodesIcon(String code){
        MultiFormatWriter mfw=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=mfw.encode(code, BarcodeFormat.QR_CODE,200,200);
            int w=bitMatrix.getWidth();
            int h=bitMatrix.getHeight();
            int[] pixels=new int[w*h];
            for(int y=0;y<h;y++){
                int firstCol=y*w;
                for(int x=0;x<w;x++){
                    pixels[firstCol+x]=bitMatrix.get(x,y)? Color.BLACK:Color.WHITE;
                }
            }
            return Bitmap.createBitmap(pixels,w,h, Bitmap.Config.ARGB_4444);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
        mOldBrightness=lp.screenBrightness;
        lp.screenBrightness=1;
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onPause() {
        super.onPause();
        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
        lp.screenBrightness=mOldBrightness;
        getActivity().getWindow().setAttributes(lp);
    }
}