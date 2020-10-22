package live.footmark.proto.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 22:15
 **/
public class NettyProtoClient {
    public static void main (String[] args) throws Exception{
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup).channel(NioSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).handler(new NettyProtoClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            clientGroup.shutdownGracefully();
        }
    }
}
