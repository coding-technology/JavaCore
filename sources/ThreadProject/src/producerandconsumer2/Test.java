package producerandconsumer2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Goods> queue = new LinkedBlockingQueue<>(100);

        GoodsStock goodsStock = new GoodsStock(queue) ;//商品库存

        GoodsProducer produce1 = new GoodsProducer(goodsStock) ;
        GoodsProducer produce2 = new GoodsProducer(goodsStock) ;

        GoodsConsumer consumer1 = new GoodsConsumer(goodsStock) ;
        GoodsConsumer consumer2 = new GoodsConsumer(goodsStock) ;

        //将生产者和消费者加入线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(produce1);
        executorService.execute(produce2);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        Thread.sleep(20000);
        executorService.shutdown();

    }
}
