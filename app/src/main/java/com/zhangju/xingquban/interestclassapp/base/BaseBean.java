package com.zhangju.xingquban.interestclassapp.base;

/**
 * Created by liush on 2016/12/20 0020.
 *
 * @所有的Bean类继承此类即可 当请求接口success为false时调用toString方法显示错误信息
 */
public class BaseBean {
    private ErrMsgBean errMsg;
    private boolean    success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ErrMsgBean getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(ErrMsgBean errMsg) {
        this.errMsg = errMsg;
    }

    public static class ErrMsgBean {
        private String phone;
        private String confirmPassword;
        private String password;
        private String verCode;
        private String varCode;
        private String sys;
        private String summary;
        private String resourcesId;
        private String classRoom;

        private String qCertificate;
        private String name;
        private String IDCard;
        private String contactTel;
        private String session;

        private String coinCardId;
        private String currencyId;

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public String getCoinCardId() {
            return coinCardId;
        }

        public void setCoinCardId(String coinCardId) {
            this.coinCardId = coinCardId;
        }

        public String getPayed() {
            return payed;
        }

        public void setPayed(String payed) {
            this.payed = payed;
        }

        private String payed;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerCode() {
            return verCode;
        }

        public void setVerCode(String verCode) {
            this.verCode = verCode;
        }

        public String getVarCode() {
            return varCode;
        }

        public void setVarCode(String varCode) {
            this.varCode = varCode;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getResourcesId() {
            return resourcesId;
        }

        public void setResourcesId(String resourcesId) {
            this.resourcesId = resourcesId;
        }

        public String getClassRoom() {
            return classRoom;
        }

        public void setClassRoom(String classRoom) {
            this.classRoom = classRoom;
        }

        public String getqCertificate() {
            return qCertificate;
        }

        public void setqCertificate(String qCertificate) {
            this.qCertificate = qCertificate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIDCard() {
            return IDCard;
        }

        public void setIDCard(String IDCard) {
            this.IDCard = IDCard;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getSession() {
            return session;
        }

        public void setSession(String session) {
            this.session = session;
        }

        public String getSys() {
            return sys;
        }

        public void setSys(String sys) {
            this.sys = sys;
        }

        @Override
        public String toString() {
            String s = phone + confirmPassword + password + varCode + verCode + sys + summary + resourcesId +
                    classRoom + qCertificate + name + IDCard + contactTel + session + payed + coinCardId + currencyId;
            return s.replace("null", "");
        }
    }
}
