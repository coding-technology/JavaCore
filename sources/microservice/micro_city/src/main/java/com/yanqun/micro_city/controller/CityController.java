package com.yanqun.micro_city.controller;

import com.yanqun.entity.Message;
import com.yanqun.entity.StatusCode;
import com.yanqun.micro_city.entity.City;
import com.yanqun.micro_city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Created by 颜群
 */

@RestController
public class CityController {//spring mvc
    @Autowired
    CityService cityService ;//service 注入mapper(dao)


//    @RequestMapping(value="addCity",method= RequestMethod.POST)
    @PostMapping("addCity")
    public Message addCity(@RequestBody City city){
        //return "success.jsp"
       boolean result =  cityService.addCity(city) ;
       Message message = new Message(true, StatusCode.OK,   result );
        return message;//将请求的状态、状态码、结果封装在message对象
    }
    @DeleteMapping("deleteById/{id}")
    public Message deleteById( @PathVariable("id") Integer  id){

        boolean result =cityService.deleteById(id) ;
        return  new Message(true, StatusCode.OK,   result );
    }

//    @RequestMapping(value = "updateCityById",method =RequestMethod.PUT)
    @PutMapping( "updateCityById")
    public Message updateCityById(@RequestBody City city)
    {
        boolean result =  cityService.updateCityById(city) ;
        return  new Message(true, StatusCode.OK,   result ) ;
    }

    @GetMapping("queryCities")
    public Message queryCities(){
        List<City> cities = cityService.queryCities();
        return  new Message(true, StatusCode.OK,   cities );
    }

    @GetMapping("queryCityBiId/{id}")
    public Message queryCityBiId( @PathVariable("id") Integer  id){
        City city = cityService.queryCityBiId(id);
        return  new Message(true, StatusCode.OK,   city );
    }
}
