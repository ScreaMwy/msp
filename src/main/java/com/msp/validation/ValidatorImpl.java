package com.msp.validation;

import java.util.Set;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ConstraintViolation;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.InitializingBean;

@Component("validator")
@Scope(scopeName = "prototype")
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

    private ValidationResult result;

    public ValidatorImpl() {}

    //實現校驗方法並返回校驗結果
    public ValidationResult validate(Object beanObject) {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(beanObject);
        result = new ValidationResult();

        if (constraintViolationSet.size() > 0) {
            //遍歷校驗集合，獲取非法錯誤信息
            result.setError(true);
            constraintViolationSet.forEach((constraintViolation) -> {
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrMsgMap().put(propertyName, errMsg);
            });
        }

        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //將hibernate validator通過工廠的初始方式進行實例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
