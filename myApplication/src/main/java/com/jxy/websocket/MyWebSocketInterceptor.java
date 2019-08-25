package com.jxy.websocket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.jxy.ssm.po.Student;




public class MyWebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler arg2,
            Map<String, Object> attributes) throws Exception {
        // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
    	Student student = null;
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            student = (Student) httpRequest.getSession().getAttribute("student");
            
            
            
            String demId = httpRequest.getParameter("demId");
            System.out.println(demId+"?????");
            System.out.println(student.toString());
            attributes.put("demId", demId);          //标识 聊天室
            attributes.put("student", student);      //标识发送信息的学生
            String path = httpRequest.getServletContext().getRealPath("/chatFile");
            attributes.put("chatPath", path);
            

            System.out.println(student.toString());

        }
       

        System.out.println("连接到我了");

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
        // TODO Auto-generated method stub

    }
}