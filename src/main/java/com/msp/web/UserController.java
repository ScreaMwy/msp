package com.msp.web;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.msp.service.UserService;
import com.msp.service.model.UserModel;
import com.msp.web.viewobject.UserVO;
import com.msp.response.CommonReturnType;
import com.msp.error.BussinesError;
import com.msp.error.BussinessException;

@Controller("userController")
@Scope(scopeName = "singleton")
@RequestMapping(path = {"/user", "/user/"})
public class UserController {
    @Resource(name = "userServiceImpl", type = UserService.class)
    private UserService userService;

    private UserModel userModel;

    private UserVO userVO;

    public UserController() {}

    @RequestMapping(path = {"/get", "/get/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getUserId(@RequestParam(name = "uid", required = true) Integer id) throws BussinessException {
        //調用service層來獲取用戶嘅id號，並且返回畀前端
        userModel = userService.getUserById(id);

        //如果獲取到嘅對應用戶訊息唔存在
        if (null == userModel) {
            throw new BussinessException(BussinesError.USER_NOT_EXIST);
        }

        //將核心领域嘅Model类转换为前端嘅viewobject
        userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (null == userModel) {
            return null;
        }

        userVO = new UserVO();
        userVO.setId(userModel.getId());
        userVO.setName(userModel.getName());
        userVO.setGender(userModel.getGender());
        userVO.setAge(userModel.getAge());
        userVO.setTelphone(userModel.getTelphone());
        return userVO;
    }

    //定義exceptionhandler解決未被controller層吸收嘅exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e) throws Exception {
        BussinessException bussinessException = (BussinessException) e;
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("errCode", bussinessException.getErrCode());
        responseData.put("errMsg", bussinessException.getErrMsg());
        return CommonReturnType.create("fail", responseData);
    }
}
