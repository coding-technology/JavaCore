package com.manager.book;

public class TestBookManager {
    public static void main(String[] args) {

        BookManager manager = new BookManager();
        manager.init();

        manager.menu();

//        Date date  = new Date();//"2019-05-11"
//        System.out.println(date);
//
//        //Date <-> String
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
//        //Date -> String
//        String dateStr = sdf.format(date) ;
//        System.out.println(dateStr);

    }
}
