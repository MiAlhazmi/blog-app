package com.in4c.blogApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tag", unique = true)
    private String tag;

    public Hashtag(long id, String tag){
        this.id = id;
        this.tag = tag;
    }

    public Hashtag(){

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}