package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindSeekChoicenessXqFbzpYulan extends BaseActivity {


    @BindView(R.id.find_seek_choiceness_xqfbzp_yulan)
    PublicHead findSeekChoicenessXqfbzpYulan;
    @BindView(R.id.headicon)
    CustomRoundView headicon;

    @Override
    public int getLayout() {
        return R.layout.activity_find_seek_choiceness_xq_fbzp_yulan;
    }

    @Override
    public void initView() {
        setFindSeekChoicenessXqfbzpYulan();
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

    public void setFindSeekChoicenessXqfbzpYulan() {
        findSeekChoicenessXqfbzpYulan.setTitle("作品详情");
        findSeekChoicenessXqfbzpYulan.setShow(true, false, true);
        findSeekChoicenessXqfbzpYulan.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findSeekChoicenessXqfbzpYulan.setRightTitle("发布");
        findSeekChoicenessXqfbzpYulan.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("发布成功");
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
