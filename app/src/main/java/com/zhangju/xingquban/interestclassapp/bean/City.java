package com.zhangju.xingquban.interestclassapp.bean;

import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.util.List;

/**
 * author zaaach on 2016/1/26.
 */
public class City
        extends BaseBean {
    private List<AaDataBean> aaData;
    private AttachDataBean   attachData;

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public AttachDataBean getAttachData() {
        return attachData;
    }

    public void setAttachData(AttachDataBean attachData) {
        this.attachData = attachData;
    }

    public static class AttachDataBean {
        private String defaultServiceCityId;
        private String defaultServiceCityName;

        public String getDefaultServiceCityId() {
            return defaultServiceCityId;
        }

        public void setDefaultServiceCityId(String defaultServiceCityId) {
            this.defaultServiceCityId = defaultServiceCityId;
        }

        public String getDefaultServiceCityName() {
            return defaultServiceCityName;
        }

        public void setDefaultServiceCityName(String defaultServiceCityName) {
            this.defaultServiceCityName = defaultServiceCityName;
        }
    }

    public static class AaDataBean {
        private String  addrShortPinyin;
        private String  name;
        private String  pid;
        private String  id;
        private boolean specialAreas;


        public AaDataBean() {
        }

        public AaDataBean(String name, String pinyin) {
            this.name = name;
            this.addrShortPinyin = pinyin;
        }

        public AaDataBean(String name, String pinyin, String id, boolean specialAreas) {
            this.name = name;
            this.addrShortPinyin = pinyin;
            this.id = id;
            this.specialAreas = specialAreas;
        }

        public String getAddrShortPinyin() {
            return addrShortPinyin;
        }

        public void setAddrShortPinyin(String addrShortPinyin) {
            this.addrShortPinyin = addrShortPinyin;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isSpecialAreas() {
            return specialAreas;
        }

        public void setSpecialAreas(boolean specialAreas) {
            this.specialAreas = specialAreas;
        }
    }
}
