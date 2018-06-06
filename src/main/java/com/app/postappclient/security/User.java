package com.app.postappclient.security;

public class User {
	
	String username;
	
	String password;
	
	
	public User() {
	}
	
	public User(String name, String password2) {
		this.username = name;
		this.password = password2;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
