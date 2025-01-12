package com.in4c.blogApp.model.helper;

import com.in4c.blogApp.model.Role;

public class UserInfoResponse {
    
    private Long ID;
    private String username;
    private String jwt;
    private String email;
    private Role role;

    public UserInfoResponse(Long ID, String username, String email, Role role, String token) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.role = role;
        this.jwt = token;
    }

	public Long getID() {
		return ID;
	}
	
    public void setID(Long iD) {
        ID = iD;
    }

	public String getUsername() {
		return username;
	}
	
    public void setUsername(String userName) {
        this.username = userName;
    }

	public String getJwt() {
		return jwt;
	}
	
    public void setJwt(String jWTtoken) {
        jwt = jWTtoken;
    }
  
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

	public Role getRole() {
		return role;
	}
	
    public void setRole(Role role) {
        this.role = role;
    }
}