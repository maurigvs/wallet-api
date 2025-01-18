package com.maurigvs.wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record WalletDto(
        List<AssetDto> assets
) {

    public record AssetDto(
            String symbol,
            BigDecimal quantity,
            BigDecimal priceUsd
    ) {

        public record Response(
                UUID walletId
        ){
        }
    }
}
