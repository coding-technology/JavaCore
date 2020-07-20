package com.yanqun.controller;

import com.yanqun.entity.Message;
import com.yanqun.entity.StatusCode;
import com.yanqun.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/*
 * Created by 颜群
 */
@CrossOrigin//支持跨域
@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private PeopleService peopleService ;

    //localhost:8885/people
//    @GetMapping
//    public Message findAll(){
//        return new Message(true,StatusCode.OK,  peopleService.findAll() ) ;
//    }

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    //用户名：username
    //密码：password
    @PostMapping(value="/login")
    public Message login(@RequestBody Map<String,String> login){
        //controller -sercice -dao 模拟操作
        String uname =    login.get("username") ;
        String upwd = login.get("password") ;
        //findUserByUsernameAndPassword
        //zs/abc -> 返回这个人的全部信息 User(zs/abc,id(1001).....)

        System.out.println("1111");
        //假设数据库中的zs/abc  （并且：假设这个人的id1001,这个人是管理员权限admin   ）
        if(uname.equals("zs") && upwd.equals("abc")){
            //登录成功，服务端生成token
            //String id, String subject,String roles
            String token = jwtUtil.createJWT("1001", "zs", "admin");

            Map map = new HashMap() ;
            map.put("token",token) ;
            map.put("username",uname) ;

            System.out.println("登录成功");
            //登录成功：返回token\用户名
            return new Message(true,StatusCode.OK,map) ;
        }else{
            System.out.println("登录失败");
            return new Message(false,StatusCode.ERROR) ;
        }


    }


//    @GetMapping(value="/jpa2/{name}")
//    public Message findByNameOrderByAgeDesc(@PathVariable String name){
//        return new Message(true,StatusCode.OK,  peopleService.findByNameOrderByAgeDesc(name) ) ;
//    }
//
//
//    @GetMapping(value="/jpa3/{age}")
//    public Message findByAgeAsc(@PathVariable Integer age){
//        return new Message(true,StatusCode.OK,  peopleService.findByAgeAsc(age) ) ;
//    }
//
//    @GetMapping(value="/jpa3/page/{age}/{currentPage}/{pageSize}")
//    public Message findByAgeAsc2(@PathVariable Integer age,@PathVariable Integer currentPage,@PathVariable Integer pageSize){
//        return new Message(true,StatusCode.OK,  peopleService.findByAgeAsc2(age,currentPage,pageSize) ) ;
//    }
//
//
//
//
//
//
//    @PostMapping
//    public Message savePeople(@RequestBody People people){
//        peopleService.savePeople(people);
//        return new Message( true , StatusCode.OK) ;
//    }
//
//    //localhost:8885/people/s1001   delete
//    @DeleteMapping(value="/{id}")
//    public Message deletePeopleById(@PathVariable String id){
//        peopleService.deletePeopleById(id);
//        return new Message( true , StatusCode.OK) ;
//    }
//
//    //update ....set name = ?,age = ?  where id = ?
//    @PutMapping // id,   name,age
//    public Message updatePeople( @RequestBody People people){
//        peopleService.updatePeople( people);
//        return new Message( true , StatusCode.OK) ;
//    }
//
//
//    @PutMapping(value="/update/{id}")
//    public Message updatePeople(@PathVariable String id){
//        return new Message(true,StatusCode.OK,  peopleService.updatePeople(id) ) ;
//    }
//
//    @PutMapping(value="/update2/{id}/{age}")
//    public Message updatePeople2(@PathVariable String id,@PathVariable  Integer age){
//        return new Message(true,StatusCode.OK,  peopleService.updatePeople2(id,age) ) ;
//    }
//
//    @PutMapping(value="/update3")
//    public Message updatePeople3(@RequestBody People people){
//        return new Message(true,StatusCode.OK,  peopleService.updatePeople3(people) ) ;
//    }
//
//
//
//
//    @GetMapping(value="/{id}")
//    public Message findPeopleById(@PathVariable String id){
//        return new Message(true,StatusCode.OK,  peopleService.findPeopleById(id) ) ;
//    }
//
//    @GetMapping(value="/jpa4/{id}")
//    public Message findNameById(@PathVariable String id){
//        return new Message(true,StatusCode.OK,peopleService.findNameById(id)) ;
//
//    }
//
//    @GetMapping(value="/jpa4/{name}/{age}")
//    public Message findPeopleByNameAndAge(@PathVariable String name, @PathVariable int age){
//        return new Message(true,StatusCode.OK,peopleService.findPeopleByNameAndAge(name,age)) ;
//
//    }
//
//    //给原生SQL传入的是集合，并且加了分页功能
//    @GetMapping(value="/jpa4/findPeopleByAges/{currentPage}/{pageSize}")
//     public  Message findPeopleByAges(@RequestParam(value="ages")  List<Integer> ages,@PathVariable Integer currentPage,@PathVariable Integer pageSize){
//        return new Message(true,StatusCode.OK,peopleService.findPeopleByAges(ages,currentPage,pageSize)) ;
//
//    }
//
//    //springmvc 接收map 和接收entiy是一致的
//    @GetMapping(value="/jpa5/findPeople/{currentPage}/{pageSize}")
//    public Message findPeople(@RequestBody Map<String,Object> map ,@PathVariable Integer currentPage,@PathVariable Integer  pageSize) {
//        return new Message(true, StatusCode.OK, peopleService.findPeople(map,currentPage,pageSize));
//    }



//
//    //jpa底层，修改和 增加都是 save()
//    public void updatePeople(People people){
//        peopleDao.save( people) ;
//    }
//
//    public void (String id){
//        peopleDao.deleteById(id);
//    }
//
//    public List<People> findAll() {
//        return  peopleDao.findAll() ;
//    }
//
//    public People findAllById(String id){
//        return peopleDao.findById( id ).get() ;
//    }


}
