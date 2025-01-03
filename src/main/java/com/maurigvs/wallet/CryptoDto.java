package com.maurigvs.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

public record CryptoDto(
        String symbol,
        int rank,

        @JsonProperty("external_id")
        String externalId,

        String name,
        BigDecimal supply,

        @JsonProperty("max_supply")
        BigDecimal maxSupply,

        @JsonProperty("market_cap_usd")
        BigDecimal marketCapUsd,

        @JsonProperty("volume_usd_24_hr")
        BigDecimal volumeUsd24Hr,

        @JsonProperty("price_usd")
        BigDecimal priceUsd,

        @JsonProperty("change_percent_24_hr")
        BigDecimal changePercent24Hr,

        @JsonProperty("vwap_24_hr")
        BigDecimal vwap24Hr,

        String explorer,

        @JsonProperty("last_update")
        Instant lastUpdate
) {

        public CryptoDto(Crypto crypto){
                this(crypto.getId(),
                        crypto.getRank(),
                        crypto.getExternalId(),
                        crypto.getName(),
                        crypto.getSupply(),
                        crypto.getMaxSupply(),
                        crypto.getMarketCapUsd(),
                        crypto.getVolumeUsd24Hr(),
                        crypto.getPriceUsd(),
                        crypto.getChangePercent24Hr(),
                        crypto.getVwap24Hr(),
                        crypto.getExplorer(),
                        crypto.getLastUpdate());
        }
}
