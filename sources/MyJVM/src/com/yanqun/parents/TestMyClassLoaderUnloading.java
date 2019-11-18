package com.yanqun.parents;

import sun.misc.GC;

class Z{}

/*
    vm参数：
        -XX:+TraceClassLoading
        -XX:+TraceClassUnloading
 */
public class TestMyClassLoaderUnloading  {
    public static void main(String[] args) throws Exception{
        MyClassLoaderImpl classLoader = new MyClassLoaderImpl() ;
        classLoader.path = "D:/" ;
        Class<?> myClass = classLoader.loadClass("com.yanqun.parents.Z");
        System.out.println("11111111111");
        classLoader = null ;
        myClass = null ;
        System.gc();

    }
}
