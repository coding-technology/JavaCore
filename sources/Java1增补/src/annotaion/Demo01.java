package annotaion;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by 颜群
 */
class Father {
    public void eat() {
        System.out.println("father eat...");
    }
}

class Son extends Father{
    @Override
    public void eat() {
        System.out.println("son eat...");
    }

    @Deprecated
    public void test(){
    }
}


//压制警告 （虽然可以使用SuppressWarnings压制警告，但不建议使用。）
//忽略对泛型等的检查操作
@SuppressWarnings("all")
public class Demo01 {
    public static void main(String[] args) {
        int a = 1 ;

        Father f = new Son();
        f.eat();

        new Thread().stop();
        new Son().test();
        List list = new ArrayList();
    }
}



