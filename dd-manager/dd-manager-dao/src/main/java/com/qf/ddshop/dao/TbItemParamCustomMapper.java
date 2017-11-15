package com.qf.ddshop.dao;

import com.qf.ddshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

public interface TbItemParamCustomMapper {
    int countItemParams();

    List<TbItemParamCustom> listItemParamByPage(Map<String, Object> map);
}
