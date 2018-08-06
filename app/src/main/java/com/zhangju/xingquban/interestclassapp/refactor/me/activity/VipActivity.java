package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.VipPayDialogFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

/**
 * Created by sgfb on 2017/11/11.
 * 会员模块
 */
@ContentView(R.layout.act_vip)
public class VipActivity extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.vipFlag)
    TextView mVipFlag;
    @Bind(R.id.personVipLayout)
    View mPersonVipLayout;
    @Bind(R.id.orgVipLayout)
    View mOrgVipLayout;
    @Bind(R.id.openVip)
    View mOpenVip;

    @Override
    protected void alreadyPrepared() {
        User user = UserManager.getInstance().getUser();

        mName.setText(user.signame);
        Glide.with(this).load(user.picture).into(mAvatar);
        if (user.degree != null) {
            if (user.degree.isOrganization || user.degree.isTeacher) {
                mOrgVipLayout.setVisibility(View.VISIBLE);
                mPersonVipLayout.setVisibility(View.GONE);
                mVipFlag.setText("商户会员");
                mVipFlag.setCompoundDrawablesWithIntrinsicBounds(user.isMember ? R.mipmap.vip_icon_merchantvip_select_17_17 : R.mipmap.vip_icon_merchantvip_normal_17_17, 0, 0, 0);
            } else {
                mOrgVipLayout.setVisibility(View.GONE);
                mPersonVipLayout.setVisibility(View.VISIBLE);
                mVipFlag.setText("普通会员");
                mVipFlag.setCompoundDrawablesWithIntrinsicBounds(user.isMember ? R.mipmap.vip_icon_uservip_select_17_17 : R.mipmap.vip_icon_uservip_normal_17_17, 0, 0, 0);
            }
        }
        mOpenVip.setVisibility(user.isMember ? View.GONE : View.VISIBLE);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 开通会员
     */
    @Bind(R.id.openVip)
    private void openVip() {
        int userType = 1;
        if (UserManager.getInstance().getUser().degree != null)
            userType = UserManager.getInstance().getUser().degree.isOrganization ? 2 : 1;
        VipPayDialogFragment payDialog = VipPayDialogFragment.getInstance(this, userType);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, payDialog)
                .commit();
    }

    /**
     * 打开信息平台
     */
    @Bind(R.id.platformInfo)
    private void openPlatformInfo() {
        if(UserManager.getInstance().getUser().isMember)
        startActivity(new Intent(VipActivity.this, MessagePlatformActivity.class));
    }

    /**
     * 打开生源推荐
     */
    @Bind(R.id.studentResRecommend)
    private void openStudentResRecommend() {
        if(UserManager.getInstance().getUser().isMember)
        startActivity(new Intent(VipActivity.this, StudentIntroduceActivity.class));
    }
}
