package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.UserProfileToDetail;

public interface UserProfileToDetailService {

	public List<UserProfileToDetail> findAll();
	
	Optional<UserProfileToDetail> getUserProfileToDetailById(Long id);
}
