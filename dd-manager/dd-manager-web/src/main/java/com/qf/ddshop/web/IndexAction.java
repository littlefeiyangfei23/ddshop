package com.qf.ddshop.web;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class IndexAction {

    @RequestMapping("/")
//    这里这个注释，有没有一个样
    @ResponseBody
    public String index(){
        return "index";
    }
}
