package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo extends  Thread {
  private static Lock lock =   new ReentrantLock();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        boolean flag = false ;
        try {
           flag = lock.tryLock(5, TimeUnit.MICROSECONDS);
            System.out.println("尝试加锁结果："+flag);
            if(flag){
                System.out.println(threadName+"加锁成功...");
                Thread.sleep(20);
            }else{
                System.out.println(threadName+"加锁失败。。。");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(flag) {
                System.out.println(threadName + "解锁...");
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        TryLockDemo t1 = new TryLockDemo();
        TryLockDemo t2 = new TryLockDemo();
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();

    }
}
