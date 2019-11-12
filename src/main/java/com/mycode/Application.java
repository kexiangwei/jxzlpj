package com.mycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class Application {

    /**
     * 设置文件上传大小
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024MB");
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
