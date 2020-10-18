package live.footmark.netty.socket.demo.hell.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-13 21:43
 **/
public class SocketClient {
    public static void main (String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new SocketClientInitializer());
            //连接到服务端
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8808).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            //优雅关闭
            eventLoopGroup.shutdownGracefully();
        }
    }

}
