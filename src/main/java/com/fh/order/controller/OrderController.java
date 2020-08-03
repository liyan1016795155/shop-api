package com.fh.order.controller;


import com.alibaba.fastjson.JSONObject;
import com.fh.Cart.model.Cart;
import com.fh.commons.MemberAnnotation;
import com.fh.commons.ServerResponse;
import com.fh.member.model.Member;
import com.fh.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("buildOrder")
    public ServerResponse  buildOrder(String listStr, Integer addressId, Integer payType, @MemberAnnotation Member member){
        List<Cart> cartList=new ArrayList<>();
        if(StringUtils.isNotEmpty(listStr)){
          cartList = JSONObject.parseArray(listStr, Cart.class);

        }else {
            return ServerResponse.error("请选择商品");
        }

        return orderService.buildOrder(cartList,payType,addressId,member);
    }
}
