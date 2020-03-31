package col;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Created by 颜群
 */
public class Demo06 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("w") ;
        list.add("a") ;
        list.add("b") ;
        list.add("x") ;
        list.add("a") ;
        list.add("i") ;

        Collections.sort(list);
        System.out.println(list);

        System.out.println(   Collections.max(list) );
        System.out.println(   Collections.min(list) );

        //二分查法(使用前，必须保证集合元素是 自然有序的)
        System.out.println(  Collections.binarySearch( list,"i")     );
        //混洗，洗牌：打乱已有顺序
        Collections.shuffle(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);

        Collections.reverse(  list);
        System.out.println(list);

        Collections.swap(list,  2,3);
        System.out.println(list);

        Collections.replaceAll(list, "a","A") ;
        System.out.println(list);

        Collections.fill(list,"H");
        System.out.println(list);
    }
}
