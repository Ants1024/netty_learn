package live.footmark.netty.socket.demo.hell.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-07-02 20:26
 **/
public class SocketServer {

    public static void main (String[] args) throws InterruptedException {
        //请求接收者
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        //请求处理者
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //服务配置
            serverBootstrap.group(boosGroup,workerGroup).
                    channel(NioServerSocketChannel.class).childHandler(new SocketServerInitializer());
            //绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8808).sync();
            //监听服务关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}
