package com.yanqun.controller;

import com.yanqun.entity.Message;
import com.yanqun.entity.People;
import com.yanqun.entity.StatusCode;
import com.yanqun.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 * Created by 颜群
 */
@CrossOrigin//支持跨域
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService ;

    //localhost:8885/people
    @GetMapping
    public Message findAll(){
        return new Message(true,StatusCode.OK,  peopleService.findAll() ) ;
    }

    @GetMapping(value="/jpa2/{name}")
    public Message findByNameOrderByAgeDesc(@PathVariable String name){
        return new Message(true,StatusCode.OK,  peopleService.findByNameOrderByAgeDesc(name) ) ;
    }


    @GetMapping(value="/jpa3/{age}")
    public Message findByAgeAsc(@PathVariable Integer age){
        return new Message(true,StatusCode.OK,  peopleService.findByAgeAsc(age) ) ;
    }

    @GetMapping(value="/jpa3/page/{age}/{currentPage}/{pageSize}")
    public Message findByAgeAsc2(@PathVariable Integer age,@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        return new Message(true,StatusCode.OK,  peopleService.findByAgeAsc2(age,currentPage,pageSize) ) ;
    }






    @PostMapping
    public Message savePeople(@RequestBody People people){
        peopleService.savePeople(people);
        return new Message( true , StatusCode.OK) ;
    }

    //localhost:8885/people/s1001   delete
    @DeleteMapping(value="/{id}")
    public Message deletePeopleById(@PathVariable String id){
        peopleService.deletePeopleById(id);
        return new Message( true , StatusCode.OK) ;
    }

    //update ....set name = ?,age = ?  where id = ?
    @PutMapping // id,   name,age
    public Message updatePeople( @RequestBody People people){
        peopleService.updatePeople( people);
        return new Message( true , StatusCode.OK) ;
    }


    @PutMapping(value="/update/{id}")
    public Message updatePeople(@PathVariable String id){
        return new Message(true,StatusCode.OK,  peopleService.updatePeople(id) ) ;
    }

    @PutMapping(value="/update2/{id}/{age}")
    public Message updatePeople2(@PathVariable String id,@PathVariable  Integer age){
        return new Message(true,StatusCode.OK,  peopleService.updatePeople2(id,age) ) ;
    }

    @PutMapping(value="/update3")
    public Message updatePeople3(@RequestBody People people){
        return new Message(true,StatusCode.OK,  peopleService.updatePeople3(people) ) ;
    }




    @GetMapping(value="/{id}")
    public Message findPeopleById(@PathVariable String id){
        return new Message(true,StatusCode.OK,  peopleService.findPeopleById(id) ) ;
    }

    @GetMapping(value="/jpa4/{id}")
    public Message findNameById(@PathVariable String id){
        return new Message(true,StatusCode.OK,peopleService.findNameById(id)) ;

    }

    @GetMapping(value="/jpa4/{name}/{age}")
    public Message findPeopleByNameAndAge(@PathVariable String name, @PathVariable int age){
        return new Message(true,StatusCode.OK,peopleService.findPeopleByNameAndAge(name,age)) ;

    }

    //给原生SQL传入的是集合，并且加了分页功能
    @GetMapping(value="/jpa4/findPeopleByAges/{currentPage}/{pageSize}")
     public  Message findPeopleByAges(@RequestParam(value="ages")  List<Integer> ages,@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        return new Message(true,StatusCode.OK,peopleService.findPeopleByAges(ages,currentPage,pageSize)) ;

    }

    //springmvc 接收map 和接收entiy是一致的
    @GetMapping(value="/jpa5/findPeople/{currentPage}/{pageSize}")
    public Message findPeople(@RequestBody Map<String,Object> map ,@PathVariable Integer currentPage,@PathVariable Integer  pageSize) {
        return new Message(true, StatusCode.OK, peopleService.findPeople(map,currentPage,pageSize));
    }



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
