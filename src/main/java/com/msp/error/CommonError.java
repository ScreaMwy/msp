package com.msp.error;

public interface CommonError {
    public abstract int getErrCode();
    public abstract String getErrMsg();
    public abstract CommonError setErrMsg(String errMsg);
}
