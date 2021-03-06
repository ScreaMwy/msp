package com.msp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.msp.error.BussinesError;
import com.msp.error.BussinessException;
import com.msp.response.CommonReturnType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class SuperController {
    public final static String CONTENT_ENCTYPE_FORM_FRT = "application/x-www-form-urlencoded";

    public final static String CONTENT_ENCTYPE_FORM_SND = "multipart/form-data";

    //定義exceptionhandler解決未被controller層吸收嘅exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Exception e) {
        Map<String, Object> responseData = new HashMap<>();

        if (e instanceof BussinessException) {
            BussinessException bussinessException = (BussinessException) e;
            responseData.put("errCode", bussinessException.getErrCode());
            responseData.put("errMsg", bussinessException.getErrMsg());
        } else {
            responseData.put("errCode", BussinesError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", BussinesError.UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.create("fail", responseData);
    }
}