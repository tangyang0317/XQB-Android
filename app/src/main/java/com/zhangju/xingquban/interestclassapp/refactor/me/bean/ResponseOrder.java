package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sgfb on 2017/10/28.
 * 订单返回实体
 */
public class ResponseOrder implements Serializable{
    public static final int STATUS_NONE=-3;
    public static final int STATUS_INVALID=-2;
    public static final int STATUS_CANCEL=-1;
    public static final int STATUS_UNPAY=0;
    public static final int STATUS_PAYED=1;
    public static final int STATUS_VALID=2;
    public static final int STATUS_USED=3;
    public int status; //0建单,1支付,2有效,3完成,-1取消,-2失效
    public int qcodeNum; //二维码数
    public int scanQcodeNum; //已被扫二维码数
    public float amount;
    public String id;
    public String sno;
    public String addUserTime;
    public String createTimeStr;
    public String payTime;
    public String phone;
    public Customer customer;
    public List<OrderData> dlist;

    public String getStatusFormat(){
        switch (status){
            case 0:return "待付款";
            case 1:return "已支付";
            case 2:return "未使用";
            case 3:return "已使用";
            case -1:return "已取消";
            case -2:return "已失效";
            default:return "未知状态";
        }
    }

    public class Customer implements Serializable{
        public String username;
        public String phone;
    }

    public class OrderData implements Serializable{
        public float price;
        public String lessonName;
        public String lessonImg;
        public Lesson lesson;

        @SerializedName("teacherTime")
        public ServerInfo serverInfo;
    }

    public static class Lesson implements Serializable{
        public String id;
        public String name;
        public String lessonDate;
        public String descript;
        public String price;
        public String method; //授课地点
        public String picture;
    }

    public class ServerInfo implements Serializable{
        public String notice;
        public String contactTel;
        public String contactUser;
        public String workTime;

        public String getContactTel(){
            return TextUtils.isEmpty(contactTel)?"":contactTel;
        }

        public String getWorkTime(){
            return TextUtils.isEmpty(workTime)?"":workTime;
        }

        public String getNotice(){
            if(TextUtils.isEmpty(notice)) return "无提示";
            int increase=0;
            int index;
            int lastIndex=0;
            StringBuilder sb=new StringBuilder();
            while((index=notice.indexOf(Integer.toString(increase++)+".",lastIndex))!=-1){
                if(lastIndex>=notice.length()) break;
                sb.append(notice.substring(lastIndex,index)).append("\n");
                lastIndex=index+1;
            }
            return sb.toString();
        }
    }
}