package com.maurigvs.wallet.model;

import com.maurigvs.wallet.model.dto.WalletDto;
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

@Entity
@Table(name = "assets")
@NoArgsConstructor
@Getter
@Setter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal quantity;
    private BigDecimal priceUsd;

    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private Crypto crypto;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    public Asset(WalletDto.AssetDto dto, Crypto crypto) {
        this.quantity = dto.quantity();
        this.priceUsd = dto.priceUsd();
        this.crypto = crypto;
    }
}
