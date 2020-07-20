package com.yanqun.micro_city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

@MapperScan(value="com.yanqun.micro_city.mapper")
@SpringBootApplication
@EnableEurekaClient
public class MicroCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCityApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil() ;
    }

}
