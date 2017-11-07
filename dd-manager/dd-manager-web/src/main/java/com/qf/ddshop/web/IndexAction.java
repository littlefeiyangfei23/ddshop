package com.qf.ddshop.web;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class IndexAction {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    //    day06下午的代码，有个item-add传过来，被/{page}接收
    @RequestMapping("/{page}")
    public String page(@PathVariable("page") String page) {
        return page;
    }
}
