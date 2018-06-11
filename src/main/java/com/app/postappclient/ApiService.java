package com.app.postappclient;

import java.util.List;

import com.app.postappclient.model.Model;
import org.springframework.web.multipart.MultipartFile;

import com.app.postappclient.model.MailMessage;

public interface ApiService {
	
	public List<String> getAllModelIds();
	public List<String> getAllUsernames();
	public Model getModel(String modelID);
	
	public List<String> getAllSubsForModel(String modelName);
	
	public void sendMail(MailMessage msg);
	void subscribeForModel(String modelName,String username);
	void unsubscribeForModel(String modelName, String username);
	void saveModel(String modelName, MultipartFile file);
}
