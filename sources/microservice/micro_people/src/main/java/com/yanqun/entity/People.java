package com.yanqun.entity;

/*
 * Created by 颜群
 */

import java.io.Serializable;

//@Entity
//@Table(name="tb_people")
public class People implements Serializable {
//    @Id
    private String id ;  //分布式系统  uuid  snowflake自动生成一套 不会重复的id
    private String name ;
    private Integer age ;
    private Integer address ;

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}


