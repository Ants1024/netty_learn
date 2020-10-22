package live.footmark.proto.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import live.footmark.proto.AddressBookProto;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-21 21:59
 **/
public class NettyProtoServerInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //对protobuf协议的的消息头上加上一个长度为32的整形字段，用于标志这个消息的长度
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //将proto协议传输的二进制数据解码成对应的对象，该对象必须是使用protoc编译.proto文件得到的
        pipeline.addLast(new ProtobufDecoder(AddressBookProto.Person.getDefaultInstance()));
        //针对protobuf协议的 ProtobufVarint32LengthFieldPrepender()所加的长度属性的解码器
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new NettyProtoServerHandler());
    }
}
