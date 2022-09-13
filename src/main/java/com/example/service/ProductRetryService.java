package com.example.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.dao.ProductsRepository;
import com.example.entity.Products;

@Slf4j
@Service
public class ProductRetryService {
	//atomicInteger safety current Integer
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    
    @Autowired
    private ProductsRepository productsRepository;    

    @Retryable(include = {RetryException.class},
    		maxAttempts = 3,
            backoff = @Backoff(value = 2000))
    public Optional<Products> getProducts() 
  	{
        int count = atomicInteger.incrementAndGet();
        Optional<Products> optProducts = productsRepository.findById((long) 0);
        
        log.info("count = " + count);
        
        if (!optProducts.isPresent()) {
        	log.info("False count = " + count);
        	throw new RetryException("get NoResultException & return null");
        } else {
        	log.info("True count = " + count);
            return optProducts;
        }
    }

    @Recover
    public Products recover(RetryException e) {
    	log.info("@Recover");
        log.info(e.getMessage());
        return null;
    }
}