package io.mart.component.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.mart.Application;
import io.mart.configuration.server.GrpcServerProperties;
import io.mart.service.MartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class GrpcServer implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    private Server server;

    @Autowired
    GrpcServerProperties properties;

    @Override
    public void run(String... args) throws Exception {
        start(properties.port).blockUntilShutdown();
    }

    public GrpcServer start(int port) {
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new MartService())
                    .build()
                    .start();
        } catch (IOException e) {
            // TODO: Refactor
            throw new RuntimeException(e);
        }

        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
        return this;
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public GrpcServer blockUntilShutdown() {
        if (server != null) {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                // TODO: Refactor
                throw new RuntimeException(e);
            }
        }
        return this;
    }
}
