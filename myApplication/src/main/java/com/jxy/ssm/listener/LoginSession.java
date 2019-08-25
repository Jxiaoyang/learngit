package com.jxy.ssm.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class LoginSession {
	
	private static LoginSession instance;
	private HashMap mymap;
	private LoginSession() {
		mymap = new HashMap();
	}
	public static LoginSession getInstance() {
		
		if (instance == null) {  

			instance = new LoginSession();  

			}  

			return instance;  

	} 
	public synchronized void AddSession(HttpSession session) {  

		if (session != null) {  

			mymap.put(session.getId(), session);  
		
		}  

	}  

	  

	public synchronized void DelSession(HttpSession session) {  

		if (session != null) {  

			mymap.remove(session.getId());  

		}  

	}  
	public synchronized HttpSession getSession(String session_id) {  

		if (session_id == null) return null;  

			return (HttpSession) mymap.get(session_id);  

	}  


	

  





  


  


  

}  

