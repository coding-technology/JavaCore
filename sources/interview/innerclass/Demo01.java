package interview.innerclass;

import java.util.Date;

public class Demo01 {

    //String str = “zs”
    public static boolean empty(String str){
        return str.isEmpty() ;
    }

    public static long empty2(Date date){//Date date = new Date的子类() ;
        return date.getTime();   //2020 - 09-19 12:22:55   -  1970.-1-1 0:0:0.000
    }

    public static void main(String[] args) {
//        System.out.println(          empty(     new String()  )         ) ;
//        System.out.println(          empty(     new String(){}  )         ) ;
        System.out.println(         empty2(new Date())    );
        System.out.println(         empty2( new Date(){
            @Override
            public long getTime() {
                return 1000000;
            }
        }  )    );//内部类
        // empty2( new Date(){}  )  ：其中 方法的参数是  Date的一个子类的 对象
    }

}
