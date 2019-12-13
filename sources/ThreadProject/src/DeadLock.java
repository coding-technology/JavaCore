
//本题出现死锁的情况 是一种假设：如果符合预期（A给a加了锁，在等待b;B给b加了锁，在等待a），则会出现死锁；否则，不会。
class AThread implements  Runnable{
    @Override
    public void run() {
        synchronized (DeadLock.resource_a) {
            System.out.println("A线程：已经给resource-a加了锁");
            try {
                Thread.sleep((long)(Math.random()*3000) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (DeadLock.resource_b){//尝试给B加锁
                System.out.println("A线程：等待给resource-b加锁...");
            }
        }
    }
}
class BThread implements  Runnable{
    @Override
    public void run() {
        synchronized (DeadLock.resource_b) {
            System.out.println("B线程：已经给resource-b加了锁");
            try {
                Thread.sleep((long)(Math.random()*3000) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DeadLock.resource_a){
                System.out.println("B线程：等待给resource-a加锁...");
            }
        }
    }
}



public class DeadLock {
    static String resource_a = "resource-a" ;
    static String resource_b = "resource-b" ;

    public static void main(String[] args) {
        Thread A = new Thread(new AThread());
        Thread B = new Thread(new BThread());

        A.start();
        B.start();
    }

}
