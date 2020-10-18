package live.footmark.netty.socket.demo.hell.socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @program: netty_learn
 * @description: socket请求处理器
 * @author: wanshubin
 * @create: 2020-10-13 21:17
 **/
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("ip: "+ctx.channel().remoteAddress());
        System.out.println("content "+msg);
        ctx.writeAndFlush("from server "+UUID.randomUUID());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.channel().writeAndFlush("hell");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常时调用
        cause.printStackTrace();
        ctx.channel().close();
    }
}
