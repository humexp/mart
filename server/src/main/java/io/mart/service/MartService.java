package io.mart.service;

import io.grpc.stub.StreamObserver;
import io.mart.*;
import io.mart.component.mart.MartChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MartService extends MartGrpc.MartImplBase {
    @Autowired
    MartChecker martChecker;

    @Override
    public void check(CheckRequest request, StreamObserver<CheckResponse> responseObserver) {
        responseObserver.onNext(martChecker.check(request));
        responseObserver.onCompleted();
    }
}
