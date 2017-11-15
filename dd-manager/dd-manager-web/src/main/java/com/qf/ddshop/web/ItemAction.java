package com.qf.ddshop.web;


import com.qf.ddshop.common.dto.Order;
import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.pojo.vo.TbItemCustom;
import com.qf.ddshop.pojo.vo.TbItemQuery;
import com.qf.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@Scope("prototype")
public class ItemAction {
//    日志的定义
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

//    @RequestMapping("/items")
//    @ResponseBody
//    public List<TbItem> listItems(){
//        List<TbItem> list= null;
//        try{
//            list = itemService.listItems();
//        }catch (Exception e){
////            打印到日志里
//            logger.error(e.getMessage(),e);
////            打印到控制台
//            e.printStackTrace();
//        }
//       return list;
//    }

    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query){
        Result<TbItemCustom> list = null;
        try {
            list = itemService.listItemsByPage(page,order,query);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }



    @ResponseBody
    @RequestMapping("/items/batch")
    // @RequestParam把长整型的数组直接转成List，底层有地方要用list，这里用这springMVC的转换器先转化了，方便了
    public int updateBatch(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try {
            i = itemService.updateBatch(ids);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    @ResponseBody
    @RequestMapping("/items/batchAdd")
    // @RequestParam把长整型的数组直接转成List，底层有地方要用list，这里用这springMVC的转换器先转化了，方便了
    public int updateBatchAdd(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try {
            i = itemService.updateBatchAdd(ids);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }


    @ResponseBody
    @RequestMapping("/items/batchRemove")
    // @RequestParam把长整型的数组直接转成List，底层有地方要用list，这里用这springMVC的转换器先转化了，方便了
    public int updateBatchbatchRemove(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try {
            i = itemService.updateBatchbatchRemove(ids);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

//   string后面的参数content与item-add.jsp中编辑器的那么是一样的
//    11.14 保存商品，3个表
    @ResponseBody
    @RequestMapping("/item")
    public int saveItem(TbItem tbItem,String content,String paramData){
        int i = 0;
        try {
            i = itemService.saveItem(tbItem, content,paramData);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }



}
