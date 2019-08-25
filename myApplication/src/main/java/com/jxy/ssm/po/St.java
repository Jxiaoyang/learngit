package com.jxy.ssm.po;

import java.util.Date;

public class St {
	private String st_id;
	private String st_receiveid;
	private Date st_receiveTime;
	public St() {
		
	}
	public String getSt_id() {
		return st_id;
	}
	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}
	public String getSt_receiveid() {
		return st_receiveid;
	}
	public void setSt_receiveid(String st_receiveid) {
		this.st_receiveid = st_receiveid;
	}
	public Date getSt_receiveTime() {
		return st_receiveTime;
	}
	public void setSt_receiveTime(Date st_receiveTime) {
		this.st_receiveTime = st_receiveTime;
	}
	public St(String st_id, String st_receiveid, Date st_receiveTime) {
		super();
		this.st_id = st_id;
		this.st_receiveid = st_receiveid;
		this.st_receiveTime = st_receiveTime;
	}
	

}
