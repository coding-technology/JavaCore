package heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/*
 * Created by 颜群
 *
 */
public class MyNettyServerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {


        ChannelPipeline pipeline = ch.pipeline();
        //netty自带的处理器
        //三个参数：readerIdleTimeSeconds、writerIdleTimeSeconds、allIdleTimeSeconds
        pipeline.addLast( "",  new IdleStateHandler(30,5,7));
        //自定义处理器
        pipeline.addLast("MyNettyServerHandler",new MyNettyServerHandler()) ;


    }
}
