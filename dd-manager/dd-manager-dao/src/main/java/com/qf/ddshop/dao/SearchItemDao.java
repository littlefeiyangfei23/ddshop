package com.qf.ddshop.dao;

import com.qf.ddshop.pojo.vo.TbItemSearchCustom;
import com.qf.ddshop.pojo.vo.TbSearchItemResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchItemDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SolrServer solrServer;

    /**
     *根据查询条件查询索引库
     */
    public TbSearchItemResult search(SolrQuery query) {
        TbSearchItemResult result=null;
        try {
            //根据query查询索引库
            QueryResponse queryResponse = solrServer.query(query);
            //取查询结果。
            SolrDocumentList solrDocumentList = queryResponse.getResults();
            //取查询结果总记录数
            long numFound = solrDocumentList.getNumFound();
            result = new TbSearchItemResult();
            result.setRecordCount(numFound);
            //取商品列表，需要取高亮显示
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<TbItemSearchCustom> itemList = new ArrayList<>();
            //遍历文档域对象
            for (SolrDocument solrDocument : solrDocumentList) {
                TbItemSearchCustom item = new TbItemSearchCustom();
                item.setId((String) solrDocument.get("id"));
                item.setCatName((String) solrDocument.get("item_category_name"));
                item.setImage((String) solrDocument.get("item_image"));
                item.setPrice((long) solrDocument.get("item_price"));
                item.setSellPoint((String) solrDocument.get("item_sell_point"));
                //取高亮显示
                List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
                //                {
//                    "1039296": {
//                    "item_title": [
//                    "合约惠<em style=\"color:red\">机</em>测试<em style=\"color:red\">手机</em>（请勿下单）"
//                        ]
//                }}
                String title = "";
                //有高亮
                if (list != null && list.size() > 0) {
                    title = list.get(0);
                } else {
                    title = (String) solrDocument.get("item_title");
                }
                item.setTitle(title);
                //添加到商品列表
                itemList.add(item);
            }
            result.setItemList(itemList);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
}
