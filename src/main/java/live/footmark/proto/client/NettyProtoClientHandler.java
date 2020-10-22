package live.footmark.proto.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import live.footmark.proto.AddressBookProto;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 22:21
 **/
public class NettyProtoClientHandler extends SimpleChannelInboundHandler<AddressBookProto.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProto.Person msg) throws Exception {
        System.out.println("来自服务端的信息");
        System.out.println(msg.getId());
        System.out.println(msg.getName());
        System.out.println(msg.getEmail());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("向服务端发送数据");
        AddressBookProto.Person person = AddressBookProto.Person.newBuilder().setId(01).
                setName("三毛").setEmail("www.shanmao@qq.com").build();
        ctx.writeAndFlush(person);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
