package ws.dot2dot;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Scanner;

/*
 * Created by 颜群
 * 自定义功能：聊天
 */
public class MyNettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //接收消息
        System.out.println("server接收到的客户端消息：" + msg.text());
        //向客户端发出响应
        ctx.channel().writeAndFlush(new TextWebSocketFrame("hello client...")) ;
    }


    //监听是否有客户端链接上了
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接上了："+ctx.channel().id());
    }

    //监听是否有客户端断开了
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端断开了："+ctx.channel().id());
    }
}
