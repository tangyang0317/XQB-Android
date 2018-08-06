package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindSeekChoicenessXqBmxx extends BaseActivity {


    @BindView(R.id.find_seek_xq_bmxx_money)
    TextView findSeekXqBmxxMoney;
    @BindView(R.id.find_seek_xq_bmxx_moneyYouhuiq)
    TextView findSeekXqBmxxMoneyYouhuiq;
    @BindView(R.id.find_seek_xq_bmxxButton)
    Button findSeekXqBmxxButton;
    @BindView(R.id.find_seek_xq_bmxx_return)
    ImageView findSeekXqBmxxReturn;
    @BindView(R.id.find_seek_xq_bmxx_wanc)
    TextView findSeekXqBmxxWanc;
    @BindView(R.id.find_seek_xq_bmxx_zfsb)
    LinearLayout getFindSeekXqBmxxZfsb;
    @BindView(R.id.find_seek_xq_bmxx_jbxx)
    LinearLayout findSeekXqBmxxJbxx;
    @BindView(R.id.find_seek_xq_bmxxCxfqzf)
    Button findSeekXqBmxxCxfqzf;
    @BindView(R.id.find_seek_xq_bmxxCkpq)
    Button findSeekXqBmxxCkpq;
    @BindView(R.id.find_seek_xq_bmxx_imZfFalse)
    ImageView findSeekXqBmxxImZfFalse;
    @BindView(R.id.find_seek_xq_bmxx_imZfTrue)
    ImageView findSeekXqBmxxImZfTrue;
    @BindView(R.id.find_seek_xq_bmxx_txZfFalse)
    TextView findSeekXqBmxxTxZfFalse;
    @BindView(R.id.find_seek_xq_bmxx_txZfTrue)
    TextView findSeekXqBmxxTxZfTrue;

    @Override
    public int getLayout() {
        return R.layout.activity_find_seek_choiceness_xq_bmxx;
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

    @OnClick({R.id.find_seek_xq_bmxx_return, R.id.find_seek_xq_bmxxButton, R.id.find_seek_xq_bmxx_wanc, R.id.find_seek_xq_bmxxCxfqzf, R.id.find_seek_xq_bmxxCkpq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_seek_xq_bmxx_return:
                finish();
                break;
            case R.id.find_seek_xq_bmxx_wanc:
                finish();
                break;

            //立即支付
            case R.id.find_seek_xq_bmxxButton:
//                findSeekXqBmxxReturn.setVisibility(View.GONE);
//                findSeekXqBmxxWanc.setVisibility(View.VISIBLE);
//                findSeekXqBmxxJbxx.setVisibility(View.GONE);
//                getFindSeekXqBmxxZfsb.setVisibility(View.VISIBLE);
//                findSeekXqBmxxButton.setVisibility(View.GONE);
//                findSeekXqBmxxCxfqzf.setVisibility(View.VISIBLE);

                findSeekXqBmxxReturn.setVisibility(View.GONE);
                getFindSeekXqBmxxZfsb.setVisibility(View.VISIBLE);
                findSeekXqBmxxWanc.setVisibility(View.VISIBLE);
                findSeekXqBmxxJbxx.setVisibility(View.GONE);
                findSeekXqBmxxImZfFalse.setVisibility(View.GONE);
                findSeekXqBmxxTxZfFalse.setVisibility(View.GONE);
                findSeekXqBmxxImZfTrue.setVisibility(View.VISIBLE);
                findSeekXqBmxxTxZfTrue.setVisibility(View.VISIBLE);
                findSeekXqBmxxCkpq.setVisibility(View.VISIBLE);

                break;

            //查看重新支付
            case R.id.find_seek_xq_bmxxCxfqzf:
                findSeekXqBmxxReturn.setVisibility(View.VISIBLE);
                findSeekXqBmxxWanc.setVisibility(View.GONE);
                findSeekXqBmxxJbxx.setVisibility(View.VISIBLE);
                getFindSeekXqBmxxZfsb.setVisibility(View.GONE);
                findSeekXqBmxxButton.setVisibility(View.VISIBLE);
                findSeekXqBmxxCxfqzf.setVisibility(View.GONE);
                break;

            //查看票券
            case R.id.find_seek_xq_bmxxCkpq:

                break;
        }
    }
}
