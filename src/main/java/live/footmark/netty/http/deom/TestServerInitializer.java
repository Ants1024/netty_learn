package live.footmark.netty.http.deom;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-07-01 15:31
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * @Description: 连接被注册时调用
     * @Author: wanshubin
     * @Date: 2020/7/1 3:34 PM
     * @param ch:
     * @return: void
     **/
    @Override
    protected void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();
        //HttpServerCodec() 处理请求与响应的加解码  是对 HttpRequestDecoder  HttpResponseEncoder的一个结合
        pipeline.addLast("httpServerCodec",new HttpServerCodec());

        //请求处理
        pipeline.addLast("testServerInitializer",new TestHttpServerHandler());

    }
}
