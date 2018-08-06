package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 钱包余额和汇率返回实体
 */
public class ResponseWallet implements Serializable{
    public boolean isSetTradePwd;
    public WalletRemain money; //余额
    public List<WalletRemain> currency; //其他货币
    public List<Rate> currencyRate; //所有汇率

    /**
     * 货币余额
     */
    public class WalletRemain implements Serializable{
        public long id;
        public String name;
        public String unit;
        public String icon;
        public float coinNum;
    }

    /**
     * 汇率
     */
    public class Rate implements Serializable{
        public float rate;
        public long currencyId;
        public long toCurrencyId;
        public float toAmount;
        public float fromAmount;
        public String id;
        public RateCurrency toCurrency;
        public RateCurrency currency;
        public List<Rate> sons;
    }

    /**
     * 汇率指定货币
     */
    public class RateCurrency implements Serializable{
        public String id;
        public String name;
        public String icon;
        public String alias;
    }
}