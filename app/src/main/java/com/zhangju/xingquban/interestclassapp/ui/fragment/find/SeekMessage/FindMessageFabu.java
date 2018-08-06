package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.MessageSendBean;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

//信息平台发布
public class FindMessageFabu extends BaseActivity {

    @BindView(R.id.find_message_fabuCity)
    RelativeLayout findMessageFabuCity;
    @BindView(R.id.find_message_fabuHead)
    PublicHead findMessageFabuHead;
    @BindView(R.id.find_message_radio_zhaop)
    RadioButton findMessageRadioZhaop;
    @BindView(R.id.find_message_radio_zhuanr)
    RadioButton findMessageRadioZhuanr;
    @BindView(R.id.find_message_radio_zhaos)
    RadioButton findMessageRadioZhaos;
    @BindView(R.id.find_message_radio_zul)
    RadioButton findMessageRadioZul;
    @BindView(R.id.find_message_radiogroup1)
    RadioGroup findMessageRadiogroup1;
    @BindView(R.id.find_message_radio_qit)
    RadioButton findMessageRadioQit;
    @BindView(R.id.find_message_radiogroup2)
    RadioGroup findMessageRadiogroup2;
    @BindView(R.id.find_message_llxinxiliebie)
    LinearLayout findMessageLlxinxiliebie;
    @BindView(R.id.edit_fabu_title)
    EditText editFabuTitle;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_fabu_city)
    TextView tvFabuCity;
    @BindView(R.id.edit_fabu_content)
    EditText editFabuContent;
    int position = 0;

    private String type = "";//发布信息类别   1  (招生)  2  (招聘) 3  （租赁） 4  （转租）
    private String strTitle;//发布信息标题
    private String strCity;//发布城市
    private String strDescription;//发布描述

    private Subscription subscription;


    @Override
    public int getLayout() {
        return R.layout.activity_find_message_fabu;
    }

    @Override
    public void initView() {
        setFindMessageFabuHead();
        initGroup();
    }

    private void initGroup() {
        //按钮状态的重置
        type = "";
        findMessageRadiogroup1.setOnCheckedChangeListener(listener1);
        findMessageRadiogroup2.setOnCheckedChangeListener(listener2);

    }


    RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            findMessageRadiogroup2.setOnCheckedChangeListener(null);
            findMessageRadiogroup2.clearCheck();
            findMessageRadiogroup2.setOnCheckedChangeListener(listener2);
        }
    };
    RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            findMessageRadiogroup1.setOnCheckedChangeListener(null);
            findMessageRadiogroup1.clearCheck();
            findMessageRadiogroup1.setOnCheckedChangeListener(listener1);
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.find_message_fabuCity, R.id.find_message_radio_qit, R.id.find_message_radio_zhaos, R.id.find_message_radio_zhuanr, R.id.find_message_radio_zhaop})
    public void onClick(View v) {
        switch (v.getId()) {
            //招聘
            case R.id.find_message_radio_zhaop:
                type = "2";
                break;
            //转让
            case R.id.find_message_radio_zhuanr:
                type = "4";
                break;
            //招生
            case R.id.find_message_radio_zhaos:
                type = "1";
                break;
            //租赁
            case R.id.find_message_radio_zul:
                type = "3";
                break;
            //其他
            case R.id.find_message_radio_qit:
                type = null;
                break;
            //城市选择
            case R.id.find_message_fabuCity:
                Intent intent = new Intent(this, FindMessageFabuCity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //接收选中的城市
        if (requestCode == 1 && resultCode==RESULT_OK) {
            String city = data.getStringExtra("city");
            String[] split = city.split(",");
            position = Integer.parseInt(split[1]);//位置
            tvFabuCity.setText(split[0]);//具体城市
        }
    }

    public void setFindMessageFabuHead() {
        findMessageFabuHead.setTitle("发布信息");
        findMessageFabuHead.setShow(true, false, true);
        findMessageFabuHead.setRightTitle("发布");
        findMessageFabuHead.setRightTextColor(R.color.color_main);
        findMessageFabuHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findMessageFabuHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strTitle = editFabuTitle.getText().toString().trim();
                strCity = tvFabuCity.getText().toString().trim();
                strDescription = editFabuContent.getText().toString().trim();
                if (strTitle.isEmpty()) {
                    ToastUtil.showToast("请输入标题");
                } else if (strCity.isEmpty()) {
                    ToastUtil.showToast("请选择城市");
                } else {
                    try {
                        User user = UserManager.getInstance().getUser();
                        String teacherTimeId = user.teacherTimeId;

                        sendInfoData(teacherTimeId);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }


        });
    }

    //信息发布
    private void sendInfoData(String id) {

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("teacherTimeId", id + "");
        params.addBodyParameter("infoType", type + "");
        params.addBodyParameter("stime", "");
        params.addBodyParameter("etime", "");
        params.addBodyParameter("title", strTitle);
        params.addBodyParameter("cityName", strCity);
        params.addBodyParameter("infoContent", strDescription);
        String url = UrlUtils.URL_MESSAGE_PLATFORM;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        MessageSendBean homeRecylerBean = JSONObject.parseObject(json, MessageSendBean.class);
                        try {
                            boolean success = homeRecylerBean.isSuccess();
                            if (success) {
                                ToastUtil.showToast("发布成功");
                                finish();
                            } else {
                                ToastUtil.showToast("发布失败,请重试");
                            }

                        } catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
