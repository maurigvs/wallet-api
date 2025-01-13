package com.maurigvs.wallet;

import java.util.List;

public record CryptoCriteriaDto(
        List<CryptoFilterDto> filters,
        List<CryptoSortDto> sorts
) {
}
