package com.jxy.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jxy.ssm.po.Student;

@Repository
public interface StudentMapper {
	public Student findById(String stu_id);//查找学生
	public int setStudentPwd(Student student);//更新学生
	
	public int setStudentImg(Student student);//更新头像
	public List<Student> getReport(String task_id);//已接受某任务人员信息报表

}
