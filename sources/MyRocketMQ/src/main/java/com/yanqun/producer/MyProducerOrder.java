package com.yanqun.producer;

import com.yanqun.RequestInfo;
import com.yanqun.RequestService;
import com.yanqun.api.CONST;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class MyProducerOrder {

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //创建生产者
        DefaultMQProducer producer = new DefaultMQProducer("myProducer");
        producer.setNamesrvAddr(CONST.NAMESERVER_ADDR);
        try {
            producer.start(); //启动生产者
        } catch (MQClientException e) {
            e.printStackTrace();
        }
     //准备消息
        List<RequestInfo> requests = RequestService.getRequests();

        for(RequestInfo requestInfo :requests){
            //send(消息Message，选择器，算法依据)
            Message message = new Message("requestTopic","request", requestInfo.getRequestId()+"",requestInfo.getRequsetDesc().getBytes());
            //send(Message msg, MessageQueueSelector selector, Object arg
            producer.send(message ,
                    //List<MessageQueue> mqs, Message msg, Object id
                    (mqs,msg,id)->{
                           long rId = (long) id ;
                           long index =  rId % mqs.size() ;
                          return mqs.get((int)index);
                    },
                    requestInfo.getRequestId() ) ;
            System.out.println(message);
        }
        System.out.println("发送成功");
        producer.shutdown();
    }
}
