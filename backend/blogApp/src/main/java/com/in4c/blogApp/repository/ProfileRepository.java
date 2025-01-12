package com.in4c.blogApp.repository;

import com.in4c.blogApp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
