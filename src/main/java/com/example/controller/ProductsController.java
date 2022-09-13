package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ProductsRepository;
import com.example.entity.Products;
import com.example.common.api.CommonResult;
import com.example.controller.ProductsController;
import com.example.dto.ProductsDto;
import com.example.service.ProductsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Api(tags = "ProductsController")
@Tag(name = "ProductsController", description = "商品管理")
@RequestMapping("/products")
@Slf4j
public class ProductsController {
	
    @Autowired
    private ProductsRepository productsRepository;
    
    @Autowired
    private ProductsService productsService;    

    @ApiOperation(value = "取得商品", notes = "列出所有商品")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/listAll")
    public List<Products> getAll() { 	
        return productsRepository.findAll();
    }  	
	
    @ApiOperation("添加商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createProducts(@RequestBody ProductsDto productsDto) {
        CommonResult commonResult;
        Products saveProducts = productsService.createProducts(productsDto);
        if (saveProducts != null) {
            commonResult = CommonResult.success(saveProducts);
            log.debug("createProduct success:{}", productsDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("createProduct failed:{}", productsDto);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateProducts(@PathVariable("id") Long id, @RequestBody ProductsDto productsDto, BindingResult result) {
        CommonResult commonResult;
        int count = productsService.updateProducts(id, productsDto);
        if (count == 1) {
            commonResult = CommonResult.success(productsDto);
            log.debug("updateProduct success:{}", productsDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("updateProduct failed:{}", productsDto);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteProducts(@PathVariable("id") Long id) {
        int count = productsService.deleteProducts(id);
        if (count == 1) {
            log.debug("deleteProduct success :id={}", id);
            return CommonResult.success(null);
        } else {
            log.debug("deleteProduct failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Products>> listProducts(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize) {
        return CommonResult.success(productsService.listProducts(pageNum, pageSize));
    }

    @ApiOperation("取得指定id商品資料")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Products> products(@PathVariable("id") Long id) {
        return CommonResult.success(productsService.getProducts(id));
    }    
    
    
}
