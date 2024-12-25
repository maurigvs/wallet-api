package com.maurigvs.wallet.service;

import com.maurigvs.wallet.model.entity.Crypto;
import com.maurigvs.wallet.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public Flux<Crypto> saveAll(Iterable<Crypto> cryptos) {
        return Flux.fromIterable(cryptoRepository.saveAll(cryptos))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Crypto> findAll() {
        return Flux.fromIterable(cryptoRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic());
    }
}
