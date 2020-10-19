package live.footmark.netty.socket.demo.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-18 15:26
 **/
public class WebSocketChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //处理http请求与响应加解码
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        //聚合http请求与响应数据(处理一份数据拆成多次请求)
        pipeline.addLast(new HttpObjectAggregator(4096));
        //请求路径 ws://localhost:8899/test
        pipeline.addLast(new WebSocketServerProtocolHandler("/test"));
        pipeline.addLast(new TextWebSocketHandler());
    }
}
