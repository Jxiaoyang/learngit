	package com.jxy.ssm.interceptor;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jxy.ssm.listener.LoginSession;
import com.jxy.ssm.po.Student;

/**
 * 
* <p>Title: LoginInterceptor</p>  
* <p>Description: </p>  
* <p>Company: 小阳</p>  
* @author 小阳 
* @date 2018年8月21日 下午6:50:27
* @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor{
	LoginSession loginSession = LoginSession.getInstance();

	/*在所有请求处理后执行，用来处理 记录，日志，或释放资源等等*/
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	} 

	/* 处理器执行完，并在渲染视图前执行，用于对视图前数据的处理等等*/
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*处理器执行前执行，用于拦截请求等等*/
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//拦截没有登陆的用户
		
		HttpSession session = request.getSession(false);  //小技巧，不能判断URL中得jsessionid
		String sessionid = request.getParameter("jsessionid");
		if(sessionid!=null) {
			if(loginSession.getSession(sessionid) !=null) {
				return true;
				
			}
		}
		
		
		if(session!=null) {
			
			return true;
		}else {
			
			
			response.setCharacterEncoding("UTF-8");  
			 response.setContentType("application/json; charset=utf-8");
			  PrintWriter out = null; 
			   System.out.println("过期了");
			   out = response.getWriter();  
			   out.append("-99");   //表示 session 过期 ，重新登录 
			
			return false;
		}
		
	}
	
	

}
