package com.bwei.oracle_coundday0421;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//自动定时任务的注解
@MapperScan("com.bwei.oracle_coundday0421.dao")
public class OracleCoundday0421Application {

    public static void main(String[] args) {
        SpringApplication.run(OracleCoundday0421Application.class, args);
    }

}
