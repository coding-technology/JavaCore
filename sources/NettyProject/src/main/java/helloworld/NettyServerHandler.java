package helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/*
 * Created by 颜群
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //doGet  doPost
    //接收并处理请求
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest httpRequest =  (HttpRequest)msg ;
          String requstTypeName =   httpRequest.method().name();

            URI uri = new URI(httpRequest.uri());


            if(!"/favicon.ico".equals( uri.getPath()    )){
                System.out.println("请求方式：" + requstTypeName);

                //定义响应内容
                ByteBuf content = Unpooled.copiedBuffer("welcome netty family...", CharsetUtil.UTF_8);
                //封装响应对象
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());//不是content.readByte()

                ctx.writeAndFlush(response); //return  刷新Servlet  doGet/doPost
                System.out.println();
            }

        }
    }


    //当增加一个处理器时，自动触发该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("1.增加了处理器");
        super.handlerAdded(ctx);
    }

//  选择器  - 通道
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("2.有新的通道被注册");
        super.channelRegistered(ctx);
    }

    //当某个通道被激活时，触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("3.通道被激活...");
        super.channelActive(ctx);
    }

    //当正在被激活的通道 失去激活状态时

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("4.激活状态失效");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("5.通道被取消注册");
        super.channelUnregistered(ctx);
    }

    //当发生异常时
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生了异常。。。");
        super.exceptionCaught(ctx, cause);
    }
}
