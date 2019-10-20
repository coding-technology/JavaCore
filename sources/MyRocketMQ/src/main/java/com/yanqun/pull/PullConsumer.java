package com.yanqun.pull;

import com.yanqun.api.CONST;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.PullStatus;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PullConsumer {

    private static HashMap<MessageQueue ,Long> offset = new HashMap<>();
    public static void main(String[] args) throws Exception {

        String groupName = "pullGroup" ;
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(groupName );
        consumer.setNamesrvAddr(CONST.NAMESERVER_ADDR);
        consumer.start();

        System.out.println("pull consumer start...");

        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("mytopic1");
        for(MessageQueue mq : mqs){
            //分别获取每个mq中的数据
            while(true){
                //pullBlockIfNotFound: 1队列  2null  3偏移量  4每次最多pull消息的个数
                PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getOffset(mq), 2);
                System.out.println(pullResult);
                //当消费pull了若个干消息之后，需要重新设置偏移量
                setOffset(mq, pullResult.getNextBeginOffset()) ;

                //有数据
                if(  pullResult.getPullStatus()== PullStatus.FOUND ){
                    List<MessageExt> messages = pullResult.getMsgFoundList();
                    for(MessageExt message :messages){
                        System.out.println(message);
                    }
                }else if(pullResult.getPullStatus()== PullStatus.NO_MATCHED_MSG ){
                    break ;
                }else{
                    System.out.println("error...");
                }

            }
        }
        consumer.shutdown();
    }

    //获取某个mq的偏移量
    private static long getOffset(MessageQueue mq){
        return  offset.get(mq) == null ? 0 : offset.get(mq);
    }
    private static void setOffset(MessageQueue mq,long mqoffset){
        offset.put(mq,mqoffset);

    }

}
