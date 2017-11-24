package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.dto.Order;
import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.common.util.IDUtils;
import com.qf.ddshop.dao.TbItemCustomMapper;
import com.qf.ddshop.dao.TbItemDescMapper;
import com.qf.ddshop.dao.TbItemMapper;
import com.qf.ddshop.dao.TbItemParamItemMapper;
import com.qf.ddshop.pojo.po.*;
import com.qf.ddshop.pojo.vo.TbItemCustom;
import com.qf.ddshop.pojo.vo.TbItemQuery;
import com.qf.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl  implements ItemService {
//    这里教一招处理异常的方法
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //    注入一个POJO
//    itemDao有MapperScannerConfigure
    @Autowired
    private TbItemMapper itemDao;

    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Autowired
    private TbItemDescMapper itemDescDao;

    @Autowired
    private TbItemParamItemMapper itemParamItemDao;

//    @Override
//    public List<TbItem> listItems() {
//        List<TbItem> list= null;
//        try{
////            参数为null就是查询全部了。
//            list =itemDao.selectByExample(null);
//        }catch (Exception e){
////            打印到日志里
//            logger.error(e.getMessage(),e);
////            打印到控制台
//            e.printStackTrace();
//        }
//        return list;
//    }

    @Override
    public TbItem getById(Long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return tbItem;
    }

//    @Override
//    public Result<TbItemCustom> listItemsByPage(Page page, Order order,TbItemQuery query) {
//        Result<TbItemCustom> result = null;
//
//        try {
////            0 创建一个Map封装前台传过来的参数
//            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("page",page);
//            map.put("order",order);
//            map.put("query",query);
////            Map<String,Object>
//            //1 创建一个响应参数实体类
//            result = new Result<TbItemCustom>();
//            //2 对total进行设值(符合条件的总记录数)
////            这里的参数是query的话，就报错了，错过一次
//            int total = itemCustomDao.countItems(map);
//            result.setTotal(total);
//            //3 对rows进行设值(指定页码显示记录集合)
//            List<TbItemCustom> list = itemCustomDao.listItemsByPage(map);
//            result.setRows(list);
//        }catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//        }
//        return result;
//    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;

        try {
//            0 创建一个Map封装前台传过来的参数
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("page",page);
            map.put("order",order);
            map.put("query",query);
//            Map<String,Object>
            //1 创建一个响应参数实体类
            result = new Result<TbItemCustom>();
            //2 对total进行设值(符合条件的总记录数)
//            这里的参数是query的话，就报错了，错过一次
            int total = itemCustomDao.countItems(map);
            result.setTotal(total);
            //3 对rows进行设值(指定页码显示记录集合)
            List<TbItemCustom> list = itemCustomDao.listItemsByPage(map);
            result.setRows(list);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateBatch(List<Long> ids) {
        int i = 0;
        try {
            //准备商品对象，这个对象包含了状态为3的字段
            TbItem record = new TbItem();
            record.setStatus((byte) 3);

            //创建更新模板 update tb_item set status=? where id in (?,?,?),现在还什么都没有
            // 下面3行创建了查询模板
            TbItemExample example = new TbItemExample();
//            TbItemExample里面有个内部类是Criteria，创建一个查询对象
            TbItemExample.Criteria criteria = example.createCriteria();
//            下面函数的参数是Long型的数组，ids符合条件，给查询对象加内容
//            执行完下面这一行，意味着ids已经填充了上面sql语句的后面的？
            criteria.andIdIn(ids);
            //执行更新，这个方法在Mapper.xml里面是动态sql的
//            执行完下面这一行，意味着已经填充了上面sql语句的前面的？
            i = itemDao.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updateBatchAdd(List<Long> ids) {
        int i = 0;
        try {
            TbItem record = new TbItem();
            record.setStatus((byte) 1);

            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            i = itemDao.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }


    @Override
    public int updateBatchbatchRemove(List<Long> ids) {
        int i = 0;
        try {
            TbItem record = new TbItem();
            record.setStatus((byte) 2);

            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            i = itemDao.updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    //加上注解@Transactional之后，这个方法就变成了事务方法
    //并不是事务方法越多越好，查询方法不需要添加为事务方法
//    3张表要处理
    @Transactional
    @Override
    public Long saveItem(TbItem tbItem, String content,String paramData) {
        Long itemId= null;
        try {
            //这个方法中需要处理两张表格tb_item tb_item_desc，  tb_item-param-item
            //调用工具类生成商品的ID
            //处理tb_item
            itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(new Date());
            int i = itemDao.insert(tbItem);
            //处理tb_item_desc
            TbItemDesc desc = new TbItemDesc();
            desc.setItemId(itemId);
            desc.setItemDesc(content);
            desc.setCreated(new Date());
            desc.setUpdated(new Date());
            i += itemDescDao.insert(desc);

//            处理tb-item-param-item
            TbItemParamItem  tbItemParamItem=new TbItemParamItem();
            tbItemParamItem.setItemId(itemId);
            tbItemParamItem.setParamData(paramData);
            tbItemParamItem.setCreated(new Date());
            tbItemParamItem.setUpdated(new Date());
            i+=itemParamItemDao.insert(tbItemParamItem);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return itemId;
    }
}
