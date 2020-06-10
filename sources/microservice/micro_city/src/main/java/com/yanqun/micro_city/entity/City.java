package com.yanqun.micro_city.entity;

import java.io.Serializable;

/*
 * Created by 颜群
 */
public class City implements Serializable {
    private Integer id ;
    private String name;
    private double area ;//城市面积
    public City() {
    }
    public City(Integer id, String name, double area) {
        this.id = id;
        this.name = name;
        this.area = area;
    }

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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
