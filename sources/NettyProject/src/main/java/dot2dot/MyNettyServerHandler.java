package dot2dot;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

/*
 * Created by 颜群
 * 自定义功能：聊天
 */
public class MyNettyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //接收消息
        System.out.println("服务端 接收到了"+ctx.channel().remoteAddress()+",消息是："+msg);

        //发送消息
        System.out.println("请输入内容：");
        String send = new Scanner(System.in).nextLine() ;
        ctx.channel().writeAndFlush(send) ;
    }
}
