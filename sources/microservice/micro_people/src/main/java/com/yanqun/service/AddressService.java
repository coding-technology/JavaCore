package com.yanqun.service;

import com.yanqun.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Created by 颜群
 */

@Service
public class AddressService  {
    @Autowired
    private AddressDao addressDao ;


    public List<Object[]> findAddressNamesAndCount() {
        return addressDao.findAddressNamesAndCount() ;
    }




    @Transactional
    public int addAddress(Integer id ,String name){
        return addressDao.addAddress(id,name) ;
    }

    @Transactional
    public int deleteAddressById(Integer id){
        return addressDao.deleteAddressById(id) ;
    }

    @Transactional
    public int updateAddress( String name , Integer id ){
        return addressDao.updateAddress(name,id) ;
    }
}
