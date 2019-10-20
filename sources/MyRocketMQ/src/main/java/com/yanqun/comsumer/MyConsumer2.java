package com.yanqun.comsumer;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MyConsumer2 {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr(CONST.NAMESERVER_ADDR);
//        consumer.setMessageModel(MessageModel.CLUSTERING);

        consumer.setConsumeFromWhere( ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET );
        try {
            consumer.subscribe("mytopic1","mytag11||mytag12");
//            consumer.setConsumeMessageBatchMaxSize(100 ); 消费者一次性消费多少 消息数量。可以防止 消费者因为自身性能不足 造成的宕机的情况，按需从MQ中拉取数据
//            consumer.setConsumeThreadMax(4); 设置消费者 并发的线程数量
//            consumer.setConsumeThreadMin(2);

//            consumer.setConsumeTimeout();//设置消费的超时时间
              //如果消费失败（超时等），可以设置重试次数,并且同时修改代码（补偿处理）
            consumer.setMaxReconsumeTimes(3);

            //设置监听器：当生产者生产数据时，将数据推送给 消费者
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    for (MessageExt messageExt : list) {
//                        System.out.println(messageExt.getMsgId());
                        System.out.println("消费："+messageExt.getTopic()+ "，\t"+messageExt.getTags()+ "，\t"+messageExt.getKeys()+ "，\t"+messageExt.getBody()  );

                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                });

            consumer.start();
            System.out.println("consumer2..start...");

        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }
}
