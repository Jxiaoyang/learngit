package com.jxy.ssm.po;

import org.springframework.stereotype.Component;

@Component
public class ChatHome {
	private String ch_id;
	private String chatPath;
	public ChatHome() {
		
	}
	public ChatHome(String ch_id, String chatPath) {
		super();
		this.ch_id = ch_id;
		this.chatPath = chatPath;
	}
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	public String getChatPath() {
		return chatPath;
	}
	public void setChatPath(String chatPath) {
		this.chatPath = chatPath;
	}

}
