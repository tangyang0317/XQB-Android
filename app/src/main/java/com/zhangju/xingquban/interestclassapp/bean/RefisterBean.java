package com.zhangju.xingquban.interestclassapp.bean;

/**
 * Created by Administrator on 2017/6/22.
 */

public class RefisterBean {

    @Override
    public String toString() {
        return "RefisterBean{" +
                "success=" + success +
                ", isLogin=" + isLogin +
                ", errMsg=" + errMsg +
                '}';
    }

    /**
     * success : false
     * isLogin : false
     * errMsg : {"confirmPassword":"确认码不能为空","phone":"手机不能为空","password":"密码不能为空"}
     */

    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;

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

    public static class ErrMsgBean {
        /**
         * confirmPassword : 确认码不能为空
         * phone : 手机不能为空
         * password : 密码不能为空
         */

        private String confirmPassword;
        private String phone;
        private String password;

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
