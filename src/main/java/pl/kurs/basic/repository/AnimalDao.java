package pl.kurs.basic.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.basic.entity.Animal;

@Repository
@RequiredArgsConstructor
public class AnimalDao {
    private final EntityManager entityManager;

    @Transactional
    public void save(Animal animal) {
        entityManager.persist(animal);
    }
}
