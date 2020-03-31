package ex;

/*
 * Created by 颜群
 */
public class Demo02 {

    public static void test01(){

        Object obj = null   ;
        try {
//            System.out.println("111");
            obj.equals("");//可能产生异常的代码
//            System.out.println("222");
//            System.out.println("正常时，关闭资源。。。");
        }catch (NullPointerException e){//捕获特定类型的异常
//            System.out.println("异常。。。");
//            System.out.println("发生了空指针异常。。");
//            System.out.println("异常时，关闭资源。。。");
        }finally{
            System.out.println("无论正常，还是异常，始终都会执行的代码。。。");
        }
    }

    public static int test02(){
        try{
            Object obj = null   ;
            System.exit(1);//关闭jvm
            obj.equals("") ;
            return 1 ;
        }catch (NullPointerException e){
                return 0 ;
        }finally{
            System.out.println("finally...");
        }
    }

    public static void test03(){
        try{
            Class.forName("xxx") ;//程序会加载指定的类
            Object obj = null   ;
            obj.equals("") ;//空指针

            int[] nums = new int[3] ;
           nums[3] = 3 ;//数组越界异常

        }catch (NullPointerException e){//捕获空指针
            System.out.println("空指针异常..");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("数组越界异常...");
        }catch (Exception e) {//还有一些 根本临时想不到的异常
            System.out.println("其他异常。。。");
            System.out.println(e);
            e.printStackTrace();//打印异常的堆栈信息
            System.out.println(  "getMessage:" +  e.getMessage());
        }
    }

    public static void test04() throws NullPointerException,ClassNotFoundException{//抛出异常，抛出给上级（方法调用处）
            Object obj = null   ;
            obj.equals("") ;//空指针
            Class.forName("xxx") ;
    }

    public static void main(String[] args) throws Exception{//继续往上抛出异常(JVM)
            test04();//异常
    }
}
