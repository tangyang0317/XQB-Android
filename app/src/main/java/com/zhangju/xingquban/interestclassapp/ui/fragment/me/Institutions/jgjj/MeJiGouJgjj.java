package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.jgjj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeJiGouJgjj extends BaseActivity {
    public static final String TAG = "MeJiGouJgjj";
    private LinearLayout ll_tp, ll_wb;
    private ImageView quxiao, quxiaox;

    @BindView(R.id.me_jg_jgjj_head)
    PublicHead meJgJgjjHead;
    @BindView(R.id.me_jg_spkc_ljtj)
    Button meJgSpkcLjtj;
    private BottomDialog2 bottomDialog2;
    private ImageView mIvTitleIcon;

    /**
     * 接收选择图片的本地路径
     */
    private ArrayList<String> pathList;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_jgjj;
    }

    @Override
    public void initView() {
        setMeJgJgjjHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_jg_spkc_ljtj})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_jg_spkc_ljtj:
                startActivity(new Intent(MeJiGouJgjj.this, MeJiGouJgjjTj.class));
//                bottomDialog2 = BottomDialog2.getInstance(R.layout.item_me_jg_jjtj, R.anim.in_bottom_to_top, getResources().getColor(R.color.translucent), new BottomDialog2.Callback() {
//
//                    @Override
//                    public void bind(View v) {
//                        v.findViewById(R.id.me_jg_spkc_tp).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
////                                selectpic();
//                                startActivity(new Intent(MeJiGouJgjj.this, MeJiGouJgjjTj.class));
//                            }
//                        });
//                        v.findViewById(R.id.me_jg_spkc_wb).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
//                                startActivity(new Intent(MeJiGouJgjj.this, MeJiGouJgjjWb.class));
//                            }
//                        });
//                        v.findViewById(R.id.me_jg_spkc_quxiao).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
//                                bottomDialog2.setRemove();
//                            }
//                        });
//                        v.findViewById(R.id.me_jg_spkc_quxiaox).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
//                                bottomDialog2.setRemove();
//                            }
//                        });
//
//                    }
//                });
//                bottomDialog2.show(getSupportFragmentManager());
                break;

        }
    }

    public void selectpic() {
        SImagePicker
                .from(MeJiGouJgjj.this)
                .maxCount(1)
                .rowCount(3)
                .showCamera(true)
                .pickMode(SImagePicker.MODE_IMAGE)
                .forResult(101);
    }

    /**
     * 通过imagepath来绘制immageview图像
     *
     * @param imagPath
     */
    private void displayImage(String imagPath) {
        if (imagPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagPath);
            mIvTitleIcon.setImageBitmap(bitmap);
            startActivity(new Intent(MeJiGouJgjj.this, MeJiGouJgjjTj.class));
        } else {
            Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        switch (resultCode) {
            case 101:
                pathList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                displayImage(pathList.get(0));
                break;

            default:
                break;
        }
    }

    public void setMeJgJgjjHead() {
        meJgJgjjHead.setTitle("机构简介");
        meJgJgjjHead.setShow(true, false, false);
        meJgJgjjHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
