package com.msp.service.impl;

import javax.annotation.Resource;

import com.msp.dao.UserPasswordDao;
import com.msp.pojo.UserInfoDO;
import com.msp.pojo.UserPasswordDO;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;

import com.msp.dao.UserInfoDao;
import com.msp.service.UserService;
import com.msp.service.model.UserModel;

@Service("userServiceImpl")
@Scope(scopeName = "singleton")
public class UserServiceImpl implements UserService {
    @Resource(name = "userInfoDao", type = UserInfoDao.class)
    private UserInfoDao userInfoDao;

    @Resource(name = "userPasswordDao", type = UserPasswordDao.class)
    private UserPasswordDao userPasswordDao;

    private UserInfoDO userInfoDO;

    private UserPasswordDO userPasswordDO;

    private UserModel userModel;

    public UserServiceImpl() {}

    @Override
    public UserModel getUserById(Integer id) {
        userInfoDO = userInfoDao.findById(id);

        if (null == userInfoDO) {
            return null;
        }

        userPasswordDO = userPasswordDao.findPasswordByUserId(id);
        return convertFromDataObject(userInfoDO, userPasswordDO);
    }

    private UserModel convertFromDataObject(UserInfoDO userInfoDO, UserPasswordDO userPasswordDO) {
        if (null == userInfoDO) {
            return null;
        }

        userModel = new UserModel();
        //BeanUtils.copyProperties(userInfoDO, userModel);
        userModel.setId(userInfoDO.getId());
        userModel.setName(userInfoDO.getName());
        userModel.setGender(userInfoDO.getGender());
        userModel.setAge(userInfoDO.getAge());
        userModel.setTelphone(userInfoDO.getTelphone());
        userModel.setRegisterMode(userInfoDO.getRegisterMode());
        userModel.setThirdPartyId(userInfoDO.getThirdId());

        if (null != userPasswordDO) {
            userModel.setEncryptPassword(userPasswordDO.getEncrypt());
        }

        return userModel;
    }
}
