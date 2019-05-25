package io.mart.configuration.server;

import io.mart.component.server.GrpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {
    @Autowired
    GrpcServerProperties properties;

    @Bean
    GrpcServer grpcServer() {
        return new GrpcServer().start(properties.port)
                .blockUntilShutdown();
    }
}
