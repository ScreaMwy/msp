package com.msp.pojo;

import java.math.BigDecimal;

public class ProductStockDO {
    private Integer id;

    //商品庫存
    private Integer stock;

    private Integer itemId;

    public ProductStockDO() {}

    public ProductStockDO(Integer id, Integer stock, Integer itemId) {
        this.id = id;
        this.stock = stock;
        this.itemId = itemId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "ProductStockDO{" +
                "id=" + id +
                ", stock=" + stock +
                ", itemId=" + itemId +
                '}';
    }
}
