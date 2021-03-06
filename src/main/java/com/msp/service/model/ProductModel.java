package com.msp.service.model;

import java.math.BigDecimal;

public class ProductModel {
    private Integer id;

    //商品名稱
    private String name;

    //商品價格
    private BigDecimal price;

    //商品庫存
    private Integer stock;

    //商品描述
    private String description;

    //商品銷量
    private Integer sales;

    //商品的圖片url
    private String imgUrl;

    public ProductModel() {}

    public ProductModel(Integer id,
                        String name,
                        BigDecimal price,
                        Integer stock,
                        String description,
                        Integer sales,
                        String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.sales = sales;
        this.imgUrl = imgUrl;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
