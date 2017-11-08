package com.qf.ddshop.pojo.vo;

import com.qf.ddshop.pojo.po.TbItem;

/**
 * 自定义的商品显示类(VO)
 */
public class TbItemCustom extends TbItem{

    private String catName;

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}

