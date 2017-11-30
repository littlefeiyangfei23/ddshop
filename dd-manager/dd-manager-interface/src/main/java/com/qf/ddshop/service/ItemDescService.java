package com.qf.ddshop.service;

import com.qf.ddshop.pojo.po.TbItemDesc;

public interface ItemDescService {
    TbItemDesc getByItemId(Long itemId);
}
