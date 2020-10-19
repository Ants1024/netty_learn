package live.footmark.netty.socket.demo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalTime;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-18 15:47
 **/
public class TextWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("from client ："+msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("hell client:"+LocalTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // ctx.channel().id().asLongText() 获取通道唯一标示
        System.out.println("handlerAdd:"+ctx.channel().id().asLongText());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved:"+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
