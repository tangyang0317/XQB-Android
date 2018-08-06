package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.RectangleAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.ChargeActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhangju.xingquban.R.id.recycler_pic;

//发表视频/音频
public class MeRecrousePopuYp extends BaseActivity {

    @BindView(R.id.me_recrouse_popw_ypHead)
    PublicHead meRecrousePopwYpHead;
    @BindView(R.id.line_video)
    LinearLayout lineVideo;
    @BindView(R.id.image_vedio_cover)
    ImageView imageVedioCover;
    @BindView(R.id.tv_resource_title)
    EditText tvResourceTitle;
    @BindView(R.id.tv_resource_introduce)
    EditText tvResourceIntroduce;
    @BindView(recycler_pic)
    RecyclerView recyclerPic;
    @BindView(R.id.me_recrouse_popw_ypsc)
    LinearLayout meRecrousePopwYpsc;
    @BindView(R.id.tv_resource_charge)
    TextView tvResourceCharge;
    @BindView(R.id.my_lesson)
    LinearLayout myLesson;
    @BindView(R.id.tv_resource_address)
    TextView tvResourceAddress;
    @BindView(R.id.me_recrouse_popw_tp)
    LinearLayout meRecrousePopwTp;
    @BindView(R.id.image_fengmian)
    ImageView imageFengmian;
    @BindView(R.id.tv_fengmian)
    TextView tvFengmian;
    private RectangleAdapter adapter;
    private List<String> mVidioList = new ArrayList<>();
    private List<String> mListTime = new ArrayList<>();
    private View viewFoot;

    private String title;
    private String introduce;
    private String headPic;
    private String videioUrl;
    private Double price;
    private Integer isCharge;
    private String address;
    private String latitude;
    private String longitude;

    @Override
    public int getLayout() {
        return R.layout.activity_me_recrouse_popu_yp;
    }

    @Override
    public void initView() {
        setMeRecrousePopwYpHead();
        intiViewFoot();
        setVideoAdapter();
        for (int i = 0; i < 10; i++) {
            mVidioList.add("https://gma.alicdn.com/bao/uploaded/i3/97671248/TB2GrgiagMPMeJjy1XdXXasrXXa_!!0-saturn_solar.jpg_sum.jpg");
            mListTime.add("12:12");
        }
    }

    private void intiViewFoot() {
        viewFoot = LayoutInflater.from(MeRecrousePopuYp.this).inflate(R.layout.picture_item, null);
        viewFoot.findViewById(R.id.pic_delete).setVisibility(View.GONE);
        viewFoot.findViewById(R.id.photo_image).setBackgroundResource(R.mipmap.me_recrouse_ypbg);
    }

    private void setVideoAdapter() {
        adapter = new RectangleAdapter(mContext, mVidioList, mListTime, 1, viewFoot);
        ScrollGridManager scrollGridManager = new ScrollGridManager(MeRecrousePopuYp.this, 4);
        scrollGridManager.setScrollEnabled(false);
        recyclerPic.setLayoutManager(scrollGridManager);
        recyclerPic.setAdapter(adapter);
    }

    @Override
    public void initData() {
    }


    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public void setMeRecrousePopwYpHead() {
        meRecrousePopwYpHead.setTitle("发表视频/音频");
        meRecrousePopwYpHead.setShow(true, false, true);
        meRecrousePopwYpHead.setRightTitle("发布");
        meRecrousePopwYpHead.setRightTextColor(R.color.color_main);
        meRecrousePopwYpHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meRecrousePopwYpHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=tvResourceTitle.getText().toString().trim();
                introduce=tvResourceIntroduce.getText().toString().trim();




            }
        });
    }

    @OnClick({R.id.line_video, R.id.me_recrouse_popw_tp,R.id.my_lesson})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //封面
            case R.id.line_video:
                SImagePicker
                        .from(MeRecrousePopuYp.this)
                        .maxCount(1)
                        .rowCount(3)
                        .showCamera(true)
                        .pickMode(SImagePicker.MODE_IMAGE)
                        .forResult(101);
                break;
            case R.id.my_lesson:
                Intent intent = new Intent(mContext, ChargeActivity.class);
                startActivityForResult(intent, 11);
                break;
            //地址选取
            case R.id.me_recrouse_popw_tp:
                Intent intent2 = new Intent(MeRecrousePopuYp.this, LocationActive.class);
                startActivityForResult(intent2, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
            tvFengmian.setVisibility(View.GONE);
            imageFengmian.setVisibility(View.GONE);
            imageVedioCover.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(piclist.get(0)).into(imageVedioCover);
        }

        //收费回调
        if (requestCode == 11 && resultCode == 22) {
            String money = data.getStringExtra("money");
            if (money.isEmpty()) {
                isCharge = 0;
                price = null;
            } else {
                isCharge = 1;
                price = Double.valueOf(money);
                tvResourceCharge.setText(money);
            }
        }
        //地理位置回调
        if (requestCode == 1 && resultCode == 2) {
            latitude = data.getStringExtra("lat");
            longitude = data.getStringExtra("lng");
            address = data.getStringExtra("address");
            tvResourceAddress.setText(address);
        }
    }
}
