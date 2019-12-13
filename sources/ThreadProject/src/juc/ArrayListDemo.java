package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("aaa") ;
        list.add("bb") ;
        list.add("cc") ;
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
            list.add("xxx");
        }
        System.out.println("---");

        Iterator<String> iter2 = list.iterator();
        while(iter2.hasNext()){
            System.out.println(iter2.next());
        }
    }
}
