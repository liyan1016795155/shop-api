package com.fh.Cart.service;

import com.alibaba.fastjson.JSONObject;
import com.fh.Cart.mapper.CartMapper;
import com.fh.Cart.model.Cart;
import com.fh.commons.ServerResponse;
import com.fh.member.model.Member;
import com.fh.product.model.Product;
import com.fh.product.service.product.ProductService;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemContans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductService productService;
    @Override
    public ServerResponse buy(Integer productId, Integer count, HttpServletRequest request) {
         //验证商品是否存在
           Product product=productService.queryProductById(productId);
           if(product==null){
               return ServerResponse.error("商品不存在");
           }
        //验证商品是否上架
          if(product.getStatus()==2){
              return ServerResponse.error("商品已下架");
          }


        //验证购物车中是否存在该商品
        Member member = (Member) request.getSession().getAttribute(SystemContans.SESSION_KEY);
        boolean exist = RedisUtil.exist(SystemContans.CART_KEY+member.getId(),productId.toString());
          if(!exist){
              //不存在的时候存值
              Cart cart=new Cart();
              cart.setProductId(productId);
              cart.setCount(count);
              cart.setImgUrl(product.getImgUrl());
              cart.setName(product.getName());
              cart.setPrice(product.getPrice());
              String jsonString = JSONObject.toJSONString(cart);
              RedisUtil.hset(SystemContans.CART_KEY+member.getId(),productId.toString(),jsonString);
          }else {
              //如果存在则获取数量
              String productJson = RedisUtil.hget(SystemContans.CART_KEY+member.getId(),productId.toString());
              Cart cart = JSONObject.parseObject(productJson, Cart.class);
              cart.setCount(cart.getCount()+count);

              //修改玩数量之后在存入redis
              String jsonString = JSONObject.toJSONString(cart);
              RedisUtil.hset(SystemContans.CART_KEY+member.getId(),productId.toString(),jsonString);


          }


        return ServerResponse.success();
    }


}
