package com.yanqun.tools;

import java.util.ArrayList;
import java.util.List;

class MyBuff
{
    byte[] buff = new byte[1024*1024] ;
}

public class Test1 {
    public static void main(String[] args) throws Exception {
        List<MyBuff> list = new ArrayList<>();
        for(int i=0;i<300 ;i++){ // 对象保存在堆
            list.add( new MyBuff() ) ;
        }
        Thread.sleep(10000000000000L);
        System.out.println("end");
    }
}
