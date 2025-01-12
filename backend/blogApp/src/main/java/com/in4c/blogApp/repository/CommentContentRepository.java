package com.in4c.blogApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.in4c.blogApp.model.CommentContent;
public interface CommentContentRepository extends JpaRepository<CommentContent, Long>{
	
}
