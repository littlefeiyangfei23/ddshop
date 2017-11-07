package com.qf.ddshop.pojo.vo;

import com.qf.ddshop.pojo.po.TbItem;

/**
 * 自定义的商品显示类(VO)
 */
public class TbItemCustom extends TbItem{

    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}

