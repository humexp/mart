package io.mart.component.checker;

import io.mart.Application;
import io.mart.MartRequest;
import io.mart.component.client.MartGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MartChecker implements Checker {
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    @Autowired
    MartGrpcClient martGrpcClient;

    @Override
    public void check() {
        MartRequest request = MartRequest.newBuilder().setMessage("TestMessage request").build();
        logger.info(martGrpcClient.stub.check(request).getMessage());
    }
}
