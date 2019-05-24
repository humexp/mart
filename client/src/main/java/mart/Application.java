package mart;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.mart.MartGrpc;
import io.mart.MartRequest;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Application {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    private final ManagedChannel channel;
    private final MartGrpc.MartBlockingStub blockingStub;

    public Application(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    Application(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = MartGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        logger.info("Start");
        Application client = new Application("localhost", 8080);
        logger.info("Ready for check");
        try {
            MartRequest request = MartRequest.newBuilder().setMessage("TestMessage request").build();

            IntStream.range(1, 10).forEach((i) -> logger.info(client.blockingStub.check(request).getMessage()));

            logger.info("Finish check");
        } finally {
            client.shutdown();
        }
    }
}
