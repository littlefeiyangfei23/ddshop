package com.qf.ddshop.service.impl;

import com.qf.ddshop.dao.SearchItemDao;
import com.qf.ddshop.dao.TbItemSearchCustomMapper;
import com.qf.ddshop.pojo.vo.TbItemSearchCustom;
import com.qf.ddshop.pojo.vo.TbSearchItemResult;
import com.qf.ddshop.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomMapper;

    //这个属性是spring和SolrServer整合所必须的。
    @Autowired
    private SolrServer httpSolrServer;

    //直接在com.qf.ddshop.dao下建了一个SearchItemDao类
    @Autowired
    private SearchItemDao searchItemDao;

    @Override
    public boolean importAllItems() {

        //采集数据，查询数据list
        List<TbItemSearchCustom> list =tbItemSearchCustomMapper.listSearchItems();


        //创建索引库， documenList

        //遍历
        for(TbItemSearchCustom tbItemSearchCustom :list){
            //创建solr的文档对象

            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",tbItemSearchCustom.getId());
            document.addField("item_title",tbItemSearchCustom.getTitle());
            document.addField("item_sell_point",tbItemSearchCustom.getSellPoint());
            document.addField("item_price",tbItemSearchCustom.getPrice());
            document.addField("item_image",tbItemSearchCustom.getImage());
            document.addField("item_category_name",tbItemSearchCustom.getCatName());
            //写入索引库
            try {
                httpSolrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //提交
        try {
            httpSolrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public TbSearchItemResult search(String keyword, Integer page, int rows) {
        //创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keyword);
        //设置分页条件
        if (page <=0 ) page = 1;
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", "item_keywords");
        //开启高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //调用dao执行查询
        TbSearchItemResult searchResult = searchItemDao.search(query);
        //计算总页数,注释掉的是本来的方法,是3目运算，写了一个好的
        long recordCount = searchResult.getRecordCount();
        /*int totalPage = (int) (recordCount / rows);
        if (recordCount % rows > 0){
            totalPage ++;
        }*/
//        long转int
        int totalPage = (int)(recordCount+rows-1)/rows;
        //添加到返回结果
        searchResult.setTotalPages(totalPage);
        //返回结果
        return searchResult;
    }
}
