package helloworld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpServerCodec;

/*
 * Created by 颜群
 */
//综合（内置+自定义）初始化器
public class NettyServerInit extends ChannelInitializer<SocketChannel> {
    //当连接注册到channel时，执行此方法
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //内置初始化器 （编码初始化器：HttpServerCodec）
        pipeline.addLast( "HttpServerCodec",new HttpServerCodec()  )  ;

        //自定义初始化器（NettyServerHandler）
        pipeline.addLast("NettyServerHandler",new NettyServerHandler());
    }


}
