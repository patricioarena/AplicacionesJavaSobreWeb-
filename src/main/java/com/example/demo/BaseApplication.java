package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class BaseApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(BaseApplication.class,args);
    }
}
