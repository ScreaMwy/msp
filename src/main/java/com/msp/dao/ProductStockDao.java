package com.msp.dao;

import com.msp.pojo.ProductStockDO;

import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.apache.ibatis.annotations.Param;

@Repository("productStockDao")
@Scope(scopeName = "singleton")
public interface ProductStockDao {
    public abstract int add(@Param("productStock") ProductStockDO productStockDO);
}
