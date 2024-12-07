package com.interview.taqplay.pooling.repository;

import com.interview.taqplay.pooling.configuration.TaqplayProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Slf4j
@Repository
public class ShipRepository {

    private final WebClient webClient = WebClient.builder().build();
    private final TaqplayProperties taqplayProperties;

    public void triggerShipInformation() {
        log.debug("URL {}", taqplayProperties.getUrl().getShip());
        WebClient.ResponseSpec retrieve = webClient.get()
                .uri(taqplayProperties.getUrl().getShip())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve();
        retrieve.onStatus(httpStatusCode -> httpStatusCode.equals(HttpStatus.NO_CONTENT), clientResponse -> {
            log.debug("NO_CONTENT {}", clientResponse);
            return null;
        });
        retrieve.onStatus(httpStatusCode -> httpStatusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR), clientResponse -> {
            log.debug("INTERNAL_SERVER_ERROR {}", clientResponse);
            return null;
        });
        retrieve.onStatus(httpStatusCode -> httpStatusCode.equals(HttpStatus.NOT_FOUND), clientResponse -> {
            log.debug("NOT_FOUND {}", clientResponse);
            return null;
        });

    }
}
