package io.mart.configuration.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "client")
@Data
public class ClientProperties {
    Mart mart;

    @Data
    static class Mart {
        String host;
        int port;
    }
}
