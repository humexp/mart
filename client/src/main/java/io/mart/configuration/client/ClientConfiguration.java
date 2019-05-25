package io.mart.configuration.client;

import io.mart.component.client.MartGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {
    @Autowired
    ClientProperties properties;

    @Bean
    MartGrpcClient martGrpcClient() {
        return new MartGrpcClient(properties.mart.host, properties.mart.port);
    }
}
