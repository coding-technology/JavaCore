package com.yanqun.micro_city.service;

import com.yanqun.micro_city.entity.City;
import com.yanqun.micro_city.mapper.CityMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
        return cityMapper.queryCities() ;
    }

}
