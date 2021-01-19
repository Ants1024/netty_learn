package live.footmark.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @program: netty_learn
 * @description:
 * @author: wanshubin
 * @create: 2020-11-14 15:38
 **/
public class MessageDataInfoServer {
     Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        final MessageDataInfoServer messageDataInfoServer = new MessageDataInfoServer();
        messageDataInfoServer.start();
        messageDataInfoServer.blockUntilShutdown();
    }

    private  void start() throws IOException {
        Integer port = 8899;
        server = ServerBuilder.forPort(port).
                addService(new MessageDataInfoServiceGrpcImpl()).
                build().start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    private  void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
