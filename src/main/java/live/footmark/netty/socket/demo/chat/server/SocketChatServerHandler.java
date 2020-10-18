package live.footmark.netty.socket.demo.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: netty_learn
 * @description:  需求：
 * 1.当客户端连接上服务端时，会通知所有已连接服务端的客户该用户已上线
 * 2.将客户端的发送到服务端的数据进行广播
 * @author: wanshubin
 * @create: 2020-10-15 15:27
 **/
public class SocketChatServerHandler extends SimpleChannelInboundHandler<String>{

    /**存储channel对象**/
    static final ChannelGroup channelGroup= new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch){
                ch.writeAndFlush("【"+channel.remoteAddress()+"】："+msg+"\r\n");
            }else{
                ch.writeAndFlush("【自己】："+msg+"\r\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("发送"+channel.remoteAddress()+" 上线广播");
        channelGroup.writeAndFlush("【服务端】"+channel.remoteAddress()+" 已上线\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("发送"+channel.remoteAddress()+" 下线广播");
        //不需要手动remove,当连接断开时会自动清除
        //channelGroup.remove(channel);
        channelGroup.writeAndFlush("【服务端】"+channel.remoteAddress()+" 已下线\n");
    }
}
