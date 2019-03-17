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

import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DuplicateKeyException;

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

    @Transactional(transactionManager = "transactionManagement")
    @Override
    public void validateLogin(UserModel userModel) throws Exception {
        if (null == userModel) {
            throw new BussinessException(BussinesError.PARAMETER_VALIDATION_ERROR, "無效用戶");
        }

        if (StringUtils.isNotEmpty(userModel.getName())
                || StringUtils.isNotEmpty(userModel.getEncryptPassword())) {
            //通過用戶手機號獲取用戶信息
            userInfoDO = userInfoDao.findByTelphone(userModel.getTelphone());

            //比較用戶信息中加密嘅密碼是否與傳入來嘅密碼相匹配
            userPasswordDO = userPasswordDao.findUserIdByPassword(userModel.getEncryptPassword());

            if (null != userInfoDO || null != userPasswordDO) {
                if (!userInfoDO.getId().equals(userPasswordDO.getUserId())) {
                    throw new BussinessException(BussinesError.USER_NOT_EXIST, "用戶不存在或密碼錯誤");
                }
            }
        }
    }

    @Transactional(transactionManager = "transactionManagement")
    @Override
    public void registry(UserModel userModel) throws Exception {
        if (null == userModel) {
            throw new BussinessException(BussinesError.PARAMETER_VALIDATION_ERROR, "無效用戶");
        }

        if (StringUtils.isNotEmpty(userModel.getName())
                || StringUtils.isNotEmpty(String.valueOf(userModel.getGender()))
                || StringUtils.isNotEmpty(String.valueOf(userModel.getAge()))
                || StringUtils.isNotEmpty(userModel.getTelphone())) {
            //
            userInfoDO = convertUserToDataObject(userModel);
            try {
                userInfoDao.add(userInfoDO);
            } catch (DuplicateKeyException e) {
                throw new BussinessException(BussinesError.USER_EXIST);
            }


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
}
