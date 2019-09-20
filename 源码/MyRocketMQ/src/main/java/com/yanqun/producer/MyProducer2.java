package com.yanqun.producer;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class MyProducer2 {

    public static void main(String[] args) {
        //创建生产者
        DefaultMQProducer producer = new DefaultMQProducer("myProducer");
        producer.setNamesrvAddr(CONST.NAMESERVER_ADDR);
        try {
            producer.start(); //启动生产者
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //生产消息（生产主题和数据）
        for(int i=0;i<10;i++){
            /*
                    topic:主题（一级目录）
                    tags:标签（二级目录）
                    keys + body :  以key-value的形式 存放内容
             */
            Message message = new Message("mytopic1","mytag12", "key2"+i,("mymq"+i) .getBytes());
            //发送延迟消息 的延迟级别：从1开始数
//            message.setDelayTimeLevel(3);
            //生产者发送消息
            try {
                //1发送同步消息
//                SendResult result = producer.send(message);
//                System.out.println("发送成功："+ result);
                //2发送异步消息
                /*
                发送异步消息之后：有2个线程：a.Main线程，发送完毕 立刻执行以后的程序 ；
                                b.处理消息的线程 ，并在处理完毕后 触发回调函数onSuccess（）\onException()
*/
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送成功："+sendResult);
                    }
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("发送失败，异常："+ throwable.getMessage());
                    }
                });

//                producer.sendOneway(message);//只发送，不接收返回值，不可靠的消息 ；不重要的数据，日志
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(3000);//模拟业务逻辑  ，获取用户信息、获取登录日期。。。。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.shutdown();


    }
}
