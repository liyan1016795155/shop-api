package com.fh.product.controller;

import com.fh.commons.ServerResponse;
import com.fh.product.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("queryIsHotProductList")
    public ServerResponse queryIsHotProductList(){

        return productService.queryIsHotProductList();
    }

    @RequestMapping("queryProductList")
    public ServerResponse queryProductList(){

        return productService.queryProductList();
    }

    @RequestMapping("queryListPage")
    public ServerResponse queryListPage(long currentPage,long pageSize){

        return productService.queryListPage(currentPage,pageSize);
    }





}

