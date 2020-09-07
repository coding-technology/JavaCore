package chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyNettyClientTest {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup =  new NioEventLoopGroup() ;
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyNettyClientInitializer());
            Channel channel =  bootstrap.connect("127.0.0.1",8888).sync().channel();
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(System.in)) ;
           for(;;){//客户端不断的通过控制台向服务端发送消息
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n") ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}