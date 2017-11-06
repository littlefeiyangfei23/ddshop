package com.qf.ddshop.web;


import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class ItemAction {

    @Autowired
    private ItemService itemService;

//序列化成JSON
    @ResponseBody
//    限制了访问方式Get,value里的itemId与@PathVariable("itemId")的itemId必须一致，  Long 之后的itemId不强求一致
    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") Long itemId){
        System.out.println(itemId);
        ;
        TbItem tbItem = itemService.getById(itemId);
        return  tbItem;

    }
}
