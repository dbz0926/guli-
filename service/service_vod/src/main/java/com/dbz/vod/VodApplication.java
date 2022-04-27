package com.dbz.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 10979
 * @description:
 * @date 2022/4/919:36
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(value = "com.dbz")
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
