package com.in4c.blogApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }, targetEntity = User.class)
	@JoinColumn(name = "user_id")
    private User user;
    @Column(name = "user_tag", length = 20, nullable = false)
    private String userTag;
    @Column(name = "interests", length = 50)
    private String interests;
    @Column(name = "bio", length = 50)
    private String bio;

    public Profile(long id, User user, String userTag, String interests, String bio) {
        this.id = id;
        this.user = user;
        this.userTag = userTag;
        this.interests = interests;
        this.bio = bio;
    }

    public Profile() {

    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", user=" + user +
                ", userTag='" + userTag + '\'' +
                ", interests='" + interests + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
