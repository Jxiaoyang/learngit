package com.jxy.ssm.po;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private String stu_id;
	private String stu_name;
	private String stu_sex;
	private String stu_area;
	private String stu_yard;
	private String stu_major;
	private String stu_class;
	private String stu_password;
	private String stu_phone;
	private String stu_img;
	private String stu_access;
	public Student() {
		
	}
	public Student(String stu_id, String stu_name, String stu_sex, String stu_area, String stu_yard, String stu_major,
			String stu_class, String stu_password, String stu_phone, String stu_img,String stu_access) {
		super();
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.stu_sex = stu_sex;
		this.stu_area = stu_area;
		this.stu_yard = stu_yard;
		this.stu_major = stu_major;
		this.stu_class = stu_class;
		this.stu_password = stu_password;
		this.stu_phone = stu_phone;
		this.stu_img = stu_img;
		this.stu_access = stu_access;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_area() {
		return stu_area;
	}
	public void setStu_area(String stu_area) {
		this.stu_area = stu_area;
	}
	public String getStu_yard() {
		return stu_yard;
	}
	public void setStu_yard(String stu_yard) {
		this.stu_yard = stu_yard;
	}
	public String getStu_major() {
		return stu_major;
	}
	public void setStu_major(String stu_major) {
		this.stu_major = stu_major;
	}
	public String getStu_class() {
		return stu_class;
	}
	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	public String getStu_phone() {
		return stu_phone;
	}
	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}
	public String getStu_img() {
		return stu_img;
	}
	public void setStu_img(String stu_img) {
		this.stu_img = stu_img;
	}
	public String getStu_access() {
		return stu_access;
	}
	public void setStu_access(String stu_access) {
		this.stu_access = stu_access;
	}
	@Override
	public String toString() {
		return "Student [stu_id=" + stu_id + ", stu_name=" + stu_name + ", stu_sex=" + stu_sex + ", stu_area="
				+ stu_area + ", stu_yard=" + stu_yard + ", stu_major=" + stu_major + ", stu_class=" + stu_class
				+ ", stu_password=" + stu_password + ", stu_phone=" + stu_phone + ", stu_img=" + stu_img
				+ ", stu_access=" + stu_access + "]";
	}
	
}