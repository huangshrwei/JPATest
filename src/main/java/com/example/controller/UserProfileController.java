package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserProfileRepository;
import com.example.entity.UserProfile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "UserProfile")
@RestController
@RequestMapping(value = "/api")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @ApiOperation(value = "取得使用者資料", notes = "列出所有使用者資料")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/userProfile")
    public List<UserProfile> getAll() {
        return userProfileRepository.findAll();
    }  
    
}