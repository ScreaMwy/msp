package com.msp.error;

public enum BussinesError implements CommonError {
    //通用錯誤訊息類型：100
    PARAMETER_VALIDATION_ERROR(101, "參數唔合法"),

    UNKNOWN_ERROR(105, "未知錯誤"),

    //20000開頭係用戶相關錯誤定義
    USER_NOT_EXIST(200, "用戶唔存在");

    private int errCode;

    private String errMsg;

    private BussinesError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    @Override
    public String toString() {
        return "BussinesError{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
