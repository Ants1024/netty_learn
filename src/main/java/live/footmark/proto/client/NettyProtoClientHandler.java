package live.footmark.proto.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import live.footmark.proto.MessageDataInfo;

import java.util.Random;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 22:21
 **/
public class NettyProtoClientHandler extends SimpleChannelInboundHandler< MessageDataInfo.DataInfo> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,  MessageDataInfo.DataInfo msg) throws Exception {
        System.out.println("来自服务端的信息");
        switch (msg.getDataType()){
            case cat_type:
                MessageDataInfo.Cat cat = msg.getCat();
                System.out.println("来自服务端关于猫的信息：");
                System.out.println(cat.getId());
                System.out.println(cat.getName());
                System.out.println("-----------");
                break;
            case dog_type:
                MessageDataInfo.Dog dog = msg.getDog();
                System.out.println("来自服务端关于狗的信息：");
                System.out.println(dog.getId());
                System.out.println(dog.getName());
                System.out.println("-----------");

                break;
            case person_type:
                MessageDataInfo.Person person = msg.getPerson();
                System.out.println("来自服务端关于人的信息：");
                System.out.println(person.getId());
                System.out.println(person.getName());
                System.out.println("-----------");
                break;
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int nextInt = new Random().nextInt(2);
        switch (nextInt){
            case 0:
                MessageDataInfo.DataInfo.Builder cat = MessageDataInfo.DataInfo.newBuilder().
                        setDataType(MessageDataInfo.DataInfo.DataType.cat_type).
                        setCat(MessageDataInfo.Cat.newBuilder().setId(1).setName("红猫"));
                ctx.writeAndFlush(cat);
                break;

            case 1:
                MessageDataInfo.DataInfo.Builder dog = MessageDataInfo.DataInfo.newBuilder().
                        setDataType(MessageDataInfo.DataInfo.DataType.dog_type).
                        setDog(MessageDataInfo.Dog.newBuilder().setId(777).setName("啸天犬"));
                ctx.writeAndFlush(dog);
                break;
            case 2:
                MessageDataInfo.DataInfo.Builder person = MessageDataInfo.DataInfo.newBuilder().
                        setDataType(MessageDataInfo.DataInfo.DataType.person_type).
                        setDog(MessageDataInfo.Dog.newBuilder().setId(888).setName("打工人"));
                ctx.writeAndFlush(person);
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
