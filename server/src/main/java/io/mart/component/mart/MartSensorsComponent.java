package io.mart.component.mart;

import io.mart.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MartSensorsComponent {
    public SensorDataList getSensorsData(SensorsDataRequest sensorsDataRequest) {

        List<SensorData> sensorsData = new ArrayList<>();

        sensorsDataRequest.getGroupList().forEach((group)->{
            SensorData sensorData = SensorData.newBuilder()
                    .setGroup(group)
                    .setReport(group + "_report")
                    .build();

            sensorsData.add(sensorData);
        });

        return SensorDataList.newBuilder()
                  .setId(sensorsDataRequest.getId())
                    .addAllData(sensorsData)
                    .build();
    }
}
