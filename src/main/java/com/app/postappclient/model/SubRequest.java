package com.app.postappclient.model;

public class SubRequest {
	String modelID;
	String subscribent;
	
	public String getModelID() {
		return modelID;
	}
	public void setModelID(String modelID) {
		this.modelID = modelID;
	}
	public String getSubscribent() {
		return subscribent;
	}
	public void setSubscribent(String subscribent) {
		this.subscribent = subscribent;
	}
	public SubRequest(String modelID, String subscribent) {
		this.modelID = modelID;
		this.subscribent = subscribent;
	}
	public SubRequest() {
	}
	
}
