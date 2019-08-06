package interview.day02;

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
