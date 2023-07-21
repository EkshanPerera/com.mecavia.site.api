package com.mecavia.site.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSApi {
	
	@Value("${SMSID}")
	private String smsid;
	@Value("${SMSPASSWORD}")
	private String smspassword;
	private String res;
	private String resa[]; 
	private String inputLine; 
	
	public String sendSMS(String text,String receiver) throws Exception {
		URL textit = new URL("http://textit.biz/sendmsg/index.php?id="+smsid+"&pw="+smspassword+"&to="+ receiver +"&text="+text);
		BufferedReader in = new BufferedReader(
		new InputStreamReader(textit.openStream()));
		while ((inputLine = in.readLine()) != null)
		res = inputLine;
		in.close();
		resa = res.split(":");
		if (resa[0].equals("OK")){
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_ERROR;
		}
	}

}
