package com.maurigvs.wallet.repository;

import com.maurigvs.wallet.model.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, String> {

}
