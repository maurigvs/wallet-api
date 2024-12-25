package com.maurigvs.wallet.mapper;

import com.maurigvs.wallet.model.dto.CryptoDto;
import com.maurigvs.wallet.model.entity.Crypto;

public final class DtoMapper {

    private DtoMapper() {}

    public static CryptoDto mapCrypto(Crypto crypto){
        return new CryptoDto(
                crypto.getId(),
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
