package producerandconsumer;

public class Test {
    public static void main(String[] args) {
        GoodsStock goodsStock = new GoodsStock() ;//商品库存

//        GoodsProducer p1 = new GoodsProducer() ;
//        GoodsProducer p2 = new GoodsProducer() ;
        //注意多个生产者线程 共享同一份库存
        GoodsProducer p = new GoodsProducer(goodsStock) ;

        Thread tp1 = new Thread(p) ;
        Thread tp2 = new Thread(p) ;

//        GoodsConsumer c1 = new GoodsConsumer() ;
//        GoodsConsumer c2 = new GoodsConsumer() ;
        GoodsConsumer c = new GoodsConsumer(goodsStock) ;
        Thread tc1 = new Thread(c) ;
        Thread tc2 = new Thread(c) ;


        tp1.start();
        tp2.start();

        tc1.start();
        tc2.start();
    }
}
