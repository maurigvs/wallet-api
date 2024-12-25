package com.maurigvs.wallet.mapper;

import com.maurigvs.wallet.model.dto.CoincapDto;
import com.maurigvs.wallet.model.entity.Crypto;

import java.time.Instant;
import java.util.List;

public final class EntityMapper {

    private EntityMapper() {}

    public static Crypto mapCryptoSingle(CoincapDto.Single dto){
        return mapCoincap(dto.data(), dto.timestamp());
    }

    public static List<Crypto> mapCoincapMany(CoincapDto.Many dto){
        return dto.data().stream()
                .map(c -> mapCoincap(c, dto.timestamp()))
                .toList();
    }

    public static Crypto mapCoincap(CoincapDto dto, long timestamp){
        var crypto = new Crypto();
        crypto.setId(dto.symbol());
        crypto.setExternalId(dto.id());
        crypto.setRank(dto.rank());
        crypto.setName(dto.name());
        crypto.setSupply(dto.supply());
        crypto.setMaxSupply(dto.maxSupply());
        crypto.setMarketCapUsd(dto.marketCapUsd());
        crypto.setVolumeUsd24Hr(dto.volumeUsd24Hr());
        crypto.setPriceUsd(dto.priceUsd());
        crypto.setChangePercent24Hr(dto.changePercent24Hr());
        crypto.setVwap24Hr(dto.vwap24Hr());
        crypto.setExplorer(dto.explorer());
        crypto.setLastUpdate(Instant.ofEpochMilli(timestamp));
        return crypto;
    }
}
