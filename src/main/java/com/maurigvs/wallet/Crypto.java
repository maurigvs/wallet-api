package com.maurigvs.wallet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "crypto", cascade = CascadeType.ALL)
    private Set<CryptoLog> logSet = new HashSet<>();

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crypto crypto)) return false;
        return rank == crypto.rank && Objects.equals(id, crypto.id) && Objects.equals(supply, crypto.supply) && Objects.equals(maxSupply, crypto.maxSupply) && Objects.equals(marketCapUsd, crypto.marketCapUsd) && Objects.equals(volumeUsd24Hr, crypto.volumeUsd24Hr) && Objects.equals(priceUsd, crypto.priceUsd) && Objects.equals(changePercent24Hr, crypto.changePercent24Hr) && Objects.equals(vwap24Hr, crypto.vwap24Hr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank, supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr);
    }
}
