package ws.dot2dot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*
 * Created by 颜群
 * 入口类->初始化器 ->自定义处理器
 */
public class MyNettyServerTest {

    public static void main(String[] args) {

        //创建事件循环组 （master -slaver）
        EventLoopGroup bossGroup =  new NioEventLoopGroup() ;//接收客户端连接，并分发给workerGroup
        EventLoopGroup workerGroup =  new NioEventLoopGroup() ;//真正的处理连接
        //启动
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)

                    //综合（内置+自定义）初始化器
                    .childHandler(new MyNettyServerInit())

                    .bind(8888)
                    .sync();
            channelFuture.channel().closeFuture().sync() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully() ;
        }

    }

}
