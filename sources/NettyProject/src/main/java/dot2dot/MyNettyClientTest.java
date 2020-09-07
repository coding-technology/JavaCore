package dot2dot;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/*
 * Created by 颜群
 * 入口类->初始化器 ->自定义处理器
 */
public class MyNettyClientTest {

    public static void main(String[] args) {

        //创建事件循环组
        EventLoopGroup eventLoopGroup =  new NioEventLoopGroup() ;

        //启动
       Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    //综合（内置+自定义）初始化器
                    .handler(new MyNettyClientInit());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();
            channelFuture.channel().closeFuture().sync() ;//future模式

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            eventLoopGroup.shutdownGracefully();
        }

    }

}
