package pl.kurs.relations.onetoone.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kurs.relations.onetoone.entity.Person;

@Repository
@Transactional
@RequiredArgsConstructor
public class PersonDao {
    private final EntityManager entityManager;

    public Person get(Long id) {
        return entityManager.find(Person.class, id);
    }

    public void save(Person person) {
        if (person.getPersonDetails() != null && person.getPersonDetails().getId() == null) {
            entityManager.persist(person.getPersonDetails());
        }
        entityManager.persist(person);
    }

    public void update(Person person) {
        if (person.getPersonDetails() != null) {
            entityManager.merge(person.getPersonDetails());
        }
        entityManager.merge(person);
    }

    public void delete(Long id) {
        Person person = get(id);
        if (person != null) {
            entityManager.remove(person);
            entityManager.remove(person.getPersonDetails());
        }
    }

    public void delete(Person person) {
        Person toDelete = entityManager.merge(person);
        entityManager.remove(toDelete);
        entityManager.remove(toDelete.getPersonDetails());
    }

}
