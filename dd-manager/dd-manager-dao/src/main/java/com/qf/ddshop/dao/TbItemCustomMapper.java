package com.qf.ddshop.dao;

import com.qf.ddshop.pojo.vo.TbItemCustom;

import java.util.List;
import java.util.Map;

/**
 * 自定义的商品实体数据访问层接口
 */
public interface TbItemCustomMapper {
    /**
     * 查询商品表中所有记录的数量
     * @return
     */
    int countItems(Map<String,Object> map);

    /**
     * 查询指定页码显示记录集合
     * 这是mybatis是注解
     * @param map
     * @return
     */
//    List<TbItemCustom> listItemsByPage(@Param("page") Page page, @Param("order")Order order);
//    用map封装所有参数
    List<TbItemCustom> listItemsByPage(Map<String,Object> map);

}
