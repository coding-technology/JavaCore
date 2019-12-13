package producerandconsumer3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//库存
public class GoodsStock {


//    Goods[] goods = new Goods[100]
    //队列：生产者和消费者 共享的队列
    private BlockingQueue<Goods> queue ;
    private static int count ;//记录一共生产了多少量商品

    private Lock lock = new ReentrantLock() ;
    private Condition condition =  lock.newCondition();

    public GoodsStock(BlockingQueue<Goods> queue) {
        this.queue = queue;
    }


    public GoodsStock() {
    }
    //新增
    public  void produceGoods(){
        lock.lock();
        try {
            Goods goods = new Goods();
            boolean flag = queue.offer(goods, 3, TimeUnit.SECONDS);
            if(flag){
                count++ ;
                goods.setId(count);
                goods.setName("goods"+count);

                System.out.println("生产成功：商品编号："+goods.getId()+",名字："+goods.getName() +",库存："+ queue.size() );
                Thread.sleep((long)(Math.random()*10));

//                notifyAll();//有新商品了，通知消费者消费...
                condition.signalAll();

            }else{
                System.out.println("生产商品失败...");
            }

            if(queue.size() >= 100){
                System.out.println("库存已满，等待消费。。。");
//                wait();
                condition.await();
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    //减少
    public  void consumeGoods(){
        lock.lock();
        try {
            Goods goods = queue.poll(3, TimeUnit.SECONDS);
            if(goods != null){
                System.out.println("消费goods，编号："+goods.getId()+ ",名称："+goods.getName() +",库存："+ queue.size());
                Thread.sleep( (long)(Math.random()*10) );
//                notifyAll();
                condition.signalAll();
            }else{
                System.out.println("消费失败。。。");
            }
            if(queue.size()>0){

            }else{
                System.out.println("库存为空，等待生产。。。");
//                wait();
                condition.await();
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
