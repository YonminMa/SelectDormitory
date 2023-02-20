package com.pku.dormitory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan("com.pku.dormitory.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DormitoryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryServerApplication.class, args);
    }

}
