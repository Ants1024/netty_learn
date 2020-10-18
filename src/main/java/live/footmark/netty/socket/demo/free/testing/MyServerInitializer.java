package live.footmark.netty.socket.demo.free.testing;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-18 13:41
 **/
public class MyServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //闲置状态监听 ,
        // 当3秒没有触发读请求时触发 readerIdleTimeSeconds
        // 当5秒没有触发写请求时触发 writerIdleTimeSeconds
        // 当 10秒没有触发读写请求时触发 allIdleTimeSeconds
        pipeline.addLast(new IdleStateHandler(5,6,3));
        pipeline.addLast(new MyServerHandler());

    }




}
