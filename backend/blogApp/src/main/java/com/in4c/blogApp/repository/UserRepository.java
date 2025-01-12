package com.in4c.blogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in4c.blogApp.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
