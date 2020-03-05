package com.yanqun.micro_city2.service;

import com.yanqun.micro_city2.entity.City;
import com.yanqun.micro_city2.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by 颜群
 */
@Service
public class CityService {

    @Autowired
    CityMapper cityMapper ;//service 注入mapper(dao)

    public boolean addCity(City city){
        return cityMapper.addCity(city);
    }

    public boolean deleteById(Integer  id){
            return cityMapper.deleteById(id) ;
    }

    public boolean updateCityById(City city){
        return cityMapper.updateCityById(city) ;
    }

    public List<City> queryCities(){
        System.out.println(cityMapper);
        return cityMapper.queryCities() ;
    }

}
