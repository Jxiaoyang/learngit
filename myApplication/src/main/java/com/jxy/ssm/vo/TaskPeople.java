package com.jxy.ssm.vo;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class TaskPeople {
	private int task_id;  //任务编号
	private int task_type;   // 任务类型
	private String task_name;  // 任务名字
	private String task_content;  //任务内容
	private String task_pricontent;//隐私内容
	private int task_people;    //任务需要人数
	private int task_money;    //任务佣金
	private int task_receive;  // 任务是否接受
	private int task_accomplish;  //任务是否完成
	private Date task_taskTime;  //任务发布时间
	private String task_putID;		//任务发布人ID
	private int people;
	private String stu_img;
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getTask_type() {
		return task_type;
	}
	public void setTask_type(int task_type) {
		this.task_type = task_type;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_content() {
		return task_content;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public String getTask_pricontent() {
		return task_pricontent;
	}
	public void setTask_pricontent(String task_pricontent) {
		this.task_pricontent = task_pricontent;
	}
	public int getTask_people() {
		return task_people;
	}
	public void setTask_people(int task_people) {
		this.task_people = task_people;
	}
	public int getTask_money() {
		return task_money;
	}
	public void setTask_money(int task_money) {
		this.task_money = task_money;
	}
	public int getTask_receive() {
		return task_receive;
	}
	public void setTask_receive(int task_receive) {
		this.task_receive = task_receive;
	}
	public int getTask_accomplish() {
		return task_accomplish;
	}
	public void setTask_accomplish(int task_accomplish) {
		this.task_accomplish = task_accomplish;
	}
	public Date getTask_taskTime() {
		return task_taskTime;
	}
	public void setTask_taskTime(Date task_taskTime) {
		this.task_taskTime = task_taskTime;
	}
	public String getTask_putID() {
		return task_putID;
	}
	public void setTask_putID(String task_putID) {
		this.task_putID = task_putID;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getStu_img() {
		return stu_img;
	}
	public void setStu_img(String stu_img) {
		this.stu_img = stu_img;
	}
	public TaskPeople(int task_id, int task_type, String task_name, String task_content, String task_pricontent,
			int task_people, int task_money, int task_receive, int task_accomplish, Date task_taskTime,
			String task_putID, int people, String stu_img) {
		super();
		this.task_id = task_id;
		this.task_type = task_type;
		this.task_name = task_name;
		this.task_content = task_content;
		this.task_pricontent = task_pricontent;
		this.task_people = task_people;
		this.task_money = task_money;
		this.task_receive = task_receive;
		this.task_accomplish = task_accomplish;
		this.task_taskTime = task_taskTime;
		this.task_putID = task_putID;
		this.people = people;
		this.stu_img = stu_img;
	}
	@Override
	public String toString() {
		return "TaskPeople [task_id=" + task_id + ", task_type=" + task_type + ", task_name=" + task_name
				+ ", task_content=" + task_content + ", task_pricontent=" + task_pricontent + ", task_people="
				+ task_people + ", task_money=" + task_money + ", task_receive=" + task_receive + ", task_accomplish="
				+ task_accomplish + ", task_taskTime=" + task_taskTime + ", task_putID=" + task_putID + ", people="
				+ people + ", stu_img=" + stu_img + "]";
	}
	public TaskPeople() {
		
	}
	
	
	
}