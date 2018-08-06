package com.zhangju.xingquban.interestclassapp.refactor.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sgfb on 17/2/21.
 */
public class PasswordCheckbox extends View{
    private boolean isChecked;
    private Paint mRectPaint;
    private Paint mCirclePaint;

    public PasswordCheckbox(Context context) {
        super(context);
        init();
    }

    public PasswordCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mRectPaint=new Paint();
        mCirclePaint=new Paint();
        mRectPaint.setColor(Color.BLACK);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(2);
        mCirclePaint.setColor(Color.BLACK);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mRectPaint);
        if(isChecked)
            canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,Math.min(canvas.getWidth(),canvas.getHeight())/10,mCirclePaint);
    }

    public void setChecked(boolean check){
        isChecked=check;
        invalidate();
    }

    public boolean getCheckState(){
        return isChecked;
    }
}
