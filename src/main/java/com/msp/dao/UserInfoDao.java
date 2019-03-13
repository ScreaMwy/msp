package com.msp.dao;

import java.util.List;

import com.msp.pojo.UserInfoDO;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;

import org.apache.ibatis.annotations.Param;

@Repository("userInfoDao")
@Scope(scopeName = "singleton")
public interface UserInfoDao {
    public abstract List<? extends UserInfoDO> find();

    public abstract UserInfoDO findById(@Param("id") Integer id);

    public abstract int add(@Param("user") UserInfoDO userInfoDO);

    public abstract int update(@Param("user") UserInfoDO userInfoDO);

    public abstract int delete(@Param("id") Integer id);
}
