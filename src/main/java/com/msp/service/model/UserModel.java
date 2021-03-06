package com.msp.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserModel {
    private Integer id;

    @NotBlank(message = "用戶名不能為空")
    private String name;

    @NotNull(message = "性別必須填寫")
    private Integer gender;

    @NotNull(message = "性別必須填寫")
    @Max(value = 120, message = "年齡必須小於120歲")
    @Min(value = 1, message = "年齡必須大於0歲")
    private Integer age;

    @NotBlank(message = "用戶名不能為空")
    private String telphone;

    private String registerMode;

    private String thirdPartyId;

    @NotBlank(message = "密碼不能為空")
    private String encryptPassword;

    public UserModel() {}

    public UserModel(Integer id, String name, Integer gender, Integer age, String telphone, String registerMode, String thirdPartyId, String encryptPassword) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telphone = telphone;
        this.registerMode = registerMode;
        this.thirdPartyId = thirdPartyId;
        this.encryptPassword = encryptPassword;
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

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", telphone='" + telphone + '\'' +
                ", registerMode='" + registerMode + '\'' +
                ", thirdPartyId='" + thirdPartyId + '\'' +
                ", encryptPassword='" + encryptPassword + '\'' +
                '}';
    }
}
