package com.app.postappclient.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ModelID")
public class ModelID {
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
