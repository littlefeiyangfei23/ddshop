package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.dto.TreeNode;
import com.qf.ddshop.dao.TbItemCatMapper;
import com.qf.ddshop.pojo.po.TbItemCat;
import com.qf.ddshop.pojo.po.TbItemCatExample;
import com.qf.ddshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper itemCatDao;

    @Override
    public List<TreeNode> listItemCatsById(Long parentId) {
        List<TreeNode> treeNodesList=null;
        try{
            //创建查询模板
            TbItemCatExample example=new TbItemCatExample();
            TbItemCatExample.Criteria criteria=example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> itemCatList = itemCatDao.selectByExample(example);
            //要序列化成JSON的列表对象
            treeNodesList=new ArrayList<TreeNode>();
//            集合用了size，取得长度
//            遍历原有列表生成新列表
            for (int i=0;i<itemCatList.size();i++){
                TbItemCat itemCat = itemCatList.get(i);
                TreeNode treeNode =new TreeNode();
                treeNode.setId(itemCat.getId());
                treeNode.setText(itemCat.getName());
//                tinyint 微小整型，就一位数，非0就是true，0是false
                treeNode.setState(itemCat.getIsParent()?"closed":"open");
                treeNodesList.add(treeNode);

            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return treeNodesList;
    }
}
