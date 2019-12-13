package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore sem = new Semaphore(3) ;//同一时间 最多有3个线程可以并发运行
        //假设有10个线程
        for( int i=0;i<10;i++){
            final int tId = i ;
            executor.execute(
                         () ->{
                             try{
                                  sem.acquire();//获取许可（同一时间 只有3个许可）
                                 System.out.println("线程" + tId +"获取到了许可，正在执行");
                                Thread.sleep((long)(5000*Math.random()));
                                sem.release();//释放许可
                             }catch (InterruptedException e){

                             }catch(Exception e){
                                 e.printStackTrace();
                             }
                         }
            );//给线程中 加入一个新线程
        }
//            executor.shutdown();
    }
}
