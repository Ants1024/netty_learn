package live.footmark.proto.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import live.footmark.proto.AddressBookProto;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 22:03
 **/
public class NettyProtoServerHandler extends SimpleChannelInboundHandler<AddressBookProto.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProto.Person msg) throws Exception {
        System.out.println("来自客户端的信息：");
        System.out.println(msg.getId());
        System.out.println(msg.getName());
        System.out.println(msg.getEmail());
        System.out.println("-----------");
        AddressBookProto.Person person = AddressBookProto.Person.newBuilder().
                setId(02).setName("王五").setEmail("www.wanwu@qq.con").build();
        ctx.writeAndFlush(person);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();;
        ctx.channel().close();
    }
}
