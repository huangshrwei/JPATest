/**
 * 
 */
package com.example.controller;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.UserProfileController;
import com.example.dao.UserProfileRepository;
import com.example.entity.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author User
 *
 */
@AutoConfigureMockMvc
@Slf4j
class UserProfileControllerTest {

    @Autowired                           
    private MockMvc mockMvc; 		
	
    @InjectMocks
    UserProfileController testUserProfileController;	
	
    @Mock
	UserProfileRepository repository;
	
	private List<UserProfile> userList = new ArrayList<>();
	UserProfile user6L = new UserProfile();
	UserProfile user1L = new UserProfile();	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		
		//given User6L		
		
		user6L.setUserId(6L);
		user6L.setUserName("KK");
		user6L.setUserPassword("12");
		java.util.Date date = new java.util.Date();
		user6L.setCreationDate(date);
		user6L.setUpdatedDate(date);		   	
		
		//given User1L				
		user1L.setUserId(1L);
		user1L.setUserName("KK");
		user1L.setUserPassword("12");
		date = new java.util.Date();
		user1L.setCreationDate(date);
		user1L.setUpdatedDate(date);
		
		//given all		
		userList.add(user1L);
		userList.add(user6L);
       
        
        log.info("@BeforeEach");		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.example.controller.UserProfileController#getAll()}.
	 */
	@Test
	void testGetAll()  throws Exception{
		
	    when(repository.findAll()).thenReturn(userList);
	    mockMvc.perform(get("/api/v1/userProfile"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(userList.size()))
	        .andDo(print());
	}

}
