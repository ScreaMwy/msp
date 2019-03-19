package com.msp.dao;

import com.msp.pojo.ProductItemDO;

import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.apache.ibatis.annotations.Param;

@Repository("productItemDao")
@Scope(scopeName = "singleton")
public interface ProductItemDao {
    public abstract ProductItemDO findIdByName(@Param("name") String name);

    public abstract int add(@Param("productItem") ProductItemDO productItemDO);

    public abstract int deleteProductById(@Param("id") Integer id);
}
