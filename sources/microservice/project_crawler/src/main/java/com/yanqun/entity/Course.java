package com.yanqun.entity;

/*
 * Created by 颜群
 */
public class Course {

    private String name ;
    private Integer num ;
    private  String imgPath ;


    public Course() {
    }

    public Course(String name, Integer num, String imgPath) {
        this.name = name;
        this.num = num;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
