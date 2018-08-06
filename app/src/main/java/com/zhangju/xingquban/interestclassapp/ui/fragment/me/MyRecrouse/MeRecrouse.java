package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp.MeRecrousePopuYp;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp.SendNewsActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp.SendPictureActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.util.UtilAnim;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.util.UtilBitmap;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.util.UtilScreenCapture;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的 资源管理
 */

public class MeRecrouse extends BaseActivity {
    @BindView(R.id.me_recrouse_head)
    PublicHead meRecrouseHead;
    @BindView(R.id.me_recrouse_tablayout)
    TabLayout meRecrouseTablayout;
    @BindView(R.id.me_recrouse_viewpage)
    ViewPager meRecrouseViewpage;
    @BindView(R.id.me_recrouse_tianjia)
    Button meRecrouseTianjia;
    @BindView(R.id.iv_popup_window_back)
    ImageView ivPopupWindowBack;
    @BindView(R.id.tv_close_popup_window)
    TextView tvClosePopupWindow;
    @BindView(R.id.rl_popup_window)
    RelativeLayout rlPopupWindow;

    @BindView(R.id.me_recrouse_popw_yp)
    LinearLayout meRecrousePopwYp;
    @BindView(R.id.me_recrouse_popw_tp)
    LinearLayout meRecrousePopwTp;
    @BindView(R.id.me_recrouse_popw_zx)
    LinearLayout meRecrousePopwZx;

    private List<Fragment> listFragment=new ArrayList<>();
    private List<String> listTitle= Arrays.asList("最近","我发布的");

    private RecentFragment recentFragment;//最近
    private MySendFragment mySendFragment;//我发布的

    private LiveTabLayoutAdapter liveTabLayoutAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_me_recrouse;
    }

    @Override
    public void initView() {
        getMeCollectHead();
        setResTabLayout();
    }

    private void setResTabLayout() {
        recentFragment=new RecentFragment();//最近
        mySendFragment=new MySendFragment();//我发布的
        listFragment.add(recentFragment);
        listFragment.add(mySendFragment);

        meRecrouseTablayout.addTab(meRecrouseTablayout.newTab().setText(listTitle.get(0)));
        meRecrouseTablayout.addTab(meRecrouseTablayout.newTab().setText(listTitle.get(1)));



        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), listFragment, listTitle);
        meRecrouseViewpage.setAdapter(liveTabLayoutAdapter);
        meRecrouseTablayout.setupWithViewPager(meRecrouseViewpage);



    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_recrouse_tianjia, R.id.tv_close_popup_window,   R.id.me_recrouse_popw_yp,
            R.id.me_recrouse_popw_tp, R.id.me_recrouse_popw_zx
    })
    public void onClick(View v) {
        //资源发布

        switch (v.getId()) {

            //打开popup
            case R.id.me_recrouse_tianjia:
                clickPopupWindow();
                break;

            //关闭popup
            case R.id.tv_close_popup_window:
                clickClosePopupWindow();
                break;




            //popwindow音频/视频
            case R.id.me_recrouse_popw_yp:
                startActivity(new Intent(MeRecrouse.this, MeRecrousePopuYp.class));
                break;

            //popwindow图片
            case R.id.me_recrouse_popw_tp:
                startActivity(new Intent(MeRecrouse.this, SendPictureActivity.class));
                break;

            //popwindow资讯
            case R.id.me_recrouse_popw_zx:
                startActivity(new Intent(MeRecrouse.this, SendNewsActivity.class));
                break;
        }
    }

    public void getMeCollectHead() {
        meRecrouseHead.setTitle("资源管理");
        meRecrouseHead.setShow(true, false, true);
        meRecrouseHead.setRightTitle("发布");
        meRecrouseHead.setRightTextColor(R.color.color_main);
        meRecrouseHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meRecrouseHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPopupWindow();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    /**
     * 显示弹窗
     */
    private void clickPopupWindow() {
        // 获取截图的Bitmap
        Bitmap bitmap = UtilScreenCapture.getDrawing(this);

        if (bitmap != null) {
            // 将截屏Bitma放入ImageView
            ivPopupWindowBack.setImageBitmap(bitmap);
            // 将ImageView进行高斯模糊【25是最高模糊等级】【0x77000000是蒙上一层颜色，此参数可不填】
            UtilBitmap.blurImageView(this, ivPopupWindowBack, 25, 0x77000000);
        } else {
            // 获取的Bitmap为null时，用半透明代替
            ivPopupWindowBack.setBackgroundColor(0x77000000);
        }

        // 打开弹窗
        UtilAnim.showToUp(rlPopupWindow, ivPopupWindowBack);

    }

    /**
     * 关闭弹窗
     */
    private void clickClosePopupWindow() {
        UtilAnim.hideToDown(rlPopupWindow, ivPopupWindowBack);
    }
}
