package com.maurigvs.wallet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CryptoRepository cryptoRepository;

    public Flux<Crypto> saveAll(Iterable<Crypto> cryptos) {
        return Flux.fromIterable(cryptoRepository.saveAll(cryptos))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Crypto> findAll() {
        return Flux.fromIterable(cryptoRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Page<CryptoDto> findByParams(CryptoQueryDto params, CryptoFilterDto filters) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Crypto> query = cb.createQuery(Crypto.class);
        Root<Crypto> crypto = query.from(Crypto.class);
        List<Predicate> predicateList = new ArrayList<>();

        // if(Objects.nonNull(params.symbol())) predicateList.add(cb.like(crypto.get("id"), params.symbol()));
        // if(Objects.nonNull(params.rank())) predicateList.add(cb.equal(crypto.get("rank"), params.rank()));

        if(filters.isValid()){
            var predicate = crypto.get("id").in(filters.values());
            if(filters.negate())
                predicate = predicate.not();
            predicateList.add(predicate);
        }

        query.select(crypto).where(predicateList.toArray(new Predicate[0]));

        List<Crypto> cryptoList = entityManager.createQuery(query).getResultList();

        return new PageImpl<>(cryptoList.stream().map(CryptoDto::new).toList());
    }
}
