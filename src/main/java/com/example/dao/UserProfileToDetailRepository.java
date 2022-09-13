package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entity.UserProfileToDetail;

@RepositoryRestResource
public interface UserProfileToDetailRepository extends JpaRepository<UserProfileToDetail, Long> {

}
