package com.zhangju.xingquban.interestclassapp.bean.live;



import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.util.List;

/**
 * @Created by  liush on 2017/2/19.
 * @describe ${礼物列表}
 */
public class LiveGiftBean
        extends BaseBean {

    /**
     * sEcho : 1
     * iTotalRecords : 20
     * iTotalDisplayRecords : 20
     * aaData : [{"amount":2,"name":"棒棒糖","icon":"http://video.xqban
     * .com/Gift/2016-11-18/1479462570112_627.png","id":"9","flhtype":"1000"},{"amount":4,
     * "name":"鲜花","icon":"http://video.xqban.com/Gift/2016-12-05/1480937634255_633.png",
     * "id":"13","flhtype":"1000"},{"amount":20,"name":"奖杯","icon":"http://video.xqban
     * .com/Gift/2016-12-05/1480937810893_843.png","id":"18","flhtype":"1000"},{"amount":40,
     * "name":"皇冠","icon":"http://video.xqban.com/Gift/2016-12-05/1480935881815_916.png",
     * "id":"11","flhtype":"1000"},{"amount":100,"name":"献爱心","icon":"http://video.xqban
     * .com/Gift/2016-12-05/1480935915274_817.png","id":"12","flhtype":"1000"},{"amount":999,
     * "name":"钢笔","icon":"http://video.xqban.com/Gift/2016-12-05/1480937730510_940.png",
     * "id":"15","flhtype":"1000"},{"amount":1888,"name":"百科全书","icon":"http://video.xqban
     * .com/Gift/2016-11-18/1479462153629_598.png","id":"5","flhtype":"1000"},{"amount":3333,
     * "name":"水粉盒","icon":"http://video.xqban.com/Gift/2016-11-18/1479462517243_73.png",
     * "id":"10","flhtype":"1000"},{"amount":5555,"name":"数码相机","icon":"http://video.xqban
     * .com/Gift/2016-12-05/1480937844912_338.png","id":"19","flhtype":"1000"},{"amount":6666,
     * "name":"笔记本","icon":"http://video.xqban.com/Gift/2016-12-05/1480937864813_571.png",
     * "id":"20","flhtype":"1000"}]
     * attachData : null
     * total : 2
     * page : 0
     * success : true
     * isLogin : false
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-02-19 14:07:26:968
     */

    private int              sEcho;
    private int              iTotalRecords;
    private int              iTotalDisplayRecords;
    private int              total;
    private int              page;
    private boolean          isLogin;
    private boolean          isAdmin;
    private Object           cId;
    private Object           cname;
    private String           time;
    private List<AaDataBean> aaData;
    private AttachDataBean   attachData;


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

    public AttachDataBean getAttachData() {
        return attachData;
    }

    public void setAttachData(AttachDataBean attachData) {
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

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Object getCId() {
        return cId;
    }

    public void setCId(Object cId) {
        this.cId = cId;
    }

    public Object getCname() {
        return cname;
    }

    public void setCname(Object cname) {
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

    public static class AaDataBean {
        /**
         * amount : 2
         * name : 棒棒糖
         * icon : http://video.xqban.com/Gift/2016-11-18/1479462570112_627.png
         * id : 9
         * flhtype : 1000
         */

        private int    amount;
        private String name;
        private String icon;
        private String id;
        private String flhtype;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFlhtype() {
            return flhtype;
        }

        public void setFlhtype(String flhtype) {
            this.flhtype = flhtype;
        }
    }

    public static class AttachDataBean {
        /**
         * stdbeanAmount : 9227.0
         */
        private double stdbeanAmount;

        public double getStdbeanAmount() {
            return stdbeanAmount;
        }

        public void setStdbeanAmount(double stdbeanAmount) {
            this.stdbeanAmount = stdbeanAmount;
        }
    }
}
