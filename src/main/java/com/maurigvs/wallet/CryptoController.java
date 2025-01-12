package com.maurigvs.wallet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    @PostMapping
    public Page<CryptoDto> findAllByParams(CryptoQueryDto params, @RequestBody CryptoFilterDto filters) {
        log.info("Params: {} - Filters: {}", params, filters);
        return cryptoService.findByParams(params, filters);
    }
}
