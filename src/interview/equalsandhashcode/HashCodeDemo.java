package interview.equalsandhashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashCodeDemo {
    public static void main(String[] args) {
        Set set = new HashSet() ;//set:无序，唯一
        System.out.println(  set.add(new String("hello"))  );
        System.out.println( set.add( new String("world") ) );
        System.out.println( set.add( new String("hello") ) );//hash: 会根据hashcode 来寻找位置

        System.out.println("-------");
        System.out.println(  set.add(  new Student("zs")  ) );
        System.out.println(  set.add(  new Student("ls")  ) );
        System.out.println(  set.add(  new Student("zs")  ) );



    }
}
