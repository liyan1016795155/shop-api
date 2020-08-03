package com.fh.Cart.service;

import com.fh.commons.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    ServerResponse buy(Integer productId, Integer count, HttpServletRequest request);


}
