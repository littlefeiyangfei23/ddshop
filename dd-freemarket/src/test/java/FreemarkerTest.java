import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTest {

    //freemarker测试
    @Test
    public void testFreemarker1() throws IOException, TemplateException {

//拿的都是freemarker的方法
        //创建配置对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
//        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板文件所在的路径
        configuration.setClassForTemplateLoading(this.getClass(),"/ftl");
//        configuration.setClassForTemplateLoading(this.getClass(),"/ftl");
        //设置字符集
        configuration.setDefaultEncoding("UTF-8");
//        configuration.setDefaultEncoding("UTF-8");
        //创建模板对象
        Template template = configuration.getTemplate("01.ftl");
//        Template template = configuration.getTemplate("01.ftl");
        //创建数据集
        Map<String,Object> dataModel = new HashMap<String,Object>();
        dataModel.put("name","王阳飞");
//        Map<String,Object> dataModel = new HashMap<String,Object>();
//        dataModel.put("name", "丁昊成");
        //创建Writer对象
        Writer out = new FileWriter("d:/ft/01.html");
//        Writer out = new FileWriter("d:/ftl/01.html");
        //使用模板对象输出文件
        template.process(dataModel,out);
//        template.process(dataModel, out);
        //关闭流
        out.close();
    }
}
