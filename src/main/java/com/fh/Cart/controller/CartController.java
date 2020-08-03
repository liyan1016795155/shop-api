package com.fh.Cart.controller;


import com.alibaba.fastjson.JSONObject;
import com.fh.Cart.model.Cart;
import com.fh.Cart.service.CartService;
import com.fh.commons.Ignore;
import com.fh.commons.ServerResponse;
import com.fh.member.model.Member;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemContans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
  @Autowired
    private CartService cartService;

  @RequestMapping("buy")
    public ServerResponse buy(Integer productId, Integer count, HttpServletRequest request){

         return  cartService.buy(productId,count,request);
  }

    @RequestMapping("queryCartProductCount")
    public ServerResponse queryCartProductCount(HttpServletRequest request){
        Member attribute = (Member) request.getSession().getAttribute(SystemContans.SESSION_KEY);
        List<String> stringList =RedisUtil .hget(SystemContans.CART_KEY + attribute.getId());
        long totalCount = 0;
        if(stringList != null &&stringList.size()>0){
            for (String str : stringList){
                Cart cart = JSONObject.parseObject(str ,Cart.class);
                totalCount += cart.getCount();
            }
        }else{
            return ServerResponse.success(0);
        }

        return ServerResponse.success(totalCount);
    }

   @RequestMapping("queryShoppingList")
   @Ignore
  public ServerResponse queryShoppingList(HttpServletRequest request){
      Member member = (Member) request.getSession().getAttribute(SystemContans.SESSION_KEY);
      List<String> hget = RedisUtil.hget(SystemContans.CART_KEY + member.getId());
      List<Cart> list= new ArrayList<>();
      if(hget!=null&&hget.size()>0){
          for (String s: hget){
              Cart cart = JSONObject.parseObject(s, Cart.class);
              list.add(cart);
          }
      }else {
          return  ServerResponse.success();
      }
      return  ServerResponse.success(list);
  }



}
