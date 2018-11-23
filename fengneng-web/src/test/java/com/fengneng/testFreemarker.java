package com.fengneng;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxu on 2018/5/26.
 */
public class testFreemarker {
    @Test
    public void testFreemarker() throws Exception {
        //创建一个模板文件
        //创建一个configuration对象
        Configuration configuration=new Configuration(Configuration.getVersion());
        //设置模板所在路径
        configuration.setDirectoryForTemplateLoading(new File("D:\\FengNeng\\fengneng-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        //设置模板的字符集
        configuration.setDefaultEncoding("utf-8");
        //使用configuration加载一个模板文件，需要指定模板文件的文件名
        //Template template = configuration.getTemplate("hello.ftl");
        Template template = configuration.getTemplate("student.ftl");
        //创建一个数据集，可以是map也可以是pojo
        Map  data = new HashMap();
        data.put("hello","hello freemarker");
        student stu=new student(1,"java",13,"云南");
        data.put("student",stu);
        List<student> list=new ArrayList<>();
        list.add(new student(1,"java",13,"云南"));
        list.add(new student(2,"php",13,"云南"));
        list.add(new student(3,"mysql",13,"云南"));
        list.add(new student(4,"oracle",13,"云南"));
        data.put("stuList",list);
        //创建一个writer对象，指定文件输出的路径及文件名
        Writer writer=new FileWriter("D:\\test\\ftl\\student.html");
        //使用模板对象的proccess方法
        template.process(data,writer);
        //关闭流
        writer.close();
    }
    @Test
    public void testSpringFreemarker() throws Exception {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:springmvc.xml");
        FreeMarkerConfig freeMarkerConfigurer = (FreeMarkerConfig) applicationContext.getBean("freeMarkerConfigurer");
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("hello.ftl");
        Map data=new HashMap();
        data.put("hello","spring test freemarker");
        Writer out=new FileWriter("D:\\test\\ftl\\spring.html");
        template.process(data,out);
        out.close();
    }
}
