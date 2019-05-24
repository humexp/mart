package io.mart.component.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.AbstractStub;
import io.mart.MartGrpc;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MartGrpcClient {
    private static final Logger logger = Logger.getLogger(MartGrpcClient.class.getName());

    private final ManagedChannel channel;
    public final MartGrpc.MartBlockingStub stub;

    public MartGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    MartGrpcClient(ManagedChannel channel) {
        this.channel = channel;
        stub = MartGrpc.newBlockingStub(channel);
        logger.info("GRPC : Start MartGrpcClient");
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
