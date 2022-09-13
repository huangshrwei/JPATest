package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import com.example.entity.UserProfile;

@RepositoryRestResource
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	//Use Entity Name
	@Query("From UserProfile where userId = ?1")
	UserProfile findByUserId(Long userId);	
	
}
