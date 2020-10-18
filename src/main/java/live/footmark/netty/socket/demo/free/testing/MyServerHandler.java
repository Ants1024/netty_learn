package live.footmark.netty.socket.demo.free.testing;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-18 13:47
 **/

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *用户事件触发回调方法 ctx 上下文对象  evt 事件对象
     **/
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleEvent=(IdleStateEvent) evt;
            String idleType=null;
            switch (idleEvent.state()){
                case READER_IDLE:
                    idleType="读空闲";
                    break;
                case WRITER_IDLE:
                    idleType="写空闲";
                    break;
                case ALL_IDLE:
                    idleType="读写空闲";
                    break;
            }
            System.out.println(idleType);
            ctx.channel().close();
        }
    }
}
