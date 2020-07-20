package com.yanqun.micro_city.controller;

import com.yanqun.entity.Message;
import com.yanqun.entity.StatusCode;
import com.yanqun.micro_city.entity.City;
import com.yanqun.micro_city.service.CityService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * Created by 颜群
 */

@RestController
public class CityController {//spring mvc
    @Autowired
    CityService cityService ;//service 注入mapper(dao)

    @Autowired
    private HttpServletRequest request ;

    @Autowired
    private JwtUtil jwtUtil ;


//    @RequestMapping(value="addCity",method= RequestMethod.POST)
    @PostMapping("addCity")
    public Message addCity(@RequestBody City city){
        //return "success.jsp"
       boolean result =  cityService.addCity(city) ;
       Message message = new Message(true, StatusCode.OK,   result );
        return message;//将请求的状态、状态码、结果封装在message对象
    }
//    @DeleteMapping("deleteById/{id}")
//    public Message deleteById( @PathVariable("id") Integer  id){
//
//        //删除之前 先通过jwt进行权限校验
//        /*
//                已经存在生成jwt
//
//                现在：解析jwt
//                        1.客户端将jwt 传递给服务端 （postman模拟）
//                        2.解析jwt
//         */
//        //假设此时，客户端postman已经将jwt(token)传递过来了
//        String authentication = request.getHeader("authentication");
//        if(authentication == null) {//请求时（postman)时，并没有携带jwt
//            return new Message(false,StatusCode.ERROR,"权限不足！") ;
//        }
//
//        String token = authentication.substring(7);//从yanqun-token截取出token
//        Claims claims = jwtUtil.parseJWT(token);
//        String roles = (String)claims.get("roles");
//        System.out.println(roles+"roles");
//        if(!roles.equals("admin")){
//            return new Message(false,StatusCode.ERROR,"权限不足！") ;
//        }
//
//
//        boolean result =cityService.deleteById(id) ;
//        return  new Message(true, StatusCode.OK,   result );
//    }
    @DeleteMapping("deleteById/{id}")
    public Message deleteById( @PathVariable("id") Integer  id){
        //如果拦截器 判断权限足够，则会在request中 放入一个claims参数；否则，没有claims参数
        Claims claims = (Claims)request.getAttribute("claims");
        if(claims == null){
            return new Message(false,StatusCode.ERROR,"权限不足") ;
        }

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
