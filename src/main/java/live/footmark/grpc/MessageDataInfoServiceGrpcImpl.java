package live.footmark.grpc;

import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-11-14 15:18
 **/
public class MessageDataInfoServiceGrpcImpl extends MessageDataInfoServiceGrpc.MessageDataInfoServiceImplBase {

    static Map<Integer,GRPCMessageDataInfo.Person> personMap= new HashMap<>();
    static {
        GRPCMessageDataInfo.Person person = GRPCMessageDataInfo.Person.newBuilder().setId(1).setEmail("www.-----@qq.com").setName("咔卟哒").build();
        personMap.put(1,person);
    }

    @Override
    public void getPersonInfo(GRPCMessageDataInfo.RequestBody request, StreamObserver<GRPCMessageDataInfo.Person> responseObserver) {
        GRPCMessageDataInfo.Person person = personMap.get(request.getId());
        responseObserver.onNext(person);
        responseObserver.onCompleted();
    }
}
