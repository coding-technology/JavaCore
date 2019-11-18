package com.yanqun;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JVMDemo {
    public static void main(String[] args) throws Exception{
        List list = new ArrayList<>();
        for(int i=0;i<900;i++){
            list.add(new byte[1024]);
        }
        TimeUnit.SECONDS.sleep(10000000);
    }
}
