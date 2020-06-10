package com.yanqun.micro_city2.remote.city;

/*
 * Created by 颜群
 */

//city2 访问city的远程对象
//city2 ->CityClient ->city

import com.yanqun.entity.Message;
import com.yanqun.micro_city2.entity.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("city")
public interface CityClient {
    @GetMapping("queryCityBiId/{id}")
    public Message queryCityBiId(@PathVariable("id") Integer  id) ;

    @PostMapping("addCity")
    public Message addCity(@RequestBody City city) ;
}
