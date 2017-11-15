package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.dao.TbItemParamCustomMapper;
import com.qf.ddshop.dao.TbItemParamMapper;
import com.qf.ddshop.pojo.po.TbItemParam;
import com.qf.ddshop.pojo.po.TbItemParamExample;
import com.qf.ddshop.pojo.vo.TbItemParamCustom;
import com.qf.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;
    @Autowired
    private TbItemParamMapper itemParamDao;

    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result=null;
        try {
            //dao层接口多个参数解决方案之一
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("page",page);
//            取出tb_item_param的记录数目
            int count =itemParamCustomDao.countItemParams();
            //取出对应页码的集合
            List<TbItemParamCustom> list=itemParamCustomDao.listItemParamByPage(map);
            result =new Result<TbItemParamCustom>();
            result.setTotal(count);
            result.setRows(list);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbItemParam getItemParamByCid(Long cid) {
        TbItemParam tbItemParam=null;
        try{
            //创建查询模板
            TbItemParamExample example=new TbItemParamExample();
            TbItemParamExample.Criteria criteria=example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
//            用这个方法,select了所有的参数，没有漏掉的参数。，执行查询
            List<TbItemParam> list = itemParamDao.selectByExampleWithBLOBs(example);
//            集合要判断长度的，查询结果中只取一条记录的
            if(list!=null && list.size()>0){
                 tbItemParam = list.get(0);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return tbItemParam;
    }
}

