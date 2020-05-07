package com.atguigu.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/7 9:51
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }
}
