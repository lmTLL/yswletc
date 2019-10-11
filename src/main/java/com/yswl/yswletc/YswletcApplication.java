package com.yswl.yswletc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.yswl.yswletc.dao")
@SpringBootApplication(scanBasePackages = "com.yswl.yswletc")
@EnableCaching
public class YswletcApplication {

    public static void main(String[] args) {
        SpringApplication.run(YswletcApplication.class, args);
    }

}
