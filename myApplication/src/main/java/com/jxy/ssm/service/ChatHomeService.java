package com.jxy.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxy.ssm.mapper.ChatHomeMapper;
import com.jxy.ssm.po.ChatHome;

@Service
public class ChatHomeService {
	
	@Autowired
	ChatHomeMapper chatHomeMapper;
	public int countChat(ChatHome chatHome) {
		
		return chatHomeMapper.countChat(chatHome);
	}
	public int insertChat(ChatHome chatHome) {
		return chatHomeMapper.createChat(chatHome);
	}
	public int updateChat(ChatHome chatHome) {
		return chatHomeMapper.updateChat(chatHome);
	}
	public ChatHome getChat(String ch_id) {
		return chatHomeMapper.getChat(ch_id);
	}

}
