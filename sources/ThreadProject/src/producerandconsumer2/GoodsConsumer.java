package producerandconsumer2;

public class GoodsConsumer implements Runnable {
    GoodsStock goodsStock ;

    public GoodsConsumer(){
    }
    public GoodsConsumer(GoodsStock goodsStock){
        this.goodsStock = goodsStock ;
    }

    @Override
    public void run() {
        while(true){
            goodsStock.consumeGoods();
        }
    }
}
