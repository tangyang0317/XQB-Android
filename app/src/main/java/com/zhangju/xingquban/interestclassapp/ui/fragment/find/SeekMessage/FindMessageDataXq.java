package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity.bean.LooktabListBean;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


@ContentView(R.layout.activity_find_message_data_xq)
public class FindMessageDataXq
        extends FastActivity {

    @BindView(R.id.meMessage_dataXq)
    PublicHead meMessageDataXq;
    @BindView(R.id.find_messageXq_image)
    ImageView  findMessageXqImage;
    @BindView(R.id.me_messageXq_title)
    TextView   meMessageXqTitle;
    @BindView(R.id.me_messageXq_name)
    TextView   meMessageXqName;
    @BindView(R.id.me_messageXq_time)
    TextView   meMessageXqTime;
    @BindView(R.id.me_messageXq_city)
    TextView   meMessageXqCity;
    @BindView(R.id.me_messageXq_content)
    TextView   meMessageXqContent;
    @BindView(R.id.home_banner)
    Banner     mHomeBanner;
    private MyShareDialog mMyShareDialog;
    private BannerHelper  mBannerHelper;


    public void setMeMessageDataXq() {
        meMessageDataXq.setTitle("信息平台");
        meMessageDataXq.setShow(true, true, false);
        meMessageDataXq.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meMessageDataXq.setImgSearch(R.drawable.nav_btn_share_red);
        meMessageDataXq.setImgSeachRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyShareDialog.show();
            }
        });
    }

    @Override
    protected void alreadyPrepared() {
        try {
            setMeMessageDataXq();
            Bundle bundle = getIntent().getExtras();
            FindMessageBean.AaDataBean meMessageData = (FindMessageBean.AaDataBean) bundle.getSerializable("MeMessageData");
            meMessageXqTitle.setText(meMessageData.getTitle());
            meMessageXqName.setText(meMessageData.getTeacherName());
            String s = meMessageData.getAddUserTime();
            //            s = s.substring(0, s.indexOf(" "));
            meMessageXqTime.setText(s);
            meMessageXqCity.setText(meMessageData.getCityName());
            meMessageXqContent.setText(meMessageData.getInfoContent());

            int infoType = meMessageData.getInfoType();
            String id = meMessageData.getId();

            initShare(meMessageData);
            addbrowse(id, infoType);

            initBanner();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("18");
    }

    private void initShare(FindMessageBean.AaDataBean meMessageData) {
        mMyShareDialog = new MyShareDialog(mContext);
        String shareImage = null;
        String shareUrl = MyApp.URL + "/share/#/merchant/info?id=" + meMessageData.getId();
        String shareContent = meMessageData.getInfoContent();
        String shareTitle = meMessageData.getTitle();
        mMyShareDialog.initShare(shareImage, shareUrl, shareContent, shareTitle);
    }

    private void addbrowse(String id, int infoType) {
        final Request request = Request.obtain(MessageInterface.POST_ADD_VISITE);
        String token = UserManager.getInstance().getToken();
        request.addHeader("X-CustomToken", token);
        request.put("id", id);
        request.put("infoType", infoType);
        request.setListener(new SimpleListener<LooktabListBean>() {
            @Override
            public void onResponseListener(Request r, LooktabListBean result) {

            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBannerHelper.stopBanner();
    }
}
