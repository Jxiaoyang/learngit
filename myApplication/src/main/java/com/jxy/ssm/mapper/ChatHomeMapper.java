package com.jxy.ssm.mapper;

import org.springframework.stereotype.Repository;

import com.jxy.ssm.po.ChatHome;

@Repository
public interface ChatHomeMapper {
	
	public int createChat(ChatHome chatHome);
	public int updateChat(ChatHome chatHome);
	public ChatHome getChat(String ch_id);
	public int countChat(ChatHome chatHoem);

}
