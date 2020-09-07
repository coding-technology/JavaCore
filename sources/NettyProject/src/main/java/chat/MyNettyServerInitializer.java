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

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //DelimiterBasedFrameDecoder(maxFrameLength, delimiters)：分隔符处理器；将接收到的客户端消息，通过回车符（Delimiters.lineDelimiter()）进行分割。
        pipeline.addLast("DelimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()));
        pipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8)) ;
        pipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8)) ;
        //自定义处理器
        pipeline.addLast("MyNettyServerHandler", new MyNettyServerHandler());
    }
}
