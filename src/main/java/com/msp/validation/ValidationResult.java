package com.msp.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ValidationResult {
    //是否有錯誤
    private boolean isError = false;

    //存放錯誤信息的map
    private Map<String, String > errMsgMap = new HashMap<>();

    public ValidationResult() {}

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Map<String, String> getErrMsgMap() {
        return errMsgMap;
    }

    public void setErrMsgMap(Map<String, String> errMsgMap) {
        this.errMsgMap = errMsgMap;
    }

    //通用格式化錯誤返回
    public String getErrMsg() {
        return StringUtils.join(errMsgMap.values().toArray(), ",");
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "isError=" + isError +
                ", errMsgMap=" + errMsgMap +
                '}';
    }
}
