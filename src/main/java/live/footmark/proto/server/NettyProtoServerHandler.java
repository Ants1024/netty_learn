package live.footmark.proto.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import live.footmark.proto.MessageDataInfo;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 22:03
 **/
public class NettyProtoServerHandler extends SimpleChannelInboundHandler<MessageDataInfo.DataInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageDataInfo.DataInfo msg) throws Exception {

        switch (msg.getDataType()){
            case cat_type:
                MessageDataInfo.Cat cat = msg.getCat();
                System.out.println("来自客户端关于猫的信息：");
                System.out.println(cat.getId());
                System.out.println(cat.getName());
                System.out.println("-----------");
                MessageDataInfo.DataInfo.Builder blackCat = MessageDataInfo.DataInfo.newBuilder().
                        setCat(MessageDataInfo.Cat.newBuilder().setId(123).setName("黑猫")).
                        setDataType(MessageDataInfo.DataInfo.DataType.cat_type);
                ctx.writeAndFlush(blackCat);
                break;
            case dog_type:
                MessageDataInfo.Dog dog = msg.getDog();
                System.out.println("来自客户端关于狗的信息：");
                System.out.println(dog.getId());
                System.out.println(dog.getName());
                System.out.println("-----------");
                MessageDataInfo.DataInfo.Builder hachik = MessageDataInfo.DataInfo.newBuilder().setDataType(MessageDataInfo.DataInfo.DataType.dog_type).
                        setDog(MessageDataInfo.Dog.newBuilder().setId(555).setName("八公"));
                ctx.writeAndFlush(hachik);
                break;
            case person_type:
                MessageDataInfo.Person person = msg.getPerson();
                System.out.println("来自客户端关于人的信息：");
                System.out.println(person.getId());
                System.out.println(person.getName());
                System.out.println("-----------");
                MessageDataInfo.DataInfo.Builder toolPerson = MessageDataInfo.DataInfo.newBuilder().setDataType(MessageDataInfo.DataInfo.DataType.person_type).
                        setPerson(MessageDataInfo.Person.newBuilder().setId(666).setName("工具人"));
                ctx.writeAndFlush(toolPerson);
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
