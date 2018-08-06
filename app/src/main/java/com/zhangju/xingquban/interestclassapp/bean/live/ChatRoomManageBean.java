package com.zhangju.xingquban.interestclassapp.bean.live;

import java.util.List;

/**
 * Created by liush on 2016/12/6 0006.
 *
 * @我的场控列表
 */
public class ChatRoomManageBean {

    /**
     * sEcho : 1
     * iTotalRecords : 2
     * iTotalDisplayRecords : 2
     * aaData : [{"types":"manager","rooms":{"summary":null,"channelsId":"62","onlineUserCount":0,"amount":0,
     * "cityId":null,"roomName":"直播测试","valid":true,"roomPic":"http://192.168.1
     * .9:80/stdv2/files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg","cityName":null,
     * "chatUserId":"38","categoriesId":null,"areasName":null,"id":"36","chatroomId":"5012558","status":1},
     * "editUserId":"1","editUserName":"欣然(13872490333)","addUserId":"1","targetChatUserId":",14,",
     * "editUserTime":"2016-12-06 20:07:37","display":1,"published":true,"roomsId":"36","chatUser":{"tabId":"1",
     * "followes":null,"gender":"0","icon":"files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg",
     * "sign":null,"mobile":null,"birth":null,"ex":"{\"give\":8082,\"fllows\":0,\"get\":7783,
     * \"accid\":\"b0b8def80ae8a943cb204eec46956e77\",\"id\":\"38\",\"likes\":0.0,\"fans\":2}","tab":"com.studying.cm
     * .model.impl.customer.Customer","name":"欣然","accid":"b0b8def80ae8a943cb204eec46956e77","roomFollows":null,
     * "id":"38","email":null},"blacklisted":null,"addUserTime":"2016-12-06 19:03:31","removed":0,"chatUserId":"38",
     * "digest":0,"id":"21","isAddUserType":"customer","isEditUserType":"customer","targetChatUser":[{"tabId":"2654",
     * "followes":null,"gender":"1","icon":"http://video.xqban.com/Customer/2016-07-14/1468476962862_filename.jpg",
     * "sign":null,"mobile":null,"birth":null,"ex":"{\"give\":3908,\"fllows\":4,\"get\":4455,
     * \"accid\":\"b0b8def80ae8a943d260393cd7edbfe6\",\"id\":\"14\",\"fans\":2}","tab":"com.studying.cm.model.impl
     * .customer.Customer","name":"西门音乐","accid":"b0b8def80ae8a943d260393cd7edbfe6","roomFollows":null,"id":"14",
     * "email":null}],"muted":null,"sorts":0,"addUserName":"欣然(13872490333)"},{"types":"manager",
     * "rooms":{"summary":null,"channelsId":"55","onlineUserCount":0,"amount":0,"cityId":null,"roomName":"直播测试",
     * "valid":true,"roomPic":"http://video.xqban.com/Customer/2016-07-14/1468476962862_filename.jpg",
     * "cityName":null,"chatUserId":"14","categoriesId":null,"areasName":null,"id":"31","chatroomId":"4582507",
     * "status":0},"editUserId":"2654","editUserName":"王刚(18666650022)","addUserId":"2654","targetChatUserId":",38,",
     * "editUserTime":"2016-12-06 19:34:15","display":1,"published":true,"roomsId":"31","chatUser":{"tabId":"2654",
     * "followes":null,"gender":"1","icon":"http://video.xqban.com/Customer/2016-07-14/1468476962862_filename.jpg",
     * "sign":null,"mobile":null,"birth":null,"ex":"{\"give\":3908,\"fllows\":4,\"get\":4455,
     * \"accid\":\"b0b8def80ae8a943d260393cd7edbfe6\",\"id\":\"14\",\"fans\":2}","tab":"com.studying.cm.model.impl
     * .customer.Customer","name":"西门音乐","accid":"b0b8def80ae8a943d260393cd7edbfe6","roomFollows":null,"id":"14",
     * "email":null},"blacklisted":null,"addUserTime":"2016-11-01 15:58:45","removed":0,"chatUserId":"14","digest":0,
     * "id":"15","isAddUserType":"customer","isEditUserType":"customer","targetChatUser":[{"tabId":"1",
     * "followes":null,"gender":"0","icon":"files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg",
     * "sign":null,"mobile":null,"birth":null,"ex":"{\"give\":8082,\"fllows\":0,\"get\":7783,
     * \"accid\":\"b0b8def80ae8a943cb204eec46956e77\",\"id\":\"38\",\"likes\":0.0,\"fans\":2}","tab":"com.studying.cm
     * .model.impl.customer.Customer","name":"欣然","accid":"b0b8def80ae8a943cb204eec46956e77","roomFollows":null,
     * "id":"38","email":null}],"muted":null,"sorts":0,"addUserName":"王刚(18666650022)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 1
     * cname : 13872490333
     * time : 2016-12-06 21:42:01:943
     */

    private int sEcho;
    private int        iTotalRecords;
    private int        iTotalDisplayRecords;
    private Object     attachData;
    private int        total;
    private int        page;
    private boolean    success;
    private boolean    isLogin;
    private ErrMsgBean errMsg;
    private boolean    isAdmin;
    private String     cId;
    private String     cname;
    private String     time;
    /**
     * types : manager
     * rooms : {"summary":null,"channelsId":"62","onlineUserCount":0,"amount":0,"cityId":null,"roomName":"直播测试",
     * "valid":true,"roomPic":"http://192.168.1
     * .9:80/stdv2/files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg","cityName":null,
     * "chatUserId":"38","categoriesId":null,"areasName":null,"id":"36","chatroomId":"5012558","status":1}
     * editUserId : 1
     * editUserName : 欣然(13872490333)
     * addUserId : 1
     * targetChatUserId : ,14,
     * editUserTime : 2016-12-06 20:07:37
     * display : 1
     * published : true
     * roomsId : 36
     * chatUser : {"tabId":"1","followes":null,"gender":"0",
     * "icon":"files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg","sign":null,"mobile":null,
     * "birth":null,"ex":"{\"give\":8082,\"fllows\":0,\"get\":7783,\"accid\":\"b0b8def80ae8a943cb204eec46956e77\",
     * \"id\":\"38\",\"likes\":0.0,\"fans\":2}","tab":"com.studying.cm.model.impl.customer.Customer","name":"欣然",
     * "accid":"b0b8def80ae8a943cb204eec46956e77","roomFollows":null,"id":"38","email":null}
     * blacklisted : null
     * addUserTime : 2016-12-06 19:03:31
     * removed : 0
     * chatUserId : 38
     * digest : 0
     * id : 21
     * isAddUserType : customer
     * isEditUserType : customer
     * targetChatUser : [{"tabId":"2654","followes":null,"gender":"1","icon":"http://video.xqban.com/Customer/2016-07
     * -14/1468476962862_filename.jpg","sign":null,"mobile":null,"birth":null,"ex":"{\"give\":3908,\"fllows\":4,\"get
     * \":4455,\"accid\":\"b0b8def80ae8a943d260393cd7edbfe6\",\"id\":\"14\",\"fans\":2}","tab":"com.studying.cm.model
     * .impl.customer.Customer","name":"西门音乐","accid":"b0b8def80ae8a943d260393cd7edbfe6","roomFollows":null,
     * "id":"14","email":null}]
     * muted : null
     * sorts : 0
     * addUserName : 欣然(13872490333)
     */

    private List<AaDataBean> aaData;

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
    }

    public static class AaDataBean {
        private String types;
        /**
         * summary : null
         * channelsId : 62
         * onlineUserCount : 0
         * amount : 0
         * cityId : null
         * roomName : 直播测试
         * valid : true
         * roomPic : http://192.168.1.9:80/stdv2/files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg
         * cityName : null
         * chatUserId : 38
         * categoriesId : null
         * areasName : null
         * id : 36
         * chatroomId : 5012558
         * status : 1
         */

        private RoomsBean rooms;
        private String  editUserId;
        private String  editUserName;
        private String  addUserId;
        private String  targetChatUserId;
        private String  editUserTime;
        private int     display;
        private boolean published;
        private String  roomsId;
        /**
         * tabId : 1
         * followes : null
         * gender : 0
         * icon : files/2016-01-13/TeacherTime/1452682099010@ps20080813101048.jpg
         * sign : null
         * mobile : null
         * birth : null
         * ex : {"give":8082,"fllows":0,"get":7783,"accid":"b0b8def80ae8a943cb204eec46956e77","id":"38","likes":0.0,
         * "fans":2}
         * tab : com.studying.cm.model.impl.customer.Customer
         * name : 欣然
         * accid : b0b8def80ae8a943cb204eec46956e77
         * roomFollows : null
         * id : 38
         * email : null
         */

        private ChatUserBean chatUser;
        private Object blacklisted;
        private String addUserTime;
        private int    removed;
        private String chatUserId;
        private int    digest;
        private String id;
        private String isAddUserType;
        private String isEditUserType;
        private Object muted;
        private int    sorts;
        private String addUserName;
        /**
         * tabId : 2654
         * followes : null
         * gender : 1
         * icon : http://video.xqban.com/Customer/2016-07-14/1468476962862_filename.jpg
         * sign : null
         * mobile : null
         * birth : null
         * ex : {"give":3908,"fllows":4,"get":4455,"accid":"b0b8def80ae8a943d260393cd7edbfe6","id":"14","fans":2}
         * tab : com.studying.cm.model.impl.customer.Customer
         * name : 西门音乐
         * accid : b0b8def80ae8a943d260393cd7edbfe6
         * roomFollows : null
         * id : 14
         * email : null
         */

        private List<TargetChatUserBean> targetChatUser;

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public RoomsBean getRooms() {
            return rooms;
        }

        public void setRooms(RoomsBean rooms) {
            this.rooms = rooms;
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

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getTargetChatUserId() {
            return targetChatUserId;
        }

        public void setTargetChatUserId(String targetChatUserId) {
            this.targetChatUserId = targetChatUserId;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public String getRoomsId() {
            return roomsId;
        }

        public void setRoomsId(String roomsId) {
            this.roomsId = roomsId;
        }

        public ChatUserBean getChatUser() {
            return chatUser;
        }

        public void setChatUser(ChatUserBean chatUser) {
            this.chatUser = chatUser;
        }

        public Object getBlacklisted() {
            return blacklisted;
        }

        public void setBlacklisted(Object blacklisted) {
            this.blacklisted = blacklisted;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public String getChatUserId() {
            return chatUserId;
        }

        public void setChatUserId(String chatUserId) {
            this.chatUserId = chatUserId;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public Object getMuted() {
            return muted;
        }

        public void setMuted(Object muted) {
            this.muted = muted;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public List<TargetChatUserBean> getTargetChatUser() {
            return targetChatUser;
        }

        public void setTargetChatUser(List<TargetChatUserBean> targetChatUser) {
            this.targetChatUser = targetChatUser;
        }

        public static class RoomsBean {
            private Object  summary;
            private String  channelsId;
            private int     onlineUserCount;
            private int     amount;
            private Object  cityId;
            private String  roomName;
            private boolean valid;
            private String  roomPic;
            private Object  cityName;
            private String  chatUserId;
            private Object  categoriesId;
            private Object  areasName;
            private String  id;
            private String  chatroomId;
            private int     status;

            public Object getSummary() {
                return summary;
            }

            public void setSummary(Object summary) {
                this.summary = summary;
            }

            public String getChannelsId() {
                return channelsId;
            }

            public void setChannelsId(String channelsId) {
                this.channelsId = channelsId;
            }

            public int getOnlineUserCount() {
                return onlineUserCount;
            }

            public void setOnlineUserCount(int onlineUserCount) {
                this.onlineUserCount = onlineUserCount;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public Object getCityId() {
                return cityId;
            }

            public void setCityId(Object cityId) {
                this.cityId = cityId;
            }

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public String getRoomPic() {
                return roomPic;
            }

            public void setRoomPic(String roomPic) {
                this.roomPic = roomPic;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
            }

            public String getChatUserId() {
                return chatUserId;
            }

            public void setChatUserId(String chatUserId) {
                this.chatUserId = chatUserId;
            }

            public Object getCategoriesId() {
                return categoriesId;
            }

            public void setCategoriesId(Object categoriesId) {
                this.categoriesId = categoriesId;
            }

            public Object getAreasName() {
                return areasName;
            }

            public void setAreasName(Object areasName) {
                this.areasName = areasName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getChatroomId() {
                return chatroomId;
            }

            public void setChatroomId(String chatroomId) {
                this.chatroomId = chatroomId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class ChatUserBean {
            private String tabId;
            private Object followes;
            private String gender;
            private String icon;
            private Object sign;
            private Object mobile;
            private Object birth;
            private String ex;
            private String tab;
            private String name;
            private String accid;
            private Object roomFollows;
            private String id;
            private Object email;

            public String getTabId() {
                return tabId;
            }

            public void setTabId(String tabId) {
                this.tabId = tabId;
            }

            public Object getFollowes() {
                return followes;
            }

            public void setFollowes(Object followes) {
                this.followes = followes;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getSign() {
                return sign;
            }

            public void setSign(Object sign) {
                this.sign = sign;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getBirth() {
                return birth;
            }

            public void setBirth(Object birth) {
                this.birth = birth;
            }

            public String getEx() {
                return ex;
            }

            public void setEx(String ex) {
                this.ex = ex;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public Object getRoomFollows() {
                return roomFollows;
            }

            public void setRoomFollows(Object roomFollows) {
                this.roomFollows = roomFollows;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }
        }

        public static class TargetChatUserBean {
            private String tabId;
            private Object followes;
            private String gender;
            private String icon;
            private Object sign;
            private Object mobile;
            private Object birth;
            private String ex;
            private String tab;
            private String name;
            private String accid;
            private Object roomFollows;
            private String id;
            private Object email;

            public String getTabId() {
                return tabId;
            }

            public void setTabId(String tabId) {
                this.tabId = tabId;
            }

            public Object getFollowes() {
                return followes;
            }

            public void setFollowes(Object followes) {
                this.followes = followes;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getSign() {
                return sign;
            }

            public void setSign(Object sign) {
                this.sign = sign;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getBirth() {
                return birth;
            }

            public void setBirth(Object birth) {
                this.birth = birth;
            }

            public String getEx() {
                return ex;
            }

            public void setEx(String ex) {
                this.ex = ex;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public Object getRoomFollows() {
                return roomFollows;
            }

            public void setRoomFollows(Object roomFollows) {
                this.roomFollows = roomFollows;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }
        }
    }
}
