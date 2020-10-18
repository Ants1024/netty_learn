package live.footmark.netty.socket.demo.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: netty_learn
 * @description: 长连接客户端
 * @author: wanshubin
 * @create: 2020-10-15 16:02
 **/
public class SocketChatClient {

    public static void main (String[] args) throws Exception{
        NioEventLoopGroup clientEventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientEventLoopGroup).
                    channel(NioSocketChannel.class).handler(new SocketChantInitializer());
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            BufferedReader msgBuff = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                channel.writeAndFlush(msgBuff.readLine()+"\r\n");
            }
        }finally {
            clientEventLoopGroup.shutdownGracefully();
        }
    }
}
