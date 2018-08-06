package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.jgjj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouJgjjTj extends BaseActivity {

    private static final String TAG = "MeJiGouJgjjTj";
    @BindView(R.id.me_jg_jgjj_wb_return)
    LinearLayout meJgJgjjWbReturn;
    @BindView(R.id.me_jg_jgjj_wb_text)
    TextView meJgJgjjWbText;
    @BindView(R.id.me_jg_jgjj_wb_scrollview2)
    ScrollView meJgJgjjWbScrollview2;
    @BindView(R.id.me_jg_jgjjTj_tj)
    ImageView meJgJgjjTjTj;
    @BindView(R.id.me_jgtj_recycler)
    RecyclerView meJgtjRecycler;

    private LinearLayoutManager linearLayoutManager;
    private List<MeJiGouJgjjTjBean> mList = new ArrayList<>();
    private ImageView mIvTitleIcon;
    private BottomDialog2 bottomDialog2;
    private MeJiGouJgjjTjAdapter meJiGouJgjjTjAdapter;

    private Subscription suscription;
    private static final String SEP1 = "#";
    private static final String SEP2 = "|";
    private static final String SEP3 = "=";

    /**
     * 接收选择图片的本地路径
     */
    private ArrayList<String> pathList;
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

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_jgjj_tj;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        meJgtjRecycler.setLayoutManager(linearLayoutManager);
        meJiGouJgjjTjAdapter = new MeJiGouJgjjTjAdapter(mContext, mList);
        meJgtjRecycler.setAdapter(meJiGouJgjjTjAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    public void getOrgan() {
//
//        suscription = NetWork.getMe().organ(CONTANCE.customerId, s)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer1);
    }

    public void getOrganUpload() {
        File file = new File(pathList.get(0));


        suscription = NetWork.getMe().organUpload(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }


    public void setBottondialog2() {
        bottomDialog2 = BottomDialog2.getInstance(R.layout.item_me_jg_jjtj, R.anim.in_bottom_to_top, getResources().getColor(R.color.translucent), new BottomDialog2.Callback() {

            @Override
            public void bind(View v) {
                v.findViewById(R.id.me_jg_spkc_tp).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectpic();
                    }
                });

                v.findViewById(R.id.me_jg_spkc_wb).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(MeJiGouJgjjTj.this, MeJiGouJgjjWb.class), 0);
                    }
                });

                v.findViewById(R.id.me_jg_spkc_quxiaox).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog2.setRemove();
                    }
                });

                v.findViewById(R.id.me_jg_spkc_quxiao).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog2.setRemove();
                    }
                });

            }
        });
        bottomDialog2.show(getSupportFragmentManager());
    }

    @OnClick({R.id.me_jg_jgjjTj_tj, R.id.me_jg_jgjj_wb_return, R.id.me_jg_jgjj_wb_text})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.me_jg_jgjjTj_tj:
                setBottondialog2();
                break;

            case R.id.me_jg_jgjj_wb_text:
                getOrganUpload();
                getOrgan();

//                meJgJgjjWbText.setText("编辑");
//                meJgJgjjWbText.setTextColor(this.getResources().getColor(R.color.color_main));
//                meJgJgjjWbScrollview2.setVisibility(View.VISIBLE);

                break;

            case R.id.me_jg_jgjj_wb_return:
                finish();
                break;
        }
    }

    public void selectpic() {
        SImagePicker
                .from(MeJiGouJgjjTj.this)
                .maxCount(1)
                .rowCount(3)
                .showCamera(true)
                .pickMode(SImagePicker.MODE_IMAGE)
                .forResult(101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                pathList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                for (int i = 0; i < pathList.size(); i++) {
                    MeJiGouJgjjTjBean meJiGouJgjjTjBean = new MeJiGouJgjjTjBean();
                    meJiGouJgjjTjBean.setContent(pathList.get(i));
                    meJiGouJgjjTjBean.setType(1);
                    mList.add(meJiGouJgjjTjBean);
                }
                bottomDialog2.setRemove();
                meJiGouJgjjTjAdapter.notifyDataSetChanged();
                break;

            case 110:
                String s = data.getExtras().getString("s");
                MeJiGouJgjjTjBean meJiGouJgjjTjBean = new MeJiGouJgjjTjBean();
                meJiGouJgjjTjBean.setContent(s);
                meJiGouJgjjTjBean.setType(0);
                mList.add(meJiGouJgjjTjBean);
                bottomDialog2.setRemove();
                meJiGouJgjjTjAdapter.notifyDataSetChanged();
                break;

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
