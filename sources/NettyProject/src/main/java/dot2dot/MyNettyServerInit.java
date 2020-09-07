package dot2dot;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
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

        //合并、解码

        ChannelPipeline pipeline = ch.pipeline();
        //解码
        pipeline.addLast("LengthFieldBasedFrameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,8,0,8 )) ;
        //合并
        pipeline.addLast("LengthFieldPrepender", new LengthFieldPrepender(8)) ;

        //统一乱码
        pipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast("MyNettyServerHandler",new MyNettyServerHandler()) ;


    }
}
