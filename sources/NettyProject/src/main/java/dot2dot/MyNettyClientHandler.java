package dot2dot;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

/*
 * Created by 颜群
 */
public class MyNettyClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        //接收消息
        System.out.println("客户端 接收到了"+ctx.channel().remoteAddress()+",消息是："+msg);

        //发送消息
        System.out.println("请输入内容：");
        String send = new Scanner(System.in).nextLine() ;
        ctx.channel().writeAndFlush(send) ;
    }

    //为了 避免 客户端和服务端 都在等待对方发送第一条消息，就需要  有人（客户端）主动发送第一条消息


    //当连接成功时，会触发channelActive()方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("开始聊天吧...") ;
    }
}
