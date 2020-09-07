package ws.dot2dot;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/*
 * Created by 颜群
 *
 * netty自带类库解决
 */
public class MyNettyServerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //netty自带的类
        ChannelPipeline pipeline = ch.pipeline();
        //服务端解析器
        pipeline.addLast("HttpServerCodec", new HttpServerCodec()) ;
        //组装工具类：可以将多个请求、响应进行组装的工具类
        pipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(4096)) ;
        //localhost:8888/myWebsocket
        pipeline.addLast("WebSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/myWebsocket"));

        //自定义
        pipeline.addLast("MyNettyServerHandler",new MyNettyServerHandler()) ;


    }
}
