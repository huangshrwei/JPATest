package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserProfileToDetailRepository;
import com.example.entity.UserProfileToDetail;
import com.example.service.UserProfileToDetailService;


@Service
public class UserProfileToDetailServiceImpl  implements UserProfileToDetailService{
	@Autowired
	private UserProfileToDetailRepository userProfileToDetailRepository;

	@Override
	public List<UserProfileToDetail> findAll() {
		//System.out.println("\n使用 Spring Data JPA 衍生方法查詢 one to many 的資料:");
		//this.userProfileToDetailRepository.findAll().forEach(System.out::println);
		return  userProfileToDetailRepository.findAll();
	}	
	
	@Override
	public Optional<UserProfileToDetail> getUserProfileToDetailById(Long id) {
		Optional<UserProfileToDetail> optUserProfileToDetail =  userProfileToDetailRepository.findById(id);
		return optUserProfileToDetail;
	}
	
}
