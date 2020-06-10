package com.yanqun.micro_city.service;

import com.yanqun.micro_city.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Created by 颜群
 */
@Service
public class CityService {

//    @Autowired
//    CityMapper cityMapper ;//service 注入mapper(dao)

//    public boolean addCity(City city){
//        return cityMapper.addCity(city);
//    }
//
//    public boolean deleteById(Integer  id){
//            return cityMapper.deleteById(id) ;
//    }
//
//    public boolean updateCityById(City city){
//        return cityMapper.updateCityById(city) ;
//    }
//
//    public List<City> queryCities(){
//        return cityMapper.queryCities() ;
//    }

    public boolean addCity(City city){
        return true;
    }

    public boolean deleteById(Integer  id){
        return true ;
    }

    public boolean updateCityById(City city){
        return true ;
    }

    public List<City> queryCities(){
        return null;
    }

    public City queryCityBiId(Integer  id){
        return new City(1,"xa",9999999);
    }

}
