package io.mart.service;

import io.mart.component.mart.MartChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CheckerService {
    @Autowired
    MartChecker martChecker;

    @Scheduled(cron = "${checker.cron}")
    void check() {
        martChecker.check();
    }
}
