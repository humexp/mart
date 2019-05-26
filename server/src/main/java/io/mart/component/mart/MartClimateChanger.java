package io.mart.component.mart;

import io.mart.ClimateActionList;
import io.mart.ClimateActionResponse;
import io.mart.ClimateActionResponseList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static io.mart.ClimateActionState.CA_OK;

@Component
public class MartClimateChanger {
    public ClimateActionResponseList changeClimate(ClimateActionList climateActionList) {
        List<ClimateActionResponse> climateActionResponses = new ArrayList<>();

        climateActionList.getActionList().forEach((action) -> {
            climateActionResponses.add(ClimateActionResponse.newBuilder()
                    .setGroup(action.getGroup())
                    .setState(CA_OK)
                    .build());
        });


        return ClimateActionResponseList.newBuilder()
                .setId(climateActionList.getId())
                .addAllActionResponse(climateActionResponses)
                .build();
    }
}
