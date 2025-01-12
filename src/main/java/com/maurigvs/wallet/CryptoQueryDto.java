package com.maurigvs.wallet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CryptoQueryDto(
        int page,
        int size,
        String sort
) {
}
