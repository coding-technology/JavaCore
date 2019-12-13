import java.nio.channels.SeekableByteChannel;

public class TicketsDemo implements  Runnable {
    private int tickets = 10000 ;
    //买火车票
    @Override
    public void run() {
        while(true){
            //买火车票
            sell();
        }
    }

//    public  synchronized  void sell(){
//        if(tickets > 0){
//            tickets -- ;
//            System.out.println(Thread.currentThread().getName()+"-正在售卖,剩余票数：" + tickets );
//        }
//    }

    public  void sell(){
        synchronized (this) {
            if (tickets > 0) {
                tickets--;
                System.out.println(Thread.currentThread().getName() + "-正在售卖,剩余票数：" + tickets);
            }
        }
    }

    public static void main(String[] args) {
        TicketsDemo ticket = new TicketsDemo() ;//Rnnable

        Thread a = new Thread(ticket,"a");
        Thread b = new Thread(ticket,"b");



        a.start();
        b.start();
    }
}
