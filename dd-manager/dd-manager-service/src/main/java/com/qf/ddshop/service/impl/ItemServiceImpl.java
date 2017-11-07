package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.dto.Page;
import com.qf.ddshop.common.dto.Result;
import com.qf.ddshop.dao.TbItemCustomMapper;
import com.qf.ddshop.dao.TbItemMapper;
import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.pojo.vo.TbItemCustom;
import com.qf.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page) {
        Result<TbItemCustom> result = null;
        try {
            //1 创建一个响应参数实体类
            result = new Result<TbItemCustom>();
            //2 对total进行设值(符合条件的总记录数)
            int total = itemCustomDao.countItems();
            result.setTotal(total);
            //3 对rows进行设值(指定页码显示记录集合)
            List<TbItemCustom> list = itemCustomDao.listItemsByPage(page);
            result.setRows(list);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
