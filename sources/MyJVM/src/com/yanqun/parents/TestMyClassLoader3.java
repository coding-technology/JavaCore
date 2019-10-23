package com.yanqun.parents;
//A  a

//AppClassLoader.class : TestMyClassLoader2
//自定义加载器: A.class/B.class
public class TestMyClassLoader3 {
    public static void main(String[] args) throws Exception{
        MyClassLoaderImpl myClassLoader = new MyClassLoaderImpl() ;
        //自定义加载路径
        myClassLoader.path = "d:/" ;
        //程序第一次加载时（X），使用的是  自定义加载器
        Class<?> aClass = myClassLoader.loadClass("com.yanqun.parents.X");



        Object aObject = aClass.newInstance();//newInstance()会调用 该类的构造方法(new 构造方法())
        System.out.println(aObject);
    }
}
