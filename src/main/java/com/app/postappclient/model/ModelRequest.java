package com.app.postappclient.model;

public class ModelRequest {
	String name;
	String content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ModelRequest(String name, String content) {
		this.name = name;
		this.content = content;
	}
	public ModelRequest() {
	}
}
