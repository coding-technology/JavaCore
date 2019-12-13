package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    //可重入的读写锁
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock() ;


    public static void main(String[] args) {
        ReadWriteLockDemo demo = new  ReadWriteLockDemo();
            new Thread(  () ->{
                //读
                demo.readMethod(Thread.currentThread());
                //写
                demo.writeMethod(Thread.currentThread());

            },"t1" ).start( );


            new Thread(   () ->{
                //读
                demo.readMethod(Thread.currentThread());
                //写
                demo.writeMethod(Thread.currentThread());

            },"t2" ).start( );
    }

    //读
    private void readMethod(Thread t ){
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 9999; i++) {
                System.out.println(t.getName() + "正在执行读操作...");
            }
            System.out.println(t.getName() + "读操作【完毕】...");
        }finally {
            rwl.readLock().unlock();
        }

    }
    //写
    private void writeMethod(Thread t){
        rwl.writeLock().lock();
        try {
            for (int i = 0; i < 9999; i++) {
                System.out.println(t.getName() + "正在执行写操作...");
            }
            System.out.println(t.getName() + "写操作【完毕】...");
        }finally {
            rwl.writeLock().unlock();
        }
    }


}
