package com.interview.taqplay.pooling.configuration;

import com.interview.taqplay.pooling.service.PoolDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class PoolingSchedulerConfiguration {

    private final PoolDataService poolDataService;

    @Scheduled(cron = "${taqplay.pooling.cron}")
    public void poolData() {
        log.debug("Run scheduler at {}", LocalDateTime.now());
        poolDataService.poolShip();

    }
}
