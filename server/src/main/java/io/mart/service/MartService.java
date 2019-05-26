package io.mart.service;

import io.grpc.stub.StreamObserver;
import io.mart.*;
import io.mart.component.mart.MartChecker;
import io.mart.component.mart.MartClimateChanger;
import io.mart.component.mart.MartSensorsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MartService extends MartGrpc.MartImplBase {
    @Autowired
    MartChecker martChecker;

    @Autowired
    MartClimateChanger martClimateChanger;

    @Autowired
    MartSensorsComponent martSensorsComponent;

    @Override
    public void check(CheckRequest request, StreamObserver<CheckResponse> responseObserver) {
        responseObserver.onNext(martChecker.check(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getSensorsData(SensorsDataRequest request, StreamObserver<SensorDataList> responseObserver) {
        responseObserver.onNext(martSensorsComponent.getSensorsData(request));
        responseObserver.onCompleted();
    }

    @Override
    public void changeClimate(ClimateActionList request, StreamObserver<ClimateActionResponseList> responseObserver) {
        responseObserver.onNext(martClimateChanger.changeClimate(request));
        responseObserver.onCompleted();
    }
}
