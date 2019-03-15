package com.msp.service.impl;

import javax.annotation.Resource;

import com.msp.dao.UserPasswordDao;
import com.msp.error.BussinesError;
import com.msp.error.BussinessException;
import com.msp.pojo.UserInfoDO;
import com.msp.pojo.UserPasswordDO;
import com.msp.dao.UserInfoDao;
import com.msp.service.UserService;
import com.msp.service.model.UserModel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(transactionManager = "transactionManagement")
    @Override
    public void registry(UserModel userModel) throws BussinessException {
        if (null == userModel) {
            throw new BussinessException(BussinesError.PARAMETER_VALIDATION_ERROR, "無效用戶");
        }

        if (StringUtils.isNotEmpty(userModel.getName())
                || StringUtils.isNotEmpty(String.valueOf(userModel.getGender()))
                || StringUtils.isNotEmpty(String.valueOf(userModel.getAge()))
                || StringUtils.isNotEmpty(userModel.getTelphone())) {
            userInfoDO = convertUserToDataObject(userModel);
            userInfoDao.add(userInfoDO);
            userPasswordDO = convertPasswordToDataOjbect(userModel);
            userPasswordDO.setUserId(userInfoDao.findByTelphone(userInfoDO.getTelphone()).getId());
            userPasswordDao.addSelection(userPasswordDO);
        }
    }

    private UserInfoDO convertUserToDataObject(UserModel userModel) {
        if (null == userModel) {
            return null;
        }

        userInfoDO = new UserInfoDO();
        userInfoDO.setName(userModel.getName());
        userInfoDO.setGender(userModel.getGender());
        userInfoDO.setAge(userModel.getAge());
        userInfoDO.setTelphone(userModel.getTelphone());
        userInfoDO.setRegisterMode(userModel.getRegisterMode());
        //
        userInfoDO.setThirdId(Integer.toString(userModel.getRegisterMode().hashCode()));
        return userInfoDO;
    }

    private UserPasswordDO convertPasswordToDataOjbect(UserModel userModel) {
        if (null == userModel) {
            return null;
        }

        userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrypt(userModel.getEncryptPassword());
        return userPasswordDO;
    }

    private UserModel convertFromDataObject(UserInfoDO userInfoDO, UserPasswordDO userPasswordDO) {
        if (null == userInfoDO) {
            return null;
        }

        userModel = new UserModel();
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
