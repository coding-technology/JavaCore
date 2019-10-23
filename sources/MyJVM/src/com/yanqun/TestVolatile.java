package com.yanqun;

import java.util.concurrent.atomic.AtomicInteger;

public class TestVolatile {
//    static volatile  int num =  0;
    static AtomicInteger num = new AtomicInteger(0) ;

    public static void main(Math[] args) throws InterruptedException {
        for(int i=0;i<100;i++){
            //每个线程：将num类似3万次；100个线程 在线程安全时，结果应该300万
            new Thread( () ->{
                for(int j=0;j<30000;j++){
//                    num++ ;//不是一个原子性操作
                    num.incrementAndGet() ;
                    /*
                         num = num + 1  :
                          ①num+1
                          ② num = ①的结果


                          2个线程同时执行 num +1  （假设此时num的值是10）
                            一个线程：   10 +1 -> 11
                            另一个线程： 10 + 1 -> 11
                     */


                }
            }  ).start();//lambda
        }
        Thread.sleep(1000);
        System.out.println(num);
    }

}
