package com.msp.web;

import com.mchange.util.Base64Encoder;
import com.msp.service.UserService;
import com.msp.service.model.UserModel;
import com.msp.web.viewobject.UserVO;
import com.msp.response.CommonReturnType;
import com.msp.error.BussinesError;
import com.msp.error.BussinessException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.security.MD5Encoder;
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

import sun.misc.BASE64Encoder;

@Controller("userController")
@Scope(scopeName = "singleton")
@CrossOrigin(allowCredentials = "true", allowedHeaders = {"*"})
@RequestMapping(path = {"/user", "/user/"})
public class UserController extends SuperController {
    @Autowired
    private  HttpServletRequest request;

    @Resource(name = "userService", type = UserService.class)
    private UserService userService;

    private UserModel userModel;

    private UserVO userVO;

    public UserController() {}

    //用戶登陆接口
    @RequestMapping(path = {"/login", "/login/"}, method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType usereLogin(@RequestParam(name = "telphone", required = true) String telphone,
                                       @RequestParam(name = "password", required = true) String password)
            throws Exception {
        //校驗用戶登錄是否合法
        userModel = new UserModel();
        userModel.setTelphone(telphone);
        userModel.setEncryptPassword(this.encodeByMD5(password));
        userModel = userService.validateLogin(userModel);

        //將登錄成功的憑證保存到用戶登錄成功的session內
        this.request.getSession().setAttribute("LOGIN_SUCCESS", true);
        this.request.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

    //用戶注冊接口
    @RequestMapping(path = {"/registry", "/registry/"},
                    method = {RequestMethod.GET, RequestMethod.POST})/*,
                    consumes = {CONTENT_ENCTYPE_FORM_FRT})*/
    @ResponseBody
    public CommonReturnType userRegistry(@RequestParam(name = "name", required = true) String name,
                                         @RequestParam(name = "gender", required = true) Integer gender,
                                         @RequestParam(name = "age", required = true) Integer age,
                                         @RequestParam(name = "telphone", required = true) String telphone,
                                         @RequestParam(name = "password", required = true) String password,
                                         @RequestParam(name = "otpCode", required = true) String otpCode
    ) throws Exception {
        //驗正手機號對應嘅otpcode相匹配
        String sessionOtpCode = (String) request.getSession().getAttribute(telphone);

        if (null != sessionOtpCode) {
            if (!sessionOtpCode.equals(otpCode) || "" == otpCode
                    || null == otpCode) {
                throw new BussinessException(BussinesError.PARAMETER_VALIDATION_ERROR, "OTP唔匹配");
            }
        } else {
            throw new BussinessException(BussinesError.PARAMETER_VALIDATION_ERROR, "OTP唔匹配");
        }

        //用戶嘅注冊流程
        userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncryptPassword(this.encodeByMD5(password));
        userService.registry(userModel);
        return CommonReturnType.create(this.convertFromModel(userModel));
    }

    private String encodeByMD5(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //確定計算方法
        MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();

        //加密字符串
        String encodedData = base64Encoder.encode(md5Encoder.digest(data.getBytes("utf-8")));
        return encodedData;
    }

    @RequestMapping(path = {"/recieveOtp", "/recieveOtp/"},
                    method = {RequestMethod.POST})/*,
                    consumes = {CONTENT_ENCTYPE_FORM_FRT})*/
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone", required = true) String telphone) {
        //随机获取otpCode
        Random rand = new Random();
        int randCode = rand.nextInt(9999);
        randCode += 10000;
        String otpCode = String.valueOf(randCode);

        //将otpCode同用户嘅手机号绑定
        request.getSession().setAttribute(telphone, otpCode);

        //将ottcode通过短信api发送畀用户
        String date = DateFormat.getDateTimeInstance().format(new Date());
        System.out.printf("尊敬嘅用戶(%s)，您收到嘅用戶驗證碼係：%s (%s)\n", telphone, request.getSession().getAttribute(telphone), date);
        return CommonReturnType.create("短信發送成功");
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
