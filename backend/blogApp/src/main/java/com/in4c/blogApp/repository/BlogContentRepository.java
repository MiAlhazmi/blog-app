package com.in4c.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in4c.blogApp.model.BlogContent;

public interface BlogContentRepository extends JpaRepository<BlogContent, Long> {

}