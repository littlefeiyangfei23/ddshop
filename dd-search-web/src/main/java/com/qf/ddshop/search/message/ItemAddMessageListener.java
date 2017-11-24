package com.qf.ddshop.search.message;

import com.qf.ddshop.dao.TbItemSearchCustomMapper;
import com.qf.ddshop.pojo.vo.TbItemSearchCustom;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//xml和注解2选1，xml里面有了，这里不注解
public class ItemAddMessageListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemSearchCustomMapper tbItemSearchCustomMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        try {
            //接收消息，并且获取商品ID
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
//            Long itemId = new Long(text);
            long itemId = Long.parseLong(text);
            //按照商品ID查询出指定商品
            TbItemSearchCustom searchItem = tbItemSearchCustomMapper.getById(itemId);
             //创建一个文档对象
            SolrInputDocument document = new SolrInputDocument();
            //向文档对象中添加域
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSellPoint());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCatName());

            //把文档写入索引库
            solrServer.add(document);
            //提交
            solrServer.commit();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }
}
