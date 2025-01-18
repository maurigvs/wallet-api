package com.maurigvs.wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "cryptos_log")
@NoArgsConstructor
@Getter
@Setter
public class CryptoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    private int rank;
    private BigDecimal supply;
    private BigDecimal maxSupply;
    private BigDecimal marketCapUsd;
    private BigDecimal volumeUsd24Hr;
    private BigDecimal priceUsd;
    private BigDecimal changePercent24Hr;
    private BigDecimal vwap24Hr;
    private Instant lastUpdate;

    public CryptoLog(Crypto crypto) {
        this.crypto = crypto;
        this.rank = crypto.getRank();
        this.supply = crypto.getSupply();
        this.maxSupply = crypto.getMaxSupply();
        this.marketCapUsd = crypto.getMarketCapUsd();
        this.volumeUsd24Hr = crypto.getVolumeUsd24Hr();
        this.priceUsd = crypto.getPriceUsd();
        this.changePercent24Hr = crypto.getChangePercent24Hr();
        this.vwap24Hr = crypto.getVwap24Hr();
        this.lastUpdate = crypto.getLastUpdate();
    }

}
