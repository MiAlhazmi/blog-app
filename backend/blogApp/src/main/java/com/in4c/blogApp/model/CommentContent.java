package com.in4c.blogApp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CommentContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "content", nullable = false)
	private String content;

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	}, targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	// TODO: media

	public CommentContent() {

	}

	public CommentContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User obj) {
		this.user = obj;
	}

	public long getId() {
		return this.id;
	}

	// public void setId(long id) {
	// 	this.id = id;
	// }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}