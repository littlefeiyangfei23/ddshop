package com.qf.ddshop.sso.web;

import com.qf.ddshop.common.dto.MessageResult;
import com.qf.ddshop.common.util.CookieUtils;
import com.qf.ddshop.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SSOLoginAction {
  //或者是SSOLoginAction.class
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("user/login")
    public MessageResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        MessageResult mr = null;

        try {
//调用方法登录
            mr = loginService.userLogin(username,password);
            if (mr.isSuccess()){
                //成功登录
                String token = mr.getData().toString();
                //写入cookie
                CookieUtils.setCookie(request,response,"tt_token",token);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  mr;
    }
}
