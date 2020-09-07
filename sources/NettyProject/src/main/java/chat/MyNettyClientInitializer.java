package chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyNettyClientInitializer  extends ChannelInitializer<SocketChannel> {
    //连接被注册后，立刻执行此方法
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //与服务端的Initializer作用相同：通过DelimiterBasedFrameDecoder将接收到的服务端消息，通过回车符（Delimiters.lineDelimiter()）进行分割。
        pipeline.addLast("DelimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()));
        pipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8)) ;
        pipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8)) ;
        //自定义处理器
        pipeline.addLast("MyNettyClientHandler", new MyNettyClientHandler());
    }
}
