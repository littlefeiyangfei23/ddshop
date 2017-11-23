package com.qf.ddshop.service;

import com.qf.ddshop.pojo.vo.TbSearchItemResult;

public interface SearchItemService {
    //采集数据导入索引库
    boolean importAllItems();

    /**
     * 带条件分页查询索引库的内容
     * @param keyword
     * @param page
     * @param i
     * @return
     */
    TbSearchItemResult search(String keyword, Integer page, int i);
}
