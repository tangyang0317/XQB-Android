package com.zhangju.xingquban.interestclassapp.bean.live;

import java.util.List;

/**
 * Created by liush on 2016/12/7 0007.
 *
 * @用户自己的账号信息
 */
public class ChatRoomFollowBean {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"id":"38","tabId":"1","tab":"com.studying.cm.model.impl.customer.Customer",
     * "accid":"b0b8def80ae8a943cb204eec46956e77","name":"欣然",
     * "icon":"files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg",
     * "mToken":"30de3a3e1d07659f40eeac1263f2fd9c","sign":null,"email":null,"birth":null,"mobile":null,"gender":"0",
     * "ex":"{\"give\":7290,\"fllows\":1,\"get\":5515,\"accid\":\"b0b8def80ae8a943cb204eec46956e77\",\"id\":\"38\",
     * \"likes\":0.0,\"fans\":2}","rooms":null,"roomFollows":null,
     * "followes":[{"followAccid":"b0b8def80ae8a943cb204eec46956e77","followChatUserId":"38","targetChatUserId":"14",
     * "targetAccid":"b0b8def80ae8a943d260393cd7edbfe6","id":"166"}],"removed":0,"addUserId":"1","addUserName":"欣然
     * (13872490333)","addUserTime":"2016-11-17 18:02:29","editUserId":"1","editUserName":"欣然(13872490333)",
     * "editUserTime":"2016-12-07 14:55:16","isAddUserType":"customer","isEditUserType":"customer","published":true,
     * "sorts":0,"display":1,"digest":0,"del":false,"degreeId":null}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 1
     * cname : 13872490333
     * time : 2016-12-07 15:00:15:941
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    /**
     * id : 38
     * tabId : 1
     * tab : com.studying.cm.model.impl.customer.Customer
     * accid : b0b8def80ae8a943cb204eec46956e77
     * name : 欣然
     * icon : files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg
     * mToken : 30de3a3e1d07659f40eeac1263f2fd9c
     * sign : null
     * email : null
     * birth : null
     * mobile : null
     * gender : 0
     * ex : {"give":7290,"fllows":1,"get":5515,"accid":"b0b8def80ae8a943cb204eec46956e77","id":"38","likes":0.0,
     * "fans":2}
     * rooms : null
     * roomFollows : null
     * followes : [{"followAccid":"b0b8def80ae8a943cb204eec46956e77","followChatUserId":"38","targetChatUserId":"14",
     * "targetAccid":"b0b8def80ae8a943d260393cd7edbfe6","id":"166"}]
     * removed : 0
     * addUserId : 1
     * addUserName : 欣然(13872490333)
     * addUserTime : 2016-11-17 18:02:29
     * editUserId : 1
     * editUserName : 欣然(13872490333)
     * editUserTime : 2016-12-07 14:55:16
     * isAddUserType : customer
     * isEditUserType : customer
     * published : true
     * sorts : 0
     * display : 1
     * digest : 0
     * del : false
     * degreeId : null
     */

    private AaDataBean aaData;
    private Object     attachData;
    private Object     total;
    private Object     page;
    private boolean    success;
    private boolean    isLogin;
    private ErrMsgBean errMsg;
    private boolean    isAdmin;
    private String     cId;
    private String     cname;
    private String     time;

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

    public AaDataBean getAaData() {
        return aaData;
    }

    public void setAaData(AaDataBean aaData) {
        this.aaData = aaData;
    }

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
        this.attachData = attachData;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
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

    public static class AaDataBean {
        private String  id;
        private String  tabId;
        private String  tab;
        private String  accid;
        private String  name;
        private String  icon;
        private String  token;
        private Object  sign;
        private Object  email;
        private Object  birth;
        private Object  mobile;
        private String  gender;
        private String  ex;
        private Object  rooms;
        private Object  roomFollows;
        private int     removed;
        private String  addUserId;
        private String  addUserName;
        private String  addUserTime;
        private String  editUserId;
        private String  editUserName;
        private String  editUserTime;
        private String  isAddUserType;
        private String  isEditUserType;
        private boolean published;
        private int     sorts;
        private int     display;
        private int     digest;
        private boolean del;
        private Object  degreeId;
        /**
         * followAccid : b0b8def80ae8a943cb204eec46956e77
         * followChatUserId : 38
         * targetChatUserId : 14
         * targetAccid : b0b8def80ae8a943d260393cd7edbfe6
         * id : 166
         */

        private List<FollowesBean> followes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTabId() {
            return tabId;
        }

        public void setTabId(String tabId) {
            this.tabId = tabId;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getBirth() {
            return birth;
        }

        public void setBirth(Object birth) {
            this.birth = birth;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEx() {
            return ex;
        }

        public void setEx(String ex) {
            this.ex = ex;
        }

        public Object getRooms() {
            return rooms;
        }

        public void setRooms(Object rooms) {
            this.rooms = rooms;
        }

        public Object getRoomFollows() {
            return roomFollows;
        }

        public void setRoomFollows(Object roomFollows) {
            this.roomFollows = roomFollows;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public String getEditUserId() {
            return editUserId;
        }

        public void setEditUserId(String editUserId) {
            this.editUserId = editUserId;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public Object getDegreeId() {
            return degreeId;
        }

        public void setDegreeId(Object degreeId) {
            this.degreeId = degreeId;
        }

        public List<FollowesBean> getFollowes() {
            return followes;
        }

        public void setFollowes(List<FollowesBean> followes) {
            this.followes = followes;
        }

        public static class FollowesBean {
            private String followAccid;
            private String followChatUserId;
            private String targetChatUserId;
            private String targetAccid;
            private String id;

            public String getFollowAccid() {
                return followAccid;
            }

            public void setFollowAccid(String followAccid) {
                this.followAccid = followAccid;
            }

            public String getFollowChatUserId() {
                return followChatUserId;
            }

            public void setFollowChatUserId(String followChatUserId) {
                this.followChatUserId = followChatUserId;
            }

            public String getTargetChatUserId() {
                return targetChatUserId;
            }

            public void setTargetChatUserId(String targetChatUserId) {
                this.targetChatUserId = targetChatUserId;
            }

            public String getTargetAccid() {
                return targetAccid;
            }

            public void setTargetAccid(String targetAccid) {
                this.targetAccid = targetAccid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class ErrMsgBean {
    }
}
