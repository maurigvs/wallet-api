package com.maurigvs.wallet;

import java.util.List;
import java.util.Objects;

public record CryptoFilterDto(
        String field,
        List<String> values,
        boolean negate
) {

    public boolean isValid(){
        return Objects.nonNull(field) && Objects.nonNull(values) && !values.isEmpty();
    }
}
