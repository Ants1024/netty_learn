package live.footmark.netty.http.deom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: netty_learn
 * @description: 服务端
 * @author: wanshubin
 * @create: 2020-07-01 15:00
 **/
public class TestServer {
    public static void main (String[] args){
        //用于接收请求，不处理请求，会把请求交给workerGroup处理
        EventLoopGroup boosGroup = new NioEventLoopGroup();

        //请求的真实处理者
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于启动netty服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,workerGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());
            //绑定端口
            ChannelFuture channelFuture =  serverBootstrap.bind(8099).sync();
            //服务关闭监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
