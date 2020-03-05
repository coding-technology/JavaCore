package com.yanqun.micro_city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value="com.yanqun.micro_city.mapper")
@SpringBootApplication
public class MicroCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCityApplication.class, args);
    }

}
