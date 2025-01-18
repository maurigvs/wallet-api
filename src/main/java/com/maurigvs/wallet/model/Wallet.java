package com.maurigvs.wallet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID walletId;
    private Instant createDate;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private final Set<Asset> assetSet = new HashSet<>();

    public Wallet(List<Asset> assetList) {
        this.walletId = UUID.randomUUID();
        this.createDate = Instant.now();
        this.assetSet.addAll(assetList);
        this.assetSet.forEach(asset -> asset.setWallet(this));
    }
}
