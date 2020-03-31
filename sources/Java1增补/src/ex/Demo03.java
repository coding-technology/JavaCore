package ex;

/*
 * Created by 颜群
 */
public class Demo03 {

    public static void main(String[] args)   {
        int age = 188 ;
        //约定，年龄不能大于120
        if(age<= 0 || age>120){
            try {
                //手工声明一个异常
                throw new MyException("年龄不能大于120");
            }catch ( MyException e ){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
