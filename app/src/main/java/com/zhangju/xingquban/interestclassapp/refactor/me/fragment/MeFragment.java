package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ActiveActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ApplyToMemberActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.BusinessOrderActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CollectionActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CommentActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.FeedbackActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.InviteActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.MyLiveRoomActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.NotificationActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrderActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrgManagerActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.PersonDataActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ResourceManageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.SettingsActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.VipActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.WalletActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.EventLogin;
import com.zhangju.xingquban.interestclassapp.refactor.user.EventUserDataChanged;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/10/25.
 * 个人中心一级页面
 */
@ContentView(R.layout.frag_me)
public class MeFragment extends FastFragment{
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.vipFlag)
    ImageView mVipFlag;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.roleAndLocation)
    TextView mRoleAndLocation;
    @Bind(R.id.orgProfile)
    TextView mOrgProfile;
    @Bind(R.id.openVip)
    View mOpenVip;
    @Bind(R.id.openVipLayout)
    View mOpenVipLayout;
    @Bind(R.id.vendorOrder)
    View mVendorOrder;

    @Override
    protected void alreadyPrepared(){
        if(UserManager.getInstance().isLogin()) loginState();
        else logoutState();
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestRefreshUserData();
            }
        });
    }

    /**
     * 刷新用户数据
     */
    private void requestRefreshUserData(){
        if(!UserManager.getInstance().isLogin()) {
            mRefresh.setRefreshing(false);
            return;
        }
        Request request=Request.obtain(MeInterface.POST_USER_DATA);
        request.setListener(new SimpleListener<Response<List<User>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<User>> result){
                mRefresh.setRefreshing(false);
                if(result.success) {
                    UserManager.getInstance().refreshUser(result.data.get(0));
                    inflateUserData();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                mRefresh.setRefreshing(false);
            }
        });
        net(request);
    }

    /**
     * 进入VIP界面
     */
    @Bind({R.id.openVip,R.id.vipFlag})
    private void openVip(){
        startActivity(VipActivity.class);
    }

    /**
     * 用户信息
     */
    @Bind(R.id.userLine)
    private void openUserData(){
        if(!checkLogin()) return;
        startActivity(PersonDataActivity.class);
    }

    /**
     * 设置
     */
    @Bind(R.id.settings)
    private void openSettings(){
        if(!checkLogin()) return;
        startActivity(SettingsActivity.class);
    }

    /**
     * 邀请有奖
     */
    @Bind(R.id.invite)
    private void invite(){
        if(!checkLogin()) return;
        startActivity(InviteActivity.class);
    }

    /**
     * 我的钱包
     */
    @Bind(R.id.wallet)
    private void wallet(){
        if(!checkLogin()) return;
        startActivity(WalletActivity.class);
    }

    /**
     * 我的订单
     */
    @Bind(R.id.order)
    private void order(){
        if(!checkLogin()) return;
        startActivity(OrderActivity.class);
    }

    /**
     * 商户订单
     */
    @Bind(R.id.vendorOrder)
    private void vendorOrder(){
        if(!checkLogin()) return;
        startActivity(BusinessOrderActivity.class);
    }

    /**
     * 消息
     */
    @Bind({R.id.notification,R.id.notificationLayout})
    private void notification(){
        if(!checkLogin()) return;
        startActivity(NotificationActivity.class);
    }

    /**
     * 我的评论
     */
    @Bind(R.id.toComment)
    private void openComment(){
        if(!checkLogin()) return;
        startActivity(CommentActivity.class);
    }

    /**
     * 反馈
     */
    @Bind(R.id.feedback)
    private void openFeedback(){
        if(!checkLogin()) return;
        startActivity(FeedbackActivity.class);
    }

    /**
     * 我的收藏
     */
    @Bind(R.id.collection)
    private void openCollection(){
        if(!checkLogin()) return;
        startActivity(CollectionActivity.class);
    }

    /**
     * 我的活动
     */
    @Bind(R.id.active)
    private void openActve(){
        if(!checkLogin()) return;
        N.showShort(getContext(),"敬请期待");
//        startActivity(ActiveActivity.class);
    }

    /**
     * 注册或管理机构
     */
    @Bind(R.id.teacherRegister)
    private void openOrgManager(){
        if(!checkLogin()) return;
        if(UserManager.getInstance().getUser().status==1){
            N.showShort(getContext(),"正在审核中,请耐心等待");
            return;
        }
        User.Degree degree=UserManager.getInstance().getUser().degree;
        if(degree!=null){
            if(degree.isTeacher||degree.isOrganization)
                startActivity(OrgManagerActivity.class);
            else startActivity(ApplyToMemberActivity.class);
        }
    }

    /**
     * 我的直播间
     */
    @Bind(R.id.livingRoom)
    private void openMyLivingRoom(){
        if(!checkLogin()) return;
        startActivity(MyLiveRoomActivity.class);
    }

    /**
     * 资源管理
     */
    @Bind(R.id.resourceManage)
    private void openResourceManage(){
        if(!checkLogin()) return;
        startActivity(ResourceManageActivity.class);
    }

    @Event
    private void eRefreshUserData(EventUserDataChanged event){
        inflateUserData();
    }

    @Event
    private void eLoginStateChanged(EventLogin event){
        if(event.isLogin()) loginState();
        else logoutState();
    }

    /**
     * 登出状态
     */
    private void logoutState(){
        mName.setText("未登录");
        mAvatar.setImageResource(R.mipmap.me_touxiang);
        mRoleAndLocation.setVisibility(View.GONE);
        mOpenVipLayout.setVisibility(View.GONE);
        mRefresh.setEnabled(false);
    }

    /**
     * 登入状态
     */
    private void loginState(){
        mRefresh.setEnabled(true);
        inflateUserData();
        requestRefreshUserData();
    }

    /**
     * 填充用户数据到视图
     */
    private void inflateUserData(){
        User user=UserManager.getInstance().getUser();

        mOpenVipLayout.setVisibility(View.VISIBLE);
        mName.setText((user.degree!=null&&user.degree.isTeacher)?user.username:user.signame);
        mRoleAndLocation.setText(String.format(Locale.getDefault(),"%s  %s",
                TextUtils.isEmpty(user.degree.nameCn)?"":user.degree.nameCn,
                TextUtils.isEmpty(user.cityName)?"":user.cityName));
        mRoleAndLocation.setVisibility(View.VISIBLE);
        if(user.degree!=null){
            if(user.degree.isOrganization||user.degree.isTeacher){
                mVendorOrder.setVisibility(View.VISIBLE);
                mOrgProfile.setText("机构管理");
            }
            else {
                mVendorOrder.setVisibility(View.GONE);
                mOrgProfile.setText("申请成为老师/机构");
            }
        }
        else {
            mVendorOrder.setVisibility(View.GONE);
            mOrgProfile.setText("申请成为老师/机构");
        }
        if(user.isMember){
            mOpenVip.setVisibility(View.GONE);
            mVipFlag.setVisibility(View.VISIBLE);
            mVipFlag.setImageResource(R.mipmap.vip_merchant_74_18);
        }
        else{
            mOpenVip.setVisibility(View.VISIBLE);
            mVipFlag.setVisibility(View.GONE);
            mVipFlag.setImageResource(R.mipmap.vip_user_74_18);
        }
        Glide.with(this)
                .load(user.picture)
                .placeholder(R.mipmap.me_touxiang)
                .error(R.mipmap.me_touxiang)
                .dontTransform()
                .dontAnimate()
                .into(mAvatar);
    }

    /**
     * 检查登录状态，如果未登录调起登录界面
     */
    private boolean checkLogin(){
        if(UserManager.getInstance().isLogin()) return true;
        else{
            startActivity(LoginActivity.class);
            return false;
        }
    }
}