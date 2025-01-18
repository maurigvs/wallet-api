package com.maurigvs.wallet.controller;

import com.maurigvs.wallet.model.Wallet;
import com.maurigvs.wallet.model.dto.WalletDto;
import com.maurigvs.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UUID> postWallet(@RequestBody WalletDto dto){
        return walletService.save(dto).map(Wallet::getWalletId);
    }
}
