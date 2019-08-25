package com.jxy.websocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.jxy.ssm.po.ChatHome;
import com.jxy.ssm.po.Student;
import com.jxy.ssm.service.ChatHomeService;
import com.jxy.ssm.vo.ChatMessage;
@Controller
public class WebSocketPushHandler implements WebSocketHandler {
  
	@Autowired
	private ChatHomeService chatHomeService;
	
	
	//表示 每个 聊天室得所有成员 即 demId,学号,和通道
	private static final Map<String,Map<String,WebSocketSession>> user = new HashMap<String,Map<String,WebSocketSession>>();
	
    private static final Map<String,List<WebSocketSession>> taskChat = new HashMap<String,List<WebSocketSession>>();

    // 用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	
        System.out.println("成功进入系统");
        
        Map<String,Object> map = session.getHandshakeAttributes();
        
        
        Student student = (Student) map.get("student");//获得学生
        
        String studentid = student.getStu_id();
        
        String demId = (String) map.get("demId");   //拿到聊天室的唯一标识
        String path = (String) map.get("chatPath"); //拿到聊天记录存储路径
        System.out.println(path);
       
        if(taskChat.containsKey(demId)) {  //如果这个聊天室已经建起来了，直接加入
        	taskChat.get(demId).add(session);  	 //放入聊天室中， 每个session 未标识
        	user.get(demId).put(studentid, session);  //这个标识了
        	
        	
        }else {                                //如果是第一个人进入，则新建
        	/**/
        	List<WebSocketSession> list = new ArrayList<WebSocketSession>();
        	list.add(session);
        	taskChat.put(demId, list);
        	/**/
        	Map<String,WebSocketSession> userMap = new HashMap<String,WebSocketSession>();
        	userMap.put(studentid, session);
        	user.put(demId, userMap);
        	
        }
        ChatHome chatHome = new ChatHome();
        chatHome.setCh_id(demId);
        chatHome.setChatPath("");
        System.out.println("读取该demId的聊天记录");
        if(chatHomeService.countChat(chatHome) >0) {
        	//取出聊天记录
        	ChatHome c = chatHomeService.getChat(demId);
        	File filepath = new File(path,c.getChatPath());
        	BufferedReader br = new BufferedReader(new FileReader(filepath));
        	
        	String fileStr;
        	while((fileStr= br.readLine()) !=null) {  //每次读出一句就发送一句
        		
        		
        		sendMessageToUser(demId,studentid,new TextMessage(fileStr));
        		
        		System.out.println("成功发送记录");
        	}
        	
        	
        }else {
        	// 新建聊天记录文件
        	System.out.println(demId);
        	System.out.println(path);
        	 File filepath = new File(path,demId+".txt"); 
        	 System.out.println(filepath.getParent());
        	//判断路径是否存在，如果不存在就创建一个
             if (!filepath.getParentFile().exists()) { 
                 filepath.getParentFile().mkdirs();
                
             }
             if(!filepath.exists()) {
            	 filepath.createNewFile();
            	 System.out.println("创建成功");
             }
             chatHome.setChatPath(demId+".txt");
             chatHomeService.insertChat(chatHome);
        	
        }
       // sendMessagesToUsers(new TextMessage("{\"message\":\"今天晚上服务器维护,请注意\"}"));
      //  System.out.println("发送成功");
    }

    //
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	System.out.println("接受消息："+message.getPayload().toString());
    	
    	 Map<String,Object> map = session.getHandshakeAttributes();
         String demId = (String) map.get("demId");   //拿到聊天室的唯一标识
         Student student =(Student) map.get("student"); //拿到聊天的同学信息
         String path = (String) map.get("chatPath"); //拿到聊天记录存储路径
         
         ChatMessage chatMessage = new ChatMessage();
         chatMessage.setMessage(message.getPayload().toString());
         chatMessage.setStu_img(student.getStu_img());
         chatMessage.setStu_name(student.getStu_name());
         chatMessage.setTask_id(demId);
         chatMessage.setTime(new Date());
         String json = JSON.toJSONString(chatMessage);
        
         File file = new File(path,demId+".txt");
         FileWriter fw = new FileWriter(file,true);
         BufferedWriter br = new BufferedWriter(fw);
         br.write(json+"\r\n");
         br.close();
         fw.close();
         System.out.println(json);
         TextMessage msg = new TextMessage(json);
         System.out.println(msg.toString());
    	sendMessagesToUsers(demId,msg);
    	
        // 将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage，
        // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等
        // TextMessage msg = (TextMessage)message.getPayload();
        // 给所有用户群发消息
        //sendMessagesToUsers(msg);
        // 给指定用户群发消息
        //sendMessageToUser(userId, msg);

    }

    // 后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    // 用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        Map<String,Object> map = session.getHandshakeAttributes();
        String demId = (String) map.get("demId");   //拿到聊天室的唯一标识
        List<WebSocketSession> list = taskChat.get(demId);
        if(list.contains(session)&&list.size() == 1) {
        	taskChat.remove("demId");
        }else {
        	list.remove(session);
        	taskChat.put(demId,list);
        }
       
        System.out.println("安全退出了系统");

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给指定聊天室的所有的用户发送消息 
     */
    public void sendMessagesToUsers(String demId,TextMessage message) {
    	List<WebSocketSession> list = taskChat.get(demId);
        for (WebSocketSession user : list) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
      
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息给指定的用户
     */
    public void sendMessageToUser(String demId,String studentid, TextMessage message) {
    	Map<String,WebSocketSession> map = user.get(demId);
    	if(map.containsKey(studentid)) {
    		WebSocketSession  session = map.get(studentid);
    		if(session.isOpen()) {
    			try {
					session.sendMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
        
    }
}
