package io.mart.component.mart;

import io.mart.CheckRequest;
import io.mart.CheckResponse;
import io.mart.CheckState;
import org.springframework.stereotype.Component;

@Component
public class MartChecker {
    public CheckResponse check(CheckRequest request) {
        return CheckResponse.newBuilder()
                .setId(request.getId())
                .setState(CheckState.OK)
                .setReport("success")
                .build();
    }
}
