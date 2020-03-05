package com.yanqun.micro_city2.mapper;

/*
 * Created by 颜群
 */

import com.yanqun.micro_city2.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface CityMapper {//mybatis 接口 操作sql
    //mybatis： 1.注解   2.SQL映射文件

    public boolean addCity(City city);

    public boolean deleteById(Integer id);

    public boolean updateCityById(City city);

    public List<City> queryCities();

}
