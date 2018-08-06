package com.zhangju.xingquban.interestclassapp.bean.live;


import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/23.
 *
 * @直播间列表
 */
public class LiveNewListBean extends BaseBean {

    /**
     * sEcho : 1
     * iTotalRecords : 7
     * iTotalDisplayRecords : 7
     * aaData : [{"summary":null,"channelsId":"44","amount":0,"onlineUserCount":1,"editUserTime":"2016-10-22
     * 17:13:31","roomName":"测","chatUser":{"tabId":"2661","tab":"com.studying.cm.model.impl.customer.Customer",
     * "gender":"1","name":"个","icon":"http://video.xqban.com/Customer/2016-10-11/1476173707633_filename.jpg",
     * "sign":null,"accid":"b0b8def80ae8a943728bce6a087ad71e","birth":"1990-10-10","id":"28","email":null},
     * "roomPic":"http://video.xqban.com/Customer/2016-10-11/1476173707633_filename.jpg","valid":true,
     * "cityName":"上海市","channels":{"crtime":"2016-09-30 11:02:18","pushUrl":"rtmp://pee49e842.live.126
     * .net/live/0b2427e8a50042d99b669a60e1ee7174?wsSecret=eb04cccee3a3c52a7d47e81fbcc7eb3f&wsTime=1475204537",
     * "rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/0b2427e8a50042d99b669a60e1ee7174",
     * "channelName":"F9726308949E65C8D96B5867D0214B6F","id":"44","roomsId":"23","type":0,
     * "cid":"0b2427e8a50042d99b669a60e1ee7174"},"chatUserId":"28","catagories":{"parentsId":"1","name":"速写",
     * "id":"133"},"categoriesId":"133","areasName":null,"id":"23","provinceName":"中华人民共和国","status":1,
     * "chatroomId":"4257541"},{"summary":null,"channelsId":"50","amount":0,"onlineUserCount":0,
     * "editUserTime":"2016-10-22 16:59:04","roomName":"军训","chatUser":{"tabId":"2133","tab":"com.studying.cm.model
     * .impl.customer.Customer","gender":"2","name":"怎么哭了22331212","icon":"http://video.xqban
     * .com/Customer/2016-07-12/1468307906146_filename.jpg","sign":null,"accid":"b0b8def80ae8a9439e98e3aab90f9967",
     * "birth":"2009-12-10","id":"33","email":null},"roomPic":"http://video.xqban
     * .com/Customer/2016-07-12/1468307906146_filename.jpg","valid":true,"cityName":"上海市",
     * "channels":{"crtime":"2016-10-13 14:56:44","pushUrl":"rtmp://pee49e842.live.126
     * .net/live/1451bd0f6d614d10a0a61756e37a9c3f?wsSecret=dccba4d5388a5e30958aaee86eacaa84&wsTime=1476341803",
     * "rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/1451bd0f6d614d10a0a61756e37a9c3f",
     * "channelName":"FC0FEDFB2FC385FDD96B5867D0214B6F","id":"50","roomsId":"29","type":0,
     * "cid":"1451bd0f6d614d10a0a61756e37a9c3f"},"chatUserId":"33","catagories":{"parentsId":"8","name":"篆刻",
     * "id":"94"},"categoriesId":"94","areasName":null,"id":"29","provinceName":"中华人民共和国","status":1,
     * "chatroomId":"4351985"},{"summary":null,"channelsId":"47","amount":12,"onlineUserCount":0,
     * "editUserTime":"2016-10-20 16:53:45","roomName":"测试我要直播的接口是否是好的","chatUser":{"tabId":"2133","tab":"com
     * .studying.cm.model.impl.customer.Customer","gender":"2","name":"怎么哭了22331212","icon":"http://video.xqban
     * .com/Customer/2016-07-12/1468307906146_filename.jpg","sign":null,"accid":"b0b8def80ae8a9439e98e3aab90f9967",
     * "birth":"2009-12-10","id":"32","email":null},"roomPic":"http://78rbm4.com2.z0.glb.qiniucdn
     * .com/a6833cab2bfa4d69ad3edca45b2bb8dc.jpg","valid":true,"cityName":"上海市","channels":{"crtime":"2016-10-13
     * 14:23:14","pushUrl":"rtmp://pee49e842.live.126
     * .net/live/0f089be4862f48fbab4c3a2d81fe98de?wsSecret=2883d705bec8e814d69ddd75cb39476a&wsTime=1476339793",
     * "rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/0f089be4862f48fbab4c3a2d81fe98de",
     * "channelName":"659C68F83D9FC3E9D96B5867D0214B6F","id":"47","roomsId":"26","type":0,
     * "cid":"0f089be4862f48fbab4c3a2d81fe98de"},"chatUserId":"32","catagories":null,"categoriesId":"12",
     * "areasName":null,"id":"26","provinceName":"中华人民共和国","status":1,"chatroomId":"4351900"},{"summary":null,
     * "channelsId":"46","amount":12,"onlineUserCount":0,"editUserTime":"2016-10-20 16:53:45",
     * "roomName":"测试我要直播的接口是否是好的","chatUser":{"tabId":"2133","tab":"com.studying.cm.model.impl.customer.Customer",
     * "gender":null,"name":null,"icon":"http://video.xqban.com/Customer/2016-07-12/1468307906146_filename.jpg",
     * "sign":null,"accid":"b0b8def80ae8a9439e98e3aab90f9967","birth":null,"id":"31","email":null},
     * "roomPic":"http://78rbm4.com2.z0.glb.qiniucdn.com/a6833cab2bfa4d69ad3edca45b2bb8dc.jpg","valid":true,
     * "cityName":"上海市","channels":{"crtime":"2016-10-13 14:19:36","pushUrl":"rtmp://pee49e842.live.126
     * .net/live/fe3a9b5ae79345229b73ce0e88ce375c?wsSecret=76d9e8492878d1e828895d317577f0e7&wsTime=1476339576",
     * "rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/fe3a9b5ae79345229b73ce0e88ce375c",
     * "channelName":"366C65996ECE473DD96B5867D0214B6F","id":"46","roomsId":"25","type":0,
     * "cid":"fe3a9b5ae79345229b73ce0e88ce375c"},"chatUserId":"31","catagories":null,"categoriesId":"12",
     * "areasName":null,"id":"25","provinceName":"中华人民共和国","status":1,"chatroomId":"4347887"},{"summary":null,
     * "channelsId":"45","amount":12,"onlineUserCount":0,"editUserTime":"2016-10-20 17:03:14",
     * "roomName":"测试我要直播的接口是否是好的","chatUser":{"tabId":"2133","tab":"com.studying.cm.model.impl.customer.Customer",
     * "gender":"2","name":"怎么哭了22331212","icon":"http://video.xqban.com/Customer/2016-07-12/1468307906146_filename
     * .jpg","sign":null,"accid":"b0b8def80ae8a9439e98e3aab90f9967","birth":"2009-12-10","id":"30","email":null},
     * "roomPic":"http://78rbm4.com2.z0.glb.qiniucdn.com/a6833cab2bfa4d69ad3edca45b2bb8dc.jpg","valid":true,
     * "cityName":"上海市","channels":{"crtime":"2016-10-13 14:06:27","pushUrl":"rtmp://pee49e842.live.126
     * .net/live/b8b8beb22c7942219efc488ef0eaa0bd?wsSecret=564c79411547a248753a64e561ee0be0&wsTime=1476338786",
     * "rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/b8b8beb22c7942219efc488ef0eaa0bd",
     * "channelName":"59B652AB386A66C7D96B5867D0214B6F","id":"45","roomsId":"24","type":0,
     * "cid":"b8b8beb22c7942219efc488ef0eaa0bd"},"chatUserId":"30","catagories":null,"categoriesId":"12",
     * "areasName":null,"id":"24","provinceName":"中华人民共和国","status":1,"chatroomId":"4353192"},{"summary":null,
     * "channelsId":"43","amount":0,"onlineUserCount":0,"editUserTime":"2016-10-20 16:54:58","roomName":"阿里郎",
     * "chatUser":{"tabId":"2134","tab":"com.studying.cm.model.impl.customer.Customer","gender":"2","name":"测量龙",
     * "icon":"http://video.xqban.com/Customer/2016-09-29/1475140239532_61c7e2380cd79123b102aa64a5345982b3b78083
     * .jpg","sign":null,"accid":"b0b8def80ae8a9432bdb54ba6550993e","birth":"2018-10-12","id":"27","email":null},
     * "roomPic":"http://video.xqban.com/Customer/2016-09-29/1475140239532_61c7e2380cd79123b102aa64a5345982b3b78083
     * .jpg","valid":true,"cityName":"上海市","channels":{"crtime":"2016-09-29 14:47:57","pushUrl":"rtmp://pee49e842
     * .live.126.net/live/4dc37520a9784ceca378607a74b86cdd?wsSecret=a50f40da470d7591d472915c4d59e011&wsTime
     * =1475131677","rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/4dc37520a9784ceca378607a74b86cdd",
     * "channelName":"6492A75CC7E215B0D96B5867D0214B6F","id":"43","roomsId":"22","type":0,
     * "cid":"4dc37520a9784ceca378607a74b86cdd"},"chatUserId":"27","catagories":{"parentsId":"1","name":"简笔画",
     * "id":"77"},"categoriesId":"77","areasName":null,"id":"22","provinceName":"中华人民共和国","status":1,
     * "chatroomId":"4247268"},{"summary":"qwqw2132","channelsId":"41","amount":21,"onlineUserCount":0,
     * "editUserTime":"2016-10-20 16:54:22","roomName":"test rooms","chatUser":{"tabId":"26","tab":"com.studying.cm
     * .model.impl.customer.Customer","gender":"0","name":"crystal","icon":"http://video.xqban
     * .com/TeacherTime/2016-07-26/1469501309657_img_0379[1].jpg","sign":null,
     * "accid":"b0b8def80ae8a943a2db5573a87eef20","birth":null,"id":"23","email":null},"roomPic":"http://video.xqban
     * .com/Rooms/2016-09-29/1475118663507_img_0081.jpg","valid":true,"cityName":"上海市",
     * "channels":{"crtime":"2016-09-29 11:11:11","pushUrl":"rtmp://pee49e842.live.126
     * .net/live/ed6c88a9690249719f4812b948cc77aa?wsSecret=7c4e9d4166bf4bfb9c64cf56e8ad80ca&wsTime=1475118671",
     * "rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/ed6c88a9690249719f4812b948cc77aa",
     * "channelName":"FEB2C57155A28AA6D96B5867D0214B6F","id":"41","roomsId":"20","type":0,
     * "cid":"ed6c88a9690249719f4812b948cc77aa"},"chatUserId":"23","catagories":{"parentsId":"8","name":"软笔书法",
     * "id":"11"},"categoriesId":"11","areasName":null,"id":"20","provinceName":"中华人民共和国","status":1,
     * "chatroomId":"4243976"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2016-10-23 10:42:41:383
     */

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
    /**
     * summary : null
     * channelsId : 44
     * amount : 0
     * onlineUserCount : 1
     * editUserTime : 2016-10-22 17:13:31
     * roomName : 测
     * chatUser : {"tabId":"2661","tab":"com.studying.cm.model.impl.customer.Customer","gender":"1","name":"个",
     * "icon":"http://video.xqban.com/Customer/2016-10-11/1476173707633_filename.jpg","sign":null,"accid":"b0b8def80ae8a943728bce6a087ad71e","birth":"1990-10-10","id":"28","email":null}
     * roomPic : http://video.xqban.com/Customer/2016-10-11/1476173707633_filename.jpg
     * valid : true
     * cityName : 上海市
     * channels : {"crtime":"2016-09-30 11:02:18","pushUrl":"rtmp://pee49e842.live.126.net/live/0b2427e8a50042d99b669a60e1ee7174?wsSecret=eb04cccee3a3c52a7d47e81fbcc7eb3f&wsTime=1475204537","rtmpPullUrl":"rtmp://vee49e842.live.126.net/live/0b2427e8a50042d99b669a60e1ee7174","channelName":"F9726308949E65C8D96B5867D0214B6F","id":"44","roomsId":"23","type":0,"cid":"0b2427e8a50042d99b669a60e1ee7174"}
     * chatUserId : 28
     * catagories : {"parentsId":"1","name":"速写","id":"133"}
     * categoriesId : 133
     * areasName : null
     * id : 23
     * provinceName : 中华人民共和国
     * status : 1
     * chatroomId : 4257541
     */

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
