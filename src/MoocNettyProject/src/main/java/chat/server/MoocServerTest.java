package chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MoocServerTest {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap：服务端启动时的初始化操作
            //将bossGroup和workerGroup注册到服务端的Channel上，并注册一个服务端的初始化器NettyServerInitializer（该初始化器中的initChannel()方法，会在连接被注册后立刻执行）；最后将端口号绑定到8888
            new ServerBootstrap().group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MoocNettyServerInitializer()).bind(10001).sync().channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class MoocNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        //DelimiterBasedFrameDecoder(maxFrameLength, delimiters)：分隔符处理器；将接收到的客户端消息，通过回车符（Delimiters.lineDelimiter()）进行分割。
        pipeline.addLast("DelimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()));
        pipeline.addLast("StringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("StringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        //自定义处理器
        pipeline.addLast("MoocServerHandler", new MoocServerHandler());
    }
}