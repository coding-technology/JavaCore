package col;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Created by 颜群
 */
//内部比较器
public class Demo08 {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>() ;
        Person p1= new Person(10,"zs",23,"xa") ;
        Person p2= new Person(2,"ls",24,"xa") ;
        Person p3= new Person(2,"zs",25,"xa") ;
        persons.add(p1) ;
        persons.add(p2) ;
        persons.add(p3) ;

        Collections.sort( persons );

        System.out.println(persons);
        System.out.println("-------");
        Student s1 = new Student(10,"zs",23,"xa") ;
        Student s2 = new Student(2,"zs",26,"xa") ;
        Student s3 = new Student(3,"zs",24,"xa") ;
        List<Student> students = new ArrayList<>() ;
        students.add(s1);
        students.add(s2);
        students.add(s3);

        Collections.sort(  students,new MyComparatorWithId() );//给students使用了MyComparatorWithId比较器
        System.out.println(students);


//        List<double> list = new ArrayList<>() ;//泛型 只能是引用类型，不能是基本类型
        List<Double> list = new ArrayList<>() ;

    }
}
