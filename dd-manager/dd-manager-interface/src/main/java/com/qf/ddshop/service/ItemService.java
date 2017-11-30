package com.qf.ddshop.service;

import com.qf.ddshop.common.dto.Order;
import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.pojo.vo.TbItemCustom;
import com.qf.ddshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {
    TbItem getById(Long itemId);

//    List<TbItem> listItems();

    /**
     * 分页
     * @param page
     * @return
     */


//    批量修改
    int updateBatch(List<Long> ids);

    int updateBatchAdd(List<Long> ids);

    int updateBatchbatchRemove(List<Long> ids);

    /**
     * 新增商品
     * @param tbItem 商品实体类
     * @param content 商品描述
     * @return 受到影响的行数
     */
    Long saveItem(TbItem tbItem, String content,String paramData);

    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query);


}
