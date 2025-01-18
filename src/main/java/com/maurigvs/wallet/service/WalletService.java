package com.maurigvs.wallet.service;

import com.maurigvs.wallet.model.Asset;
import com.maurigvs.wallet.model.Wallet;
import com.maurigvs.wallet.model.dto.WalletDto;
import com.maurigvs.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final CryptoService cryptoService;

    public Mono<Wallet> save(WalletDto dto){
        return Flux.fromStream(dto.assets().stream())
                .flatMap(asset -> cryptoService.findById(asset.symbol())
                        .map(crypto -> new Asset(asset, crypto)))
                .collectList()
                .map(Wallet::new)
                .flatMap(this::save);
    }

    private Mono<Wallet> save(Wallet wallet){
        return Mono.fromSupplier(() -> walletRepository.save(wallet))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
