package com.app.postappclient;

import java.util.List;

import com.app.postappclient.model.MailMessage;

public interface ApiService {
	
	public List<String> getAllModelIds();
	public List<String> getAllUsernames();
	public void sendMail(MailMessage msg);
}
