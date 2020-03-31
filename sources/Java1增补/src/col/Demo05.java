package col;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * Created by 颜群
 */
public class Demo05 {
    public static void main(String[] args) {
        Map<String,Person> map = new HashMap<>() ;
        Person p1 = new Person("zs",23,"xa");
        Person p2 = new Person("ls",24,"bj");
        Person p3 = new Person("ww",25,"sh");
        Person p4 = new Person("zl",26,"sz");
        Person p5 = new Person("sq",27,"tj");

        //key:名字    value:人
        map.put("zs",p1);
        map.put("ls",p2);
        map.put("ww",p3);
        map.put("zl",p4);
        map.put("sq",p5);

        System.out.println("请输入人的名字：");
        Scanner in = new Scanner(System.in) ;
        String name = in.next();

        Person person = map.get(name);
        System.out.println(person);//如果不是null,在打印时会调用toString；如果是null，则不调用。
//        System.out.println(person.toString());


//        Set<String> names = map.keySet();
//        //在names中查找name
//        for(String n :names){
//            if(n.equals( name )){
//                Person person = map.get(n);
//                System.out.println(person);
//            }
//        }
    }
}
