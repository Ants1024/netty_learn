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

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));
        THsHaServer server = new THsHaServer(arg);
        System.out.println("服务启动完成");
        server.serve();
    }
}
