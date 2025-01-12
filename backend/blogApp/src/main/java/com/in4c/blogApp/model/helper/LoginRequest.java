package com.in4c.blogApp.model.helper;

public class LoginRequest {

    private String username;
    private String password;
    
	public LoginRequest(String u, String p) {
		this.username = u;
		this.password = p;
	}
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Request (" + "username: " + this.username + " password: " + this.password + " )";
	}
}