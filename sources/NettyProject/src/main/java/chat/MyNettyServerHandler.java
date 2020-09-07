package chat;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Scanner;
public class MyNettyServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE) ;
    //每当从服务端读取到客户端写入的信息时，就将该信息转发给所有的客户端Channel（实现聊天室的效果）。
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String receiveMsg) throws Exception {
        Channel channel = ctx.channel() ;
        //遍历channelGroup，从而区分“我”和“别人”发出的消息，如果消息是自己发出的就显示“我”
        channelGroup.forEach(chnl ->{//JDK8提供的lambda表达式
        if(channel == chnl)
            chnl.writeAndFlush("【我】发送的消息：" + receiveMsg + "\n") ;
        else
             chnl.writeAndFlush("【" + channel.remoteAddress()+"】发送的消息：" + receiveMsg +"\n");
        }  );
    }

    //连接建立。每当从服务端收到新的客户端连接时，就将新客户端的Channel加入ChannelGroup列表中，并告知列表中的其他客户端Channel
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel() ;
        channelGroup.writeAndFlush("客户端-" + channel.remoteAddress() + "加入\n") ;
        channelGroup.add(channel) ;
    }

    //监听客户端上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel() ;
        System.out.println(channel.remoteAddress() + "上线");
    }
    //监听客户端下线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel() ;
        System.out.println(channel.remoteAddress() + "下线");
    }
    //连接断开。每当从服务端感知有客户端断开时，就将该客户端的Channel从ChannelGroup 列表中移除，并告知列表中的其他客户端Channel
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel() ;
        //会自动将channelGroup中断开的连接移除掉
        channelGroup.writeAndFlush("客户端-" + channel.remoteAddress() + "离开\n") ;
    }
}

