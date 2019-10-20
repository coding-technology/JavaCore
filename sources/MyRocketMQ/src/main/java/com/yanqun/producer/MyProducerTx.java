package com.yanqun.producer;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

public class MyProducerTx {

    public static void main(String[] args) {
        //创建生产者(支持事务)
        TransactionMQProducer producer = new TransactionMQProducer("myProducer");
        producer.setNamesrvAddr(CONST.NAMESERVER_ADDR);
        producer.setTransactionListener(new TransactionListener() {
            //将MQserver的响应状态 写入到本地事务中
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                //将消息的发送状态（commit、rollback、未知），写入本地事务
                if(message.getTags().equals("msg0")){//commit
                    return LocalTransactionState.COMMIT_MESSAGE ;
                }else if(message.getTags().equals("msg1")) {//rollback
                    return LocalTransactionState.ROLLBACK_MESSAGE ;
                }else{//未知 msg2
                    return LocalTransactionState.UNKNOW ;
                }

            }
            //回查事务时的操作(本次：msg2之前设置的是unkow，因此会回查。本地认为 在回查时 让其正常提交)
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("事务的回查机制："+messageExt.getTags()  );

                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        try {
            producer.start(); //启动生产者
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        //发送消息
        for(int i=0;i<3;i++) {
            Message msg = new Message("txTopic", "msg"+i, ("msg"+i+"Content").getBytes());
            try {
                TransactionSendResult result = producer.sendMessageInTransaction(msg, null);//null表示 将全部的消息 都纳入到本地事务中
                System.out.println("发送："+result);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }




//        producer.shutdown();


    }
}
