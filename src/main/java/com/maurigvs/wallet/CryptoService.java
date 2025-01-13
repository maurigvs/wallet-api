package com.maurigvs.wallet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
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

    public Flux<CryptoDto> findByParams(List<CryptoFilterDto> filters) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Crypto> query = cb.createQuery(Crypto.class);
        Root<Crypto> crypto = query.from(Crypto.class);
        List<Predicate> predicateList = new ArrayList<>();

        filters.forEach(filter -> {
            if(filter.isValid()){
                var values = filter.values();
                if (filter.field().equals("symbol")) {
                    var predicate = crypto.get("id").in(values);
                    predicate = filter.negate() ? predicate.not() : predicate;
                    predicateList.add(predicate);

                } else if (filter.field().equals("category")) {
                    var predicate = crypto.get("category").get("id").in(values.stream().map(Long::valueOf).toList());
                    predicate = filter.negate() ? predicate.not() : predicate;
                    predicateList.add(predicate);
                }
            }
        });

        query.select(crypto).where(predicateList.toArray(new Predicate[0]));

        return Flux.fromIterable(entityManager.createQuery(query).getResultList())
                .map(CryptoDto::new);
    }
}
