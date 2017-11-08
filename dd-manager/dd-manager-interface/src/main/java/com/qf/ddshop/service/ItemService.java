package com.qf.ddshop.service;

import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {
    TbItem getById(Long itemId);

//    List<TbItem> listItems();

    /**
     * 分页
     * @param page
     * @return
     */
    Result<TbItemCustom> listItemsByPage(Page page);

//    批量修改
    int updateBatch(List<Long> ids);

    int updateBatchAdd(List<Long> ids);

    int updateBatchbatchRemove(List<Long> ids);
}
