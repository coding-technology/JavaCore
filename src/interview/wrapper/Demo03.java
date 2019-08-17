package interview.wrapper;

public class Demo03 {
    public static void main(String[] args) {
        Integer n1 =  100 ; //Integer = int   ;int ->Integer,  自动装箱
        Integer n2 =  100 ;
        Integer n3 =  1000 ;//Integer n3 = new Integer(1000) ;
        Integer n4 =  1000 ;//Integer n4 = new Integer(1000) ;
        System.out.println(n1 == n2);
        System.out.println(n3 == n4);

    }
}
