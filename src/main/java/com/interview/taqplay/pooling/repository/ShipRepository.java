package com.interview.taqplay.pooling.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.taqplay.pooling.configuration.TaqplayProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Slf4j
@Repository
public class ShipRepository {

    private final WebClient webClient = WebClient.builder().build();
    private final TaqplayProperties taqplayProperties;
    private final ObjectMapper objectMapper;

    public void triggerShipInformation() {
        log.debug("URL {}", taqplayProperties.getUrl().getShip());
        WebClient.ResponseSpec retrieve = webClient.get()
                .uri(taqplayProperties.getUrl().getShip())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve();
        retrieve.bodyToMono(ApiResponse.class).subscribe(apiResponse ->
        {
            try {
                log.debug("{}", objectMapper.writeValueAsString(apiResponse.getData()));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        });

    }
}
