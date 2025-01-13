package com.maurigvs.wallet;

import java.util.List;

public record CryptoCriteriaDto(
        int page,
        int size,
        List<CryptoFilterDto> filters,
        List<CryptoSortDto> sorts
) {
}
