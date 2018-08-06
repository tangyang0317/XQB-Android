package com.zhangju.xingquban.interestclassapp.bean.live;

/**
 * Created by liush on 2016/12/5 0005.
 **/
public class ChatUserInfoBean {

    /**
     * give : 1308
     * fllows : 0
     * get : 431
     * accid : b0b8def80ae8a943cb204eec46956e77
     * id : 38
     * likes : 0.0
     * fans : 1
     */
    private int give;
    private int    fllows;
    private int    get;
    private String accid;
    private String id;
    private double likes;
    private int    fans;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGive() {
        return give;
    }

    public void setGive(int give) {
        this.give = give;
    }

    public int getFllows() {
        return fllows;
    }

    public void setFllows(int fllows) {
        this.fllows = fllows;
    }

    public int getGet() {
        return get;
    }

    public void setGet(int get) {
        this.get = get;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLikes() {
        return likes;
    }

    public void setLikes(double likes) {
        this.likes = likes;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }
}
