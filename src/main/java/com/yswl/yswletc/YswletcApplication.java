package com.yswl.yswletc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@MapperScan("com.yswl.yswletc.dao")
@SpringBootApplication(scanBasePackages = "com.yswl.yswletc")
public class YswletcApplication {

    public static void main(String[] args) {
        SpringApplication.run(YswletcApplication.class, args);
    }

}
