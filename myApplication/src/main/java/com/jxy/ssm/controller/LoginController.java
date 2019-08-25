package com.jxy.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxy.ssm.listener.LoginSession;
import com.jxy.ssm.po.Student;
import com.jxy.ssm.service.StudentService;

@Controller
@RequestMapping("/login")
public class LoginController {
//	@RequestMapping("/openid")
//	@ResponseBody
//	public String get(HttpServletRequest request) {
//		String code =request.getParameter("code");
//		System.out.println(code);
//		GetOpenidUtil getOpenidUtil = new GetOpenidUtil();
//		try {
//			
//			
//			String userMessage =  getOpenidUtil.sendPostRequest("https://api.weixin.qq.com/sns/jscode2session?appid=wxbdb2fd66a3d7cbba&secret=f7a5aecca3c7b0311c650fdbbf1b231f&js_code="+code+"&grant_type=authorization_code");
//			//获得 session key  openid 
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("user", userMessage);
//			session.setMaxInactiveInterval(900); //15分钟
//			System.out.println(session.getId());
//			return session.getId();
//		
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		
//		
//	}
	@Autowired
	private StudentService studentService;
	@RequestMapping(value="toLogin")
	public @ResponseBody Map toLogin(@RequestBody Map<String,String> map,HttpServletRequest request) {
		Map<String,String> m =new HashMap<String,String>();
		Student student = null;
		LoginSession loginSession = LoginSession.getInstance();
		ServletContext servletContext = request.getServletContext();
		
		int toast = studentService.checkPwd(map.get("stu_in").trim(),map.get("stu_pw").trim() );
		if(toast == 1) {
			String session_id = (String) servletContext.getAttribute(map.get("stu_in"));
			if( session_id !=null) {
				
				HttpSession hs = loginSession.getSession(session_id);  // 获取该session
			//	loginSession.DelSession(hs);                     // 移除
				if( hs !=null) {
					hs.invalidate();                            /////强制过期
				                           /////强制过期
//					m.put("flag", "-1");// 该账户已经登录
//					return m;
				}
				
				
			}
			
			HttpSession session = request.getSession();
			//loginSession.AddSession(session);  //若登录成功则 放入定义得单列类中
			servletContext.setAttribute(map.get("stu_in"), session.getId());  //并放入进全局作用域
			m.put("SessionID", session.getId());
			student = (Student)studentService.getStudent(map.get("stu_in").trim());
			session.setAttribute("student", student);
			
			m.put("name", student.getStu_name());
		}
		m.put("flag", toast+"");
		
		return m;
	}

}
