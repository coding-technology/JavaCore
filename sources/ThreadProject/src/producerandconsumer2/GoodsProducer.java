package producerandconsumer2;

public class GoodsProducer implements Runnable {

    GoodsStock stock ;


    public GoodsProducer(){

    }

    public GoodsProducer(GoodsStock stock){
        this.stock = stock ;
    }



    @Override
    public void run() {
        //生产商品
        while(true){
            stock.produceGoods();//生产
        }
    }





}
