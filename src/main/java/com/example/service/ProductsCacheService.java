package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.dao.ProductsRepository;
import com.example.entity.Products;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductsCacheService{

	@Autowired
	private ProductsRepository productsRepository;	
	
    @Cacheable(cacheNames = "productsCache", key = "#id")    
    public Products getProductsCache(Integer id) {    	    	
    	log.info("Get Data From DB");
    	Optional<Products> optProducts = productsRepository.findById((long) id);   
    	log.info("optProducts: "+ optProducts.get().getName());
        return optProducts.get();
    }
}