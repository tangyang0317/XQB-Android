package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ChatUser;

import java.io.Serializable;

/**
 * Created by hqf on 2017/11/21.
 * 直播支付处理
 */

public class LivePayBean implements Serializable {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"id":"1451","roomsId":"73","chatUserId":"744","startTime":"2017-11-20 16:19:55","endTime":null,"roomName":"安卓直播","roomPic":"http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg","amount":3,"currencyId":null,"summary":null,"comtCount":0,"likeCount":0,"onlineUserCount":24,"follows":0,"gets":0,"fans":0,"gives":0,"stdCoin":0,"catagoriesId":null,"catagoryName":null,"lng":null,"lat":null,"location":null,"formatted_address":null,"adcode":null,"address":null,"areasId":null,"provinceId":null,"cityId":null,"cityName":null,"areasName":null,"provinceName":null,"status":1,"syncVdoStatus":0,"chatUser":{"id":"612","tabId":"12776","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a9431a8f0c50e41b6941","name":"李老师","icon":"http://video.xqban.com/Customer/2017-11-17/1510899526707_173.jpg","token":"8ded9c8c1a7cdfac88bc85bc543144be","sign":null,"email":null,"birth":null,"mobile":null,"gender":"1","ex":"{\"give\":56,\"fllows\":1,\"get\":5819,\"accid\":\"b0b8def80ae8a9431a8f0c50e41b6941\",\"id\":\"612\",\"likes\":0,\"fans\":2}","rooms":null,"roomFollows":null,"followes":"b0b8def80ae8a9431a8f0c50e41b6941","removed":0,"addUserId":"12776","addUserName":"李老师(13411111111)","addUserTime":"2017-06-19 14:01:22","editUserId":"12776","editUserName":"李老师(13411111111)","editUserTime":"2017-11-17 16:05:36","isAddUserType":"customer","isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""},"channels":{"id":"77","allocate":true,"roomsId":"73","liveRecordId":"1451","liveChnlMapId":"430","chatuserId":"744","cid":"7ee05ba126dc4429a068e783b5cb72d8","ctime":"1511165995074","crtime":"2017-11-20 16:19:55","channelName":"932523887550275584","type":0,"pushUrl":"rtmp://pee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8?wsSecret=7f2075c9d737d1cbf919f0ff4ae6ebb5&wsTime=1511165995","httpPullUrl":"http://vee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8.flv?netease=vee49e842.live.126.net","rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8","hlsPullUrl":"http://pullhlsee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8/playlist.m3u8","versions":1,"removed":0,"addUserName":"刘北风(13422222222)","addUserTime":"2017-11-20 16:19:55","editUserId":"12777","editUserName":"刘北风(13422222222)","editUserTime":"2017-11-20 16:19:55","addUserId":"12777","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"digest":0,"published":true,"display":1,"del":false,"degreeId":""},"rooms":null,"chatroomId":"9975866","removed":0,"addUserId":"12777","addUserName":"刘北风(13422222222)","addUserTime":"2017-11-20 16:19:55","editUserId":"12776","editUserName":"李老师(13411111111)","editUserTime":"2017-11-21 10:26:57","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}
     * attachData : {"unit":"元","roomPrice":3,"isPay":false,"currencyName":"余额","appKey":"ae9af37f1bd812d22e9e29404fedb64a","currencyId":"1","chatUser":{"id":"744","tabId":"12777","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a94380be1db63b8ed8d6","name":"北风器乐培训","icon":"http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg","token":"dba200ed0759a5b598c54efd34198890","sign":null,"email":null,"birth":null,"mobile":null,"gender":"0","ex":"{\"give\":3098,\"fllows\":0,\"get\":8530,\"accid\":\"b0b8def80ae8a94380be1db63b8ed8d6\",\"id\":\"744\",\"likes\":0,\"fans\":0}","rooms":null,"roomFollows":null,"followes":null,"removed":0,"addUserId":"12777","addUserName":"刘北风(13422222222)","addUserTime":"2017-07-13 10:18:05","editUserId":"12777","editUserName":"刘北风(13422222222)","editUserTime":"2017-11-14 14:40:34","isAddUserType":"customer","isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""},"seeBalances":-388.15}
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-11-21 10:26:57:497
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private AaDataBean aaData;
    private AttachDataBean attachData;
    private Object total;
    private Object page;
    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private String cId;
    private String cname;
    private String time;


    public static LivePayBean objectFromData(String str) {

        return new Gson().fromJson(str, LivePayBean.class);
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

    public AaDataBean getAaData() {
        return aaData;
    }

    public void setAaData(AaDataBean aaData) {
        this.aaData = aaData;
    }

    public AttachDataBean getAttachData() {
        return attachData;
    }

    public void setAttachData(AttachDataBean attachData) {
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
        /**
         * id : 1451
         * roomsId : 73
         * chatUserId : 744
         * startTime : 2017-11-20 16:19:55
         * endTime : null
         * roomName : 安卓直播
         * roomPic : http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg
         * amount : 3
         * currencyId : null
         * summary : null
         * comtCount : 0
         * likeCount : 0
         * onlineUserCount : 24
         * follows : 0
         * gets : 0
         * fans : 0
         * gives : 0
         * stdCoin : 0
         * catagoriesId : null
         * catagoryName : null
         * lng : null
         * lat : null
         * location : null
         * formatted_address : null
         * adcode : null
         * address : null
         * areasId : null
         * provinceId : null
         * cityId : null
         * cityName : null
         * areasName : null
         * provinceName : null
         * status : 1
         * syncVdoStatus : 0
         * chatUser : {"id":"612","tabId":"12776","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a9431a8f0c50e41b6941","name":"李老师","icon":"http://video.xqban.com/Customer/2017-11-17/1510899526707_173.jpg","token":"8ded9c8c1a7cdfac88bc85bc543144be","sign":null,"email":null,"birth":null,"mobile":null,"gender":"1","ex":"{\"give\":56,\"fllows\":1,\"get\":5819,\"accid\":\"b0b8def80ae8a9431a8f0c50e41b6941\",\"id\":\"612\",\"likes\":0,\"fans\":2}","rooms":null,"roomFollows":null,"followes":"b0b8def80ae8a9431a8f0c50e41b6941","removed":0,"addUserId":"12776","addUserName":"李老师(13411111111)","addUserTime":"2017-06-19 14:01:22","editUserId":"12776","editUserName":"李老师(13411111111)","editUserTime":"2017-11-17 16:05:36","isAddUserType":"customer","isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}
         * channels : {"id":"77","allocate":true,"roomsId":"73","liveRecordId":"1451","liveChnlMapId":"430","chatuserId":"744","cid":"7ee05ba126dc4429a068e783b5cb72d8","ctime":"1511165995074","crtime":"2017-11-20 16:19:55","channelName":"932523887550275584","type":0,"pushUrl":"rtmp://pee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8?wsSecret=7f2075c9d737d1cbf919f0ff4ae6ebb5&wsTime=1511165995","httpPullUrl":"http://vee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8.flv?netease=vee49e842.live.126.net","rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8","hlsPullUrl":"http://pullhlsee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8/playlist.m3u8","versions":1,"removed":0,"addUserName":"刘北风(13422222222)","addUserTime":"2017-11-20 16:19:55","editUserId":"12777","editUserName":"刘北风(13422222222)","editUserTime":"2017-11-20 16:19:55","addUserId":"12777","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"digest":0,"published":true,"display":1,"del":false,"degreeId":""}
         * rooms : null
         * chatroomId : 9975866
         * removed : 0
         * addUserId : 12777
         * addUserName : 刘北风(13422222222)
         * addUserTime : 2017-11-20 16:19:55
         * editUserId : 12776
         * editUserName : 李老师(13411111111)
         * editUserTime : 2017-11-21 10:26:57
         * isAddUserType : customer
         * isEditUserType : customer
         * published : true
         * sorts : 0
         * display : 1
         * digest : 0
         * del : false
         * degreeId :
         */

        private String id;
        private String roomsId;
        private String chatUserId;
        private String startTime;
        private Object endTime;
        private String roomName;
        private String roomPic;
        private int amount;
        private Object currencyId;
        private Object summary;
        private int comtCount;
        private int likeCount;
        private int onlineUserCount;
        private int follows;
        private int gets;
        private int fans;
        private int gives;
        private int stdCoin;
        private Object catagoriesId;
        private Object catagoryName;
        private Object lng;
        private Object lat;
        private Object location;
        private Object formatted_address;
        private Object adcode;
        private Object address;
        private Object areasId;
        private Object provinceId;
        private Object cityId;
        private Object cityName;
        private Object areasName;
        private Object provinceName;
        private int status;
        private int syncVdoStatus;
        private ChatUserBean chatUser;
        private ChannelsBean channels;
        private Object rooms;
        private String chatroomId;
        private int removed;
        private String addUserId;
        private String addUserName;
        private String addUserTime;
        private String editUserId;
        private String editUserName;
        private String editUserTime;
        private String isAddUserType;
        private String isEditUserType;
        private boolean published;
        private boolean isAddOp;
        private int sorts;
        private int display;
        private int digest;
        private boolean del;
        private String degreeId;
        private String orig_video_key;

        public boolean isAddOp() {
            return isAddOp;
        }

        public void setAddOp(boolean addOp) {
            isAddOp = addOp;
        }

        public String getOrig_video_key() {
            return orig_video_key;
        }

        public void setOrig_video_key(String orig_video_key) {
            this.orig_video_key = orig_video_key;
        }

        public static AaDataBean objectFromData(String str) {

            return new Gson().fromJson(str, AaDataBean.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoomsId() {
            return roomsId;
        }

        public void setRoomsId(String roomsId) {
            this.roomsId = roomsId;
        }

        public String getChatUserId() {
            return chatUserId;
        }

        public void setChatUserId(String chatUserId) {
            this.chatUserId = chatUserId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getRoomPic() {
            return roomPic;
        }

        public void setRoomPic(String roomPic) {
            this.roomPic = roomPic;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public Object getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(Object currencyId) {
            this.currencyId = currencyId;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public int getComtCount() {
            return comtCount;
        }

        public void setComtCount(int comtCount) {
            this.comtCount = comtCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getOnlineUserCount() {
            return onlineUserCount;
        }

        public void setOnlineUserCount(int onlineUserCount) {
            this.onlineUserCount = onlineUserCount;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getGets() {
            return gets;
        }

        public void setGets(int gets) {
            this.gets = gets;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getGives() {
            return gives;
        }

        public void setGives(int gives) {
            this.gives = gives;
        }

        public int getStdCoin() {
            return stdCoin;
        }

        public void setStdCoin(int stdCoin) {
            this.stdCoin = stdCoin;
        }

        public Object getCatagoriesId() {
            return catagoriesId;
        }

        public void setCatagoriesId(Object catagoriesId) {
            this.catagoriesId = catagoriesId;
        }

        public Object getCatagoryName() {
            return catagoryName;
        }

        public void setCatagoryName(Object catagoryName) {
            this.catagoryName = catagoryName;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(Object formatted_address) {
            this.formatted_address = formatted_address;
        }

        public Object getAdcode() {
            return adcode;
        }

        public void setAdcode(Object adcode) {
            this.adcode = adcode;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getAreasId() {
            return areasId;
        }

        public void setAreasId(Object areasId) {
            this.areasId = areasId;
        }

        public Object getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Object provinceId) {
            this.provinceId = provinceId;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public Object getCityName() {
            return cityName;
        }

        public void setCityName(Object cityName) {
            this.cityName = cityName;
        }

        public Object getAreasName() {
            return areasName;
        }

        public void setAreasName(Object areasName) {
            this.areasName = areasName;
        }

        public Object getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(Object provinceName) {
            this.provinceName = provinceName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSyncVdoStatus() {
            return syncVdoStatus;
        }

        public void setSyncVdoStatus(int syncVdoStatus) {
            this.syncVdoStatus = syncVdoStatus;
        }

        public ChatUserBean getChatUser() {
            return chatUser;
        }

        public void setChatUser(ChatUserBean chatUser) {
            this.chatUser = chatUser;
        }

        public ChannelsBean getChannels() {
            return channels;
        }

        public void setChannels(ChannelsBean channels) {
            this.channels = channels;
        }

        public Object getRooms() {
            return rooms;
        }

        public void setRooms(Object rooms) {
            this.rooms = rooms;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
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

        public String getDegreeId() {
            return degreeId;
        }

        public void setDegreeId(String degreeId) {
            this.degreeId = degreeId;
        }

        public static class ChatUserBean {
            /**
             * id : 612
             * tabId : 12776
             * tab : com.studying.cm.model.impl.customer.Customer
             * accid : b0b8def80ae8a9431a8f0c50e41b6941
             * name : 李老师
             * icon : http://video.xqban.com/Customer/2017-11-17/1510899526707_173.jpg
             * token : 8ded9c8c1a7cdfac88bc85bc543144be
             * sign : null
             * email : null
             * birth : null
             * mobile : null
             * gender : 1
             * ex : {"give":56,"fllows":1,"get":5819,"accid":"b0b8def80ae8a9431a8f0c50e41b6941","id":"612","likes":0,"fans":2}
             * rooms : null
             * roomFollows : null
             * followes : b0b8def80ae8a9431a8f0c50e41b6941
             * removed : 0
             * addUserId : 12776
             * addUserName : 李老师(13411111111)
             * addUserTime : 2017-06-19 14:01:22
             * editUserId : 12776
             * editUserName : 李老师(13411111111)
             * editUserTime : 2017-11-17 16:05:36
             * isAddUserType : customer
             * isEditUserType : sys
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * del : false
             * degreeId :
             */

            private String id;
            private String tabId;
            private String tab;
            private String accid;
            private String name;
            private String icon;
            private String token;
            private Object sign;
            private Object email;
            private Object birth;
            private Object mobile;
            private String gender;
            private String ex;
            private Object rooms;
            private Object roomFollows;
            private String followes;
            private int removed;
            private String addUserId;
            private String addUserName;
            private String addUserTime;
            private String editUserId;
            private String editUserName;
            private String editUserTime;
            private String isAddUserType;
            private String isEditUserType;
            private boolean published;
            private int sorts;
            private int display;
            private int digest;
            private boolean del;
            private String degreeId;

            public static ChatUserBean objectFromData(String str) {

                return new Gson().fromJson(str, ChatUserBean.class);
            }

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

            public String getFollowes() {
                return followes;
            }

            public void setFollowes(String followes) {
                this.followes = followes;
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

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }
        }

        public static class ChannelsBean implements Serializable {
            /**
             * id : 77
             * allocate : true
             * roomsId : 73
             * liveRecordId : 1451
             * liveChnlMapId : 430
             * chatuserId : 744
             * cid : 7ee05ba126dc4429a068e783b5cb72d8
             * ctime : 1511165995074
             * crtime : 2017-11-20 16:19:55
             * channelName : 932523887550275584
             * type : 0
             * pushUrl : rtmp://pee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8?wsSecret=7f2075c9d737d1cbf919f0ff4ae6ebb5&wsTime=1511165995
             * httpPullUrl : http://vee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8.flv?netease=vee49e842.live.126.net
             * rtmpPullUrl : rtmp://vee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8
             * hlsPullUrl : http://pullhlsee49e842.live.126.net/live/7ee05ba126dc4429a068e783b5cb72d8/playlist.m3u8
             * versions : 1
             * removed : 0
             * addUserName : 刘北风(13422222222)
             * addUserTime : 2017-11-20 16:19:55
             * editUserId : 12777
             * editUserName : 刘北风(13422222222)
             * editUserTime : 2017-11-20 16:19:55
             * addUserId : 12777
             * isAddUserType : customer
             * isEditUserType : customer
             * sorts : 0
             * digest : 0
             * published : true
             * display : 1
             * del : false
             * degreeId :
             */

            private String id;
            private boolean allocate;
            private String roomsId;
            private String liveRecordId;
            private String liveChnlMapId;
            private String chatuserId;
            private String cid;
            private String ctime;
            private String crtime;
            private String channelName;
            private int type;
            private String pushUrl;
            private String httpPullUrl;
            private String rtmpPullUrl;
            private String hlsPullUrl;
            private int versions;
            private int removed;
            private String addUserName;
            private String addUserTime;
            private String editUserId;
            private String editUserName;
            private String editUserTime;
            private String addUserId;
            private String isAddUserType;
            private String isEditUserType;
            private int sorts;
            private int digest;
            private boolean published;
            private int display;
            private boolean del;
            private String degreeId;

            public static ChannelsBean objectFromData(String str) {

                return new Gson().fromJson(str, ChannelsBean.class);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isAllocate() {
                return allocate;
            }

            public void setAllocate(boolean allocate) {
                this.allocate = allocate;
            }

            public String getRoomsId() {
                return roomsId;
            }

            public void setRoomsId(String roomsId) {
                this.roomsId = roomsId;
            }

            public String getLiveRecordId() {
                return liveRecordId;
            }

            public void setLiveRecordId(String liveRecordId) {
                this.liveRecordId = liveRecordId;
            }

            public String getLiveChnlMapId() {
                return liveChnlMapId;
            }

            public void setLiveChnlMapId(String liveChnlMapId) {
                this.liveChnlMapId = liveChnlMapId;
            }

            public String getChatuserId() {
                return chatuserId;
            }

            public void setChatuserId(String chatuserId) {
                this.chatuserId = chatuserId;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getCrtime() {
                return crtime;
            }

            public void setCrtime(String crtime) {
                this.crtime = crtime;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPushUrl() {
                return pushUrl;
            }

            public void setPushUrl(String pushUrl) {
                this.pushUrl = pushUrl;
            }

            public String getHttpPullUrl() {
                return httpPullUrl;
            }

            public void setHttpPullUrl(String httpPullUrl) {
                this.httpPullUrl = httpPullUrl;
            }

            public String getRtmpPullUrl() {
                return rtmpPullUrl;
            }

            public void setRtmpPullUrl(String rtmpPullUrl) {
                this.rtmpPullUrl = rtmpPullUrl;
            }

            public String getHlsPullUrl() {
                return hlsPullUrl;
            }

            public void setHlsPullUrl(String hlsPullUrl) {
                this.hlsPullUrl = hlsPullUrl;
            }

            public int getVersions() {
                return versions;
            }

            public void setVersions(int versions) {
                this.versions = versions;
            }

            public int getRemoved() {
                return removed;
            }

            public void setRemoved(int removed) {
                this.removed = removed;
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

            public String getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(String addUserId) {
                this.addUserId = addUserId;
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

            public int getSorts() {
                return sorts;
            }

            public void setSorts(int sorts) {
                this.sorts = sorts;
            }

            public int getDigest() {
                return digest;
            }

            public void setDigest(int digest) {
                this.digest = digest;
            }

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public int getDisplay() {
                return display;
            }

            public void setDisplay(int display) {
                this.display = display;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
            }

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }
        }
    }

    public static class AttachDataBean implements Serializable {
        private String unit;//单位
        private Double roomPrice;//
        private boolean isPay;//
        private String currencyName;//
        private String appKey;//
        private String liveVdoUrl;//
        private String currencyId;//
        private Double seeBalances;//
        private ChatUsers chatUser;//

        public String getLiveVdoUrl() {
            return liveVdoUrl;
        }

        public void setLiveVdoUrl(String liveVdoUrl) {
            this.liveVdoUrl = liveVdoUrl;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Double getRoomPrice() {
            return roomPrice;
        }

        public void setRoomPrice(Double roomPrice) {
            this.roomPrice = roomPrice;
        }

        public boolean isPay() {
            return isPay;
        }

        public void setPay(boolean pay) {
            isPay = pay;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public Double getSeeBalances() {
            return seeBalances;
        }

        public void setSeeBalances(Double seeBalances) {
            this.seeBalances = seeBalances;
        }

        public ChatUsers getChatUser() {
            return chatUser;
        }

        public void setChatUser(ChatUsers chatUser) {
            this.chatUser = chatUser;
        }

        public static class ChatUsers implements Serializable {
            private String id;
            private String tabId;
            private String accid;
            private String name;
            private String icon;

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
        }

    }

    public static class ErrMsgBean {
        public static ErrMsgBean objectFromData(String str) {

            return new Gson().fromJson(str, ErrMsgBean.class);
        }
    }
}
