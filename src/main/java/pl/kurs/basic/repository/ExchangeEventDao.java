package pl.kurs.basic.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.basic.entity.ExchangeEvent;

@Repository
@RequiredArgsConstructor
public class ExchangeEventDao {

    private final EntityManager entityManager;

    @Transactional
    public void save(ExchangeEvent exchangeEvent) {
        entityManager.persist(exchangeEvent);
    }
}
