package junit4.test;

/*
 * Created by 颜群
 */

import junit4.demo.MyMath;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyMathTest {

    @Before
    public void init(){//初始化：在所有方法执行前，都会执行该方法
        System.out.println("这是一个初始化方法...");
    }



    @After
    public void destroy(){//结束：在所有方法执行后，都会执行该方法
        System.out.println("这是一个销毁方法...");
    }

    @Test
    public void addTest(){

        System.out.println("add..");
        MyMath math = new MyMath() ;
        int result = math.add(10, 20);

        Assert.assertEquals( 30  ,result  );
    }

    @Test
    public void minuteTest(){
        System.out.println("minute...");
        MyMath math = new MyMath() ;
        int result = math.minute(30, 20);

        Assert.assertEquals( 10  ,result  );
    }



}
