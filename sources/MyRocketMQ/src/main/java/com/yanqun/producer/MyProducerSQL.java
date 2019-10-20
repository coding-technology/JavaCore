package com.yanqun.producer;

import com.yanqun.RequestInfo;
import com.yanqun.RequestService;
import com.yanqun.api.CONST;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class MyProducerSQL {

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

        for(int i=0;i<9 ;i++){
            //send(消息Message，选择器，算法依据)
            Message message = new Message("requestTopic","request", requests.get(i).getRequestId()+"",requests.get(i).getRequsetDesc().getBytes());
            message.putUserProperty("myRequest",i+"" );//myRequest:  0 1 2 3 4 5 6..8
                                                                    //where myRequest>10
            producer.send(message) ;
        }
        System.out.println("发送成功");
        producer.shutdown();
    }
}
