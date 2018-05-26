package com.app.postappclient.security;

public class Auth {
	User user;
	boolean isPasswordCorrect;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPasswordCorrect() {
		return isPasswordCorrect;
	}

	public void setPasswordCorrect(boolean isPasswordCorrect) {
		this.isPasswordCorrect = isPasswordCorrect;
	}

	public Auth() {
	}

	public Auth(User user, boolean isPasswordCorrect) {
		this.user = user;
		this.isPasswordCorrect = isPasswordCorrect;
	}
}
