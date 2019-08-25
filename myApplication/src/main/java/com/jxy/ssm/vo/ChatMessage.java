package com.jxy.ssm.vo;

import java.util.Date;

public class ChatMessage {
	private String task_id; //聊天室ID 即 任务ID  
	private String stu_name;
	private String stu_img;
	private Date time;
	private String mes;
	public ChatMessage() {
		
	}
	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_img() {
		return stu_img;
	}
	public void setStu_img(String stu_img) {
		this.stu_img = stu_img;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMes() {
		return mes;
	}
	public void setMessage(String mes) {
		this.mes = mes;
	}
	public ChatMessage(String task_id, String stu_name, String stu_img, Date time, String mes) {
		super();
		this.task_id = task_id;
		this.stu_name = stu_name;
		this.stu_img = stu_img;
		this.time = time;
		this.mes = mes;
	}
	
}