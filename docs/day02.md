# 1.简述nginx    
Nginx是一款轻量级的web服务器（反向代理服务器，负载均衡服务器，动静分离服务器）。  
（1）反向代理  
a.正向代理(vpn)：为客户端做代理，相当于客户端中介。代理客户端去访问服务器。  
 （客户端->正向代理）->服务器，例如，（用户->VPN）->google，VPN就是用户的正向代理。  

b.反向代理(nginx/apache):为服务器做代理，相当于服务端中介，代理服务器接受客户端请求。    
 客户端-> (反向代理->服务端) ,就如，客户端  -> (nginx->  tomcat)，nginx就是tomcat的反向代理服务器。    

假设：买房的人是客户端，卖方的人是服务端：  
zs想买房,zs找了一个朋友ls帮忙去买，那么ls就是zs的正向代理服务区），(zs->ls)->卖房方   
zl有一套房打算卖掉，zl找了一个朋友sq去卖，那么sq就是反向代理服务器，买房方->（sq->zl）  

（2）负载均衡：分流客户端请求，均衡集群服务端压力。  Nginx支持的weight轮询（默认）、ip_hash、fair、url_hash四种负载均衡调度算法。    
（3）动静分离：分离静态请求和动态请求，将动态请求发送给web服务器，并给静态请求做缓存（或cdn加速）。  
客户端请求 -> 静态请求/动态请求  
静态请求：静态缓存/cdn加速  
动态请求: tomcat（web服务器）  

Nginx优点：  
（1）高并发连接： 官方测试Nginx能够支撑5万并发连接，实际生产环境中可以支撑2~4万并发连接数。    
（2）Nginx为开源软件，成本低廉     
（3）稳定性高：用于反向代理时，非常稳定  
（4）支持热部署能够在不间断服务的情况下，进行维护。  

# 2.两种以上方法实现“多线程交替打印123123123...”    
建立三个线程，第一个线程打印1、第二个线程打印2、第三个线程打印3；要求三个线程交替打印，123123123123……  
**方法一**  

```  
package interview;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
   //多线程交替打印123123123...（两种以上方法实现）
    锁方式一：  lock.lock();/unlock() :  await()     signal()
    锁方式二：  synchronized  :wait()  notify
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


```
**方法二**
```
package interview;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    //1  2   3
    //三个信号量
    Semaphore sem1 = new Semaphore(1);
    Semaphore sem2 = new Semaphore(0);
    Semaphore sem3 = new Semaphore(0);

    public static void main(String[] args) {
        SemaphoreDemo sem = new SemaphoreDemo();

        new Thread( ()-> sem.print1() ).start();
        new Thread( ()-> sem.print2() ).start();
        new Thread( ()-> sem.print3() ).start();

    }


    public void print1(){
        print("1",sem1,sem2 ) ;
    }

    public void print2(){
        print("2",sem2,sem3 ) ;
    }

    public void print3(){
        print("3",sem3,sem1 ) ;
    }
    /*
            value:当前值 1 2 3？
            current:当前谁用于许可证
            next：下一个谁那许可证
     */
    private void print(String value , Semaphore current,Semaphore next ){
        while(true){
            try {
                current.acquire();//当前信号量 获取许可证
                System.out.println( Thread.currentThread().getName()+"***"+  value);
                Thread.sleep(1000);
                next.release();//将许可证传递给下一个

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

```

# 3.基础  
一个".java"源文件中是否可以包括多个类？ 
可以有多个类，但只能有一个public的类，并且public的类名必须与文件名相一致。此外，类中还可以包含内部类。