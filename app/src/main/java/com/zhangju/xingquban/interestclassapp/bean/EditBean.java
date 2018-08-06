package com.zhangju.xingquban.interestclassapp.bean;

/*
 *  sEcho: 0,
	iTotalRecords: 0,
	iTotalDisplayRecords: 0,
	aaData: null,
	success: false,
	isLogin: false,
	errMsg: {
	phone: "手机不能为空",
	password: "密码不能为空"
	},
	isAdmin: false,
	cId: null,
	cname: null,
	time: "2015-12-16 10:41:27:947"
	 */
public class EditBean {
	private Data aaData;
	private boolean isLogin;
	private boolean success;
	private ErrMsg errMsg;
	
	
	
	public Data getAaData() {
		return aaData;
	}
	public void setAaData(Data aaData) {
		this.aaData = aaData;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrMsg getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(ErrMsg errMsg) {
		this.errMsg = errMsg;
	}
	public boolean isIsLogin() {
		return isLogin;
	}
	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
}
