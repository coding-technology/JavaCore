package ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;



//软引用对象
class SoftObject{}
public class SoftReferenceDemo {
    public static void main(String[] args) throws Exception {
        //softRef  -->SoftObject  设计模式中的：装饰模式
        SoftReference<SoftObject> softRef = new SoftReference<>(new SoftObject() );
        List<byte[]> list = new ArrayList<>();

        //开启一个线程，监听 是否有软引用已经被回收
        new Thread(  ()->{
            while(true) {
                if (softRef.get() == null) //软引用对象
                {
                    System.out.println("软引用对象已被回收..");
                    System.exit(0);
                }
            }
        }  ,"线程A" ) .start();     //lambda


        //不断的往集合中 存放数据，模拟内存不足
        while(true){
//          Thread.sleep(10);
            if(softRef.get() != null)
                list.add(new byte[1024*1024]) ;//每次向list中增加1m内容
        }


    }
}