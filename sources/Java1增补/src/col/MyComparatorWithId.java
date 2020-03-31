package col;

import java.util.Comparator;

/*
 * Created by 颜群
 */
public class MyComparatorWithId implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Student s1 =  (Student)o1 ;
        Student s2 =  (Student)o2 ;
        return   s2.getId() -  s1.getId()  ;
    }
}
