package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的问答
 */
public class FindWenda extends BaseActivity {
    public static final String TAG = "FindWenda";
    @BindView(R.id.find_return)
    RelativeLayout findReturn;
    @BindView(R.id.findWd_tablayout)
    TabLayout findWdTablayout;
    @BindView(R.id.findWd_viewpage)
    ViewPager findWdViewpage;
    private Context mContext = this;
    private FindWendaTj findWendaTj;
    private FindWendaZx findWendaZx;

    @BindView(R.id.find_text_wodewenda)
    TextView findTextWodewenda;
    @BindView(R.id.linear_me_wodewenda)
    LinearLayout linearMeWodewenda;
    @BindView(R.id.find_tiwen)
    RelativeLayout findTiwen;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;

    private ListView mTypeLv;
    private PopupWindow typeSelectPopup;
    private List<String> testData;

    private ArrayAdapter<String> testDataAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_find_wenda;
    }

    @Override
    public void initView() {
        setTabLayout();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.find_text_wodewenda, R.id.find_tiwen, R.id.find_return})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.find_text_wodewenda:
                initSelectPopup();
                // 使用isShowing()检查popup窗口是否在显示状态
                if (typeSelectPopup != null && !typeSelectPopup.isShowing()) {
                    typeSelectPopup.showAsDropDown(findTextWodewenda,  -10,   10);
                }
                break;

            case R.id.find_tiwen:
                startActivity(new Intent(mContext, FindTabTiWen.class));
                break;

            case R.id.find_return:
                finish();
                break;
        }
    }

    private void initSelectPopup() {
        mTypeLv = new ListView(this);
        TestData();
        // 设置适配器
        testDataAdapter = new ArrayAdapter<String>(this, R.layout.popup_text_item, testData);
        mTypeLv.setAdapter(testDataAdapter);

        // 设置ListView点击事件监听
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //我的提问
                    case 0:
                        startActivity(new Intent(FindWenda.this, FindTiWen.class));
                        break;

                    //我的回答
                    case 1:
                        startActivity(new Intent(FindWenda.this, FindMeWenda.class));
                        break;

                    //我的草稿
                    case 2:
                        startActivity(new Intent(FindWenda.this, FindWendaCaog.class));
                        break;

                    //我的收藏
                    case 3:
                        startActivity(new Intent(FindWenda.this, FindMeShoucang.class));
                        break;
                }

                // 在这里获取item数据
                String value = testData.get(position);
                // 把选择的数据展示对应的TextView上
//                findTextWodewenda.setText(value);
                // 选择完后关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
        typeSelectPopup = new PopupWindow(mTypeLv, 270, ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(this, R.color.white);
        typeSelectPopup.setBackgroundDrawable(drawable);
        typeSelectPopup.setFocusable(true);
        typeSelectPopup.setOutsideTouchable(true);
        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                typeSelectPopup.dismiss();
            }
        });
    }

    /**
     * 模拟假数据
     */
    private void TestData() {
        testData = new ArrayList<>();
        testData.add("我的提问");
        testData.add("我的回答");
        testData.add("我的草稿");
        testData.add("我的收藏");
    }

    public void setTabLayout() {
        findWendaTj = new FindWendaTj();//推荐
        findWendaZx = new FindWendaZx();//最新

        fragmentList = new ArrayList<>();
        fragmentList.add(findWendaTj);
        fragmentList.add(findWendaZx);

        stringList = new ArrayList<>();
        stringList.add("推荐");
        stringList.add("最新");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        findWdTablayout.addTab(findWdTablayout.newTab().setText(stringList.get(0)));
        findWdTablayout.addTab(findWdTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        findWdViewpage.setAdapter(liveTabLayoutAdapter);

        findWdTablayout.setupWithViewPager(findWdViewpage);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
