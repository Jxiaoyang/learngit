package com.jxy.ssm.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;



public class GetOpenidUtil {
	public String sendPostRequest(String url) throws Exception {
		
		StringBuffer stringBuffer = new StringBuffer("");
	
		URL postUrl = new URL(url);  
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();//���URL����
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST"); 
		connection.setUseCaches(false); 
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		int a =0;
		int b= 0;
		while ((line = reader.readLine()) != null) {
			stringBuffer.append(line);
	
		}
		return stringBuffer.toString();
	}

}
