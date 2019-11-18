public class ThreadDemo03 implements Runnable {

    //Th1线程执行的方法
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(   Thread.currentThread().getName()+":"+i  );
            if(i == 3){
                Thread.yield();
                System.out.println("礼让....");
            }
        }
    }

    //线程th1:执行“ T1:数字”
    //线程main：执行“ main:数字”
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo03 t1 = new ThreadDemo03();
        Thread th1 = new Thread(t1,"T1") ;
        th1.start();


        ThreadDemo03 t2 = new ThreadDemo03();
        Thread th2 = new Thread(t2,"T2") ;
        th2.start();

        //main线程
//        for(int i=0;i<10;i++){
//            System.out.println(   Thread.currentThread().getName()+":"+i  );
//            if(i==3){
////                  th1.join(); //main线程正在执行时（执行到i=3时）， th1强制抢夺执行
//                    Thread.yield(); //主动礼让给其他线程
//            }
//        }





    }
}
