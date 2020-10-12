package live.footmark.netty.http.deom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-07-01 15:37
 **/
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * @Description: 读取客户端数据，并响应数据给客户端
     * @Author: wanshubin
     * @Date: 2020/7/1 3:38 PM
     * @param ctx:
     * @param msg:
     * @return: void
     **/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest request=(HttpRequest)msg;
            //响应内容
            String uri = request.uri();

            //过滤浏览器(谷歌)第一次请求时会自动发起 /favicon.ico 请求
            if("/favicon.ico".equals(uri)){
                System.out.println(uri+"请求");
                return;
            }
            System.out.println("channelRead0被调用,请求方式"+request.method().name());
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //设置请求头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            //返回客户端
            ctx.writeAndFlush(response);
            /*
                主动关闭通道 ，不受keep-alive(持续连接时间) 影响会立即调用 channelInactive 与 channelUnregistered
                HttpVersion.HTTP_1_1 协议与生俱来就支持长链接(完成一次请求后，通道不会被立即关闭，后续如果有请求可以复用该)
             */
            ctx.channel().close();
        }
    }

    /**
     * @Description: 通道被添加
     * @Author: wanshubin
     * @Date: 2020/7/1 5:15 PM
     * @param ctx:
     * @return: void
     **/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Added 通道被添加");
        super.handlerAdded(ctx);
    }

    /**
     * @Description: 通道被注册时调用
     * @Author: wanshubin
     * @Date: 2020/7/1 5:12 PM
     * @param ctx:
     * @return: void
     **/
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registered 通道被注册");
        super.channelRegistered(ctx);
    }

    /**
     * @Description: 通道处于活动状态时调用
     * @Author: wanshubin
     * @Date: 2020/7/1 5:08 PM
     * @param ctx:
     * @return: void
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active,通道处于活动状态");
        super.channelActive(ctx);
    }



    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Inactive,通道处于不活动状态");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Unregistered,通道注销");
        super.channelUnregistered(ctx);
    }
}
