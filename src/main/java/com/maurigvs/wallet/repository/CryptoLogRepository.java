package com.maurigvs.wallet.repository;

import com.maurigvs.wallet.model.CryptoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoLogRepository extends JpaRepository<CryptoLog, Long> {

}
