package com.msp;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.msp.dao.UserPasswordDao;

import com.msp.dao.UserInfoDao;
import com.msp.pojo.UserInfoDO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Resource(name = "", type = UserInfoDao.class)
    private UserInfoDao userInfoDao;

    @Resource(name = "", type = UserPasswordDao.class)
    private UserPasswordDao userPasswordDao;

    private UserInfoDO user;

    private List<? extends UserInfoDO> userInfos;

    private int status;

    @Before
    public void init() {
        user = new UserInfoDO();
        user.setId(6);
        user.setName("F");
        user.setGender(1);
        user.setAge(23);
        user.setTelphone("12345678911");
        user.setRegisterMode("qq");
        user.setThirdId("2346");
    }

    @Test
    //@Ignore
    public void findTest() {
        UserInfoDO userInfoDO = userInfoDao.findById(1);
        Assert.assertEquals("weixin", userInfoDO.getRegisterMode());
//        UserPasswordDO userPassword = userPasswordDao.findPasswordByUserId(1);
//        System.out.printf("--->%s<---\n", userPassword.getEncrypt());
//        Assert.assertEquals("asdfghjkl123", userPassword.getEncrypt());
    }

    @Test
    //@Ignore
    public void addTest() {
        status = userInfoDao.add(user);
        Assert.assertEquals(1, status);
    }

    @Test
    //Ignore
    public void update() {
        status = userInfoDao.update(user);
        Assert.assertEquals(1, status);
    }

    @Test
    //@Ignore
    public void delete() {
        status = userInfoDao.delete(6);
        Assert.assertEquals(1, status);
    }
}
