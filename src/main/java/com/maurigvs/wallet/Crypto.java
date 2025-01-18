package com.maurigvs.wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "cryptos")
@NoArgsConstructor
@Getter
@Setter
public class Crypto {

    @Id
    private String id;
    private String externalId;
    private int rank;
    private String name;
    private BigDecimal supply;
    private BigDecimal maxSupply;
    private BigDecimal marketCapUsd;
    private BigDecimal volumeUsd24Hr;
    private BigDecimal priceUsd;
    private BigDecimal changePercent24Hr;
    private BigDecimal vwap24Hr;
    private String explorer;
    private Instant lastUpdate;

    public Crypto(CoincapDto dto, long timestamp) {
        this.id = dto.symbol();
        this.externalId = dto.id();
        this.rank = dto.rank();
        this.name = dto.name();
        this.supply = dto.supply();
        this.maxSupply = dto.maxSupply();
        this.marketCapUsd = dto.marketCapUsd();
        this.volumeUsd24Hr = dto.volumeUsd24Hr();
        this.priceUsd = dto.priceUsd();
        this.changePercent24Hr = dto.changePercent24Hr();
        this.vwap24Hr = dto.vwap24Hr();
        this.explorer = dto.explorer();
        this.lastUpdate = Instant.ofEpochMilli(timestamp);
    }
}
