//模拟vip学员的线程
class VIPStudent implements  Runnable {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(i+":vip学员在练车。。。");
            //模拟练车时间
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//模拟普通学员的线程
class NormalStudent implements  Runnable {
    private Thread vip ;//RUnnable ->Thread
    public NormalStudent(){}
    public NormalStudent(Thread vip){
        this.vip = vip ;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            //判断是否是  最后一个普通学员
            if(i==19){
                //让给vip先执行 （在实现时：需要用 Join（）强制让vip插队，保证概率问题）
                try {
                    vip.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println(i+"普通学员在练车。。。");
            //模拟练车时间
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

public class ThreadDemo04{
    public static void main(String[] args) {
        Thread vip = new Thread(new VIPStudent());//装饰模式
        vip.setPriority(Thread.MAX_PRIORITY);//10

        Thread normal = new Thread( new NormalStudent(vip ));
        normal.setPriority(Thread.NORM_PRIORITY);//5

        vip.start();
        normal.start();
    }
}
