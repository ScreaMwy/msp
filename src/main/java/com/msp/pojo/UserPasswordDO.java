package com.msp.pojo;

public class UserPasswordDO {
    private Integer id;

    private String encrypt;

    private Integer userId;

    public UserPasswordDO() {}

    public UserPasswordDO(Integer id, String encrypt, Integer userId) {
        this.id = id;
        this.encrypt = encrypt;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserPasswordDO{" +
                "id=" + id +
                ", encrypt='" + encrypt + '\'' +
                ", userId=" + userId +
                '}';
    }
}
