package com.jxy.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jxy.ssm.mapper.StudentMapper;
import com.jxy.ssm.po.Student;

@Service
public class StudentService {
	@Autowired
	StudentMapper studentMapper;
	@Transactional(isolation=Isolation.READ_COMMITTED)  //隔离级别
	public int checkPwd(String stu_id,String stu_password) {
		Student student = studentMapper.findById(stu_id);
		if(student==null) {
			System.out.println("账号不存在");
			return 0;
		}else {
			if(student.getStu_password().equals(stu_password)) {
				System.out.println("账号存在且密码正确");
				return 1;
			}else {
				System.out.println("账号存在，密码不正确");
				return -1;
			}
		}
		
	}
	@Transactional
	public Student getStudent(String stu_id) {
		Student student = studentMapper.findById(stu_id);
		return student;
		
	}
	@Transactional
	public boolean setStudentImg(Student student) {
		int a =studentMapper.setStudentImg(student);
		return a>0? true:false;
	}
	@Transactional
	public boolean setStudentPwd(Student student) {
		int a = studentMapper.setStudentPwd(student);
		return a>0? true:false;
	}
	@Transactional
	public List<Student> getReport(String task_id){
		return studentMapper.getReport(task_id);
	}
	
	

}
