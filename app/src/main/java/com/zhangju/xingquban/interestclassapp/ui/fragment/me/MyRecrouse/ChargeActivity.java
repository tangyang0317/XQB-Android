package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//资源发布收费
public class ChargeActivity extends BaseActivity {
    public static final String ARG_RES_INT_PRICE ="price"; //收费标准，0为免费

    @BindView(R.id.me_recrouse_popw_ypHead)
    PublicHead meRecrousePopwYpHead;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.edit_cost)
    EditText editCost;
    @BindView(R.id.line_charge)
    LinearLayout lineCharge;
    int mPrice;

    @Override
    public int getLayout() {
        return R.layout.activity_charge;
    }

    @Override
    public void initView(){
        mPrice=getIntent().getIntExtra(ARG_RES_INT_PRICE,0);
        initHead();
    }

    private void initHead() {
        meRecrousePopwYpHead.setTitle("收费标准");
        meRecrousePopwYpHead.setShow(true, false, true);
        meRecrousePopwYpHead.setRightTitle("完成");
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
                String priceStr=editCost.getText().toString();
                mPrice = TextUtils.isEmpty(priceStr)?0:Integer.parseInt(editCost.getText().toString().trim());
                Intent intent = new Intent();
                intent.putExtra(ARG_RES_INT_PRICE, mPrice);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        if(mPrice>0) {
            tvRight.performLongClick();
            editCost.append(Integer.toString(mPrice));
        }
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

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                lineCharge.setVisibility(View.GONE);
                tvLeft.setTextColor(Color.WHITE);
                tvLeft.setBackgroundResource(R.drawable.left_red);
                tvRight.setTextColor(getResources().getColor(R.color.view_gray));
                tvRight.setBackgroundResource(R.drawable.right_gray);
                break;
            case R.id.tv_right:
                lineCharge.setVisibility(View.VISIBLE);
                tvRight.setBackgroundResource(R.drawable.right_red);
                tvRight.setTextColor(Color.WHITE);
                tvLeft.setTextColor(getResources().getColor(R.color.view_gray));
                tvLeft.setBackgroundResource(R.drawable.left_gray);
                break;

        }
    }
}
