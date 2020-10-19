package live.footmark.netty.socket.demo.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @program: netty_learn
 * @description: webSocket服务端
 * @author: wanshubin
 * @create: 2020-10-18 15:15
 **/
public class MyWebSocketServer {
    public static void main (String[] args) throws Exception{
        EventLoopGroup bossEventGroup=new NioEventLoopGroup();

        NioEventLoopGroup workerEventGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventGroup, workerEventGroup).
                    channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new WebSocketChannelInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossEventGroup.shutdownGracefully();
            workerEventGroup.shutdownGracefully();
        }

    }
}
