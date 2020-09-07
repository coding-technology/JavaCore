package heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Scanner;

/*
 * Created by 颜群
 * 自定义功能：聊天
 */
public class MyNettyServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收客户端 主动发来的消息
    }

    //可以用于处理心跳机制的方法
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //用于心跳机制的 数据类型
        if(evt instanceof IdleStateEvent){
            String eventType = null ;
            IdleStateEvent event = (IdleStateEvent)evt ;
            //获取具体的超时事件
            switch (event.state() ){
                case READER_IDLE:
                    eventType = "读空闲" ;
                    break ;
                case WRITER_IDLE:
                    eventType = "写空闲" ;
                    break ;
                case ALL_IDLE:
                    eventType = "读写空闲" ;
                    break ;
            }
            System.out.println( ctx.channel().remoteAddress() +"超时事件：【"+ eventType+"】");
            ctx.channel().close() ;

        }
    }
}
