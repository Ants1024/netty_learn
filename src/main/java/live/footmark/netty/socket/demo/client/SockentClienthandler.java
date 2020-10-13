package live.footmark.netty.socket.demo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalTime;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-13 21:52
 **/
public class SockentClienthandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务器ip："+ctx.channel().remoteAddress());
        System.out.println("client output"+msg);
        ctx.writeAndFlush("from client"+LocalTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
