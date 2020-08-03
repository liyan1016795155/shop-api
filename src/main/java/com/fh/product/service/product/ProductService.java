package com.fh.product.service.product;

import com.fh.commons.ServerResponse;
import com.fh.product.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


public interface ProductService {


    ServerResponse queryIsHotProductList();

    ServerResponse queryProductList();

    ServerResponse queryListPage(long currentPage,long pageSize);

    Product queryProductById(Integer productId);

    Long updateStock(Integer id, Integer count);


}
