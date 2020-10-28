package live.footmark.thrift.server;

import live.footmark.thrift.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-10-27 21:35
 **/
public class PersonThriftServer {

    public static void main (String[] args) throws TTransportException {

        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8899);

        THsHaServer.Args arg = new THsHaServer.Args(serverSocket).minWorkerThreads(2).maxWorkerThreads(5);

        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        //-----设置传输协议格式--------
        //TCompactProtocol 压缩格式
        //TBinaryProtocol 二进制格式
        //TJSONProtocol  JSON格式
        //TSimpleJSONProtocol JSON只写协议，生成的文件可以很容易被脚本语言解析
        //TDebugProtocol 使用易懂的可读协议，方便debug
        arg.protocolFactory(new TCompactProtocol.Factory());
        //------传输方式-------
        //TSocket 阻塞式socket
        //TFramedTransport 以frame为单位进行传输，非阻塞式服务模型中使用
        //TFileTransport 以文件形式进行传输
        //TZlibTransport 使用zlib进行压缩，与其它传输方式结合使用
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));
        //------服务模型------
        //TSimpleServer 单线程服务模型，一般用于测试
        //TThreadPoolServer 多线程服务模型，使用标准的阻塞式IO
        //TNonblockingServer 多线程服务模型，非阻塞式IO，（需要与TFramedTransport传输方式搭配使用）
        //THsHaServer THsHa利用线程池进行处理。THsHa包含两种模式，Half-sync模式、Half-async模式。（需要与TFramedTransport传输方式搭配使用）
        //Half-sync模式用于Handle对RPC的同步处理
        //Half-async模式用于处理IO事件（accept/读/写）
        THsHaServer server = new THsHaServer(arg);
        System.out.println("服务启动完成");
        server.serve();
    }
}
