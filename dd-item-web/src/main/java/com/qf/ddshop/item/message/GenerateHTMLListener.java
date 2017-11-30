package com.qf.ddshop.item.message;


import com.qf.ddshop.pojo.po.TbItem;
import com.qf.ddshop.pojo.po.TbItemDesc;
import com.qf.ddshop.pojo.po.TbItemExample;
import com.qf.ddshop.service.ItemDescService;
import com.qf.ddshop.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class GenerateHTMLListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void onMessage(Message message) {
        try {
            //1. 接收消息
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long itemId = Long.parseLong(text);
//            Long itemId = new Long(text);

            //2. 根据商品ID查询商品基本信息和商品描述
           TbItem tbItem = itemService.getById(itemId);
           //查商品描述
            TbItemDesc tbItemDesc = itemDescService.getByItemId(itemId);
//           /3. 创建数据集
            Map<String,Object> dataModel = new HashMap<String,Object>();
            dataModel.put("item",tbItem);
            dataModel.put("itemDesc",tbItemDesc);

            //4. 加载模板对象
            // 获取freemarker的配置对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");

            //5. 创建输出流，
            Writer out = new FileWriter("d:/ft/"+itemId+".html");//生成在D盘，也可以在虚机
            //生成静态页面
            template.process(dataModel,out);

            out.close();

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

    }
}
