package com.jxy.ssm.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public static Map userMap = new HashMap();  
	

	private   LoginSession loginSession=LoginSession.getInstance();
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		loginSession.AddSession(se.getSession());
		System.out.println("创建成功");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		loginSession.DelSession(se.getSession());  //销毁时
		System.out.println("销毁成功");
		
	} 
	
	

}
