package com.yanqun;
//双重检查式的懒汉式单例模式
public class Singleton {
    private static Singleton instance = null ;//单例
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton() ;//不是一个原子性操作
                }
            }
        }
        return instance ;
    }
}

