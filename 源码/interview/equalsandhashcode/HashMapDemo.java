package interview.equalsandhashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
    public static void main(String[] args) {
        Map map = new HashMap();
        System.out.println( map.put("k1","v1"));;//增加
        System.out.println( map.put("k1","v1"));;//覆盖掉上一次的value值
//        System.out.println(map);


        Set set = new HashSet();
        System.out.println( set.add("key")); //增true
        System.out.println( set.add("key")); ;//false
    }
}
