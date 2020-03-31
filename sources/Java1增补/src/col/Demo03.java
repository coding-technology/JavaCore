package col;

import java.util.*;

/*
 * Created by 颜群
 */
public class Demo03 {
    public static void main(String[] args) {

        List list = new ArrayList() ;
        list.add("aa") ;
        list.add("bb") ;
        list.add("cc") ;
        //普通for
        for(int i=0;i<list.size();i++){
            System.out.println(   list.get(i));
        }
        System.out.println("增强for");
        //增强for
        for(Object o :list){
            System.out.println(o);

        }

        Set set = new HashSet() ;
        set.add("A");
        set.add("b");
        set.add(1);
        set.add("D");
        //普通for，不适用于 “无序”的集合
//        for(int i=0;i<set.size();i++){
//            System.out.println(      set.get );
//        }

        for(Object e :   set){
            System.out.println(e);
        }
        System.out.println("迭代器");

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Object o = iterator.next();
            System.out.println(o);
        }


        System.out.println("遍历map。。。");
        Map map = new HashMap() ;
        map.put("s01","Zs") ;
        map.put("s02","ls") ;
        map.put("s03","ww") ;
        Set set1 = map.keySet();//将双值集合 降成单值集合
        for(Object o :set1){
            System.out.println("key:" +o);
            Object v = map.get(o);//map可以根据key，获取value; map.get(key) ->value
            System.out.println("value:"+v);

        }

//        Collection values1 = map.values();
//        Iterator iterator1 = values1.iterator();
//        while(iterator1.hasNext()){
//            System.out.println("value: "+iterator1.next() );
//        }

        System.out.println("通过entry遍历..");
        Set entries = map.entrySet();

        for(Object e :entries){
            Map.Entry et =  (Map.Entry)e ;
           Object k =  et.getKey();
            Object v = et.getValue();
            System.out.println("k-"+k);
            System.out.println("v-"+v);
        }

    }
}
