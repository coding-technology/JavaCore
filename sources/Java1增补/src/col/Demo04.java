package col;

import java.util.*;

/*
 * Created by 颜群
 */
public class Demo04 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>() ;
        List<String> list = new ArrayList<>() ;//jdk1.7之后提供类型推断
        list.add("a") ;
//        list.add(1) ;
        String s = list.get(0);
        System.out.println(s);

        Set<String> set =new HashSet<>() ;
        set.add("aa") ;

        //Key(学号s01),Value（名次）
        Map<String,Integer> map = new HashMap<>() ;
        map.put("s01",3);
        map.put("s02",1);
        map.put("s03",2);

        Set<Map.Entry<String,Integer>>  entries = map.entrySet();
        for(Map.Entry<String,Integer> entry :entries){
            System.out.println(  entry.getKey());
            System.out.println(  entry.getValue());
        }


        System.out.println("迭代器中...");
        //取key
        Set<String> keys = map.keySet();
        //遍历Key
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            Integer value = map.get(key) ;
            System.out.println(key+"--"+value);
        }


    }
}
