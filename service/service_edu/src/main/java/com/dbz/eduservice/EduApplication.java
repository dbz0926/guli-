package com.dbz.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 10979
 * @description:
 * @date 2022/4/120:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.dbz")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
