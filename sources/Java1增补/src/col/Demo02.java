package col;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/*
 * Created by 颜群
 */
public class Demo02 {
    public static void main(String[] args) {
        HashMap map = new HashMap() ;
        map.put( "s01","张三")  ;//key:学号 ，value:姓名
        map.put( "s02","李四")  ;
        map.put( "s03","王五")  ;
        map.put( "s04","王五")  ;

        System.out.println(map);

        Object v = map.get("s01");//根据key，找到value

        System.out.println( v);

        System.out.println(map.size());
        //判断是否 包含了指定的Key
        System.out.println(    map.containsKey("s01")              );
        //判断是否 包含了指定的value
        System.out.println(    map.containsValue("王五")              );

        //将Map转为单值集合
        //转为只包含了key的单值集合

        Set set = map.keySet();//为什么是set，不是list?因为map中，key是唯一的
        System.out.println(set);

        //转为只包含了value的单值集合
        Collection values = map.values();
        System.out.println(values);

        Object a = map.remove("s01");//删除的返回值，就是删除的 value对象
        System.out.println(map);
        System.out.println(a);

    }
}
