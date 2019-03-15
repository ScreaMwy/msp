package com.msp.pojo;

public class UserInfoDO {
    private Integer id;

    private String name;

    private Integer gender;

    private Integer age;

    private String telphone;

    private String registerMode;

    private String thirdId;

    private UserPasswordDO userPasswordDO;

    public UserInfoDO() {}

    public UserInfoDO(Integer id, String name, Integer gender, Integer age, String telphone, String registerMode, String thirdId, UserPasswordDO userPasswordDO) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telphone = telphone;
        this.registerMode = registerMode;
        this.thirdId = thirdId;
        this.userPasswordDO = userPasswordDO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public UserPasswordDO getUserPasswordDO() {
        return userPasswordDO;
    }

    public void setUserPasswordDO(UserPasswordDO userPasswordDO) {
        this.userPasswordDO = userPasswordDO;
    }

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", telphone='" + telphone + '\'' +
                ", registerMode='" + registerMode + '\'' +
                ", thirdId='" + thirdId + '\'' +
                ", userPasswordDO=" + userPasswordDO +
                '}';
    }
}
