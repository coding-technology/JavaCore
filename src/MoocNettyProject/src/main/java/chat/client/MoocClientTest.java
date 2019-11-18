package chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MoocClientTest {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MoocNettyClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1", 10001).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {//客户端不断的通过控制台向服务端发送消息
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

class MoocNettyClientInitializer extends ChannelInitializer<SocketChannel> {
    //连接被注册后，立刻执行此方法
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //与服务端的Initializer作用相同：通过DelimiterBasedFrameDecoder将接收到的服务端
        // 消息，通过回车符（Delimiters.lineDelimiter()）进行分割。
        pipeline.addLast("DelimiterBasedFrameDecoder",
                new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()));
        pipeline.addLast("StringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("StringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        //自定义处理器
        pipeline.addLast("MoocClientHandler", new MoocClientHandler());
    }
}