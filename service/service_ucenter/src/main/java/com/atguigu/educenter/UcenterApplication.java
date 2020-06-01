package com.atguigu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/28 9:59
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
@MapperScan(basePackages = "com.atguigu.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
