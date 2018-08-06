package com.zhangju.xingquban.interestclassapp.refactor.user;

import com.fastlib.annotation.Database;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sgfb on 2017/10/26.
 * 用户实体
 */
public class User implements Cloneable{
    @Database(keyPrimary = true)
    public final int dbId=1;

    public boolean isMember; //是否会员
    public int status; //是否教师/机构审核中 1审核中
    public String id;
    public String username; //法人
    public String editUserName;
    public String sex; //F,M
    public String birthday;
    public String uidCard;
    public String school;
    public String signame; //普通用户姓名
    public String phone;

    public String teacherTimeId;
    public String studSkill;
    public String chgDegreeId;
    public String picture;
    public String colDegree; //学位
    public String sno;

    public String addUserTime;
    public String provinceName;
    public String cityName;
    public String areasId;
    public String areasName;
    public String address;
    public String formatted_address;
    public String dnyAddress;
    public String dnyLat;
    public String dnyLng;
    public int contractAuth; //平台认证 1 待审核， 2 已认证，-1 拒绝。其他未认证 下同
    public int qcAuth; //资格认证
    public String qcAuthId;
    public int degreeAuth;
    public String degreeAuthId;
    public int realnameAuth; //身份认证
    public Degree degree=new Degree();
    public UserMaps map=new UserMaps();

    public class Degree{
        @SerializedName("isOrgaliza")
        public boolean isOrganization;
        public boolean isCommon;
        public boolean isTeacher;
        public String nameCn;
        public String id;
    }

    public static class UserMaps {
        public int degreeAuth; //学历认证 0未认证 1申请中 2已认证
        public int realnameAuth; //身份认证 状态与上同
        public int qcAuth; //资格认证 上同
        public int contractAuth; //平台认证 上同
        public int teachAge;
        public double lat;
        public double lng;
        public String agentId;
        public String formatted_address;
        public String provinceId;
        public String provinceName;
        public String cityId;
        public String cityName;
        public String areasId;
        public String areasName;
        public String school;
        public String classRoom;
        public String id;
        public String IDCard;
        public String signame;
        public String adcode;
        public String studSkill;
        public String sex;
        public String realnameAuthId;
        public String picture;
        public String colDegree;
        public String phone;
        public String location;
        public String username;

        public String getRealnameAuth(){
            return getAuthStatus(realnameAuth);
        }

        public String getQcAuth(){
            return getAuthStatus(qcAuth);
        }

        public String getPlatformAuth(){
            return getAuthStatus(contractAuth);
        }

        public String getAuthStatus(int auth){
            if(auth==0) return "未认证";
            else if(auth==1) return "认证中";
            else if(auth==2) return "已认证";
            else return "未知";
        }
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}