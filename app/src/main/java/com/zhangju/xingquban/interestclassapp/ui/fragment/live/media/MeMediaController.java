package com.zhangju.xingquban.interestclassapp.ui.fragment.live.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.view.livestream.NEMediaController;

/**
 * Created by ydw on 2017/11/2.
 */

public class MeMediaController extends NEMediaController implements View.OnClickListener {


    ImageView image_toggle;
    RelativeLayout video_toggle;
    private Context mContext;



    public MeMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeMediaController(Context context) {
        super(context);
        intiContext(context);
    }


    private void intiContext(Context context) {
        this.mContext = context;


    }

    @Override
    protected View makeControllerView() {
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.mecontroller_play, this);
        view.findViewById(R.id.video_message).setOnClickListener(this);
        view.findViewById(R.id.video_toggle).setOnClickListener(this);
        image_toggle= (ImageView) view.findViewById(R.id.image_toggle);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_message:
                message.getvideoMessage();
                break;
            case R.id.video_toggle:
                toggle.getvideoToggle();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                show(0); // show until hide is called
                break;
            case MotionEvent.ACTION_UP:
//                show(sDefaultTimeout); // start timeout
                break;
            case MotionEvent.ACTION_CANCEL:

                break;

        }
        return false;
    }

    private getMessage message;

    public void setMessage(getMessage message) {
        this.message = message;
    }

    public interface getMessage {
        void getvideoMessage();

    }
    //q
    private getToggle toggle;

    public void setToggle(getToggle toggle) {
        this.toggle = toggle;
    }

    public interface getToggle{
        void getvideoToggle();
    }
}
