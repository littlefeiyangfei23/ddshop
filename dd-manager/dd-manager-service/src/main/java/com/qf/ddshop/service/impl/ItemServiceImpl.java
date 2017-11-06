package com.qf.ddshop.service.impl;

import com.qf.ddshop.dao.TbItemMapper;
import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl  implements ItemService {
//    这里教一招处理异常的方法

//    注入一个POJO
//    itemDao有MapperScannerConfigure
    @Autowired
    private TbItemMapper itemDao;
    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return tbItem;
    }
}
