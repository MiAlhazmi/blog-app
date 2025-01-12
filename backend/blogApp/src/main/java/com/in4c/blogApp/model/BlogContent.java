package com.in4c.blogApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BlogContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "subject", nullable = false)
	private String subject;

	public BlogContent(String content,long id, String subject) {
		this.content = content;
		this.subject = subject;
		this.id = id;
	}

	public BlogContent() {
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BlogContent{" +
				"subject='" + subject + '\'' +
				'}';
	}
}