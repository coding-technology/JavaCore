package interview.wrapper;

public class Demo02 {
    public static void main(String[] args) {
        Integer n1 = new Integer(10) ;//String str = new String("aa");

        Integer  n2 =  10 ; //String str2 = "aa" ;
        int n3 = 10 ;
//        System.out.println(n1 == n2);
//        System.out.println(n1.equals( n2));
        System.out.println(n1 == n2 );
        System.out.println(n1 == n3 );//整数默认int类型，  Integer = int   ->    int = int
//        (n1+n3).
        // (Integer + int)  -> int类型
    }
}
