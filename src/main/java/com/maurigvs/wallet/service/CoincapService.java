package com.maurigvs.wallet.service;

import com.maurigvs.wallet.model.dto.CryptoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CoincapService {

    private final WebClient webClient;

    public Mono<CryptoDto> findById(String id) {
        return webClient.get()
                .uri("/assets/{id}", id)
                .retrieve()
                .bodyToMono(CryptoDto.Single.class)
                .map(CryptoDto.Single::data);
    }

    public Flux<CryptoDto> findAll() {
        return webClient.get()
                .uri("/assets")
                .retrieve()
                .bodyToFlux(CryptoDto.Many.class)
                .flatMapIterable(CryptoDto.Many::data);
    }
}
