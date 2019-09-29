package com.manager.book;

import java.util.Date;

//a  b  c
public class Book {
    private int id;//1001
    private String name;

    private int state;//1在库  0被借
    private Date borrowDate;//被借日期
    private int borrowCount;//被借次数

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {//"2019-05-11"
        this.borrowDate = borrowDate;
    }
}
