package producerandconsumer;
//库存
public class GoodsStock {

    int goodsNum  ;

    //新增
    public synchronized void produceGoods(){
        try {
            if (goodsNum < 100) {
                goodsNum++;
                //唤醒消费者
                notifyAll();
                Thread.sleep((long) (Math.random() * 10));
                System.out.println("生产商品，商品目前的数量：" + goodsNum);
            } else {
                wait();//如果超过100，则停止生产
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    //减少
    public synchronized void consumeGoods(){
    try {
        if (goodsNum > 0) {
            goodsNum--;
            //唤醒产生者
            notifyAll();

            Thread.sleep((long) (Math.random() * 10));
            System.out.println("消费商品，商品目前的数量：" + goodsNum);
        }else{
            wait();//如果没有商品，则暂停消费。
        }



    }catch (InterruptedException e){
        e.printStackTrace();
    }

    }
}
