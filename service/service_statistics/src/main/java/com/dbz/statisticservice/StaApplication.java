package com.dbz.statisticservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1921:19
 */
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.dbz"})
@MapperScan("com.dbz.statisticservice.mapper")
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class,args);
    }
}
