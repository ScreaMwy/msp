package com.msp.service;

import com.msp.service.model.UserModel;

public interface UserService {
    public abstract UserModel validateLogin(UserModel userModel) throws Exception;

    public abstract void registry(UserModel userModel) throws Exception;
}
