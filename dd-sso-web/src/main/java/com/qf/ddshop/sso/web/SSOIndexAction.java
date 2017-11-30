package com.qf.ddshop.sso.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SSOIndexAction {

    @RequestMapping("/")
    public  String index(){
        return  "login";
    }
}
