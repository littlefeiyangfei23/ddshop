package com.qf.ddshop.service.impl;

import com.qf.ddshop.dao.TbItemDescMapper;
import com.qf.ddshop.pojo.po.TbItemDesc;
import com.qf.ddshop.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc getByItemId(Long itemId) {
        TbItemDesc tbItemDesc = null;
        try {
           tbItemDesc =  tbItemDescMapper.selectByPrimaryKey(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  tbItemDesc;
    }
}
