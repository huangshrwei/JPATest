package com.example.TestAWS;


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

@AutoConfigureMockMvc
@Slf4j
public class UserProfileControllerTest 
{
    @InjectMocks
    UserProfileController testUserProfileController;	
	
	@Mock
	UserProfileRepository repository;
	
	private List<UserProfile> userList = new ArrayList<>();
	UserProfile user6L = new UserProfile();
	UserProfile user1L = new UserProfile();

	
    @Autowired                           
    private MockMvc mockMvc; 	

	
	@BeforeEach
	void setup() throws Exception
	{
		//
		MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testUserProfileController).build();

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
	
	
	@AfterEach
	void tearDown() throws Exception
	{
		userList.clear();
		
		user1L.setCreationDate(null);
		user1L.setUpdatedDate(null);
		user1L.setUserId(null);
		user1L.setUserName(null);
		user1L.setUserPassword(null);
		
		user6L.setCreationDate(null);
		user6L.setUpdatedDate(null);
		user6L.setUserId(null);
		user6L.setUserName(null);
		user6L.setUserPassword(null);		
		
	}	

	@Test
	void GetUserProfile()
	{
		//given
        Mockito.when(repository.findByUserId(6L)).thenReturn(user6L);
        Mockito.when(repository.findByUserId(1L)).thenReturn(user1L);

        //then
        Assertions.assertEquals(6L, repository.findByUserId(6L).getUserId()); 
	}
	
	
	@Test
	void FindUserProfileAll()
	{
		// given
        Mockito.when(repository.findByUserId(6L)).thenReturn(user6L);
        Mockito.when(repository.findByUserId(1L)).thenReturn(user1L);
        Mockito.when(repository.findAll()).thenReturn(userList);  			
		
        //then
        Assertions.assertEquals(userList, repository.findAll());
	}	
	
	@Test
	void ShouldFetchAllUserProfile() throws Exception{
					
		ObjectMapper objectMapper = new ObjectMapper();
		
        // given
		when(repository.findAll()).thenReturn(userList);
		String ujson = objectMapper.writeValueAsString(userList);
		
        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/userProfile")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        Assertions.assertEquals(response.getStatus(),HttpStatus.OK.value());
        Assertions.assertEquals(response.getContentAsString(),ujson);	
	}
	
}