package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.UserProfileToDetail;
import com.example.exception.NotFoundException;
import com.example.service.UserProfileToDetailService;
import com.example.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;

@Api(tags = "UserProfileToDetail")
@RestController
@RequestMapping(value = "/userProfileToDetail")
public class UserProfileToDetailController {
	
	@Autowired
	private UserProfileToDetailService userProfileToDetailService;

    @ApiOperation(value = "取得資料", notes = "列出所有明細")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody    
    public CommonResult<List<UserProfileToDetail>> getAll() {
    	CommonResult commonResult;
    	List<UserProfileToDetail> listProfileToDetail = userProfileToDetailService.findAll();
    	if (listProfileToDetail.size()== 0) {
    		commonResult = CommonResult.notFound();
    		//commonResult.setData(listProfileToDetail);
    	}else {
    		commonResult = CommonResult.success(listProfileToDetail);
    	}
    	
        return commonResult;
    }
    
    /*
    //CommonResult
    @ApiOperation("取得指定id員工資料")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UserProfileToDetail> userDetail(@PathVariable("id") Long id) {
    	Optional<UserProfileToDetail> optUserProfileToDetail = userProfileToDetailService.getUserProfileToDetailById(id);
    	CommonResult commonResult;
    	if (!optUserProfileToDetail.isEmpty()) {
    		commonResult = CommonResult.success(optUserProfileToDetail.get());
    	}else {
    		commonResult = CommonResult.notFound();
    	}
        return commonResult;
    }
    */
    
    //Optional
    @ApiOperation("取得指定id員工資料")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<UserProfileToDetail> optUserDetail(@PathVariable("id") Long id) {
    	Optional<UserProfileToDetail> optUserProfileToDetail = userProfileToDetailService.getUserProfileToDetailById(id);
        return userProfileToDetailService.getUserProfileToDetailById(id);
    } 
    
    /*
    //By Code
    @ApiOperation("取得指定id員工資料")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UserProfileToDetail> userDetailException(@PathVariable("id") Long id) {
    	Optional<UserProfileToDetail> optUserProfileToDetail = userProfileToDetailService.getUserProfileToDetailById(id);
    	CommonResult commonResult;
    	if (!optUserProfileToDetail.isEmpty()) {
    		commonResult = CommonResult.success(optUserProfileToDetail.get());
    	}else {
    		throw new NotFoundException("");
    	}
        return commonResult;
    } 
    */   
    
}
