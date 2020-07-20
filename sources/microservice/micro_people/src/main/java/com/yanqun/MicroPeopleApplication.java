package com.yanqun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

@SpringBootApplication
public class MicroPeopleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroPeopleApplication.class, args);
    }


    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil() ;
    }
}
