package io.mart.component.mart;

import io.mart.CheckRequest;
import io.mart.component.client.MartGrpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class MartChecker {
    @Autowired
    MartGrpcClient martGrpcClient;

    public void check() {
        CheckRequest request = CheckRequest.newBuilder()
                .setId(UUID.randomUUID().toString())
                .build();

        log.info("MART : checker : result={}", martGrpcClient.stub.check(request).toString());
    }
}
