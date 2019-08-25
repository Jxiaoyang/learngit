package com.jxy.ssm.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class WhoReceive {
	private String  whoImg ;
	private String whoName;
	private String whoId;
	private Date receiveTime;
	public WhoReceive() {
		
	}
	public WhoReceive(String whoImg, String whoName, String whoId, Date receiveTime) {
		super();
		this.whoImg = whoImg;
		this.whoName = whoName;
		this.whoId = whoId;
		this.receiveTime = receiveTime;
	}
	public String getWhoImg() {
		return whoImg;
	}
	public void setWhoImg(String whoImg) {
		this.whoImg = whoImg;
	}
	public String getWhoName() {
		return whoName;
	}
	public void setWhoName(String whoName) {
		this.whoName = whoName;
	}
	public String getWhoId() {
		return whoId;
	}
	public void setWhoId(String whoId) {
		this.whoId = whoId;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	

}
