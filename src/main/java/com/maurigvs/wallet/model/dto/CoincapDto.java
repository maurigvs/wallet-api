package com.maurigvs.wallet.model.dto;

import java.math.BigDecimal;
import java.util.List;

public record CoincapDto(
        String id,
        int rank,
        String symbol,
        String name,
        BigDecimal supply,
        BigDecimal maxSupply,
        BigDecimal marketCapUsd,
        BigDecimal volumeUsd24Hr,
        BigDecimal priceUsd,
        BigDecimal changePercent24Hr,
        BigDecimal vwap24Hr,
        String explorer
) {

    public record Single(
            CoincapDto data,
            long timestamp
    ) {
    }

    public record Many(
            List<CoincapDto> data,
            long timestamp
    ) {
    }
}
