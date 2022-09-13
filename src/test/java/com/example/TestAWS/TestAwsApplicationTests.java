package com.example.TestAWS;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.Transactional;

import com.example.TestAwsApplication;
import com.example.config.MyDataSourceConfig;
import com.example.dao.UserDetailRepository;
import com.example.dao.UserProfileRepository;
import com.example.entity.Products;
import com.example.entity.UserDetail;
import com.example.entity.UserProfile;
import com.example.service.ProductRetryService;
import com.example.service.ProductsCacheService;

import lombok.extern.slf4j.Slf4j;

@ComponentScan(basePackages = { "com.example.config","com.example.controller","com.example.dao","com.example.service","com.example.aop"})
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.example.entity"})
@EnableRetry
@EnableCaching
@SpringBootTest
@Slf4j
class TestAwsApplicationTests {

    @Autowired
    private UserDetailRepository userDetailRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;	        
    
    @Autowired
    private ProductRetryService productRetryService;    
    
    @Autowired
    private ProductsCacheService productsCacheService;    

    @Autowired
    private CacheManager cacheManager;
    
    @Autowired
    private MyDataSourceConfig myDataSourceConfig;   
    
    
    @Test
    public void testProductsCacheService() {
    	productsCacheService.getProductsCache(1);
    	productsCacheService.getProductsCache(2);
        // 校验缓存里的内容~~~~
        org.springframework.cache.Cache testProductsCache = cacheManager.getCache("productsCache");
        log.info(testProductsCache.getName());    
    }    
    
    

	@Test
	@Transactional
	void testProductRetryService() {
		log.info("....testProductRetryService");
        try {
        	Optional<Products> product = productRetryService.getProducts();
        	if (product.isPresent()) {
        		log.info("....testProductRetryService, Product Name is: " + product.get().getName());
        	}             	
        	log.info("product.isPresent(): "+ product.isPresent());
        } catch (Exception e) {
        	log.info("Exception e");
            e.printStackTrace();
        }
	}	
	
	@Test
	@Transactional
	void testQueryByUserId() {
		log.info("....testQueryByUserId");
		log.info("....myDataSourceConfig: "+myDataSourceConfig.getDatasource().toString());
		UserProfile userProfile = userProfileRepository.findByUserId(3L);
		log.info("....testQueryByUserId user_id = "+ userProfile.getUserId());
	}	
    
	
	@Test
	@Transactional
	void testQueryByUserName() {
		log.info("....testQueryByFirstName");
		log.info("....myDataSourceConfig: "+myDataSourceConfig.getDatasource().toString());
		List<UserDetail> userDetail = userDetailRepository.findByUserName("K10");
		
		Assertions.assertNotNull(userDetail);
		Assertions.assertFalse(userDetail.isEmpty());
		log.info("....testQueryByUserName user_id = "+ userDetail.get(0).getUserId());
	}
	
	@Test
	@Transactional
	void testQueryByFirstName() {
		log.info("....testQueryByFirstName");
		
		List<UserDetail> userDetail = userDetailRepository.findByFirstName("Bob");
		Assertions.assertNotNull(userDetail);
		Assertions.assertFalse(userDetail.isEmpty());
		log.info("....testQueryByFirstName LastName = "+ userDetail.get(0).getLastName());
		
	}	
	
	@Test
	@Transactional
	void testQueryByFirstNameAndLastName() {
		log.info("....testQueryByFirstNameAndLastName");
		
		List<UserDetail> userDetail = userDetailRepository.findByFirstNameAndLastName("Bob", "Huang");
		Assertions.assertNotNull(userDetail);
		Assertions.assertFalse(userDetail.isEmpty());
		log.info("....testQueryByFirstNameAndLastName user_id = "+ userDetail.get(0).getUserId());
		
	}
	
	@Test
	@Transactional
	void testQueryByFirstNameOrdeByLastName() {
		log.info("....testQueryFindUserDetail");
		
		List<UserDetail> userDetail = userDetailRepository.findByFirstNameOrdeByLastName("Bob");
		Assertions.assertNotNull(userDetail);
		Assertions.assertFalse(userDetail.isEmpty());
		log.info("....testQueryFindUserDetail LastName = "+ userDetail.get(0).getLastName());
		
	}		

	@Test
	@Transactional
	void testQueryByFirstNameContainingIgnoreCase() {
		log.info("....testQueryByFirstNameContainingIgnoreCase");
		
		List<UserDetail> userDetail = userDetailRepository.findByFirstNameContainingIgnoreCase("b");
		Assertions.assertNotNull(userDetail);
		Assertions.assertFalse(userDetail.isEmpty());
		log.info("....testQueryByFirstNameContainingIgnoreCase LastName = "+ userDetail.get(0).getLastName());
		
	}		
	
	
	@Test
	@Transactional
	void testQueryFindUserDetail() {
		log.info("....testQueryFindUserDetail");
		
		List<UserDetail> userDetail = userDetailRepository.findUserDetail("Bob");
		Assertions.assertNotNull(userDetail);
		Assertions.assertFalse(userDetail.isEmpty());
		log.info("....testQueryFindUserDetail LastName = "+ userDetail.get(0).getLastName());
		
	}  	
	

}
