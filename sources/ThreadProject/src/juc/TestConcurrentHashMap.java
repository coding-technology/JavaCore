package juc;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Object> chm =  new ConcurrentHashMap<>();
        chm.put("s01", new Object());
        chm.put("s02", new Object());
        chm.put("s03", new Object());
        chm.putIfAbsent("s03",new Object());//如果key不存在，则增加；否则不增加

        System.out.println(chm);
    }
}
