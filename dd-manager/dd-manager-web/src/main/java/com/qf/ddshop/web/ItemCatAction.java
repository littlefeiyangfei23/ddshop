package com.qf.ddshop.web;

import com.qf.ddshop.common.dto.TreeNode;
import com.qf.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
//没有成员变量就不用@Scope了,
public class ItemCatAction {
//    日志相关,import org.slf4j.Logger;
   private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/itemCats")
    @ResponseBody
    public List<TreeNode> listItemCatsByPid(@RequestParam("parentId") Long parentId){
       List<TreeNode> treeNodesList=null;
       try{
           treeNodesList=itemCatService.listItemCatsById(parentId);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
       return treeNodesList;
    }
}
