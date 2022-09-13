package com.example.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.UserProfile;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
class UserProfileRepositoryTest {
	
	
	@Autowired
	private UserProfileRepository daoTest; 
/*
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
*/
	@Test
	void testFindByUserId() {
		
		//given
		/*
		Date date = new Date();
		UserProfile userProfile = new UserProfile();
		userProfile.setUserId(1L);
		userProfile.setUserName("Bob");
		userProfile.setUserPassword("0000");
		userProfile.setCreationDate(date);
		userProfile.setUpdatedDate(date);
		
		daoTest.save(userProfile);
		*/
		//when
		UserProfile user = new UserProfile();
		user = daoTest.findByUserId(1L);
		boolean isExist = (user!=null);
		
		//then
		Assertions.assertTrue(isExist);
		
		//fail("Not yet implemented");
		
		 
	}

}
