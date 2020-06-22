package com.yanqun.micro_city2.controller;

import com.yanqun.entity.Message;
import com.yanqun.entity.StatusCode;
import com.yanqun.micro_city2.entity.City;
import com.yanqun.micro_city2.remote.city.CityClient;
import com.yanqun.micro_city2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
 * Created by 颜群
 */

@RestController
public class CityController2 {//spring mvc
    //feign方式的，调用远程（city）的service  （通过“city远程对象(CityClient)“ 的访问 city）
    @Autowired
    private CityClient cityClient ;

    //ribbon方式的，远程调用
    @Autowired
    private RestTemplate restTemplate ;


    //调用自己的service
    @Autowired
    CityService cityService ;//service 注入mapper(dao)


    //远程访问city中的方法(ribbon)
    @GetMapping("/findCityByIdRibbon/{id}")
    public Message findCityByIdRibbon(@PathVariable("id") Integer id){
        System.out.println("ribbon调用远程方法");
        //返回值 = getForObject( 映射地址，返回值类型，参数)  ip?a=b&c=d

        //http://city/queryCityBiId/{id}
//        return  restTemplate.getForObject( "http://city/queryCityBiId/"  , Message.class,id  );//方法的返回值，就是远程访问方法的返回值
        return  restTemplate.getForObject( "http://city/queryCityBiId/"+id  , Message.class  );//方法的返回值，就是远程访问方法的返回值
//        restTemplate.getForEntity() ;
    }



    //远程访问city中的方法(fegin)
    @GetMapping("/findCityById2/{id}")
    public Message findCityById2(@PathVariable("id") Integer id){

        return cityClient.queryCityBiId(id) ;
    }

    @PostMapping("addCity2")
    public Message addCity2(@RequestBody City city) {
        return   cityClient.addCity(city) ;
    }



//    @RequestMapping(value="addCity",method= RequestMethod.POST)
    @PostMapping("addCity")
    public Message addCity(@RequestBody City city){
        //return "success.jsp"
       boolean result =  cityService.addCity(city) ;
       Message message = new Message(true, StatusCode.OK,   result );
        return message;//将请求的状态、状态码、结果封装在message对象
    }
    @DeleteMapping("deleteById2/{id}")
    public Message deleteById( @PathVariable("id") Integer  id){

        boolean result =cityService.deleteById(id) ;
        return  new Message(true, StatusCode.OK,   result );
    }

//    @RequestMapping(value = "updateCityById",method =RequestMethod.PUT)
    @PutMapping( "updateCityById2")
    public Message updateCityById(@RequestBody City city)
    {
        boolean result =  cityService.updateCityById(city) ;
        return  new Message(true, StatusCode.OK,   result ) ;
    }

    @GetMapping("queryCities2")
    public Message queryCities(){
        List<City> cities = cityService.queryCities();
        return  new Message(true, StatusCode.OK,   cities );
    }
}
