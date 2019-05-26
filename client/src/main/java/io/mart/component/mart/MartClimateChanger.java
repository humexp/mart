package io.mart.component.mart;

import io.mart.*;
import io.mart.component.client.MartGrpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@Component
@Slf4j
public class MartClimateChanger {
    @Autowired
    MartGrpcClient martGrpcClient;

    public void changeClimate() {
        String correlationId = UUID.randomUUID().toString();

        SensorsDataRequest request = SensorsDataRequest.newBuilder()
                .setId(correlationId)
                .addAllGroup(asList("kitchen", "living", "hall"))
                .build();

        SensorDataList sensorDataList = martGrpcClient.stub.getSensorsData(request);

        if (!correlationId.equals(sensorDataList.getId())) {
            throw new IllegalArgumentException("Wrong correlation id");
        }

        List<ClimateAction> climateActions = new ArrayList<>();

        sensorDataList.getDataList().forEach((sensorData -> {
            ClimateAction climateAction = ClimateAction.newBuilder()
                    .setGroup(sensorData.getGroup())
                    .setCommand(sensorData.getReport())
                    .build();

            climateActions.add(climateAction);
        }));

        ClimateActionList climateActionList = ClimateActionList.newBuilder()
                .setId(correlationId)
                .addAllAction(climateActions)
                .build();

        ClimateActionResponseList climateActionResponseList = martGrpcClient.stub.changeClimate(climateActionList);

        log.info("MART : changer start : id={}", climateActionResponseList.getId());

        climateActionResponseList.getActionResponseList().forEach(response -> {
            log.info("MART : changer : group={} state={}", response.getGroup(), response.getState());
        });
    }
}
