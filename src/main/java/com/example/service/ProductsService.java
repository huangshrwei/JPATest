package com.example.service;

import com.example.dto.ProductsDto;
import com.example.entity.Products; 

import java.util.List;

public interface ProductsService {
    List<Products> listAllProducts();

    Products createProducts(ProductsDto productsDto);

    int updateProducts(Long id, ProductsDto productsDto);

    int deleteProducts(Long id);

    List<Products> listProducts(int pageNum, int pageSize);

    Products getProducts(Long id);
}
