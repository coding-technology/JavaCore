package producerandconsumer;

public class GoodsProducer implements Runnable {
    GoodsStock goodsStock ;

    public GoodsProducer(){

    }
    public GoodsProducer(GoodsStock goodsStock){
        this.goodsStock = goodsStock ;
    }
    @Override
    public void run() {
        //生产商品
        while(true){
            goodsStock.produceGoods();
        }
    }





}
