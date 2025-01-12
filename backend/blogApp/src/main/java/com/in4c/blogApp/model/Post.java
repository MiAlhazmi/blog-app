package com.in4c.blogApp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

// TODO: use map for better lookup performance

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	}, targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "content_id")
	private BlogContent content;

	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity = CommentContent.class)
	@JoinTable(name = "post_comments", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private List<CommentContent> comments;

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinTable(name = "post_hashtags", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
	private List<Hashtag> hashTags;

	@Column(name = "likes")
	private int likes;

	@Column(name = "dislikes")
	private int dislikes;

	@Column(name = "views")
	private int views;

	public Post(long id, User user, BlogContent content, List<CommentContent> comments, List<Hashtag> hashTags,
			int likes,
			int dislikes, int views) {
		this.id = id;
		this.user = user;
		this.content = content;
		this.comments = comments;
		this.hashTags = hashTags;
		this.likes = likes;
		this.dislikes = dislikes;
		this.views = views;
	}

	public Post() {
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BlogContent getContent() {
		return content;
	}

	public void setContent(BlogContent content) {
		this.content = content;
	}

	public List<CommentContent> getComments() {
		return comments;
	}

	public void setComments(List<CommentContent> comments) {
		this.comments = comments;
	}

	public List<Hashtag> getHashTags() {
		return hashTags;
	}

	public void setHashTags(List<Hashtag> hashTags) {
		this.hashTags = hashTags;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", user=" + user +
				", content=" + content +
				", comments=" + comments +
				", hashTags=" + hashTags +
				", likes=" + likes +
				", dislikes=" + dislikes +
				", views=" + views +
				'}';
	}
}