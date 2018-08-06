package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseAttach;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrderCourseAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrderDetailCodeAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.QrcodeAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCheckCourse;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/13.
 * 工单详情 (复用我的工单和商户工单详情)
 */
@ContentView(R.layout.act_order_detail)
public class OrderDetailActivity extends FastActivity{
    public static final String ARG_BOOL_ORDER_IS_BUSINESS ="isBusiness";
    public static final String ARG_STR_ORDER_ID="orderId";

    @LocalData(ARG_BOOL_ORDER_IS_BUSINESS)
    boolean isBusiness;
    @LocalData(ARG_STR_ORDER_ID)
    String mOrderId;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.headLayout)
    View mHeadLayout;
    @Bind(R.id.status)
    TextView mStatus;
    @Bind(R.id.price)
    TextView mPrice;
    @Bind(R.id.orderNum)
    TextView mOrderNum;
    @Bind(R.id.orderCreateTime)
    TextView mOrderCreateTime;
    @Bind(R.id.payTime)
    TextView mPayTime;
    @Bind(R.id.phone)
    TextView mPhone;
    @Bind(R.id.serverPhoneNum)
    TextView mServerPhone;
    @Bind(R.id.serverWorkTime)
    TextView mServerWorkTime;
    @Bind(R.id.notice)
    TextView mNotice;
    @Bind(R.id.connectCustomer)
    View mConnectCustomer;
    @Bind(R.id.cancel)
    View mCancelOrder;
    @Bind(R.id.pay)
    View mPay;
    @Bind(R.id.customerLayout)
    View mCustomerLayout;
    @Bind(R.id.serverLayout)
    View mServerLayout;
    @Bind(R.id.customerPhone)
    TextView mCustomerPhone;
    @Bind(R.id.customerName)
    TextView mCustomerName;
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.orderId)
    TextView mOrderIdTv;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.validQrcodeCount)
    TextView mValidQrcodeCount;
    @Bind(R.id.noticeLayout)
    View mNoticeLayout;
    @Bind(R.id.orderBar)
    View mOrderBar;
    @Bind(R.id.bottomLine)
    View mBottomLine;
    @Bind(R.id.qrCodeListLayout)
    View mQrCodeLayout;
    @Bind(R.id.qrCodeList)
    ListView mQrcodeList;
    @Bind(R.id.toComment)
    View mToComment;
    OrderCourseAdapter mAdapter;
    QrcodeAdapter mQrcodeAdapter;
    ResponseOrder mData;

    @Override
    protected void alreadyPrepared(){
        if(isBusiness){
            mPay.setVisibility(View.GONE);
            mCancelOrder.setVisibility(View.GONE);
            mPhone.setVisibility(View.GONE);
            mServerLayout.setVisibility(View.GONE);
            mNoticeLayout.setVisibility(View.GONE);
        }
        else{
            mHeadLayout.setBackgroundColor(Color.parseColor("#FB9678"));
            mConnectCustomer.setVisibility(View.GONE);
            mCustomerLayout.setVisibility(View.GONE);
            mOrderBar.setVisibility(View.GONE);
        }
        mList.setAdapter(mAdapter=new OrderCourseAdapter(this));
        mQrcodeList.setAdapter(mQrcodeAdapter=new QrcodeAdapter(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestOrderDetail();
    }

    private void requestOrderDetail(){
        Request request=Request.obtain(MeInterface.POST_ORDER_LIST);
        request.put("isBussiness",isBusiness);
        request.put("sid",mOrderId);
        request.setListener(new SimpleListener<ResponseAttach<List<ResponseOrder>,ResponseCheckCourse>>(){
            @Override
            public void onResponseListener(Request r,ResponseAttach<List<ResponseOrder>,ResponseCheckCourse> result) {
                if(result.success){
                    inflaterData(mData=result.data.get(0),result.attachData==null?null:result.attachData.ordersQCode);
                }
            }
        });
        net(request);
    }

    @Bind(R.id.pay)
    private void payNow(){
        if(mData==null){
            N.showShort(this,"数据加载中");
            return;
        }
        Intent intent=new Intent(this,OrderPayActivity.class);
        intent.putExtra(OrderPayActivity.ARG_SER_ORDER,mData);
        startActivity(intent);
    }

    @Bind(R.id.cancel)
    private void cancel(){
        FastDialog.showMessageDialog("确认取消？",true).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request=Request.obtain(MeInterface.POST_ORDER_CANCEL);
                request.put("isBussiness",0);
                request.put("sid",mOrderId);
                request.setListener(new SimpleListener<Response>(){

                    @Override
                    public void onResponseListener(Request r, Response result) {
                        if(result.success){
                            N.showLong(OrderDetailActivity.this,"已取消");
                            requestOrderDetail();
                        }
                    }
                });
                net(request);
            }
        });
    }

    @Bind(R.id.connectCustomer)
    private void connectCustomer(){
        if(mData==null) return;
        mPermissionHelper.requestPermission(this, new String[]{Manifest.permission.CALL_PHONE}, new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+mData.phone));
                try{
                    startActivity(intent);
                }catch (SecurityException e){
                    e.printStackTrace();
                }
            }
        }, new Runnable() {
            @Override
            public void run() {
                N.showShort(OrderDetailActivity.this,"请给予拨打电话的权限");
            }
        });
    }

    /**
     * 填充数据到试图
     * @param order 数据
     */
    private void inflaterData(ResponseOrder order, List<ResponseCheckCourse.QrCode> codes){
        DecimalFormat df=new DecimalFormat("##.##");

        //根据状态调整底部按键
        mBottomLine.setVisibility(order.status==ResponseOrder.STATUS_UNPAY||isBusiness?View.VISIBLE:View.INVISIBLE);
        mPayTime.setVisibility(order.status==ResponseOrder.STATUS_UNPAY||order.status==ResponseOrder.STATUS_CANCEL?View.GONE:View.VISIBLE);
        mDate.setText(order.createTimeStr);
        mOrderIdTv.setText(String.format(Locale.getDefault(),"订单号：%s",order.sno));
        mStatus.setText(order.getStatusFormat());

        mPrice.setText("订单总价 "+String.format(Locale.getDefault(),"￥%s",df.format(order.amount)));
        mOrderNum.setText(String.format(Locale.getDefault(),"订单编号：%s",safeNullStr(order.sno)));
        mPayTime.setText(String.format(Locale.getDefault(),"付款时间：%s",safeNullStr(order.payTime)));
        mOrderCreateTime.setText(String.format(Locale.getDefault(),"下单时间：%s",safeNullStr(order.addUserTime)));
        mPhone.setText(String.format(Locale.getDefault(),"手机号码：%s",safeNullStr(order.phone)));
        if((order.status==ResponseOrder.STATUS_PAYED||
                order.status==ResponseOrder.STATUS_VALID||
                order.status==ResponseOrder.STATUS_USED)&&!isBusiness){
            mQrCodeLayout.setVisibility(View.VISIBLE);
            mNoticeLayout.setVisibility(View.GONE);
            mValidQrcodeCount.setText(String.format(Locale.getDefault(),"可使用劵%d张",order.qcodeNum-order.scanQcodeNum));
            mQrcodeAdapter.setData(codes);
        }
        else{
            mQrCodeLayout.setVisibility(View.GONE);
            mNoticeLayout.setVisibility(View.VISIBLE);
        }
        if(order.dlist!=null){
            List<ResponseOrder.Lesson> list=new ArrayList<>();
            for(ResponseOrder.OrderData od:order.dlist)
                list.add(od.lesson);
            mAdapter.setData(list);
        }
        if(order.customer!=null){
            mCustomerName.setText(String.format(Locale.getDefault(),"买家姓名：%s",safeNullStr(order.customer.username)));
            mCustomerPhone.setText(String.format(Locale.getDefault(),"手机号码：%s",safeNullStr(order.customer.phone)));
        }
        if(order.dlist!=null&&!order.dlist.isEmpty()){
            if(order.dlist.get(0).serverInfo!=null){
                ResponseOrder.ServerInfo si=order.dlist.get(0).serverInfo;
                mServerPhone.setText(String.format(Locale.getDefault(),"客服电话：%s",safeNullStr(si.getContactTel())));
                mServerWorkTime.setText(String.format(Locale.getDefault(),"工作时间：%s",safeNullStr(si.workTime)));
                mNotice.setText(si.getNotice());
            }
        }
    }

    @Bind(R.id.moreQrcode)
    private void moreQrcode(){
        if(mData==null) return;
        Intent intent=new Intent(this,CheckCourseCodeActivity.class);
        intent.putExtra(CheckCourseCodeActivity.ARG_STR_ID,mData.id);
        startActivity(intent);
    }

    @Bind(R.id.toComment)
    private void toComment(){
        startActivity(OrderCommentActivity.class);
    }

    private String safeNullStr(String s){
        return TextUtils.isEmpty(s)?"":s;
    }
}
