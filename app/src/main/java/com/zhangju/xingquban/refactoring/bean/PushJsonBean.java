package com.zhangju.xingquban.refactoring.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @packageName com.zhangju.xingquban.refactoring.bean
 * @FileName PushJsonBean
 * @Author tangyang
 * @DATE 2018/9/28
 **/
public class PushJsonBean {
    /**
     * android : {"alert":"教育才是生产力","extras":{"id":"1331","pushInfotype":"3","typeId":"42271"},"title":"教育才是生产力"}
     * ios : {"alert":{"body":"教育才是生产力","title":"教育才是生产力"},"badge":"1","content-available":1,"extras":{"id":"1331","pushInfotype":"3","typeId":"42271"},"sound":"sound.caf"}
     * winphone : {"alert":"教育才是生产力","extras":{"id":"1331","pushInfotype":"3","typeId":"42271"},"title":"教育才是生产力"}
     */
    private AndroidBean android;
    private IosBean ios;
    private WinphoneBean winphone;

    public AndroidBean getAndroid() {
        return android;
    }

    public void setAndroid(AndroidBean android) {
        this.android = android;
    }

    public IosBean getIos() {
        return ios;
    }

    public void setIos(IosBean ios) {
        this.ios = ios;
    }

    public WinphoneBean getWinphone() {
        return winphone;
    }

    public void setWinphone(WinphoneBean winphone) {
        this.winphone = winphone;
    }

    public static class AndroidBean {
        /**
         * alert : 教育才是生产力
         * extras : {"id":"1331","pushInfotype":"3","typeId":"42271"}
         * title : 教育才是生产力
         */

        private String alert;
        private ExtrasBean extras;
        private String title;

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public ExtrasBean getExtras() {
            return extras;
        }

        public void setExtras(ExtrasBean extras) {
            this.extras = extras;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ExtrasBean {
            /**
             * id : 1331
             * pushInfotype : 3
             * typeId : 42271
             */

            private String id;
            private String pushInfotype;
            private String typeId;
            private String isTeacher;

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

            public String getIsTeacher() {
                return isTeacher;
            }

            public void setIsTeacher(String isTeacher) {
                this.isTeacher = isTeacher;
            }
        }
    }

    public static class IosBean {
        /**
         * alert : {"body":"教育才是生产力","title":"教育才是生产力"}
         * badge : 1
         * content-available : 1
         * extras : {"id":"1331","pushInfotype":"3","typeId":"42271"}
         * sound : sound.caf
         */

        private AlertBean alert;
        private String badge;
        @SerializedName("content-available")
        private int contentavailable;
        private ExtrasBeanX extras;
        private String sound;

        public AlertBean getAlert() {
            return alert;
        }

        public void setAlert(AlertBean alert) {
            this.alert = alert;
        }

        public String getBadge() {
            return badge;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }

        public int getContentavailable() {
            return contentavailable;
        }

        public void setContentavailable(int contentavailable) {
            this.contentavailable = contentavailable;
        }

        public ExtrasBeanX getExtras() {
            return extras;
        }

        public void setExtras(ExtrasBeanX extras) {
            this.extras = extras;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public static class AlertBean {
            /**
             * body : 教育才是生产力
             * title : 教育才是生产力
             */

            private String body;
            private String title;

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ExtrasBeanX {
            /**
             * id : 1331
             * pushInfotype : 3
             * typeId : 42271
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

    public static class WinphoneBean {
        /**
         * alert : 教育才是生产力
         * extras : {"id":"1331","pushInfotype":"3","typeId":"42271"}
         * title : 教育才是生产力
         */

        private String alert;
        private ExtrasBeanXX extras;
        private String title;

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public ExtrasBeanXX getExtras() {
            return extras;
        }

        public void setExtras(ExtrasBeanXX extras) {
            this.extras = extras;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ExtrasBeanXX {
            /**
             * id : 1331
             * pushInfotype : 3
             * typeId : 42271
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
}
