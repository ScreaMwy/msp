package com.msp.service;

import com.msp.service.model.UserModel;

public interface UserService {
    public abstract UserModel getUserById(Integer id);

    public abstract void registry(UserModel userModel) throws Exception;
}
