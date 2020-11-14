package live.footmark.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import live.footmark.grpc.GRPCMessageDataInfo;
import live.footmark.grpc.MessageDataInfoServiceGrpc;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-11-14 15:42
 **/
public class MessageDataInfoClient {

    public static void main (String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8899").usePlaintext().build();
        MessageDataInfoServiceGrpc.MessageDataInfoServiceBlockingStub personClient = MessageDataInfoServiceGrpc.newBlockingStub(channel);
        GRPCMessageDataInfo.RequestBody requestBody = GRPCMessageDataInfo.RequestBody.newBuilder().setId(1).build();
        GRPCMessageDataInfo.Person personInfo = personClient.getPersonInfo(requestBody);
        System.out.println("name:"+personInfo.getName());

    }

}
