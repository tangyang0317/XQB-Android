package com.zhangju.xingquban.interestclassapp.bean;

import java.util.ArrayList;

public class OrderData {
	
	/*
	 * 订单列表
	 */
	
	private String lessonName;
	private double amount;
	private int counts;
	private String lessonImg;
	private String sno;
	private Data lesson;
	private Data customer;
	
	/*
	 * 课程详情
	 */
	private Data teacherTime;
	private double price;
	private double vipPrice;
	private int allows;
	private ArrayList<Data> res;
	private String region;
	private String lessonDate;
	private boolean isCommented;
	private String id;
	private int workState; 
	
	
	
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getWorkState() {
		return workState;
	}
	public void setWorkState(int workState) {
		this.workState = workState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isIsCommented() {
		return isCommented;
	}
	public void setIsCommented(boolean isCommented) {
		this.isCommented = isCommented;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getLessonImg() {
		return lessonImg;
	}
	public void setLessonImg(String lessonImg) {
		this.lessonImg = lessonImg;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public Data getLesson() {
		return lesson;
	}
	public void setLesson(Data lesson) {
		this.lesson = lesson;
	}
	public Data getCustomer() {
		return customer;
	}
	public void setCustomer(Data customer) {
		this.customer = customer;
	}
	public Data getTeacherTime() {
		return teacherTime;
	}
	public void setTeacherTime(Data teacherTime) {
		this.teacherTime = teacherTime;
	}
	public double getVipPrice() {
		return vipPrice;
	}
	public void setVipPrice(double vipPrice) {
		this.vipPrice = vipPrice;
	}
	public int getAllows() {
		return allows;
	}
	public void setAllows(int allows) {
		this.allows = allows;
	}
	public ArrayList<Data> getRes() {
		return res;
	}
	public void setRes(ArrayList<Data> res) {
		this.res = res;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(String lessonDate) {
		this.lessonDate = lessonDate;
	}

	
	
}
