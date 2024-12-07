package com.interview.taqplay.pooling.service;

import com.interview.taqplay.pooling.repository.ShipRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service("poolDataService")
public class PoolDataServiceImpl implements PoolDataService {

    private final ShipRepository shipRepository;

    @Async
    @Override
    public void poolShip() {
        log.debug("trigger ship to get information");
        shipRepository.triggerShipInformation();
    }
}
