package com.yswl.yswletc;


import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

@MapperScan("com.yswl.yswletc.dao")
@SpringBootApplication(scanBasePackages = "com.yswl.yswletc")
@Import(FdfsClientConfig.class)
@EnableCaching
public class YswletcApplication {

    public static void main(String[] args) {
        SpringApplication.run(YswletcApplication.class, args);
    }
}
