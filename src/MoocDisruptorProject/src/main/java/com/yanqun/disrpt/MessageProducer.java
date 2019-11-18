package com.yanqun.disrpt;


import com.lmax.disruptor.RingBuffer;


public class MessageProducer {
    //定义生产者和消费者交换数据的媒介：环形缓冲区
    private RingBuffer<MessageEvent> buffer;

    public void setBuffer(RingBuffer<MessageEvent> buffer) {
        this.buffer = buffer;
    }

    //生产数据
    public void produce(String data) {
        long seq = buffer.next();
        buffer.get(seq).setMessage(data);
        buffer.publish(seq);
    }
}
