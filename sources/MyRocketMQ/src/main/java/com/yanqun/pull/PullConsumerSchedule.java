package com.yanqun.pull;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PullConsumerSchedule {

    public static void main(String[] args) throws Exception {

        String groupName = "pullGroup" ;

        MQPullConsumerScheduleService consumerService = new MQPullConsumerScheduleService("pullGroup" );
        consumerService.getDefaultMQPullConsumer().setNamesrvAddr(CONST.NAMESERVER_ADDR);
        consumerService.setMessageModel(MessageModel.CLUSTERING);
//        consumerService.setNamesrvAddr(CONST.NAMESERVER_ADDR);
//        consumerService.start();

        System.out.println("pull consumerService start...");

        consumerService.registerPullTaskCallback("mytopic1" ,new PullTaskCallback(){//匿名内部类
            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext pullTaskContext) {
                System.out.println("----" + mq.getQueueId());//默认内部包含了4个队列
                MQPullConsumer consumer = pullTaskContext.getPullConsumer();
                try {
                    long offset = consumer.fetchConsumeOffset(mq, false);
                    System.out.println(offset);
                    PullResult pullResult = consumer.pull(mq, "*", offset, 2);
                    System.out.println(pullResult);
                    //有数据
                    if(  pullResult.getPullStatus()== PullStatus.FOUND ){
                        List<MessageExt> messages = pullResult.getMsgFoundList();
                        for(MessageExt message :messages){
                            System.out.println(message);
                        }
                    }else if(pullResult.getPullStatus()== PullStatus.NO_MATCHED_MSG ){
                        System.out.println("NO_MATCHED_MSG");
                    }else{
                        System.out.println("error...");
                    }
                    consumer.updateConsumeOffset(mq,pullResult.getNextBeginOffset());
                    //设置每次拉取的间隔时间
                    pullTaskContext.setPullNextDelayTimeMillis( 3000 );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } );
//        consumerService.shutdown();
        consumerService.start();

    }

}
