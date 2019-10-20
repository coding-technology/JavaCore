package interview.equalsandhashcode;

import java.util.HashMap;
import java.util.Map;

public class EqualsDemo01 {
    public static void main(String[] args) {
//        Object obj1 = new Object() ;
//        Object obj2 = new Object() ;
//        System.out.println(obj1 == obj2);
//        System.out.println(obj1.equals(obj2));
        Person p1 = new Person(23,"zs");
        Person p2 = new Person(23,"zs");
//      System.out.println(p1.equals(p2));
        Map<Person,String> map = new HashMap<>();
        map.put(p1,"AAAA") ;
        map.put(p2,"BBB") ;
        System.out.println( map.get(p1) );//p1 ->p1的hashcode
        System.out.println( map.get(p2) );//p2 ->p2的hashcode
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        //equals:内容是否相同，不是内存地址
        //map.get(key) ：依据的是 Key的hashcode值 （内存地址）
        /*
         map是一个集合(k1-v1,k2-v2,k3-v3, . k100000-v100000(0xddd6)..,kn-vn) ，如何从中查找元素？
         hashCode（哈希算法）：
         k1-v1 ，k1的哈希码:0xab4
         k2-v2 ，k2的哈希码:0xfb3
         k3-v3 ，k1的哈希码:0xaa2
         ...
        k100000-v100000 ，k100000的哈希码:0xddd6
        map.get(k10000):获取k10000对应的VALUE之前 先计算k10000的hashcode，然后依据hahcode从元集合中获取value
         */
    }
}
