package com.msp.pojo;

import java.math.BigDecimal;

public class ProductItemDO {
    private Integer id;

    //商品名稱
    private String name;

    //商品價格
    private BigDecimal price;

    //商品描述
    private String description;

    //商品銷量
    private Integer sales;

    //商品的圖片url
    private String imgUrl;

    public ProductItemDO() {}

    public ProductItemDO(Integer id,
                         String name,
                         BigDecimal price,
                         String description,
                         Integer sales,
                         String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
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
        return "ProductItemDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
