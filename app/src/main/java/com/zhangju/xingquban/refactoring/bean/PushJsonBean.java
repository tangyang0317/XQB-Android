package com.zhangju.xingquban.refactoring.bean;

import java.io.Serializable;

/**
 * @packageName com.zhangju.xingquban.refactoring.bean
 * @FileName PushJsonBean
 * @Author tangyang
 * @DATE 2018/9/28
 **/
public class PushJsonBean implements Serializable {


    /**
     * extras : {"id":"1339","pushInfotype":"3","typeId":"42568"}
     */

    private ExtrasBean extras;

    public ExtrasBean getExtras() {
        return extras;
    }

    public void setExtras(ExtrasBean extras) {
        this.extras = extras;
    }

    public static class ExtrasBean implements Serializable {
        /**
         * id : 1339
         * pushInfotype : 3
         * typeId : 42568
         */

        private String id;
        private String pushInfotype;
        private String typeId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPushInfotype() {
            return pushInfotype;
        }

        public void setPushInfotype(String pushInfotype) {
            this.pushInfotype = pushInfotype;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }
}
