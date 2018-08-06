package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ydw on 2017/10/31.
 */
//热门
public class HotNewsLiveBean implements Serializable {
    /**
     * success : true
     * errMsg : {}
     * isAdmin : false
     * sEcho : 0
     * iTotalRecords : 17
     * iTotalDisplayRecords : 17
     * aaData : [{"models":{"id":"1386","roomsId":"69","chatUserId":"744","startTime":"2017-11-01 16:27:11","roomName":"安卓直播","roomPic":"http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg","amount":0,"comtCount":0,"likeCount":0,"onlineUserCount":36,"follows":0,"gets":0,"fans":0,"gives":0,"stdCoin":0,"catagoryName":"儿绘","address":"浙江省-杭州市","cityName":"杭州市","status":1,"syncVdoStatus":0,"chatUser":{"id":"744","tabId":"12777","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a94380be1db63b8ed8d6","name":"超神学院","icon":"http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg","gender":"0","ex":"{\"give\":8408,\"fllows\":0,\"get\":4890,\"accid\":\"b0b8def80ae8a94380be1db63b8ed8d6\",\"id\":\"744\",\"likes\":0,\"fans\":0}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"addUserTime":"2017-11-01 16:27:11","iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"rstype":1},{"roomPic":"http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg","models":{"id":"489","video_name":"282_20170802-164852_20170802-165831","orig_video_key":"f19b3bdadcf942b3b6e6016a935d0c7a_1501663732225_1501664311785_19930412-00000.mp4","uid":"30015","vid":"7900924","nId":"nId3034355","sdate":"2017-08-02 16:48:52","edate":"2017-08-02 16:58:32","beginTime":1501663732225,"endTime":1501664311785,"hotshow":true,"published":true,"channelsId":"64","chatUserId":"240","roomsId":"27","liveRecordId":"1303","liveChnlMapId":"282","likesCount":24,"comtCount":45,"seeCount":444,"chatUser":{"id":"240","tabId":"9589","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a9433ad91f351faeb506","name":"未来畅想画室","icon":"http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg","gender":"2","ex":"{\"give\":8199,\"fllows\":0,\"get\":678,\"accid\":\"b0b8def80ae8a9433ad91f351faeb506\",\"id\":\"240\",\"likes\":0,\"fans\":7}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-08-02 16:59:04","editUserId":"12777","editUserName":"超神学院(13422222222)","editUserTime":"2017-11-06 14:52:04","isAddUserType":"sys","isEditUserType":"customer","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":true,"rstype":2,"roomName":"未来畅想画室"},{"roomPic":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","models":{"id":"483","video_name":"276_20170802-151401_20170802-154413","orig_video_key":"5b46a82afd2e4a3b8d8de5d7f5be0a85_1501658041673_1501659853073_19918833-00000.mp4","uid":"30015","vid":"7879667","nId":"nId3028735","sdate":"2017-08-02 15:14:02","edate":"2017-08-02 15:44:13","beginTime":1501658041673,"endTime":1501659853073,"hotshow":true,"published":true,"channelsId":"62","chatUserId":"94","roomsId":"26","liveRecordId":"1297","liveChnlMapId":"276","likesCount":1,"comtCount":21,"seeCount":111,"chatUser":{"id":"94","tabId":"8935","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a943409bab32de3abe84","name":"我们都爱兴趣班","icon":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","gender":"2","ex":"{\"give\":9028,\"fllows\":18,\"get\":5666,\"accid\":\"b0b8def80ae8a943409bab32de3abe84\",\"id\":\"94\",\"likes\":0,\"fans\":19}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-08-02 15:44:32","editUserId":"12777","editUserName":"超神学院(13422222222)","editUserTime":"2017-11-06 14:17:05","isAddUserType":"sys","isEditUserType":"customer","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":true,"rstype":2,"roomName":"我们都爱兴趣班"},{"roomPic":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","models":{"id":"479","video_name":"275_20170802-145003_20170802-145345","orig_video_key":"f19b3bdadcf942b3b6e6016a935d0c7a_1501656603926_1501656825797_19915843-00000.mp4","uid":"30015","vid":"7883030","nId":"nId3031038","sdate":"2017-08-02 14:50:04","edate":"2017-08-02 14:53:46","beginTime":1501656603926,"endTime":1501656825797,"hotshow":true,"published":true,"channelsId":"64","chatUserId":"94","roomsId":"26","liveRecordId":"1296","liveChnlMapId":"275","likesCount":1,"comtCount":1,"seeCount":42,"chatUser":{"id":"94","tabId":"8935","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a943409bab32de3abe84","name":"我们都爱兴趣班","icon":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","gender":"2","ex":"{\"give\":9028,\"fllows\":18,\"get\":5666,\"accid\":\"b0b8def80ae8a943409bab32de3abe84\",\"id\":\"94\",\"likes\":0,\"fans\":19}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-08-02 14:54:55","editUserId":"12776","editUserName":"刺激大脑疯狂的惊喜(13411111111)","editUserTime":"2017-11-03 16:42:25","isAddUserType":"sys","isEditUserType":"sys","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":false,"rstype":2,"roomName":"我们都爱兴趣班"},{"roomPic":"http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg","models":{"id":"478","video_name":"274_20170802-135017_20170802-145106","orig_video_key":"5b46a82afd2e4a3b8d8de5d7f5be0a85_1501653017106_1501656666821_19908435-00000.mp4","uid":"30015","vid":"7879177","nId":"nId3024949","sdate":"2017-08-02 13:50:17","edate":"2017-08-02 14:51:07","beginTime":1501653017106,"endTime":1501656666821,"hotshow":true,"published":true,"channelsId":"62","chatUserId":"240","roomsId":"27","liveRecordId":"1295","liveChnlMapId":"274","likesCount":0,"comtCount":0,"seeCount":36,"chatUser":{"id":"240","tabId":"9589","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a9433ad91f351faeb506","name":"未来畅想画室","icon":"http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg","gender":"2","ex":"{\"give\":8199,\"fllows\":0,\"get\":678,\"accid\":\"b0b8def80ae8a9433ad91f351faeb506\",\"id\":\"240\",\"likes\":0,\"fans\":7}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-08-02 14:52:07","editUserId":"12776","editUserName":"二傻(13411111111)","editUserTime":"2017-10-20 15:19:07","isAddUserType":"sys","isEditUserType":"customer","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":false,"rstype":2,"roomName":"未来畅想画室"},{"roomPic":"http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg","models":{"id":"477","video_name":"273_20170802-132358_20170802-133002","orig_video_key":"5b46a82afd2e4a3b8d8de5d7f5be0a85_1501651438702_1501651802871_19905108-00000.mp4","uid":"30015","vid":"7866075","nId":"nId3016862","sdate":"2017-08-02 13:23:59","edate":"2017-08-02 13:30:03","beginTime":1501651438702,"endTime":1501651802871,"hotshow":true,"published":true,"channelsId":"62","chatUserId":"240","roomsId":"27","liveRecordId":"1294","liveChnlMapId":"273","likesCount":0,"comtCount":0,"seeCount":19,"chatUser":{"id":"240","tabId":"9589","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a9433ad91f351faeb506","name":"未来畅想画室","icon":"http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg","gender":"2","ex":"{\"give\":8199,\"fllows\":0,\"get\":678,\"accid\":\"b0b8def80ae8a9433ad91f351faeb506\",\"id\":\"240\",\"likes\":0,\"fans\":7}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-08-02 13:30:37","editUserTime":"2017-08-02 13:30:37","isAddUserType":"sys","isEditUserType":"sys","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":false,"rstype":2,"roomName":"未来畅想画室"},{"roomPic":"http://video.xqban.com/Customer/2017-03-24/1490353109321_528.jpg","models":{"id":"465","video_name":"261_20170723-163620_20170723-180546","orig_video_key":"da16e635a1d441fdacf820763b880b70_1500798980082_1500804346302_18390743-00000.mp4","uid":"30015","vid":"6080428","nId":"nId2372039","sdate":"2017-07-23 16:36:20","edate":"2017-07-23 18:05:46","beginTime":1500798980082,"endTime":1500804346302,"hotshow":true,"published":true,"channelsId":"53","chatUserId":"74","roomsId":"77","liveRecordId":"1282","liveChnlMapId":"261","likesCount":11,"comtCount":1,"seeCount":168,"chatUser":{"id":"74","tabId":"8460","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a943b517f5b439ddbd57","name":"惠州兴趣班","icon":"http://video.xqban.com/Customer/2017-03-24/1490353109321_528.jpg","gender":"1","ex":"{\"give\":9934,\"fllows\":1,\"get\":7336,\"accid\":\"b0b8def80ae8a943b517f5b439ddbd57\",\"id\":\"74\",\"likes\":0,\"fans\":4}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-07-23 18:07:59","editUserId":"12776","editUserName":"哈哈哈(13411111111)","editUserTime":"2017-11-03 16:40:50","isAddUserType":"sys","isEditUserType":"sys","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"isAddOp":true,"rstype":2,"roomName":"惠州兴趣班"},{"roomPic":"http://video.xqban.com/Customer/2017-03-24/1490353109321_528.jpg","models":{"id":"463","video_name":"259_20170723-135607_20170723-155608","orig_video_key":"95636617af5e4d92a43536fb99b8cac2_1500789367737_1500796568916_18384867-00000.mp4","uid":"30015","vid":"6066024","nId":"nId2368687","sdate":"2017-07-23 13:56:08","edate":"2017-07-23 15:56:09","beginTime":1500789367737,"endTime":1500796568916,"hotshow":true,"published":true,"channelsId":"52","chatUserId":"74","roomsId":"77","liveRecordId":"1280","liveChnlMapId":"259","likesCount":1,"comtCount":1,"seeCount":60,"chatUser":{"id":"74","tabId":"8460","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a943b517f5b439ddbd57","name":"惠州兴趣班","icon":"http://video.xqban.com/Customer/2017-03-24/1490353109321_528.jpg","gender":"1","ex":"{\"give\":9934,\"fllows\":1,\"get\":7336,\"accid\":\"b0b8def80ae8a943b517f5b439ddbd57\",\"id\":\"74\",\"likes\":0,\"fans\":4}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-07-23 15:59:13","editUserId":"12776","editUserName":"刺激大脑疯狂的惊喜(13411111111)","editUserTime":"2017-11-02 17:12:37","isAddUserType":"sys","isEditUserType":"customer","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"isAddOp":true,"rstype":2,"roomName":"中国国际青少年儿童艺术节"},{"roomPic":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","models":{"id":"372","video_name":"240_20170716-141005_20170716-142018","orig_video_key":"17be6d7fd5c94683a5c70ef4c6db16a7_1500185405271_1500186018438_17601619-00000.mp4","uid":"30015","vid":"5086809","nId":"nId2231931","sdate":"2017-07-16 14:10:05","edate":"2017-07-16 14:20:18","beginTime":1500185405271,"endTime":1500186018438,"hotshow":true,"published":true,"channelsId":"61","chatUserId":"94","roomsId":"26","liveRecordId":"1261","liveChnlMapId":"240","likesCount":1,"comtCount":0,"seeCount":85,"chatUser":{"id":"94","tabId":"8935","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a943409bab32de3abe84","name":"我们都爱兴趣班","icon":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","gender":"2","ex":"{\"give\":9028,\"fllows\":18,\"get\":5666,\"accid\":\"b0b8def80ae8a943409bab32de3abe84\",\"id\":\"94\",\"likes\":0,\"fans\":19}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-07-16 14:22:01","editUserId":"8935","editUserName":"陈燕娜(18804007023)","editUserTime":"2017-07-17 08:56:17","isAddUserType":"sys","isEditUserType":"customer","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":false,"rstype":2,"roomName":"我们都爱兴趣班"},{"roomPic":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","models":{"id":"371","video_name":"239_20170716-133821_20170716-140653","orig_video_key":"e692e0175e344d4fb460d81e273936bf_1500183501922_1500185213384_17597222-00000.mp4","uid":"30015","vid":"5085404","nId":"nId2232443","sdate":"2017-07-16 13:38:22","edate":"2017-07-16 14:06:53","beginTime":1500183501922,"endTime":1500185213384,"hotshow":true,"published":true,"channelsId":"60","chatUserId":"94","roomsId":"26","liveRecordId":"1260","liveChnlMapId":"239","likesCount":2,"comtCount":0,"seeCount":36,"chatUser":{"id":"94","tabId":"8935","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a943409bab32de3abe84","name":"我们都爱兴趣班","icon":"http://video.xqban.com/Customer/2017-02-08/1486540639738_134.jpg","gender":"2","ex":"{\"give\":9028,\"fllows\":18,\"get\":5666,\"accid\":\"b0b8def80ae8a943409bab32de3abe84\",\"id\":\"94\",\"likes\":0,\"fans\":19}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"removed":0,"addUserTime":"2017-07-16 14:09:26","editUserId":"12776","editUserName":"刺激大脑疯狂的惊喜(13411111111)","editUserTime":"2017-11-03 13:38:23","isAddUserType":"sys","isEditUserType":"customer","sorts":0,"display":1,"digest":0,"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"amount":0,"address":"辽宁省-沈阳市","isAddOp":true,"rstype":2,"roomName":"我们都爱兴趣班"}]
     * attachData : {"liveVdoUrl":"http://vodf4axywdy.vod.126.net/vodf4axywdy"}
     * total : 2
     * page : 1
     */

    private boolean success;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private AttachDataBean attachData;
    private int total;
    private int page;
    private List<AaDataBean> aaData;

    public static HotNewsLiveBean objectFromData(String str) {

        return new Gson().fromJson(str, HotNewsLiveBean.class);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public static class AttachDataBean {
        /**
         * liveVdoUrl : http://vodf4axywdy.vod.126.net/vodf4axywdy
         */

        private String liveVdoUrl;

        public static AttachDataBean objectFromData(String str) {

            return new Gson().fromJson(str, AttachDataBean.class);
        }

        public String getLiveVdoUrl() {
            return liveVdoUrl;
        }

        public void setLiveVdoUrl(String liveVdoUrl) {
            this.liveVdoUrl = liveVdoUrl;
        }
    }

    public static class AaDataBean {
        /**
         * models : {"id":"1386","roomsId":"69","chatUserId":"744","startTime":"2017-11-01 16:27:11","roomName":"安卓直播","roomPic":"http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg","amount":0,"comtCount":0,"likeCount":0,"onlineUserCount":36,"follows":0,"gets":0,"fans":0,"gives":0,"stdCoin":0,"catagoryName":"儿绘","address":"浙江省-杭州市","cityName":"杭州市","status":1,"syncVdoStatus":0,"chatUser":{"id":"744","tabId":"12777","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a94380be1db63b8ed8d6","name":"超神学院","icon":"http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg","gender":"0","ex":"{\"give\":8408,\"fllows\":0,\"get\":4890,\"accid\":\"b0b8def80ae8a94380be1db63b8ed8d6\",\"id\":\"744\",\"likes\":0,\"fans\":0}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"addUserTime":"2017-11-01 16:27:11","iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false}
         * rstype : 1
         * roomPic : http://video.xqban.com/Customer/2016-12-07/1481069018890_256.jpg
         * amount : 0
         * address : 辽宁省-沈阳市
         * isAddOp : true
         * roomName : 未来畅想画室
         */

        private ModelsBean models;
        private int rstype;
        private String roomPic;
        private int amount;
        private String address;
        private boolean isAddOp;
        private String roomName;

        public static AaDataBean objectFromData(String str) {

            return new Gson().fromJson(str, AaDataBean.class);
        }

        public ModelsBean getModels() {
            return models;
        }

        public void setModels(ModelsBean models) {
            this.models = models;
        }

        public int getRstype() {
            return rstype;
        }

        public void setRstype(int rstype) {
            this.rstype = rstype;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isIsAddOp() {
            return isAddOp;
        }

        public void setIsAddOp(boolean isAddOp) {
            this.isAddOp = isAddOp;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public static class ModelsBean {
            /**
             * id : 1386
             * roomsId : 69
             * chatUserId : 744
             * startTime : 2017-11-01 16:27:11
             * roomName : 安卓直播
             * roomPic : http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg
             * amount : 0
             * comtCount : 0
             * likeCount : 0
             * onlineUserCount : 36
             * follows : 0
             * gets : 0
             * fans : 0
             * gives : 0
             * stdCoin : 0
             * catagoryName : 儿绘
             * address : 浙江省-杭州市
             * cityName : 杭州市
             * status : 1
             * syncVdoStatus : 0
             * chatUser : {"id":"744","tabId":"12777","tab":"com.studying.cm.model.impl.customer.Customer","accid":"b0b8def80ae8a94380be1db63b8ed8d6","name":"超神学院","icon":"http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg","gender":"0","ex":"{\"give\":8408,\"fllows\":0,\"get\":4890,\"accid\":\"b0b8def80ae8a94380be1db63b8ed8d6\",\"id\":\"744\",\"likes\":0,\"fans\":0}","roomFollows":{"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false},"followes":{},"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false}
             * addUserTime : 2017-11-01 16:27:11
             * iDisplayStart : 0
             * iDisplayLength : 10
             * sEcho : 1
             * curpage : 0
             * rows : 10
             * isAdmin : false
             */

            private String id;
            private String roomsId;
            private String chatUserId;
            private String startTime;
            private String roomName;
            private String roomPic;
            private double amount;
            private int comtCount;
            private int likesCount;
            private int onlineUserCount;
            private int follows;
            private int gets;
            private int fans;
            private int gives;
            private int stdCoin;
            private String catagoryName;
            private String address;
            private String cityName;
            private int status;
            private int syncVdoStatus;
            private ChatUserBean chatUser;
            private String addUserTime;
            private int iDisplayStart;
            private int iDisplayLength;
            private int sEcho;
            private int curpage;
            private int rows;
            private int seeCount;
            private boolean isAdmin;

            public int getSeeCount() {
                return seeCount;
            }

            public void setSeeCount(int seeCount) {
                this.seeCount = seeCount;
            }

            public static ModelsBean objectFromData(String str) {

                return new Gson().fromJson(str, ModelsBean.class);
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

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public int getComtCount() {
                return comtCount;
            }

            public void setComtCount(int comtCount) {
                this.comtCount = comtCount;
            }

            public int getLikesCount() {
                return likesCount;
            }

            public void setLikesCount(int likesCount) {
                this.likesCount = likesCount;
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

            public String getCatagoryName() {
                return catagoryName;
            }

            public void setCatagoryName(String catagoryName) {
                this.catagoryName = catagoryName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
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

            public String getAddUserTime() {
                return addUserTime;
            }

            public void setAddUserTime(String addUserTime) {
                this.addUserTime = addUserTime;
            }

            public int getIDisplayStart() {
                return iDisplayStart;
            }

            public void setIDisplayStart(int iDisplayStart) {
                this.iDisplayStart = iDisplayStart;
            }

            public int getIDisplayLength() {
                return iDisplayLength;
            }

            public void setIDisplayLength(int iDisplayLength) {
                this.iDisplayLength = iDisplayLength;
            }

            public int getSEcho() {
                return sEcho;
            }

            public void setSEcho(int sEcho) {
                this.sEcho = sEcho;
            }

            public int getCurpage() {
                return curpage;
            }

            public void setCurpage(int curpage) {
                this.curpage = curpage;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public boolean isIsAdmin() {
                return isAdmin;
            }

            public void setIsAdmin(boolean isAdmin) {
                this.isAdmin = isAdmin;
            }

            public static class ChatUserBean {
                /**
                 * id : 744
                 * tabId : 12777
                 * tab : com.studying.cm.model.impl.customer.Customer
                 * accid : b0b8def80ae8a94380be1db63b8ed8d6
                 * name : 超神学院
                 * icon : http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg
                 * gender : 0
                 * ex : {"give":8408,"fllows":0,"get":4890,"accid":"b0b8def80ae8a94380be1db63b8ed8d6","id":"744","likes":0,"fans":0}
                 * roomFollows : {"iDisplayStart":0,"iDisplayLength":10,"sEcho":1,"curpage":0,"rows":10,"isAdmin":false}
                 * followes : {}
                 * iDisplayStart : 0
                 * iDisplayLength : 10
                 * sEcho : 1
                 * curpage : 0
                 * rows : 10
                 * isAdmin : false
                 */

                private String id;
                private String tabId;
                private String tab;
                private String accid;
                private String name;
                private String icon;
                private String gender;
                private String ex;
                private RoomFollowsBean roomFollows;
                private FollowesBean followes;
                private int iDisplayStart;
                private int iDisplayLength;
                private int sEcho;
                private int curpage;
                private int rows;
                private boolean isAdmin;

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

                public RoomFollowsBean getRoomFollows() {
                    return roomFollows;
                }

                public void setRoomFollows(RoomFollowsBean roomFollows) {
                    this.roomFollows = roomFollows;
                }

                public FollowesBean getFollowes() {
                    return followes;
                }

                public void setFollowes(FollowesBean followes) {
                    this.followes = followes;
                }

                public int getIDisplayStart() {
                    return iDisplayStart;
                }

                public void setIDisplayStart(int iDisplayStart) {
                    this.iDisplayStart = iDisplayStart;
                }

                public int getIDisplayLength() {
                    return iDisplayLength;
                }

                public void setIDisplayLength(int iDisplayLength) {
                    this.iDisplayLength = iDisplayLength;
                }

                public int getSEcho() {
                    return sEcho;
                }

                public void setSEcho(int sEcho) {
                    this.sEcho = sEcho;
                }

                public int getCurpage() {
                    return curpage;
                }

                public void setCurpage(int curpage) {
                    this.curpage = curpage;
                }

                public int getRows() {
                    return rows;
                }

                public void setRows(int rows) {
                    this.rows = rows;
                }

                public boolean isIsAdmin() {
                    return isAdmin;
                }

                public void setIsAdmin(boolean isAdmin) {
                    this.isAdmin = isAdmin;
                }

                public static class RoomFollowsBean {
                    public static RoomFollowsBean objectFromData(String str) {

                        return new Gson().fromJson(str, RoomFollowsBean.class);
                    }
                }

                public static class FollowesBean {
                    public static FollowesBean objectFromData(String str) {

                        return new Gson().fromJson(str, FollowesBean.class);
                    }
                }
            }
        }
    }
}
