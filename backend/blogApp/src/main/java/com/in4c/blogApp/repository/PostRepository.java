package com.in4c.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in4c.blogApp.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}