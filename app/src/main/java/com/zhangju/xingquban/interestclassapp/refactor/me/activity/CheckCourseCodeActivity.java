package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CourseCodeAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCheckCourse;

import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 查看课程码
 */
@ContentView(R.layout.act_check_course_code)
public class CheckCourseCodeActivity extends FastActivity{
    public static final String ARG_STR_ID="id"; //订单id

    @LocalData(ARG_STR_ID)
    String mId;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.list)
    ListView mList;
    CourseCodeAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mList.setAdapter(mAdapter=new CourseCodeAdapter(this));
        requestCourseCode();
    }

    private void requestCourseCode(){
        Request request=Request.obtain(MeInterface.POST_ORDER_QRCODE);
        request.put("id",mId);
        request.setListener(new SimpleListener<Response<List<ResponseCheckCourse>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseCheckCourse>> result) {
                if(result.success&&result.data!=null&&!result.data.isEmpty()){
                    ResponseCheckCourse course=result.data.get(0);
                    mTitle.setText(course.lessonName);
                    generateCodesIcon(course);
                    mAdapter.setData(course.ordersQCode);
                }
            }
        });
        net(request);
    }

    /**
     * 生成二维码icon
     * @param checkCourse
     */
    private void generateCodesIcon(ResponseCheckCourse checkCourse){
        if(checkCourse!=null){
            if(checkCourse.ordersQCode!=null&&!checkCourse.ordersQCode.isEmpty()){
                for(ResponseCheckCourse.QrCode qr:checkCourse.ordersQCode){
                    MultiFormatWriter mfw=new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix=mfw.encode(qr.qcode, BarcodeFormat.QR_CODE,200,200);
                        int w=bitMatrix.getWidth();
                        int h=bitMatrix.getHeight();
                        int[] pixels=new int[w*h];
                        for(int y=0;y<h;y++){
                            int firstCol=y*w;
                            for(int x=0;x<w;x++){
                                pixels[firstCol+x]=bitMatrix.get(x,y)? Color.BLACK:Color.WHITE;
                            }
                        }
                        qr.codeIcon=Bitmap.createBitmap(pixels,w,h, Bitmap.Config.ARGB_4444);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
