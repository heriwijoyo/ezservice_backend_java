/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.service.client;

import id.ezclouds.core.integration.service.client.config.WatzapConfig;
import id.ezclouds.core.integration.service.client.request.WatzapSendRequest;
import id.ezclouds.core.integration.service.client.response.WatzapResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WatzapClientService.java, v 0.1 2024‐02‐05 1:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class WatzapClientService {

    private WebClient webClient = WebClient
            .builder()
            .baseUrl(WatzapConfig.EndPoint.BASE_URL)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Async
    public void sendWatzap(WatzapSendRequest request) {
        request.setApi_key(WatzapConfig.Credential.API_KEY);
        request.setNumber_key(WatzapConfig.Credential.NUMBER_KEY);

        Mono<ResponseEntity<WatzapResponse>> watzapResponse = webClient
                .post()
                .uri(WatzapConfig.EndPoint.SEND_MESSAGE)
                .body(Mono.just(request), WatzapSendRequest.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("is4xxClientError");
                    return Mono.error(new WebClientResponseException("Bad Request", response.statusCode().value(), response.statusCode().getReasonPhrase(), null, null, null));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("is5xxServerError");
                    return Mono.error(new WebClientResponseException("Server Error", response.statusCode().value(), response.statusCode().getReasonPhrase(), null, null, null));
                })
                .toEntity(WatzapResponse.class);

        watzapResponse.subscribe(System.out::println);
    }
}