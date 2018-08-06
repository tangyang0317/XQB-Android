package com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hqf on 2017/11/22.
 * 找活动列表tabtitle
 */

public class LooktabListBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 23
     * iTotalDisplayRecords : 23
     * aaData : [{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_art_default_meishu.png","name":"美术","id":"1","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_calligraphy_default_shufa.png","name":"书法","id":"8","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/shengyue.png","name":"声乐","id":"71","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_musical_default_jianpan.png","name":"键盘","id":"241","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/xuanyue.png","name":"弦乐类","id":"247","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/guanyue.png","name":"管乐类","id":"254","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/dajiyue1.png","name":"打击乐","id":"264","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/minzuyueqi.png","name":"民族乐器","id":"267","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_vocalmusic_default_yuyanbiaoyan.png","name":"语言表演","id":"62","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_dance_default_wudao.png","name":"舞蹈","id":"27","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_kungfu_default_gongfu.png","name":"功夫","id":"58","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/jianshen.png","name":"健身","id":"99","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/qilei.png","name":"棋类","id":"82","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_drama_default_xijuyingshi.png","name":"戏曲","id":"100","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/home_icon_handwork_default_shougong.png","name":"手工","id":"140","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/qita.png","name":"其他","id":"239","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/jixianyundong.png","name":"极限运动","id":"78","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/xijuyingshi.png","name":"戏剧影视","id":"380","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/yuyanbiaoyan.png","name":"语言","id":"400","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/guoxue.png","name":"国学","id":"113","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/qiulei.png","name":"球类","id":"292","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/youerzaojiao.png","name":"幼儿早教","id":"345","showRsSearchSort":0,"showRsSearch":0},{"summary":null,"parentsId":null,"showHome":0,"hots":0,"catagoriesPicture":"http://video.xqban.com/Catagories/2017-10-16/huwaiyundong.png","name":"户外运动","id":"366","showRsSearchSort":0,"showRsSearch":0}]
     * attachData : null
     * total : 3
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 16097
     * cname : 13400000000
     * time : 2017-11-22 13:59:28:459
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private int total;
    private int page;
    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private String cId;
    private String cname;
    private String time;
    private List<AaDataBean> aaData;

    public static LooktabListBean objectFromData(String str) {

        return new Gson().fromJson(str, LooktabListBean.class);
    }

    public int getSEcho() {
        return sEcho;
    }

    public void setSEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
        this.attachData = attachData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public ErrMsgBean getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(ErrMsgBean errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class ErrMsgBean {
        public static ErrMsgBean objectFromData(String str) {

            return new Gson().fromJson(str, ErrMsgBean.class);
        }
    }

    public static class AaDataBean implements Serializable{
        /**
         * summary : null
         * parentsId : null
         * showHome : 0
         * hots : 0
         * catagoriesPicture : http://video.xqban.com/Catagories/2017-10-16/home_icon_art_default_meishu.png
         * name : 美术
         * id : 1
         * showRsSearchSort : 0
         * showRsSearch : 0
         */

        private Object summary;
        private Object parentsId;
        private int showHome;
        private int hots;
        private String catagoriesPicture;
        private String name;
        private String id;
        private int showRsSearchSort;
        private int showRsSearch;

        public static AaDataBean objectFromData(String str) {

            return new Gson().fromJson(str, AaDataBean.class);
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public Object getParentsId() {
            return parentsId;
        }

        public void setParentsId(Object parentsId) {
            this.parentsId = parentsId;
        }

        public int getShowHome() {
            return showHome;
        }

        public void setShowHome(int showHome) {
            this.showHome = showHome;
        }

        public int getHots() {
            return hots;
        }

        public void setHots(int hots) {
            this.hots = hots;
        }

        public String getCatagoriesPicture() {
            return catagoriesPicture;
        }

        public void setCatagoriesPicture(String catagoriesPicture) {
            this.catagoriesPicture = catagoriesPicture;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getShowRsSearchSort() {
            return showRsSearchSort;
        }

        public void setShowRsSearchSort(int showRsSearchSort) {
            this.showRsSearchSort = showRsSearchSort;
        }

        public int getShowRsSearch() {
            return showRsSearch;
        }

        public void setShowRsSearch(int showRsSearch) {
            this.showRsSearch = showRsSearch;
        }
    }
}
