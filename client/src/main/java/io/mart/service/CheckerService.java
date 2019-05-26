package io.mart.service;

import io.mart.component.mart.MartChecker;
import io.mart.component.mart.MartClimateChanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CheckerService {
    @Autowired
    MartChecker martChecker;

    @Autowired
    MartClimateChanger martClimateChanger;

    @Scheduled(cron = "${mart.checker.cron}")
    void check() {
        martChecker.check();
    }

    @Scheduled(cron = "${mart.climate.cron}")
    void changeClimate() {
        martClimateChanger.changeClimate();
    }
}
