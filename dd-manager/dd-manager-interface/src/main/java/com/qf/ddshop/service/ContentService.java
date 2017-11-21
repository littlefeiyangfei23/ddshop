package com.qf.ddshop.service;

import com.qf.ddshop.pojo.po.TbContent;

import java.util.List;

public interface ContentService {
    /**
     * 根据内容分类的编号查询出内容
     * @param id
     * @return
     */
    List<TbContent> listContentsByCid(Long id);
}
