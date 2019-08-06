package interview.day02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//多线程交替打印123123123...（两种以上方法实现）
/*
    锁：  lock.lock();/unlock() :  await()     signal()
          synchronized  :wait()  notify

 */
public class LoopPrint {

    int num = 1;
    //只有一个变量，因此只需要一把锁。但需要通知三个线程，因此需要三个“条件通知”
    Lock lock = new ReentrantLock();//一把锁
    //三个线程 的通知
    Condition condition1 = lock.newCondition();//通知线程1打印
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    /*
        num : 打印的数字1/2/3
        线程1： 判断num是否为1，如果不是，则等待；如果是，则打印，打印完后 [通知]线程2
        线程2：判断num是否为2，如果不是，则等待；如果是，则打印，打印完后 [通知]线程3
        线程3：判断num是否为3，如果不是，则等待；如果是，则打印，打印完后 [通知]线程1
        123123123...
     */
    public static void main(String[] args) {
        LoopPrint print = new LoopPrint() ;
        //打印1的线程
        new Thread( ()-> {
            while(true){
                   print.print1();
            }
        } ).start(); //lambda表达式

        //打印2的线程
        new Thread( ()-> {
            while(true){
                print.print2();
            }
        } ).start();; //lambda表达式
        //打印3的线程
        new Thread( ()-> {
            while(true){
                print.print3();
            }
        } ).start();; //lambda表达式
    }


    //打印“1”
    public void print1() {
        //a b c ->  num
        lock.lock();
        try {
            //线程1： 判断num是否为1，如果不是，则等待；如果是，则打印，打印完后 [通知]线程2
            if (num != 1) {
                condition1.await();
            }
            System.out.println(1);
            num = 2 ;
            //通知线程2
            condition2.signal(); //  signal()相当于notify()  ,await()相当于wait()
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }

    //打印“2”
    public void print2() {
        //a b c ->  num
        lock.lock();
        try {
            if (num != 2) {
                condition2.await();
            }
            System.out.println(2);
            num =3 ;
            //通知线程3
            condition3.signal(); //  signal()相当于notify()  ,await()相当于wait()
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }

    public void print3() {
        //a b c ->  num
        lock.lock();
        try {
            if (num != 3) {
                condition3.await();
            }
            System.out.println(3);
            num = 1;
            //通知线程1
            condition1.signal(); //  signal()相当于notify()  ,await()相当于wait()
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
}

