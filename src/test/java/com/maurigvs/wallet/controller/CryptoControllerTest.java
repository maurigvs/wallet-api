package com.maurigvs.wallet.controller;

import com.maurigvs.wallet.model.dto.CryptoDto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CryptoControllerTest {

    public static final String BASE_ENDPOINT = "/crypto";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void should_get_all_cryptos_successfully() {
        webTestClient.get()
                .uri(BASE_ENDPOINT)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CryptoDto.class)
                .hasSize(10)
                .value(response -> {
                    CryptoDto actual = response.get(0);
                    assertEquals("BTC", actual.symbol());
                    assertEquals(1, actual.rank());
                    assertEquals("Bitcoin", actual.name());
                    assertEquals("bitcoin", actual.externalId());
                });
    }
}