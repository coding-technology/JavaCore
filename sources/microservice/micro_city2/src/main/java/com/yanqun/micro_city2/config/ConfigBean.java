package com.yanqun.micro_city2.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Created by 颜群
 */
//将远程访问对象 放入IoC容器
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced//负载均衡
    public RestTemplate getRestTemplate(){
        return  new RestTemplate() ;
    }
}
