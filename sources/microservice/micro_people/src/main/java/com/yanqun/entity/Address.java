package com.yanqun.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Created by 颜群
 */
@Entity
@Table(name="tb_address")
public class Address {
    @Id
    private Integer id ;
    private String name ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
