package com.fh.Cart.model;

import java.math.BigDecimal;

// key  filed value          memberid      productid     product
//"cart"  memberid  productList
public class Cart {

    //此id为商品id 与前台 productid传的值对应
    private Integer productId;
    private Integer count;
    private BigDecimal price;
    private String name;
    private String imgUrl;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
