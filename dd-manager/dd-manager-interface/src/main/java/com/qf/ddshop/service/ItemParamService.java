package com.qf.ddshop.service;

import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.pojo.po.TbItemParam;
import com.qf.ddshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {


    /**
     * 对规格说明表进行分页操作
     * @param page
     * @return
     */
    Result<TbItemParamCustom> listItemParamsByPage(Page page);

    /**
     * 通过分类的ID查询ItemParam
     * @param cid
     * @return
     */
    TbItemParam getItemParamByCid(Long cid);
}
