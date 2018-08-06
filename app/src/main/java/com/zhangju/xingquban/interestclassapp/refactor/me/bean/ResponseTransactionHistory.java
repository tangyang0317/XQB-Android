package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 * 交易记录返回实体
 */
public class ResponseTransactionHistory{
    public String month;
    public List<Ledger> ledger;

    public class Ledger{
        public String amount;
        public String createtime;
        public String name;
        public String cname;
        public String id;
        public String time;
    }
}