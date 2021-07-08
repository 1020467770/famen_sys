package cn.sqh.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication//包括有组件扫描等东西
@MapperScan("cn.sqh.dao")//mybatis官方的mapper扫描，扫描不到通用Mapper
@Configuration//开启事务回滚
@EnableTransactionManagement//开启事务Manager
public class ServiceTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTestApplication.class, args);
    }

}
