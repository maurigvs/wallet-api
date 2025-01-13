package com.maurigvs.wallet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoincapService {

    private final WebClient webClient;
    private final CryptoService cryptoService;

    private Random random = new Random();

    public Mono<CoincapDto.Single> findById(String id) {
        return webClient.get()
                .uri("/assets/{id}", id)
                .retrieve()
                .bodyToMono(CoincapDto.Single.class);
    }

    public Mono<CoincapDto.Many> findAll() {
        return webClient.get()
                .uri("/assets")
                .retrieve()
                .bodyToMono(CoincapDto.Many.class);
    }

    @Scheduled(initialDelay = 5, timeUnit = TimeUnit.SECONDS)
    public Flux<Crypto> updateAll() {
        return findAll()
                .map(response -> response.data().stream()
                        .map(dto -> {
                            var id = random.nextLong(1L, 10L);
                            return new Crypto(dto, response.timestamp(), new Category(id));
                        })
                        .toList())
                .flatMapMany(cryptoService::saveAll)
                .doOnComplete(() -> log.info("All cryptos updated"));
    }
}
