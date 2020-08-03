package com.fh.order.service;

import com.fh.Cart.model.Cart;
import com.fh.commons.ServerResponse;
import com.fh.member.model.Member;

import java.util.List;

public interface OrderService {
    ServerResponse buildOrder(List<Cart> cartList, Integer payType, Integer addressId, Member member);
}
