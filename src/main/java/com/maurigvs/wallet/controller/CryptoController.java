package com.maurigvs.wallet.controller;

import com.maurigvs.wallet.mapper.DtoMapper;
import com.maurigvs.wallet.model.dto.CryptoDto;
import com.maurigvs.wallet.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @GetMapping
    public Flux<CryptoDto> findAll() {
        return cryptoService.findAll()
                .map(DtoMapper::mapCrypto);
    }
}
