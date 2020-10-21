package com.scistor.develop.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private Interceptor interceptor;

    //这个方法是用来配置静态资源的，比如html，js，css，等等
    @Value("${filepath.file_path}")
    private String filepath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/tianjinDevelop/file/**").addResourceLocations("file:" + filepath);

    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**");
    }


    @Bean
    public MultipartConfigElement multipartConfigElement(@Value("${spring.servlet.multipart.max-file-size}")String maxFileSize, @Value("${spring.servlet.multipart.max-request-size}") String maxRequestSize) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(maxFileSize);
        //设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}