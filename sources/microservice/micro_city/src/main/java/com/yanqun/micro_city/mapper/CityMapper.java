package com.yanqun.micro_city.mapper;

/*
 * Created by 颜群
 */

import com.yanqun.micro_city.entity.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface CityMapper {//mybatis 接口 操作sql
    //mybatis： 1.注解   2.SQL映射文件

    @Insert("insert into city(id,name,area) values(#{id},#{name},#{area}    )")
    public boolean addCity(City city);

    @Delete("delete from city where id = #{id}")
    public boolean deleteById(Integer id);

    @Update("update city set name = #{name} ,area=#{area} where id = #{id}")
    public boolean updateCityById(City city);

    @Select("select * from city")
    public List<City> queryCities();

}
