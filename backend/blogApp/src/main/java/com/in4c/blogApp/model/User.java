package com.in4c.blogApp.model;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends Person {

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinTable(name = "followed_profiles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "profile_id", referencedColumnName = "id")
	})
	@JsonIgnore
	private List<Profile> followedProfiles = List.of();

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinTable(name = "liked_posts", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "post_id", referencedColumnName = "id")
	})

	@JsonIgnore
	private List<Post> likedPosts = List.of();
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinTable(name = "followed_tags", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "hashtag_id", referencedColumnName = "id")
	})
	private List<Hashtag> followedTags = List.of();

	public User(long id, String username, String fName, String lName, String email, String password, String gender,
			Role role) {
		super(id, username, fName, lName, email, password, gender, role);
	}

	public User() {
		super();
	}

	@JsonIgnore
	public List<Profile> getFollowedProfiles() {
		return followedProfiles;
	}

	public void setFollowedProfiles(List<Profile> followedProfiles) {
		this.followedProfiles = followedProfiles;
	}

	@JsonIgnore
	public List<Post> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(List<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public List<Hashtag> getFollowedTags() {
		return followedTags;
	}

	public void setFollowedTags(List<Hashtag> followedTags) {
		this.followedTags = followedTags;
	}

	@Override
	public String toString() {
		return "User{" +
				"followedProfiles=" + followedProfiles +
				", likedPosts=" + likedPosts +
				", followedTags=" + followedTags +
				'}';
	}
}
