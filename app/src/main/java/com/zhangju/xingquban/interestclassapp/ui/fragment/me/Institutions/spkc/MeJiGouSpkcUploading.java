package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeJiGouSpkcUploading extends BaseActivity {

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
    @BindView(R.id.mejg_scgl_scsp_number)
    EditText mejgScglScspNumber;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.mejg_scgl_scsp_money)
    TextView mejgScglScspMoney;
    @BindView(R.id.mejg_scgl_scsp_sfbz)
    RelativeLayout mejgScglScspSfbz;
    @BindView(R.id.mejg_scgl_scsp_CityText)
    TextView mejgScglScspCityText;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_spkc_upload;
    }

    @Override
    public void initView() {

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
}
