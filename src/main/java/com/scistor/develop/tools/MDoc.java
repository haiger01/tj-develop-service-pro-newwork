package com.scistor.develop.tools;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class MDoc {

    private Configuration configuration = null;


    public MDoc() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public void createDoc(Map<String, Object> dataMap, Writer out,String filePath) throws UnsupportedEncodingException {
        // dataMap 要填入模本的数据文件
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        //System.out.println(file_down_path);
        Template t = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(filePath));
            //configuration.setDirectoryForTemplateLoading(new File("C:/Users/86130/Desktop"));

            // test.ftl为要装载的模板
            t = configuration.getTemplate("ruzhang.ftl","UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*// 输出文档路径及名称
        File outFile = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            // 这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
            // out = new BufferedWriter(new OutputStreamWriter(new
            // FileOutputStream(outFile)));
            out = new BufferedWriter(oWriter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }*/

        try {
            t.process(dataMap, out);
            //out.close();
            //fos.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDoc(Map<String, Object> dataMap, String fileName) throws UnsupportedEncodingException {
        // dataMap 要填入模本的数据文件
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        Template t = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File("/home/yh/fileDown/"));
            // test.ftl为要装载的模板
            t = configuration.getTemplate("ruzhang.ftl","UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File(fileName);
        Writer out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            // 这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
            // out = new BufferedWriter(new OutputStreamWriter(new
            // FileOutputStream(outFile)));
            out = new BufferedWriter(oWriter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
            out.close();
            fos.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
