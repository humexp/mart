package io.mart.service;

import io.grpc.stub.StreamObserver;
import io.mart.MartGrpc;
import io.mart.MartRequest;
import io.mart.MartResponse;

public class MartService extends MartGrpc.MartImplBase {
    @Override
    public void check(MartRequest request, StreamObserver<MartResponse> responseObserver) {
        MartResponse response = MartResponse.newBuilder().setMessage("Test response").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
