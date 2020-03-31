package lambda;

/*
 * Created by 颜群
 */
public class Demo01 {

    public static void test01(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run....");
            }
        }).start();
    }

    public static void test02(){
        //           ()  -> { System.out.println("run....");  }

        new Thread(
                ()  ->  System.out.println("run....")
        ).start(); ;
    }

    public static void main(String[] args) {
//        test01() ;
        test02() ;

    }
}
