package chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyServerTest {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap：服务端启动时的初始化操作
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //将bossGroup和workerGroup注册到服务端的Channel上，并注册一个服务端的初始化器NettyServerInitializer（该初始化器中的initChannel()方法，会在连接被注册后立刻执行）；最后将端口号绑定到8888
            ChannelFuture channelFuture =serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyNettyServerInitializer()).bind(8888).sync() ;
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
