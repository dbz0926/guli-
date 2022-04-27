package com.dbz.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1416:26
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.dbz"})
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
