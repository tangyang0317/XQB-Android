package com.zhangju.xingquban.interestclassapp.bean;


/*
 *  phone: "手机不能为空",
	confirmPassword: "确认码不能为空",
	password: "密码不能为空"
 */
public class ErrMsg {
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
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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
	public void setVerCode(String varCode) {
		this.verCode = varCode;
	}
	public String getVarCode() {
		return varCode;
	}
	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}
	public String getSys() {
		return sys;
	}
	public void setSys(String sys) {
		this.sys = sys;
	}
	@Override
	public String toString() {
		String s = phone + confirmPassword + password + varCode + verCode + sys + summary + resourcesId + classRoom + qCertificate + name + IDCard + contactTel + session;
		return s.replace("null", "");
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
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	
}
