public class ThreadDemo02 implements Runnable {

    //线程执行的方法
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(   Thread.currentThread().getName()+":"+i  );
        }
    }

    //Runnable方式仍然是借用Thread来实现的多线程
    public static void main(String[] args) {
        ThreadDemo02 t1 = new ThreadDemo02();
        ThreadDemo02 t2 = new ThreadDemo02();


        //启动线程需要使用start()方法，但是Runnable没有提供start（）,如何解决？
        //Thread有start()   Runnable没有start()
        //Runnable ->Thread
        //Thread(Runable )
        Thread th1 = new Thread(  t1,"T1-");
        Thread th2 = new Thread(  t2);
//        th1.setName("T1");

        th1.setPriority(Thread.MAX_PRIORITY);
        th2.setPriority(Thread.MIN_PRIORITY);

        th2.setName("T2");
        th1.start();

        th2.start();


    }
}
