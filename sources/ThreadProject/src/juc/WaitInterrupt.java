package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends  Thread{
    WaitInterrupt waitInterrupt ;
    public MyThread(WaitInterrupt waitInterrupt){
        this.waitInterrupt = waitInterrupt ;
    }


    @Override
    public void run() {
        try {
            waitInterrupt.myLock(Thread.currentThread());//当前线程休眠3秒
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}

public class WaitInterrupt {
    private Lock lock = new ReentrantLock() ;

    public static void main(String[] args) throws  Exception {

        WaitInterrupt inter = new WaitInterrupt();
        MyThread t1 = new MyThread(inter);
        t1.setName("t1");
        MyThread t2 = new MyThread(inter);
        t2.setName("t2");

        t1.start();//休眠3秒
        t2.start();//休眠3秒

        Thread.sleep(1000);//main线程休眠1秒
        t2.interrupt();//中断t2
    }


    public void myLock(Thread thread   )throws InterruptedException {
        try {

            lock.lockInterruptibly();//加了一把可被中断的锁

            System.out.println( thread.getName() + "加锁");
            Thread.sleep(3000);
        }
        finally
        {
            lock.unlock();
            System.out.println( thread.getName() + "解锁");
        }
    }

}
