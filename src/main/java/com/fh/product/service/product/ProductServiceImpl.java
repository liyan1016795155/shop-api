package com.fh.product.service.product;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.commons.ServerResponse;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;


    @Override
    public ServerResponse queryIsHotProductList() {
        QueryWrapper<Product> queryWrapper=new QueryWrapper();
        queryWrapper.eq("isHot",1);
        List<Product> list = productMapper.selectList(queryWrapper);
        return ServerResponse.success(list);
    }

    @Override
    public ServerResponse queryProductList() {
        List<Product> list = productMapper.selectList(null);
        return ServerResponse.success(list);
    }

    @Override
    public ServerResponse queryListPage(long currentPage,long pageSize) {
        long start =(currentPage-1)*pageSize;

        //查询总条数
             long totalCount=productMapper.querytotalCount();

            List<Product> list= productMapper.queryList(start,pageSize);
              long totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;

        Map map=new HashMap();
        map.put("list",list);
        map.put("totalPage",totalPage);

        return ServerResponse.success(map);
    }

    @Override
    public Product queryProductById(Integer productId) {
        Product product = productMapper.selectById(productId);
        return product;
    }

    @Override
    public Long updateStock(Integer id, Integer count) {
        return productMapper.updateStock(id,count);
    }

}
