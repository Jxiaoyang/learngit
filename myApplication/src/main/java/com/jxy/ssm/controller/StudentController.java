package com.jxy.ssm.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jxy.ssm.mapper.StudentMapper;
import com.jxy.ssm.po.Student;
import com.jxy.ssm.service.StudentService;

@Controller
@RequestMapping(value="/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/getInformation")
	@ResponseBody
	public Student getInformation(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Student student = (Student)session.getAttribute("student");
		student.setStu_password("");
		return student;
		
	}
	@RequestMapping(value="/getStudent")
	@ResponseBody
	public Student getStudent(HttpServletRequest request) {
		String demPutId = request.getParameter("demPutId");
		Student student = studentService.getStudent(demPutId);
		student.setStu_password("");
		return student;
		
	}
	@RequestMapping("/upload")
	public @ResponseBody String upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()) {
            //上传文件路径
		
            String path = request.getServletContext().getRealPath("/upload");
            System.out.println(path);
            //上传文件名
            String filename =  UUID.randomUUID().toString().replaceAll("-", "");//  文件名称
            String lastname = file.getOriginalFilename();      //获得文件的原来名字
            lastname = lastname.substring(lastname.length()-4); //获得后缀名 列 .png
            //组装
            String name = filename+lastname;
            
            /*添加到数据库*/
            Student student = (Student)request.getSession().getAttribute("student");
            student.setStu_img(name); //传进session 中
           studentService.setStudentImg(student);
           
           
            
            
            File filepath = new File(path,name); 
            System.out.println(filepath);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
				file.transferTo(filepath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.getSession().setAttribute("student", student); //更新session
            return "success";
        } else {
            return "error";
        }	
	}
	@RequestMapping("/img")
	@ResponseBody
	public String execute(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,@RequestParam("name") String name) throws Exception {
   // img为图片的二进制流
		
		
		String path = httpServletRequest.getServletContext().getRealPath("/upload");
		File file  = new File(path,name);
		FileInputStream fin = new FileInputStream(file); 
		byte[] file_img = new byte[(int)file.length()];
		fin.read(file_img);
		String lastname = name.substring(name.length()-3);//后缀名
	   httpServletResponse.setContentType("image/+lastname");	
	   OutputStream os = httpServletResponse.getOutputStream();
	   os.write(file_img);
	   os.flush();
	   os.close();
	   return "success";
	}
	
	/*重置密码*/
	@RequestMapping("/setPwd")
	@ResponseBody
	public String setPwd(HttpServletRequest request,@RequestParam("old") String old,@RequestParam("now") String now ) {
		
		HttpSession session = request.getSession();
		Student student =(Student)session.getAttribute("student");
		if(old.equals(student.getStu_password())) {
			student.setStu_password(now);
			studentService.setStudentPwd(student);
			session.setAttribute("student", student);
			return "1";
		}else {
			return "0";
		}
		
		
		
		
		
		
	}
	
	/*报表*/
	@RequestMapping("/getReport")

	public void getReport(@RequestParam("demId") String task_id,HttpServletResponse response) throws InvalidFormatException, IOException {
		String path = this.getClass().getClassLoader().getResource("student.xlsx").getPath();
		System.out.println(path);
		List<Student> list = studentService.getReport(task_id);
		InputStream in = new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(in);
		
		Sheet sheet =wb.getSheetAt(0);
		Row row = null;
		Student student = null;
		for(int i = 0 ; i < list.size();i++) {
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(list.get(i).getStu_id());
			row.createCell(1).setCellValue(list.get(i).getStu_name());
			row.createCell(2).setCellValue(list.get(i).getStu_sex());
			row.createCell(3).setCellValue(list.get(i).getStu_class());
			row.createCell(4).setCellValue(list.get(i).getStu_phone());
			row.createCell(5).setCellValue(list.get(i).getStu_yard());
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		os.close();
		
		 response.setContentType("application/x-msdownload;charset=UTF-8");
		 response.setHeader("Content-Disposition","attachment;filename="+"xuesheng.xlsx" );
		 IOUtils.write(os.toByteArray(), response.getOutputStream());
		
		
	}


}
