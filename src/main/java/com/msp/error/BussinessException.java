package com.msp.error;

//包裝器業務類異常類實現
public class BussinessException extends Exception implements CommonError {

    private CommonError commonError;

    public BussinessException() {}

    //直接接收BussinessError嘅傳參用來構造業務異常
    public BussinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BussinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
