package com.msp.web;

import com.msp.service.UserService;
import com.msp.service.model.UserModel;
import com.msp.web.viewobject.UserVO;
import com.msp.response.CommonReturnType;
import com.msp.error.BussinesError;
import com.msp.error.BussinessException;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

@Controller("userController")
@Scope(scopeName = "singleton")
@CrossOrigin
@RequestMapping(path = {"/user", "/user/"})
public class UserController extends SuperController {
    @Autowired
    private  HttpServletRequest request;

    @Resource(name = "userServiceImpl", type = UserService.class)
    private UserService userService;

    private UserModel userModel;

    private UserVO userVO;

    public UserController() {}

    @RequestMapping(path = {"/recieveOtp", "/recieveOtp/"}, method = {RequestMethod.POST}, consumes = CONTENT_ENCTYPE_FORM_ONE)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone", required = true) String telphone) {
        //随机获取otpCode
        Random rand = new Random();
        int randCode = rand.nextInt(9999);
        randCode += 10000;
        String otpCode = String.valueOf(randCode);

        System.out.printf("randCode:%d\n", randCode);

        //将otpCode同用户嘅手机号绑定
        request.getSession().setAttribute(telphone, otpCode);

        //将ottcode通过短信api发送畀用户
        System.out.printf("telphone:%s\notpCode:%s\n", telphone, request.getSession().getAttribute(telphone));

        return CommonReturnType.create(null);
    }

    @RequestMapping(path = {"/findId", "/findId/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getUserId(@RequestParam(name = "uid", required = true) Integer id) throws BussinessException {
        //調用service層來獲取用戶嘅id號，並且返回畀前端
        userModel = userService.getUserById(id);

        //如果獲取到嘅對應用戶訊息唔存在
        if (null == userModel) {
            throw new BussinessException(BussinesError.USER_NOT_EXIST);
            //userModel.setEncryptPassword("123142141");
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
}
