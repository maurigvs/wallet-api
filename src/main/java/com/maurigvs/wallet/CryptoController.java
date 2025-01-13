package com.maurigvs.wallet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @PostMapping
    public Flux<CryptoDto> findAllByParams(@RequestBody CryptoCriteriaDto criteriaDto) {
        log.info("Fetch criteria: {}", criteriaDto);
        return cryptoService.findByParams(criteriaDto);
    }
}
