package com.yanqun.controller;

import com.yanqun.entity.Message;
import com.yanqun.entity.StatusCode;
import com.yanqun.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * Created by 颜群
 */
@CrossOrigin//支持跨域
@RestController
@RequestMapping("/address")
public class AddressControler {
    @Autowired
    private AddressService addressService ;


    @GetMapping(value="/jpa4/findAddressNamesAndCount")
    public Message findAddressNamesAndCount(){
        return new Message(true, StatusCode.OK,addressService.findAddressNamesAndCount()) ;
    }

    @PostMapping(value="/jpa4/addAddress/{id}/{name}")
    public Message addAddress(@PathVariable Integer id ,@PathVariable String name){
        return new Message(true,StatusCode.OK, addressService.addAddress(id,name)  ) ;
    }

    @DeleteMapping(value="/jpa4/deleteAddress/{id}")
    public Message deleteAddress(@PathVariable Integer id){
        return new Message(true,StatusCode.OK, addressService.deleteAddressById(id)  ) ;
    }

    @PutMapping(value="/jpa4/updateAddress/{id}/{name}")
    public Message updateAddress(@PathVariable String name, @PathVariable Integer id){
        return new Message(true,StatusCode.OK, addressService.updateAddress(name,id)  ) ;
    }

}
