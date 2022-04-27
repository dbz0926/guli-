package com.dbz.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1419:08
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.dbz")
@MapperScan("com.dbz.ucenterservice.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
