package com.yanqun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
 * Created by 颜群
 */
@EnableEurekaServer//启动eureka服务
@SpringBootApplication
@EnableZuulProxy
public class MicroZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroZuulApplication.class, args);
    }

}