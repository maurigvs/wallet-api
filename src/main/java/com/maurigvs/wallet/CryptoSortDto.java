package com.maurigvs.wallet;

import org.springframework.data.domain.Sort;

public record CryptoSortDto(
        String field,
        Sort.Direction direction
) {
}
