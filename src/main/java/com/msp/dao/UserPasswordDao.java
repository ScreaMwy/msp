package com.msp.dao;

import com.msp.pojo.UserPasswordDO;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;

import org.apache.ibatis.annotations.Param;

@Repository("userPasswordDao")
@Scope(scopeName = "singleton")
public interface UserPasswordDao {
    public abstract UserPasswordDO findPasswordByUserId(@Param("uid") Integer userId);
}
