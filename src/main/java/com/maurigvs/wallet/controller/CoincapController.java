package com.maurigvs.wallet.controller;

import com.maurigvs.wallet.model.dto.CryptoDto;
import com.maurigvs.wallet.service.CoincapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CoincapController {

    private final CoincapService coincapService;

    @GetMapping
    public Flux<CryptoDto> findAll() {
        return coincapService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CryptoDto> findById(@PathVariable String id) {
        return coincapService.findById(id);
    }
}
