package com.qf.ddshop.dao;

import com.qf.ddshop.common.dto.Order;
import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义的商品实体数据访问层接口
 */
public interface TbItemCustomMapper {
    /**
     * 查询商品表中所有记录的数量
     * @return
     */
    int countItems();

    /**
     * 查询指定页码显示记录集合
     * 这是mybatis是注解
     * @param page
     * @return
     */
    List<TbItemCustom> listItemsByPage(@Param("page") Page page, @Param("order")Order order);

}
