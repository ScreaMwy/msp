package com.msp.service;

import com.msp.service.model.ProductModel;

import java.util.List;

public interface ProductService {
    //商品創建
    public abstract ProductModel createProduct(ProductModel productModel) throws Exception;

    //商品列表查詢
    public abstract List<ProductModel> findListItem() throws Exception;

    //商品詳情查詢
    public abstract ProductModel findItemById(Integer id) throws Exception;
}
