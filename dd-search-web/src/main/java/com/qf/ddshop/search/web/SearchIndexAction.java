package com.qf.ddshop.search.web;

import com.qf.ddshop.common.util.PropKit;
import com.qf.ddshop.pojo.po.TbContent;
import com.qf.ddshop.pojo.vo.TbSearchItemResult;
import com.qf.ddshop.service.SearchItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchIndexAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchItemService searchItemService;
    /**
     * 查询系统首页
     * @param keyword 跟页面的空间的name保持一致
     * @param page 默认为1，@RequestParam(defaultValue = "1")
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String portalIndex(String keyword,
                              @RequestParam(defaultValue = "1") Integer page, Model model){

        try{
            if (keyword != null) {
                keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
                //从solr索引库中查询商品列表
                TbSearchItemResult searchResult = searchItemService.search(keyword, page, 60);
                //把结果传递给页面
                model.addAttribute("query", keyword);
                model.addAttribute("totalPages", searchResult.getTotalPages());
                model.addAttribute("page", page);
                model.addAttribute("recourdCount", searchResult.getRecordCount());
                model.addAttribute("itemList", searchResult.getItemList());

            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return "search";
    }
}
