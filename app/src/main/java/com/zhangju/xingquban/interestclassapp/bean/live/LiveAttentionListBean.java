package com.zhangju.xingquban.interestclassapp.bean.live;



import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/23.
 *
 * @直播间列表
 */
public class LiveAttentionListBean extends BaseBean {

    private int sEcho;
    private int     iTotalRecords;
    private int     iTotalDisplayRecords;
    private Object  attachData;
    private int     total;
    private int     page;
    private boolean isLogin;
    private boolean isAdmin;
    private Object  cId;
    private Object  cname;
    private String  time;


    private ArrayList<AaDataBean> aaData;

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

    public ArrayList<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(ArrayList<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class AaDataBean {
        private Object summary;
        private String channelsId;
        private float    amount;
        private int    onlineUserCount;
        private String editUserTime;
        private String roomName;
        /**
         * tabId : 2661
         * tab : com.studying.cm.model.impl.customer.Customer
         * gender : 1
         * name : 个
         * icon : http://video.xqban.com/Customer/2016-10-11/1476173707633_filename.jpg
         * sign : null
         * accid : b0b8def80ae8a943728bce6a087ad71e
         * birth : 1990-10-10
         * id : 28
         * email : null
         */
        private ChatUserBean chatUser;
        private LiveRecord liveRecord;
        private String  roomPic;
        private boolean valid;
        private String  cityName;
        /**
         * crtime : 2016-09-30 11:02:18
         * pushUrl : rtmp://pee49e842.live.126
         * .net/live/0b2427e8a50042d99b669a60e1ee7174?wsSecret=eb04cccee3a3c52a7d47e81fbcc7eb3f&wsTime=1475204537
         * rtmpPullUrl : rtmp://vee49e842.live.126.net/live/0b2427e8a50042d99b669a60e1ee7174
         * channelName : F9726308949E65C8D96B5867D0214B6F
         * id : 44
         * roomsId : 23
         * type : 0
         * cid : 0b2427e8a50042d99b669a60e1ee7174
         */

        private ChannelsBean channels;
        private String chatUserId;
        /**
         * parentsId : 1
         * name : 速写
         * id : 133
         */

        private CatagoriesBean catagories;
        private String categoriesId;
        private Object areasName;
        private String id;
        private String provinceName;
        private int    status;
        private String chatroomId;

        public LiveRecord getLiveRecord() {
            return liveRecord;
        }

        public void setLiveRecord(LiveRecord liveRecord) {
            this.liveRecord = liveRecord;
        }

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

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public int getOnlineUserCount() {
            return onlineUserCount;
        }

        public void setOnlineUserCount(int onlineUserCount) {
            this.onlineUserCount = onlineUserCount;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public ChatUserBean getChatUser() {
            return chatUser;
        }

        public void setChatUser(ChatUserBean chatUser) {
            this.chatUser = chatUser;
        }

        public String getRoomPic() {
            return roomPic;
        }

        public void setRoomPic(String roomPic) {
            this.roomPic = roomPic;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public ChannelsBean getChannels() {
            return channels;
        }

        public void setChannels(ChannelsBean channels) {
            this.channels = channels;
        }

        public String getChatUserId() {
            return chatUserId;
        }

        public void setChatUserId(String chatUserId) {
            this.chatUserId = chatUserId;
        }

        public CatagoriesBean getCatagories() {
            return catagories;
        }

        public void setCatagories(CatagoriesBean catagories) {
            this.catagories = catagories;
        }

        public String getCategoriesId() {
            return categoriesId;
        }

        public void setCategoriesId(String categoriesId) {
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

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }

        public static class LiveRecord {
            private String roomName;
            private Integer onlineUserCount;
            private Integer status;
            private String cityName;
            private String cityId;
            private String roomPic;
            private Integer id;

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public Integer getOnlineUserCount() {
                return onlineUserCount;
            }

            public void setOnlineUserCount(Integer onlineUserCount) {
                this.onlineUserCount = onlineUserCount;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getRoomPic() {
                return roomPic;
            }

            public void setRoomPic(String roomPic) {
                this.roomPic = roomPic;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }
        public static class ChatUserBean {


            private String tabId;
            private String tab;
            private String gender;
            private String name;
            private String icon;
            private Object sign;
            private String accid;
            private String birth;
            private String id;
            private Object email;

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

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
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

            public Object getSign() {
                return sign;
            }

            public void setSign(Object sign) {
                this.sign = sign;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
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

        public static class ChannelsBean {
            private String crtime;
            private String pushUrl;
            private String rtmpPullUrl;
            private String channelName;
            private String id;
            private String roomsId;
            private int    type;
            private String cid;

            public String getCrtime() {
                return crtime;
            }

            public void setCrtime(String crtime) {
                this.crtime = crtime;
            }

            public String getPushUrl() {
                return pushUrl;
            }

            public void setPushUrl(String pushUrl) {
                this.pushUrl = pushUrl;
            }

            public String getRtmpPullUrl() {
                return rtmpPullUrl;
            }

            public void setRtmpPullUrl(String rtmpPullUrl) {
                this.rtmpPullUrl = rtmpPullUrl;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }
        }

        public static class CatagoriesBean {
            private String parentsId;
            private String name;
            private String id;

            public String getParentsId() {
                return parentsId;
            }

            public void setParentsId(String parentsId) {
                this.parentsId = parentsId;
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
        }
    }
}
