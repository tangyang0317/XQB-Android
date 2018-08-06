package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.PayTypeBean;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/12/8.
 * <p>
 * 直播支付弹框adapter
 */

public class LivePayAdapter extends BaseRecycleViewAdapter {


    private List<PayTypeBean> payTypeBeen;
    private double roomPrice;
    private int PAY_INTEREST = 0x11;//兴趣豆支付
    private int PAY_ALIPAY = 0x22;//支付宝支付
    private int PAY_WECHAT = 0x33;//微信支付
    private int PAY_COUPON = 0x44;//优惠券
    private int PAY_BALANCE = 0x55;//余额支付
    private int mPosition = -1;

    public void setmPosition(int position) {
        this.mPosition = position;
        notifyDataSetChanged();
    }


    public LivePayAdapter(Context c, List<PayTypeBean> payTypeBeen, double roomPrice) {
        super(c);
        this.payTypeBeen = payTypeBeen;
        this.roomPrice = roomPrice;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PAY_INTEREST) {
            return new InterestViewHolder(resIdToView(parent, R.layout.item_pay_xingqudou));
        } else if (viewType == PAY_BALANCE) {
            return new BalanceViewHolder(resIdToView(parent, R.layout.item_pay_balance));
        } else if (viewType == PAY_COUPON) {
            return new CouponViewHolder(resIdToView(parent, R.layout.item_pay_youhui));
        } else if (viewType == PAY_ALIPAY) {
            return new ALIViewHolder(resIdToView(parent, R.layout.item_pay_aliwechat));
        } else if (viewType == PAY_WECHAT) {
            return new WeChatViewHolder(resIdToView(parent, R.layout.item_pay_aliwechat));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof InterestViewHolder) {
                InterestViewHolder couponViewHolder = (InterestViewHolder) holder;
                couponViewHolder.onBind();
            } else if (holder instanceof BalanceViewHolder) {
                BalanceViewHolder balanceViewHolder = (BalanceViewHolder) holder;
                balanceViewHolder.onBind();
            } else if (holder instanceof CouponViewHolder) {
                CouponViewHolder couponViewHolder = (CouponViewHolder) holder;
                couponViewHolder.onBind();

            } else if (holder instanceof ALIViewHolder) {
                ALIViewHolder couponViewHolder = (ALIViewHolder) holder;
                couponViewHolder.onBind();

            } else if (holder instanceof WeChatViewHolder) {
                WeChatViewHolder couponViewHolder = (WeChatViewHolder) holder;
                couponViewHolder.onBind();
            }
        }
    }

    @Override
    public int getItemCount() {
        return payTypeBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        String name = payTypeBeen.get(position).getName() == null ? "" : payTypeBeen.get(position).getName();

        if (name.equals("余额")) {
            return PAY_BALANCE;
        } else if (name.equals("微信")) {
            return PAY_WECHAT;

        } else if (name.equals("支付宝")) {
            return PAY_ALIPAY;
        } else if (name.equals("门票优惠券")) {
            return PAY_COUPON;
        }

        return PAY_INTEREST;
    }

    //余额
    class BalanceViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.image_balance)
        ImageView imageBalance;
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.radiobutton_balance)
        RadioButton radiobuttonBalance;

        public BalanceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            PayTypeBean bean = payTypeBeen.get(pos);
            registerOnItemClickListener(pos, itemView);
            tvBalance.setText("可用账号余额"+bean.getCoinNum());
            Glide.with(c).load(bean.getIcon()).into(imageBalance);

            if (mPosition == pos) {
                radiobuttonBalance.setChecked(true);
            } else {
                radiobuttonBalance.setChecked(false);
            }

            boolean checked = radiobuttonBalance.isChecked();
            paytype.getPayType("余额",checked);
        }
    }

    //兴趣豆
    class InterestViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.image_balance)
        ImageView imageBalance;
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.linear_chongzhi)
        LinearLayout linearChongzhi;
        @BindView(R.id.radiobutton_balance)
        RadioButton radiobuttonBalance;

        public InterestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            PayTypeBean bean = payTypeBeen.get(pos);
            double coinNum = bean.getCoinNum();

            if (coinNum > roomPrice) {
                radiobuttonBalance.setVisibility(View.VISIBLE);
                linearChongzhi.setVisibility(View.INVISIBLE);
            } else {
                radiobuttonBalance.setVisibility(View.GONE);
                linearChongzhi.setVisibility(View.VISIBLE);
            }
            tvBalance.setText("兴趣豆：" + bean.getCoinNum());
            Glide.with(c).load(bean.getIcon()).into(imageBalance);

            if (mPosition == pos) {
                radiobuttonBalance.setChecked(true);
            } else {
                radiobuttonBalance.setChecked(false);
            }
            boolean checked = radiobuttonBalance.isChecked();
            paytype.getPayType("兴趣豆",checked);
            linearChongzhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    couponPay.Recharge(pos);
                }
            });

        }
    }

    //兴趣豆充值回调
    private CouponPay couponPay;

    public void setCouponPay(CouponPay couponPay) {
        this.couponPay = couponPay;
    }

    public interface CouponPay {
        void Recharge(int pos);
    }

    //优惠券
    class CouponViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.image_coupon)
        ImageView imageCoupon;
        @BindView(R.id.tv_coupon_money)
        TextView tvCouponMoney;
        @BindView(R.id.radiobutton_coupon)
        RadioButton radiobuttonCoupon;

        public CouponViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            PayTypeBean bean = payTypeBeen.get(pos);
            registerOnItemClickListener(pos, itemView);

            double coinNum = bean.getCoinNum();
            String floor = String.valueOf(Math.floor(coinNum));
            String substring = floor.substring(0, floor.indexOf("."));

            tvCouponMoney.setText("门票优惠券：" + substring + "张");

            Glide.with(c).load(bean.getIcon()).into(imageCoupon);
            if (mPosition == pos) {
                radiobuttonCoupon.setChecked(true);
            } else {
                radiobuttonCoupon.setChecked(false);
            }
            boolean checked = radiobuttonCoupon.isChecked();
            paytype.getPayType("优惠券",checked);
        }
    }

    //支付宝
    class ALIViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.image_alipay)
        ImageView imageAlipay;
        @BindView(R.id.tv_aliwechat)
        TextView tv_aliwechat;
        @BindView(R.id.tv_aliwechat_content)
        TextView tv_aliwechat_content;
        @BindView(R.id.radiobutton_alipay)
        RadioButton radiobuttonAlipay;

        public ALIViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            PayTypeBean bean = payTypeBeen.get(pos);
            registerOnItemClickListener(pos, itemView);
            tv_aliwechat.setText(bean.getName());
            tv_aliwechat_content.setText("推荐安装支付宝客户端的用户使用");
            Glide.with(c).load(bean.getIcon()).into(imageAlipay);
            if (mPosition == pos) {
                radiobuttonAlipay.setChecked(true);
            } else {
                radiobuttonAlipay.setChecked(false);
            }
            boolean checked = radiobuttonAlipay.isChecked();
            paytype.getPayType("支付宝",checked);
        }
    }

    //微信
    class WeChatViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.image_alipay)
        ImageView imageAlipay;
        @BindView(R.id.radiobutton_alipay)
        RadioButton radiobuttonAlipay;
        @BindView(R.id.tv_aliwechat)
        TextView tv_aliwechat;
        @BindView(R.id.tv_aliwechat_content)
        TextView tv_aliwechat_content;

        public WeChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            PayTypeBean bean = payTypeBeen.get(pos);
            registerOnItemClickListener(pos, itemView);
            tv_aliwechat.setText(bean.getName());
            tv_aliwechat_content.setText("推荐安装微信客户端的用户使用");
            Glide.with(c).load(bean.getIcon()).into(imageAlipay);

            if (mPosition == pos) {
                radiobuttonAlipay.setChecked(true);
            } else {
                radiobuttonAlipay.setChecked(false);
            }
            boolean checked = radiobuttonAlipay.isChecked();
            paytype.getPayType("微信",checked);
        }
    }
    //支付类型回调
    private Paytype paytype;

    public void setPaytype(Paytype paytype) {
        this.paytype = paytype;
    }

    public interface  Paytype{
        void getPayType(String str,boolean flag);
    }
}
