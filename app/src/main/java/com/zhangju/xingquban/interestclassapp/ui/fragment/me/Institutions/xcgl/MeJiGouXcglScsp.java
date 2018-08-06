package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouXcglScsp extends BaseActivity {
    public static final String TAG = "";

    @BindView(R.id.me_helpand_return)
    RelativeLayout meHelpandReturn;
    @BindView(R.id.me_helpand_wanc)
    Button meHelpandWanc;
    @BindView(R.id.mejg_scgl_scsp_title)
    EditText mejgScglScspTitle;
    @BindView(R.id.mejg_scgl_scsp_content)
    EditText mejgScglScspContent;
    @BindView(R.id.scsp_spj_text)
    TextView scspSpjText;
    @BindView(R.id.mejg_scgl_scsp_spj)
    RelativeLayout mejgScglScspSpj;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.mejg_scgl_scsp_number)
    EditText mejgScglScspNumber;
    @BindView(R.id.mejg_scgl_scsp_CityText)
    TextView mejgScglScspCityText;
    private String title, content, number, files, city;

    private Subscription suscription;
    Observer<Object> observer1 = new Observer<Object>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(Object object) {
            Log.i(TAG, "meUserBean: " + object.toString());
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_xcgl_scsp;
    }

    @Override
    public void initView() {
        title = mejgScglScspTitle.getText().toString();
        content = mejgScglScspContent.getText().toString();
        number = mejgScglScspNumber.getText().toString();
        city = mejgScglScspCityText.getText().toString();
        Intent intent = getIntent();
        files = intent.getStringExtra("files");
        scspSpjText.setText(files);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    public void getUpVideo() {
        Location location= LocationManager.getInstance().getLocation();
        suscription = NetWork.getMe().organVideo(UserManager.getInstance().getUser().id, title, content, number, "视频地址",
                2, 200, city, location.latitude,location.longitude, "视频标题图片", files)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @OnClick({R.id.me_helpand_return, R.id.me_helpand_wanc, R.id.mejg_scgl_scsp_spj})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.me_helpand_wanc:
                getUpVideo();
                break;

            case R.id.mejg_scgl_scsp_spj:
                startActivityForResult(new Intent(this, MeJiGouXcglScspSpj.class), 1);
                break;

            case R.id.me_helpand_return:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
