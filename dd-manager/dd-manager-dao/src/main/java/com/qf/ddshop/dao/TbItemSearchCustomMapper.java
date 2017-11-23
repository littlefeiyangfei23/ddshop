package com.qf.ddshop.dao;

import com.qf.ddshop.pojo.vo.TbItemCustom;
import com.qf.ddshop.pojo.vo.TbItemSearchCustom;

import java.util.List;
import java.util.Map;

/**
 * 自定义的商品实体数据访问层接口
 */
public interface TbItemSearchCustomMapper {


    List<TbItemSearchCustom> listSearchItems();
}
