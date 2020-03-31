package ex;

/*
 * Created by 颜群
 */
//public class MyException  extends  Throwable{
//public class MyException  extends  NullPointerException{
public class MyException  extends  Exception{//推荐
    public MyException(String message){//异常信息
        super(message);
    }
}
