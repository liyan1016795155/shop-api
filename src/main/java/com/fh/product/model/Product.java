package com.fh.product.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName("t_product")
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    @TableField("brandid")
    private Integer brandid;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @TableField("pubDate")
    private Date   pubDate;
    //是否上架
    private Integer status;
    private String imgUrl;
    //库存
    private Integer stock;

    //是否热销
    @TableField("isHot")
    private Integer isHot;
    @TableField("typeid1")
    private Integer typeid1;
    @TableField("typeid2")
    private Integer typeid2;
    @TableField("typeid3")
    private Integer typeid3;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getTypeid1() {
        return typeid1;
    }

    public void setTypeid1(Integer typeid1) {
        this.typeid1 = typeid1;
    }

    public Integer getTypeid2() {
        return typeid2;
    }

    public void setTypeid2(Integer typeid2) {
        this.typeid2 = typeid2;
    }

    public Integer getTypeid3() {
        return typeid3;
    }

    public void setTypeid3(Integer typeid3) {
        this.typeid3 = typeid3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
