package com.maurigvs.wallet.service;

import com.maurigvs.wallet.model.Crypto;
import com.maurigvs.wallet.model.CryptoLog;
import com.maurigvs.wallet.repository.CryptoLogRepository;
import com.maurigvs.wallet.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final CryptoLogRepository cryptoLogRepository;

    public Flux<Crypto> saveAll(Iterable<Crypto> cryptos) {
        return Flux.fromIterable(cryptos)
                .flatMap(this::update);
    }

    public Mono<Crypto> findById(String symbol) {
        return Mono.fromSupplier(() -> cryptoRepository.findById(symbol).orElseThrow())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Crypto> findAll() {
        return Flux.fromIterable(cryptoRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Crypto> update(Crypto crypto){
        return findById(crypto.getId())
                .filter(entity -> !crypto.equals(entity))
                .map(CryptoLog::new)
                .map(cryptoLogRepository::save)
                .thenReturn(cryptoRepository.save(crypto))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
