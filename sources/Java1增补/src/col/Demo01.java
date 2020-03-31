package col;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/*
 * Created by 颜群
 */
public class Demo01 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("中国") ;
        list.add("美国") ;
        list.add("俄罗斯") ;
        System.out.println(list);

        list.remove("中国");


        LinkedList list2 = new LinkedList();
        list2.add("hello") ;
        list2.add("world") ;
        //LinkedList独有的方法
        list2.addLast("wh");
        list2.addFirst("hw");

//        list2.remove(1) ;
        list2.remove("world");

        System.out.println(list2);

        HashSet set = new HashSet() ;
        set.add("aa") ;
        set.add("bb") ;
        set.add("cc") ;
        set.remove("bb") ;//因为 set是无序的，因此无法根据“下标”删。只能根据内容删
        System.out.println(set);

        set.remove("bb") ;
    }
}
